package main

import (
	"context"
	"flag"
	"fmt"
	"net"
	"time"

	"gft.com/prince-bank-grpc-demo/go-grpc/server/internal/common"
	"gft.com/prince-bank-grpc-demo/go-grpc/server/internal/http"
	"gft.com/prince-bank-grpc-demo/go-grpc/server/internal/log"
	"gft.com/prince-bank-grpc-demo/go-grpc/server/internal/services"
	pb "gft.com/prince-bank-grpc-demo/go-grpc/service-def"
	"google.golang.org/grpc"
)

var (
	Port             = flag.Int("port", 50051, "The server port")
	ManagementApiUrl = "http://localhost:8000"
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
		employee := pb.Employee{
			Name:         apiEmployee.Name,
			EmployeeType: apiEmployee.EmployeeType,
		}
		if apiEmployee.Dob != nil {
			dobStringVal := *apiEmployee.Dob
			dobVal, _ := time.Parse("2006-01-02", dobStringVal)
			employee.Dob = common.ToPointer(dobVal.UnixMilli())
		}
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
	employee := pb.Employee{
		Name:         apiEmployee.Name,
		EmployeeType: apiEmployee.EmployeeType,
	}
	if apiEmployee.Dob != nil {
		dobStringVal := *apiEmployee.Dob
		dobVal, _ := time.Parse("2006-01-02", dobStringVal)
		employee.Dob = common.ToPointer(dobVal.UnixMilli())
	}
	log.Info(ctx, "[gRPC] GetEmployeeById -> Leave")
	return &employee, nil
}

func main() {
	flag.Parse()
	lis, err := net.Listen("tcp", fmt.Sprintf(":%d", *Port))
	if err != nil {
		log.Error(context.Background(), "failed to listen: %v", err)
	}
	s := grpc.NewServer()
	managementApiService := services.NewManagementApiService(ManagementApiUrl, http.DefaultHTTPClientWithRetry)
	pb.RegisterManagementServiceServer(s, NewServer(managementApiService))
	log.Info(context.Background(), "server listening at %v", lis.Addr())
	if err := s.Serve(lis); err != nil {
		log.Error(context.Background(), "failed to serve: %v", err)
	}
}
