# How to run

**Run protoc command to generate codes from proto files**
cd to folder proto-shared, then run command to make protoc.sh file is executable
`chmod 755 ./protoc.sh`

**Commands to run Go, Spring Boot services**
cd to folder: grpc-demo/java-rest-api, then run command `mvn spring-boot:run` to start Java Spring Boot Rest api
swagger: http://localhost:8000/swagger-ui/index.html

cd to folder: grpc-demo/go-grpc, then run command `go run server/main.go` to start GO gRPC service

**Testing**
using Postman to create new gRPC request and test
