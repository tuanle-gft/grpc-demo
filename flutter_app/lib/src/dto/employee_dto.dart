import 'package:flutter_app/src/dto/certificate_dto.dart';

class EmployeeDto {
  EmployeeDto(this.id, this.name, this.employeeType, this.dob);

  final int id;
  final String name;
  final String employeeType;
  final int? dob;
  List<CertificateDto> certificates = [];
}
