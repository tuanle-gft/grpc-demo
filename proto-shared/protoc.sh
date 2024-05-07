protoc --go_out=../go-grpc/generated --go_opt=paths=source_relative --go-grpc_out=../go-grpc/generated --go-grpc_opt=paths=source_relative management-service.proto

protoc --dart_out=grpc:../flutter_app/lib/src/generated management-service.proto
