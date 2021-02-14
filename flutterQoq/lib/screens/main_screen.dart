import 'package:flutter/material.dart';

import 'package:flutterQoq/providers/game.dart';
import 'package:flutterQoq/widgets/info_button.dart';
import 'package:flutterQoq/providers/game_map.dart';

class MainScreen extends StatefulWidget {
  MainScreen({Key key}) : super(key: key);

  @override
  _MainScreenState createState() => _MainScreenState();
}

class _MainScreenState extends State<MainScreen> {
  Future<GameMap> futureMap;
  String currentDungeonName;
  int counter;
  int playerHp;
  int playerDmg;
  int monsterDmg;
  int monsterHp;
  int currentPlayerHp;

  void _popupDialogFight(BuildContext context) {
    showDialog(
        context: context,
        builder: (context) {
          return AlertDialog(
            title: Text('Fight'),
            content: Text('You won the fight!'),
            actions: <Widget>[
              FlatButton(
                onPressed: () => Navigator.of(context).pop(),
                child: Text('OK'),
              )
            ],
          );
        });
  }

  void _popupDialogWin(BuildContext context) {
    showDialog(
        context: context,
        builder: (context) {
          return AlertDialog(
            title: Text('Orb of Quarkus'),
            content: Text('You won the fight and acquired the Orb of Quarkus!'),
            actions: <Widget>[
              FlatButton(
                onPressed: () => Navigator.of(context).pop(),
                child: Text('OK'),
              )
            ],
          );
        });
  }

  void _popupDialogCantMove(BuildContext context) {
    showDialog(
        context: context,
        builder: (context) {
          return AlertDialog(
            title: Text('Can not move'),
            content:
                Text('You cant move when there are monsters in the dungeon!'),
            actions: <Widget>[
              FlatButton(
                onPressed: () => Navigator.of(context).pop(),
                child: Text('OK'),
              )
            ],
          );
        });
  }

  void _popupDialogLose(BuildContext context) {
    showDialog(
        context: context,
        builder: (context) {
          return AlertDialog(
            title: Text('You died.'),
            content: Text('Unfortunately you died!'),
            actions: <Widget>[
              FlatButton(
                onPressed: () => Navigator.of(context).pop(),
                child: Text('OK'),
              ),
              FlatButton(
                onPressed: () {
                  setState(() {
                    playerHp = 100;
                    currentPlayerHp = 100;
                  });
                },
                child: Text('Heal up to full HP'),
              )
            ],
          );
        });
  }

  void _popupDialogMove(BuildContext context) {
    showDialog(
        context: context,
        builder: (context) {
          return AlertDialog(
            title: Text('Move'),
            content: Text('You moved!'),
            actions: <Widget>[
              FlatButton(
                onPressed: () => Navigator.of(context).pop(),
                child: Text('OK'),
              )
            ],
          );
        });
  }

  void _popupDialogFlee(BuildContext context) {
    showDialog(
        context: context,
        builder: (context) {
          return AlertDialog(
            title: Text('Flee'),
            content: Text('You fled from the fight!'),
            actions: <Widget>[
              FlatButton(
                onPressed: () => Navigator.of(context).pop(),
                child: Text('OK'),
              )
            ],
          );
        });
  }

  void _popupDialogTake(BuildContext context) {
    showDialog(
        context: context,
        builder: (context) {
          return AlertDialog(
            title: Text('Take'),
            content: Text('You took the item!'),
            actions: <Widget>[
              FlatButton(
                onPressed: () => Navigator.of(context).pop(),
                child: Text('OK'),
              )
            ],
          );
        });
  }

  void _popupDialogCantTake(BuildContext context) {
    showDialog(
        context: context,
        builder: (context) {
          return AlertDialog(
            title: Text('Nothing to take'),
            content: Text('No items in this dungeon!!'),
            actions: <Widget>[
              FlatButton(
                onPressed: () => Navigator.of(context).pop(),
                child: Text('OK'),
              )
            ],
          );
        });
  }

  void _popupDialogCantFight(BuildContext context) {
    showDialog(
        context: context,
        builder: (context) {
          return AlertDialog(
            title: Text('Cant fight'),
            content: Text('The monster is already dead!'),
            actions: <Widget>[
              FlatButton(
                onPressed: () => Navigator.of(context).pop(),
                child: Text('OK'),
              )
            ],
          );
        });
  }

