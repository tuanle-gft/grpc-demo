import 'package:flutter_app/src/dto/certificate_dto.dart';
import 'package:flutter_app/src/dto/employee_dto.dart';
import 'package:flutter_app/src/generated/management-service.pbgrpc.dart';
import 'package:grpc/grpc.dart';

class ManagementDataService {
  String baseUrl = "localhost";

  ManagementDataService._internal();
  static final ManagementDataService _instance =
      ManagementDataService._internal();

  factory ManagementDataService() => _instance;

  static ManagementDataService get instance => _instance;
  late ManagementServiceClient _client;

  Future<void> init() async {
    _createChannel();
  }

  _createChannel() {
    final channel = ClientChannel(baseUrl,
        port: 50051,
        options:
            const ChannelOptions(credentials: ChannelCredentials.insecure()));
    _client = ManagementServiceClient(channel);
  }

  Future<List<EmployeeDto>> getEmployees() async {
    List<EmployeeDto> employees = [];
    await for (var apiEmployee in _client.getEmployees(Void())) {
      employees.add(EmployeeDto(apiEmployee.id, apiEmployee.name,
          apiEmployee.employeeType, apiEmployee.dob.toInt()));
    }
    return employees;
  }

  Future<EmployeeDto> getEmployeeById(int id) async {
    EmployeeDto employee;
    final request = Id()..id = id;
    var apiEmployee = await _client.getEmployeeById(request);
    employee = EmployeeDto(apiEmployee.id, apiEmployee.name,
        apiEmployee.employeeType, apiEmployee.dob.toInt());
    if (apiEmployee.certificates.isNotEmpty) {
      employee.certificates = [];
      for (var apiCertificate in apiEmployee.certificates) {
        var certificate = CertificateDto(
            apiCertificate.id,
            apiCertificate.name,
            apiCertificate.grade,
            apiCertificate.score,
            apiCertificate.expiredDate.toInt());
        employee.certificates.add(certificate);
      }
    }
    return employee;
  }
}
