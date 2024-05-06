package main

import (
	"context"
	"flag"
	"io"
	"log"
	"time"

	pb "gft.com/prince-bank-grpc-demo/go-grpc/service-def"
	"google.golang.org/grpc"
	"google.golang.org/grpc/credentials/insecure"
)

var (
	Addr = flag.String("addr", "localhost:50051", "the address to connect to")
)

func getEmployees(client pb.ManagementServiceClient) {
	log.Println("getEmployees <- Enter")
	ctx, cancel := context.WithTimeout(context.Background(), 10*time.Second)
	defer cancel()
	stream, err := client.GetEmployees(ctx, &pb.Void{})
	if err != nil {
		log.Fatalf("getEmployees - failed: %v", err)
	}
	for {
		employee, err := stream.Recv()
		if err == io.EOF {
			break
		}
		if err != nil {
			log.Fatalf("Println - failed: %v", err)
		}
		log.Printf("Employee name: %s", employee.GetName())
	}
	log.Println("getEmployees -> Leave")
}

func getEmployeeById(client pb.ManagementServiceClient, id int32) {
	log.Printf("getEmployeeById - id: %d", id)
	ctx, cancel := context.WithTimeout(context.Background(), 10*time.Second)
	defer cancel()
	employee, err := client.GetEmployeeById(ctx, &pb.Id{Id: id})
	if err != nil {
		log.Fatalf("getEmployeeById - failed: %v", err)
		return
	}
	if employee == nil {
		log.Fatalf("getEmployeeById - not found employee")
		return
	}
	log.Printf("getEmployeeById - found employeed: %v", employee)
	log.Println("getEmployeeById -> Leave")
}

func main() {
	flag.Parse()
	// Set up a connection to the server.
	conn, err := grpc.Dial(*Addr, grpc.WithTransportCredentials(insecure.NewCredentials()))
	if err != nil {
		log.Fatalf("did not connect: %v", err)
	}
	defer conn.Close()
	c := pb.NewManagementServiceClient(conn)

	getEmployees(c)
	getEmployeeById(c, 1)
}
