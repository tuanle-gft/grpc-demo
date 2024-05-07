import 'package:flutter/material.dart';
import 'package:flutter_app/src/dto/employee_dto.dart';
import 'package:flutter_app/src/services/management_data_service.dart';

import '../settings/settings_view.dart';
import 'sample_item_details_view.dart';

class SampleItemListView extends StatefulWidget {
  static const routeName = '/';

  @override
  _EmployeeListPageState createState() => _EmployeeListPageState();
}

class _EmployeeListPageState extends State<SampleItemListView> {
  List<EmployeeDto> employees = [];

  @override
  void initState() {
    super.initState();
    _fetchEmployees();
  }

  Future<void> _fetchEmployees() async {
    final response = await ManagementDataService.instance.getEmployees();
    setState(() {
      employees = response;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Employees'),
        actions: [
          IconButton(
            icon: const Icon(Icons.settings),
            onPressed: () {
              // Navigate to the settings page. If the user leaves and returns
              // to the app after it has been killed while running in the
              // background, the navigation stack is restored.
              Navigator.restorablePushNamed(context, SettingsView.routeName);
            },
          ),
        ],
      ),

      // To work with lists that may contain a large number of items, it’s best
      // to use the ListView.builder constructor.
      //
      // In contrast to the default ListView constructor, which requires
      // building all Widgets up front, the ListView.builder constructor lazily
      // builds Widgets as they’re scrolled into view.
      body: ListView.builder(
        // Providing a restorationId allows the ListView to restore the
        // scroll position when a user leaves and returns to the app after it
        // has been killed while running in the background.
        restorationId: 'sampleItemListView',
        itemCount: employees.length,
        itemBuilder: (BuildContext context, int index) {
          final item = employees[index];

          return ListTile(
              title: Text(item.name),
              leading: const CircleAvatar(
                // Display the Flutter Logo image asset.
                foregroundImage: AssetImage('assets/images/flutter_logo.png'),
              ),
              onTap: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) => SampleItemDetailsView(employeeId: item.id),
                  ),
                );
              });
        },
      ),
    );
  }
}
