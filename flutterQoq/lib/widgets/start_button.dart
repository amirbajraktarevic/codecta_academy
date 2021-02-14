import 'package:flutter/material.dart';

import 'package:flutterQoq/screens/main_screen.dart';
import 'package:flutterQoq/providers/game.dart';

class StartButton extends StatefulWidget {
  @override
  _StartButtonState createState() => _StartButtonState();
}

class _StartButtonState extends State<StartButton> {
  final game = new Game();
  @override
  Widget build(BuildContext context) {
    return Container(
      child: ElevatedButton(
        onPressed: () => {},
        style: ElevatedButton.styleFrom(
          primary: Colors.black,
          onPrimary: Colors.white,
        ),
        child: IconButton(
          icon: Icon(
            Icons.play_arrow,
          ),
          onPressed: () {
            Navigator.push(
                context, MaterialPageRoute(builder: (context) => MainScreen()));
            game.startGame();
          },
        ),
      ),
    );
  }
}
