import 'package:flutter/material.dart';

//screens
import './screens/start_screen.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Quest of Quarkus',
      theme: ThemeData(
        brightness: Brightness.dark,
      ),
      home: StartScreen(),
    );
  }
}
