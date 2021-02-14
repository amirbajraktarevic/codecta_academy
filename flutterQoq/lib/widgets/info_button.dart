import 'package:flutter/material.dart';

import 'package:flutterQoq/screens/info_screen.dart';

class InfoButton extends StatefulWidget {
  @override
  _InfoButtonState createState() => _InfoButtonState();
}

class _InfoButtonState extends State<InfoButton> {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: FloatingActionButton(
        child: Icon(Icons.info_outline_rounded),
        backgroundColor: Colors.white38,
        onPressed: () => {
          Navigator.push(
              context, MaterialPageRoute(builder: (context) => InfoScreen()))
        },
      ),
    );
  }
}
