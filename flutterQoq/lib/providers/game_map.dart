import 'package:flutter/material.dart';
import 'dart:convert';
import 'package:flutter/foundation.dart';
import 'dart:convert';
import 'dart:async';

import 'package:http/http.dart' as http;

Future<GameMap> fetchMap() async {
  final url = 'http://10.0.2.2:8080/game/map';
  print('fetching map');
  final response = await http.get(url);
  if (response.statusCode == 200) {
    return GameMap.fromJson(jsonDecode(response.body));
  }
}

Future<Player> fetchPlayer() async {
  final url = 'http://10.0.2.2:8080/game/map';
  print('fetching player');
  final response = await http.get(url);
  if (response.statusCode == 200) {
    return Player.fromJson(jsonDecode(response.body));
  }
}

class GameMap with ChangeNotifier {
  final int id;
  final List<dynamic> dungeons;
  final dynamic currentDungeon;
  final dynamic currentPlayer;

  GameMap(
      {@required this.id,
      @required this.dungeons,
      @required this.currentDungeon,
      @required this.currentPlayer});

  factory GameMap.fromJson(Map<String, dynamic> json) {
    return GameMap(
      id: json['id'],
      dungeons: json['dungeons'],
      currentDungeon: json['currentDungeon'],
      currentPlayer: json['currentPlayer'],
    );
  }

  Map<String, dynamic> toJson() => {
        'id': id,
        'dungeons': dungeons,
        'currentDungeon': currentDungeon,
        'currentPlayer': currentPlayer
      };
}

class Dungeon with ChangeNotifier {
  final int id;
  final int mapId;
  final String name;
  final List<Monster> monsters;
  final List<Item> items;

  Dungeon({this.id, this.mapId, this.name, this.monsters, this.items});

  factory Dungeon.fromJson(Map<String, dynamic> json) {
    return Dungeon(
      id: json['id'] as int,
      mapId: json['mapId'] as int,
      name: json['name'] as String,
      monsters: json['monsters'] as List<Monster>,
      items: json['items'] as List<Item>,
    );
  }

  Map<String, dynamic> toJson() => {
        'id': id,
        'mapId': mapId,
        'name': name,
        'monsters': monsters,
        'items': items
      };
}

class Player with ChangeNotifier {
  final int id;
  final String name;
  final int hp;
  final int damage;
  final int healthPots;

  Player({this.id, this.name, this.hp, this.damage, this.healthPots});

  factory Player.fromJson(Map<String, dynamic> json) {
    return Player(
      id: json['id'] as int,
      name: json['name'] as String,
      hp: json['hp'] as int,
      damage: json['damage'] as int,
      healthPots: json['healthPots'] as int,
    );
  }
}

class Monster with ChangeNotifier {
  final int id;
  final int dungeonId;
  final int hp;
  final int damage;

  Monster({this.id, this.dungeonId, this.hp, this.damage});

  factory Monster.fromJson(Map<String, dynamic> json) {
    return Monster(
        id: json['id'] as int,
        dungeonId: json['dungeonId'] as int,
        hp: json['hp'] as int,
        damage: json['damage'] as int);
  }
}

class Item with ChangeNotifier {
  final int id;
  final String name;
  final List<int> dungeonIdsList;

  Item({this.id, this.name, this.dungeonIdsList});

  factory Item.fromJson(Map<String, dynamic> json) {
    return Item(
      id: json['id'] as int,
      name: json['name'] as String,
      dungeonIdsList: json['dungeonIdsList'] as List<int>,
    );
  }
}
