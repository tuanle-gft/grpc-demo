package main

import (
	"context"
	"flag"
	"fmt"
	"net"

	pb "gft.com/prince-bank-grpc-demo/go-grpc/generated"
	"gft.com/prince-bank-grpc-demo/go-grpc/server/internal/common"
	"gft.com/prince-bank-grpc-demo/go-grpc/server/internal/pkg/http"
	"gft.com/prince-bank-grpc-demo/go-grpc/server/internal/pkg/log"
	"gft.com/prince-bank-grpc-demo/go-grpc/server/internal/services"
	"github.com/spf13/viper"
	"google.golang.org/grpc"
)

var (
	Port = flag.Int("port", 50051, "The server port")
)

type server struct {
	pb.UnimplementedManagementServiceServer
	managementApiService services.ManagementApiService
}

func NewServer(managementApiService services.ManagementApiService) *server {
	return &server{
		managementApiService: managementApiService,
	}
}

func (s *server) GetEmployees(request *pb.Void, stream pb.ManagementService_GetEmployeesServer) error {
	ctx := context.Background()
	log.Info(ctx, "[gRPC] GetEmployees <- Enter")
	apiEmployees, err := s.managementApiService.GetEmployees(ctx)
	if err != nil {
		return err
	}
	for _, apiEmployee := range apiEmployees {
		employee := common.ToPbEmployee(apiEmployee)
		if err := stream.Send(&employee); err != nil {
			return err
		}
	}
	log.Info(ctx, "[gRPC] GetEmployees -> Leave")
	return nil
}

func (s *server) GetEmployeeById(ctx context.Context, request *pb.Id) (*pb.Employee, error) {
	log.Info(ctx, "[gRPC] GetEmployeeById <- Enter", "id", request.Id)
	apiEmployee, err := s.managementApiService.GetEmployeeById(ctx, int(request.Id))
	if err != nil {
		return nil, err
	}
	if apiEmployee == nil {
		return nil, nil
	}
	employee := common.ToPbEmployee(*apiEmployee)
	log.Info(ctx, "[gRPC] GetEmployeeById -> Leave")
	return &employee, nil
}

func main() {
	ctx := context.Background()
	flag.Parse()
	lis, err := net.Listen("tcp", fmt.Sprintf(":%d", *Port))
	if err != nil {
		log.Error(ctx, "failed to listen: %v", err)
	}
	managementApiUrl := "http://localhost:8000"
	viper.SetConfigFile("./server/config.yml")
	if err := viper.ReadInConfig(); err != nil {
		log.Warn(ctx, "Error reading config file: ", err)
	} else {
		managementApiUrl = viper.GetString("server.managementApiUrl")
	}
	log.Info(ctx, "managementApiUrl", managementApiUrl)
	s := grpc.NewServer()
	managementApiService := services.NewManagementApiService(managementApiUrl, http.DefaultHTTPClientWithRetry)
	pb.RegisterManagementServiceServer(s, NewServer(managementApiService))
	log.Info(ctx, "server listening at %v", lis.Addr())
	if err := s.Serve(lis); err != nil {
		log.Error(ctx, "failed to serve: %v", err)
	}
}
