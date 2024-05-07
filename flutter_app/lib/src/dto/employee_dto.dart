import 'package:flutter_app/src/dto/certificate_dto.dart';
import 'package:fixnum/fixnum.dart' as $fixnum;

class EmployeeDto {
  EmployeeDto(this.id, this.name, this.employeeType, this.dob);

  final int id;
  final String name;
  final String employeeType;
  final $fixnum.Int64? dob;
  List<CertificateDto> certificates = [];
}
