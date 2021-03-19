package ba.codecta.springlearning.services;

import ba.codecta.springlearning.services.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface QoQService {
    List<MapDto> findAllMaps();
    List<DungeonDto> findAllDungeons();
    List<PlayerDto> findAllPlayers();
    List<MonsterDto> findAllMonsters();
    List<ItemDto> findAllItems();


    MapDto findMapById(Integer id);
    DungeonDto findDungeonById(Integer id);
    PlayerDto findPlayerById(Integer id);
    MonsterDto findMonsterById(Integer id);
    ItemDto findItemById(Integer id);

    MapDto setCurrentDungeon(Integer dungeonId);
    DungeonDto findCurrentDungeon();

    MapDto addMap(MapDto map);
    DungeonDto addDungeon(DungeonDto dungeon);
    PlayerDto addPlayer(PlayerDto player);
    MonsterDto addMonster(MonsterDto monster);
    ItemDto addItem(ItemDto weapon);

    MapDto updateMap(MapDto map);
    DungeonDto updateDungeon(DungeonDto dungeon);
    PlayerDto updatePlayer(PlayerDto player);
    MonsterDto updateMonster(MonsterDto monster);
    ItemDto updateItem(ItemDto item);
}
