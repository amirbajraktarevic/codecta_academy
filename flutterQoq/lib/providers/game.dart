import 'package:flutter/material.dart';

import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;

class Game with ChangeNotifier {
  void startGame() async {
    final url = 'http://10.0.2.2:8080/game/start';
    try {
      final response = await http.post(
        url,
        headers: <String, String>{
          'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
        },
      );
      if (response.statusCode == 200) {
        print(response.statusCode);
        print('Game started.');
      }
    } catch (error) {
      print(error);
    }
    notifyListeners();
  }

  void fight() async {
    final url = 'http://10.0.2.2:8080/game/1/fight';
    print('usao');
    try {
      final response = await http.post(
        url,
        headers: <String, String>{
          'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
        },
      );
      if (response.statusCode == 200) {
        print(response.statusCode);
        print('Fight initiated.');
      }
    } catch (error) {
      print(error);
    }
    notifyListeners();
  }

  void move() async {
    final url = 'http://10.0.2.2:8080/game/1/move';
    print('usao');
    try {
      final response = await http.post(
        url,
        headers: <String, String>{
          'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
        },
      );
      if (response.statusCode == 200) {
        print(response.statusCode);
        print('Player moved.');
      }
    } catch (error) {
      print(error);
    }
    notifyListeners();
  }

  void take() async {
    final url = 'http://10.0.2.2:8080/game/1/take';
    print('usao');
    try {
      final response = await http.post(
        url,
        headers: <String, String>{
          'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
        },
      );
      if (response.statusCode == 200) {
        print(response.statusCode);
        print('Player took item.');
      }
    } catch (error) {
      print(error);
    }
    notifyListeners();
  }
}
