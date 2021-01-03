package services;

import services.model.*;

import java.util.List;

public interface QoQService {

    //find all
    List<MapDto> findAllMaps();
    List<DungeonDto> findAllDungeons();
    List<PlayerDto> findAllPlayers();
    List<MonsterDto> findAllMonsters();
    List<ItemDto> findAllItems();

     //find by id
     MapDto findMapById(Integer id);
     DungeonDto findDungeonById(Integer id);
     PlayerDto findPlayerById(Integer id);
     MonsterDto findMonsterById(Integer id);
     ItemDto findItemById(Integer id);

     //find/set current
     MapDto setCurrentDungeon(Integer dungeonId);
     DungeonDto findCurrentDungeon();



    //add
    MapDto addMap(MapDto map);
    DungeonDto addDungeon(DungeonDto dungeon);
    PlayerDto addPlayer(PlayerDto player);
    MonsterDto addMonster(MonsterDto monster);
    ItemDto addItem(ItemDto weapon);

    //update
    MapDto updateMap(MapDto map);
    DungeonDto updateDungeon(DungeonDto dungeon);
    PlayerDto updatePlayer(PlayerDto player);
    MonsterDto updateMonster(MonsterDto monster);
    ItemDto updateItem(ItemDto item);







}
