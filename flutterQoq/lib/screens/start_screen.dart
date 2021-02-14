import 'package:flutter/material.dart';

import 'package:flutterQoq/widgets/start_button.dart';
import 'package:flutterQoq/providers/game.dart';

class StartScreen extends StatelessWidget {
  final game = new Game();
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        decoration: BoxDecoration(
            image: DecorationImage(
          image: AssetImage("images/dungeon.jpg"),
          fit: BoxFit.cover,
        )),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: [
            Center(
              child: Text(
                "Quest of Quarkus",
                style: TextStyle(
                  fontFamily: 'Pacifico',
                  fontSize: 40.0,
                  backgroundColor: Colors.black54,
                ),
                textAlign: TextAlign.center,
              ),
            ),
            Center(
              child: Text(
                'Press play to begin!',
                style: TextStyle(
                  fontSize: 25.0,
                  fontFamily: 'Pacifico',
                ),
              ),
            ),
            Center(
              child: StartButton(),
            )
          ],
        ),
      ),
    );
  }
}
