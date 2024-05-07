//
//  Generated code. Do not modify.
//  source: management-service.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:async' as $async;
import 'dart:core' as $core;

import 'package:grpc/service_api.dart' as $grpc;
import 'package:protobuf/protobuf.dart' as $pb;

import 'management-service.pb.dart' as $0;

export 'management-service.pb.dart';

@$pb.GrpcServiceName('managementservice.ManagementService')
class ManagementServiceClient extends $grpc.Client {
  static final _$getEmployees = $grpc.ClientMethod<$0.Void, $0.Employee>(
      '/managementservice.ManagementService/GetEmployees',
      ($0.Void value) => value.writeToBuffer(),
      ($core.List<$core.int> value) => $0.Employee.fromBuffer(value));
  static final _$getEmployeeById = $grpc.ClientMethod<$0.Id, $0.Employee>(
      '/managementservice.ManagementService/GetEmployeeById',
      ($0.Id value) => value.writeToBuffer(),
      ($core.List<$core.int> value) => $0.Employee.fromBuffer(value));

  ManagementServiceClient($grpc.ClientChannel channel,
      {$grpc.CallOptions? options,
      $core.Iterable<$grpc.ClientInterceptor>? interceptors})
      : super(channel, options: options,
        interceptors: interceptors);

  $grpc.ResponseStream<$0.Employee> getEmployees($0.Void request, {$grpc.CallOptions? options}) {
    return $createStreamingCall(_$getEmployees, $async.Stream.fromIterable([request]), options: options);
  }

  $grpc.ResponseFuture<$0.Employee> getEmployeeById($0.Id request, {$grpc.CallOptions? options}) {
    return $createUnaryCall(_$getEmployeeById, request, options: options);
  }
}

@$pb.GrpcServiceName('managementservice.ManagementService')
abstract class ManagementServiceBase extends $grpc.Service {
  $core.String get $name => 'managementservice.ManagementService';

  ManagementServiceBase() {
    $addMethod($grpc.ServiceMethod<$0.Void, $0.Employee>(
        'GetEmployees',
        getEmployees_Pre,
        false,
        true,
        ($core.List<$core.int> value) => $0.Void.fromBuffer(value),
        ($0.Employee value) => value.writeToBuffer()));
    $addMethod($grpc.ServiceMethod<$0.Id, $0.Employee>(
        'GetEmployeeById',
        getEmployeeById_Pre,
        false,
        false,
        ($core.List<$core.int> value) => $0.Id.fromBuffer(value),
        ($0.Employee value) => value.writeToBuffer()));
  }

  $async.Stream<$0.Employee> getEmployees_Pre($grpc.ServiceCall call, $async.Future<$0.Void> request) async* {
    yield* getEmployees(call, await request);
  }

  $async.Future<$0.Employee> getEmployeeById_Pre($grpc.ServiceCall call, $async.Future<$0.Id> request) async {
    return getEmployeeById(call, await request);
  }

  $async.Stream<$0.Employee> getEmployees($grpc.ServiceCall call, $0.Void request);
  $async.Future<$0.Employee> getEmployeeById($grpc.ServiceCall call, $0.Id request);
}
