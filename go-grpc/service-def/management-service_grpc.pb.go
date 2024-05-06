// Code generated by protoc-gen-go-grpc. DO NOT EDIT.
// versions:
// - protoc-gen-go-grpc v1.2.0
// - protoc             v5.26.1
// source: service-def/management-service.proto

package go_grpc

import (
	context "context"
	grpc "google.golang.org/grpc"
	codes "google.golang.org/grpc/codes"
	status "google.golang.org/grpc/status"
)

// This is a compile-time assertion to ensure that this generated file
// is compatible with the grpc package it is being compiled against.
// Requires gRPC-Go v1.32.0 or later.
const _ = grpc.SupportPackageIsVersion7

// ManagementServiceClient is the client API for ManagementService service.
//
// For semantics around ctx use and closing/ending streaming RPCs, please refer to https://pkg.go.dev/google.golang.org/grpc/?tab=doc#ClientConn.NewStream.
type ManagementServiceClient interface {
	GetEmployees(ctx context.Context, in *Void, opts ...grpc.CallOption) (ManagementService_GetEmployeesClient, error)
	GetEmployeeById(ctx context.Context, in *Id, opts ...grpc.CallOption) (*Employee, error)
}

type managementServiceClient struct {
	cc grpc.ClientConnInterface
}

func NewManagementServiceClient(cc grpc.ClientConnInterface) ManagementServiceClient {
	return &managementServiceClient{cc}
}

func (c *managementServiceClient) GetEmployees(ctx context.Context, in *Void, opts ...grpc.CallOption) (ManagementService_GetEmployeesClient, error) {
	stream, err := c.cc.NewStream(ctx, &ManagementService_ServiceDesc.Streams[0], "/managementservice.ManagementService/GetEmployees", opts...)
	if err != nil {
		return nil, err
	}
	x := &managementServiceGetEmployeesClient{stream}
	if err := x.ClientStream.SendMsg(in); err != nil {
		return nil, err
	}
	if err := x.ClientStream.CloseSend(); err != nil {
		return nil, err
	}
	return x, nil
}

type ManagementService_GetEmployeesClient interface {
	Recv() (*Employee, error)
	grpc.ClientStream
}

type managementServiceGetEmployeesClient struct {
	grpc.ClientStream
}

func (x *managementServiceGetEmployeesClient) Recv() (*Employee, error) {
	m := new(Employee)
	if err := x.ClientStream.RecvMsg(m); err != nil {
		return nil, err
	}
	return m, nil
}

func (c *managementServiceClient) GetEmployeeById(ctx context.Context, in *Id, opts ...grpc.CallOption) (*Employee, error) {
	out := new(Employee)
	err := c.cc.Invoke(ctx, "/managementservice.ManagementService/GetEmployeeById", in, out, opts...)
	if err != nil {
		return nil, err
	}
	return out, nil
}

// ManagementServiceServer is the server API for ManagementService service.
// All implementations must embed UnimplementedManagementServiceServer
// for forward compatibility
type ManagementServiceServer interface {
	GetEmployees(*Void, ManagementService_GetEmployeesServer) error
	GetEmployeeById(context.Context, *Id) (*Employee, error)
	mustEmbedUnimplementedManagementServiceServer()
}

// UnimplementedManagementServiceServer must be embedded to have forward compatible implementations.
type UnimplementedManagementServiceServer struct {
}

func (UnimplementedManagementServiceServer) GetEmployees(*Void, ManagementService_GetEmployeesServer) error {
	return status.Errorf(codes.Unimplemented, "method GetEmployees not implemented")
}
func (UnimplementedManagementServiceServer) GetEmployeeById(context.Context, *Id) (*Employee, error) {
	return nil, status.Errorf(codes.Unimplemented, "method GetEmployeeById not implemented")
}
func (UnimplementedManagementServiceServer) mustEmbedUnimplementedManagementServiceServer() {}

// UnsafeManagementServiceServer may be embedded to opt out of forward compatibility for this service.
// Use of this interface is not recommended, as added methods to ManagementServiceServer will
// result in compilation errors.
type UnsafeManagementServiceServer interface {
	mustEmbedUnimplementedManagementServiceServer()
}

func RegisterManagementServiceServer(s grpc.ServiceRegistrar, srv ManagementServiceServer) {
	s.RegisterService(&ManagementService_ServiceDesc, srv)
}

func _ManagementService_GetEmployees_Handler(srv interface{}, stream grpc.ServerStream) error {
	m := new(Void)
	if err := stream.RecvMsg(m); err != nil {
		return err
	}
	return srv.(ManagementServiceServer).GetEmployees(m, &managementServiceGetEmployeesServer{stream})
}

type ManagementService_GetEmployeesServer interface {
	Send(*Employee) error
	grpc.ServerStream
}

type managementServiceGetEmployeesServer struct {
	grpc.ServerStream
}

func (x *managementServiceGetEmployeesServer) Send(m *Employee) error {
	return x.ServerStream.SendMsg(m)
}

func _ManagementService_GetEmployeeById_Handler(srv interface{}, ctx context.Context, dec func(interface{}) error, interceptor grpc.UnaryServerInterceptor) (interface{}, error) {
	in := new(Id)
	if err := dec(in); err != nil {
		return nil, err
	}
	if interceptor == nil {
		return srv.(ManagementServiceServer).GetEmployeeById(ctx, in)
	}
	info := &grpc.UnaryServerInfo{
		Server:     srv,
		FullMethod: "/managementservice.ManagementService/GetEmployeeById",
	}
	handler := func(ctx context.Context, req interface{}) (interface{}, error) {
		return srv.(ManagementServiceServer).GetEmployeeById(ctx, req.(*Id))
	}
	return interceptor(ctx, in, info, handler)
}

// ManagementService_ServiceDesc is the grpc.ServiceDesc for ManagementService service.
// It's only intended for direct use with grpc.RegisterService,
// and not to be introspected or modified (even as a copy)
var ManagementService_ServiceDesc = grpc.ServiceDesc{
	ServiceName: "managementservice.ManagementService",
	HandlerType: (*ManagementServiceServer)(nil),
	Methods: []grpc.MethodDesc{
		{
			MethodName: "GetEmployeeById",
			Handler:    _ManagementService_GetEmployeeById_Handler,
		},
	},
	Streams: []grpc.StreamDesc{
		{
			StreamName:    "GetEmployees",
			Handler:       _ManagementService_GetEmployees_Handler,
			ServerStreams: true,
		},
	},
	Metadata: "service-def/management-service.proto",
}