  @override
  void initState() {
    playerDmg = 60;
    futureMap = fetchMap();
    currentDungeonName = '';
    playerHp = 100;
    monsterDmg = 0;
    currentPlayerHp = playerHp;
    monsterHp = 0;
    counter = 1;
    super.initState();
  }

  final game = new Game();
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      bottomNavigationBar: BottomAppBar(
        child: Row(
          children: [
            Text('Player HP: $playerHp'),
            SizedBox(
              width: 10,
            ),
            Text('Monster damage: $monsterDmg'),
            SizedBox(
              width: 10,
            ),
            Text('Player damage: $playerDmg')
          ],
        ),
      ),
      appBar: AppBar(
        title: Text('Quest of Quarkus'),
        backgroundColor: Colors.black,
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Container(
              child: FutureBuilder<GameMap>(
                future: futureMap,
                builder: (context, snapshot) {
                  if (snapshot.hasData) {
                    WidgetsBinding.instance
                        .addPostFrameCallback((_) => setState(() {
                              currentDungeonName = snapshot
                                  .data.currentDungeon['name']
                                  .toString();
                              monsterDmg = snapshot.data
                                      .currentDungeon['monsters'][0]['damage']
                                      .toInt() +
                                  5;
                              monsterHp = snapshot
                                  .data.currentDungeon['monsters'][0]['hp']
                                  .toInt();
                            }));
                    return Text(
                      snapshot.data.currentDungeon['name'].toString(),
                      style: TextStyle(
                          fontSize: 30.0, fontWeight: FontWeight.bold),
                    );
                  } else if (snapshot.hasError) {
                    return Center(child: Text("${snapshot.error}."));
                  }
                  return CircularProgressIndicator();
                },
              ),
            ),
            InkWell(
              onTap: () => {
                print(monsterHp),
                game.fight(),
                setState(() {
                  if (playerHp > 0 || playerHp > monsterDmg) {
                    if (monsterHp < 0) {
                      _popupDialogCantFight(context);
                    } else {
                      playerHp = playerHp - monsterDmg;
                      if (currentDungeonName == 'The final battle') {
                        _popupDialogWin(context);
                      } else {
                        _popupDialogFight(context);
                      }
                    }
                  } else {
                    _popupDialogLose(context);
                  }
                }),
              },
              child: Container(
                color: Colors.red,
                child: Center(
                  child: Text(
                    'Fight',
                  ),
                ),
                margin: const EdgeInsets.all(15.0),
                padding: const EdgeInsets.all(3.0),
                width: 200.0,
                height: 50.0,
              ),
            ),
            InkWell(
              onTap: () => {
                if (monsterHp > 0)
                  {_popupDialogCantMove(context)}
                else
                  {
                    print(monsterHp),
                    game.move(),
                    counter++,
                    _popupDialogFlee(context),
                    setState(() {
                      futureMap = fetchMap();
                      print(currentDungeonName);
                      if (currentDungeonName != 'The final battle') {
                        print('Player fled.');
                      } else {
                        print('Can`t move');
                      }
                    }),
                  }
              },
              child: Container(
                color: Colors.green,
                child: Center(
                  child: Text(
                    'Flee',
                  ),
                ),
                margin: const EdgeInsets.all(15.0),
                padding: const EdgeInsets.all(3.0),
                width: 200.0,
                height: 50.0,
              ),
            ),
            InkWell(
              onTap: () => {
                game.move(),
                counter++,
                _popupDialogMove(context),
                setState(() {
                  futureMap = fetchMap();
                  if (currentDungeonName != 'The final battle') {
                    print('Player moved.');
                  } else {
                    setState(() {});
                    print('Can`t move');
                  }
                }),
              },
              child: Container(
                color: Colors.grey,
                child: Center(
                  child: Text(
                    'Move',
                  ),
                ),
                margin: const EdgeInsets.all(15.0),
                padding: const EdgeInsets.all(3.0),
                width: 200.0,
                height: 50.0,
              ),
            ),
            InkWell(
              onTap: () => {
                game.take(),
                setState(() {
                  if (counter % 2 == 1) {
                    playerHp = playerHp + 50;
                    _popupDialogTake(context);
                  } else {
                    _popupDialogCantTake(context);
                  }
                })
              },
              child: Container(
                color: Colors.black,
                child: Center(
                  child: Text(
                    'Take',
                  ),
                ),
                margin: const EdgeInsets.all(15.0),
                padding: const EdgeInsets.all(3.0),
                width: 200.0,
                height: 50.0,
              ),
            ),
            Center(
              child: InfoButton(),
            )
          ],
        ),
      ),
    );
  }
}
