//
//  Generated code. Do not modify.
//  source: management-service.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use voidDescriptor instead')
const Void$json = {
  '1': 'Void',
};

/// Descriptor for `Void`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List voidDescriptor = $convert.base64Decode(
    'CgRWb2lk');

@$core.Deprecated('Use idDescriptor instead')
const Id$json = {
  '1': 'Id',
  '2': [
    {'1': 'id', '3': 1, '4': 1, '5': 5, '10': 'id'},
  ],
};

/// Descriptor for `Id`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List idDescriptor = $convert.base64Decode(
    'CgJJZBIOCgJpZBgBIAEoBVICaWQ=');

@$core.Deprecated('Use employeeDescriptor instead')
const Employee$json = {
  '1': 'Employee',
  '2': [
    {'1': 'id', '3': 1, '4': 1, '5': 5, '10': 'id'},
    {'1': 'name', '3': 2, '4': 1, '5': 9, '10': 'name'},
    {'1': 'employeeType', '3': 3, '4': 1, '5': 9, '10': 'employeeType'},
    {'1': 'dob', '3': 4, '4': 1, '5': 3, '9': 0, '10': 'dob', '17': true},
    {'1': 'certificates', '3': 5, '4': 3, '5': 11, '6': '.managementservice.Certificate', '10': 'certificates'},
  ],
  '8': [
    {'1': '_dob'},
  ],
};

/// Descriptor for `Employee`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List employeeDescriptor = $convert.base64Decode(
    'CghFbXBsb3llZRIOCgJpZBgBIAEoBVICaWQSEgoEbmFtZRgCIAEoCVIEbmFtZRIiCgxlbXBsb3'
    'llZVR5cGUYAyABKAlSDGVtcGxveWVlVHlwZRIVCgNkb2IYBCABKANIAFIDZG9iiAEBEkIKDGNl'
    'cnRpZmljYXRlcxgFIAMoCzIeLm1hbmFnZW1lbnRzZXJ2aWNlLkNlcnRpZmljYXRlUgxjZXJ0aW'
    'ZpY2F0ZXNCBgoEX2RvYg==');

@$core.Deprecated('Use certificateDescriptor instead')
const Certificate$json = {
  '1': 'Certificate',
  '2': [
    {'1': 'id', '3': 1, '4': 1, '5': 5, '10': 'id'},
    {'1': 'name', '3': 2, '4': 1, '5': 9, '10': 'name'},
    {'1': 'grade', '3': 3, '4': 1, '5': 9, '10': 'grade'},
    {'1': 'score', '3': 4, '4': 1, '5': 2, '9': 0, '10': 'score', '17': true},
    {'1': 'expiredDate', '3': 5, '4': 1, '5': 3, '10': 'expiredDate'},
  ],
  '8': [
    {'1': '_score'},
  ],
};

/// Descriptor for `Certificate`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List certificateDescriptor = $convert.base64Decode(
    'CgtDZXJ0aWZpY2F0ZRIOCgJpZBgBIAEoBVICaWQSEgoEbmFtZRgCIAEoCVIEbmFtZRIUCgVncm'
    'FkZRgDIAEoCVIFZ3JhZGUSGQoFc2NvcmUYBCABKAJIAFIFc2NvcmWIAQESIAoLZXhwaXJlZERh'
    'dGUYBSABKANSC2V4cGlyZWREYXRlQggKBl9zY29yZQ==');

