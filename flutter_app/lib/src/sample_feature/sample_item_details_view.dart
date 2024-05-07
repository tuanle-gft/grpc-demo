import 'package:flutter/material.dart';
import 'package:flutter_app/src/dto/employee_dto.dart';
import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:flutter_app/src/services/management_data_service.dart';
import 'package:intl/intl.dart';

class SampleItemDetailsView extends StatefulWidget {
  static const routeName = '/employee_item';
  final int? employeeId;

  const SampleItemDetailsView({super.key, this.employeeId});

  @override
  _EmployeeDetailPageState createState() => _EmployeeDetailPageState();
}

class _EmployeeDetailPageState extends State<SampleItemDetailsView> {
  late EmployeeDto? employee = null;

  @override
  void initState() {
    super.initState();
    _fetchEmployeeById();
  }

  Future<void> _fetchEmployeeById() async {
    if (widget.employeeId == null) return;
    final response = await ManagementDataService.instance
        .getEmployeeById(widget.employeeId!);
    setState(() {
      employee = response;
    });
  }

  String _convertTimestampToString(int? timestamp, String format) {
    if (timestamp == null) return "";
    DateTime dateTime = DateTime.fromMillisecondsSinceEpoch(timestamp.toInt());
    return DateFormat(format).format(dateTime);
  }

  @override
  Widget build(BuildContext context) {
    if (employee == null) {
      return Scaffold(
          appBar: AppBar(
        title: Text(""),
      ));
    }
    return Scaffold(
      appBar: AppBar(
        title: Text(employee!.name),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text('id: ${employee!.id}'),
            Text('name: ${employee!.name}'),
            Text('employee type: ${employee!.employeeType}'),
            Text(
                'dob: ${_convertTimestampToString(employee!.dob, "yyyy-MM-dd")}'),
            SizedBox(height: 16),
            Text(
              'Certificates:',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            SizedBox(height: 8),
            Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: employee!.certificates.map((certificate) {
                return Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text('  certificate name: ${certificate.name}'),
                    Text('    score: ${certificate.score}'),
                    Text('    grade: ${certificate.grade}'),
                    SizedBox(height: 8),
                  ],
                );
              }).toList(),
            ),
          ],
        ),
      ),
    );
  }
}
