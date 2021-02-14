import 'package:flutter/material.dart';

import 'package:flutterQoq/providers/game_map.dart';

class InfoScreen extends StatefulWidget {
  InfoScreen({Key key}) : super(key: key);
  @override
  _InfoScreenState createState() => _InfoScreenState();
}

class _InfoScreenState extends State<InfoScreen> {
  Future<GameMap> futureMap;
  Future<Player> futurePlayer;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    futureMap = fetchMap();
    futurePlayer = fetchPlayer();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Info screen'),
      ),
      body: FutureBuilder<GameMap>(
        future: futureMap,
        builder: (context, snapshot) {
          if (snapshot.hasData) {
            return Column(
              children: [
                Center(
                    child: Column(
                  children: [
                    Text(''),
                    Text(
                      'Player Information:',
                      style: TextStyle(
                        fontSize: 30.0,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                    Text(''),
                    Text(
                      'Player name: ${snapshot.data.currentPlayer['name'].toString()}',
                    ),
                    Text(
                        'Player damage: ${snapshot.data.currentPlayer['damage'].toString()}'),
                    Text(
                        'Player HP: ${snapshot.data.currentPlayer['hp'].toString()}'),
                    Text(
                        'Player health pots: ${snapshot.data.currentPlayer['healthPots'].toString()}'),
                    Text(''),
                    Text(''),
                  ],
                )),
                Center(
                    child: Column(
                  children: [
                    Text(
                      'Dungeon information: ',
                      style: TextStyle(
                        fontSize: 30.0,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                    Text(''),
                    Text(
                      'Next dungeon: ${snapshot.data.currentDungeon['id'].toString()}',
                    ),
                    Text(
                        'Dungeon name: ${snapshot.data.currentDungeon['name'].toString()}'),
                  ],
                ))
              ],
            );
          } else if (snapshot.hasError) {
            return Center(child: Text("${snapshot.error}."));
          }

          return CircularProgressIndicator();
        },
      ),
    );
  }
}
