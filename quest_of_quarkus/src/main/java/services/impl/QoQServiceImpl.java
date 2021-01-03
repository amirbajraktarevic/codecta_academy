package services.impl;

import repository.*;
import repository.entity.*;
import services.QoQService;
import services.model.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

@ApplicationScoped
@Transactional
public class QoQServiceImpl implements QoQService {



    @Inject
    MapRepository mapRepository;

    @Inject
    DungeonRepository dungeonRepository;

    @Inject
    PlayerRepository playerRepository;

    @Inject
    ItemRepository itemRepository;

    @Inject
    MonsterRepository monsterRepository;


    //find all
    @Override
    public List<MapDto> findAllMaps() {
       List<MapEntity> mapEntityList = mapRepository.findAll();
       if(mapEntityList == null || mapEntityList.isEmpty()){
           return null;
       }
       List<MapDto> mapDtoList = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for(MapEntity map : mapEntityList){
            mapDtoList.add(modelMapper.map(map, MapDto.class));
        }
        return mapDtoList;
    }

    @Override
    public List<DungeonDto> findAllDungeons() {
        List<DungeonEntity> dungeonEntityList = dungeonRepository.findAll();
        if(dungeonEntityList == null || dungeonEntityList.isEmpty()){
            return null;
        }
        List<DungeonDto> dungeonDtoList = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for(DungeonEntity dungeon : dungeonEntityList){
            dungeonDtoList.add(modelMapper.map(dungeon, DungeonDto.class));
        }
        return dungeonDtoList;
    }

    @Override
    public List<PlayerDto> findAllPlayers() {
        List<PlayerEntity> playerEntityList = playerRepository.findAll();
        if(playerEntityList == null || playerEntityList.isEmpty()){
            return null;
        }
        List<PlayerDto> playerDtoList = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for(PlayerEntity player : playerEntityList){
            playerDtoList.add(modelMapper.map(player, PlayerDto.class));
        }
        return playerDtoList;
    }

    @Override
    public List<MonsterDto> findAllMonsters() {
        List<MonsterEntity> monsterEntityList = monsterRepository.findAll();
        if(monsterEntityList == null || monsterEntityList.isEmpty()){
            return null;
        }
        List<MonsterDto> monsterDtoList = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for(MonsterEntity monster : monsterEntityList){
            monsterDtoList.add(modelMapper.map(monster, MonsterDto.class));
        }
        return monsterDtoList;
    }

    @Override
    public List<ItemDto> findAllItems() {
        List<ItemEntity> itemEntityList = itemRepository.findAll();
        if(itemEntityList == null || itemEntityList.isEmpty()){
            return null;
        }
        List<ItemDto> itemDtoList = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for(ItemEntity item : itemEntityList){
            itemDtoList.add(modelMapper.map(item, ItemDto.class));
        }
        return itemDtoList;
    }

    //find by ID
    @Override
    public MapDto findMapById(Integer id) {
        MapEntity mapEntity = mapRepository.findById(id);
        if (mapEntity == null){
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(mapEntity, MapDto.class);
    }


    @Override
    public DungeonDto findDungeonById(Integer id) {
        DungeonEntity dungeonEntity = dungeonRepository.findById(id);
        if (dungeonEntity == null){
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dungeonEntity, DungeonDto.class);
    }

    @Override
    public PlayerDto findPlayerById(Integer id) {
        PlayerEntity playerEntity = playerRepository.findById(id);
        if (playerEntity == null){
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(playerEntity, PlayerDto.class);
    }

    @Override
    public MonsterDto findMonsterById(Integer id) {
        MonsterEntity monsterEntity = monsterRepository.findById(id);
        if (monsterEntity == null){
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(monsterEntity, MonsterDto.class);
    }


    @Override
    public ItemDto findItemById(Integer id) {
        ItemEntity itemEntity = itemRepository.findById(id);
        if (itemEntity == null){
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(itemEntity, ItemDto.class);
    }

    //add
    @Override
    public MapDto addMap(MapDto map) {
        ModelMapper modelMapper = new ModelMapper();
        MapEntity mapEntity = modelMapper.map(map, MapEntity.class);
        mapEntity = mapRepository.add(mapEntity);
        PlayerEntity playerEntity = playerRepository.findById(1);
        mapEntity.setCurrentPlayer(playerEntity);
        return modelMapper.map(mapEntity, MapDto.class);
    }



    @Override
    public DungeonDto addDungeon(DungeonDto dungeon) {
        if(dungeon.getMapId() == null){
            return null;
        }
        MapEntity mapEntity = mapRepository.findById(dungeon.getMapId());
        if (mapEntity == null){
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        DungeonEntity dungeonEntity = modelMapper.map(dungeon, DungeonEntity.class);
        dungeonEntity.setMap(mapEntity);
        dungeonEntity = dungeonRepository.add(dungeonEntity);
        return modelMapper.map(dungeonEntity, DungeonDto.class);
    }



    @Override
    public PlayerDto addPlayer(PlayerDto player) {
        ModelMapper modelMapper = new ModelMapper();
        PlayerEntity playerEntity = modelMapper.map(player, PlayerEntity.class);
        playerEntity = playerRepository.add(playerEntity);
        return  modelMapper.map(playerEntity, PlayerDto.class);
    }

    @Override
    public MonsterDto addMonster(MonsterDto monster) {
        if(monster.getDungeonId() == null){
            return null;
        }
        DungeonEntity dungeonEntity = dungeonRepository.findById(monster.getDungeonId());
        if (dungeonEntity == null){
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        MonsterEntity monsterEntity = modelMapper.map(monster, MonsterEntity.class);
        monsterEntity.setDungeon(dungeonEntity);
        monsterEntity = monsterRepository.add(monsterEntity);
        return modelMapper.map(monsterEntity, MonsterDto.class);
    }



    @Override
    public ItemDto addItem(ItemDto item) {
     if(item.getDungeonIdsList() == null || item.getDungeonIdsList().isEmpty()){
         return null;
     }
     List<DungeonEntity> dungeonEntityList = dungeonRepository.findAllByIdList(item.getDungeonIdsList());
     if(dungeonEntityList == null || dungeonEntityList.isEmpty()){
         return null;
     }
     List<Integer> dungeonEntityIdList = new ArrayList<>();
     for(Integer it : item.getDungeonIdsList()){
         dungeonEntityIdList.add(it);
     }

     ModelMapper modelMapper = new ModelMapper();
     ItemEntity itemEntity = modelMapper.map(item,ItemEntity.class);
     itemEntity = itemRepository.add(itemEntity);

     for(DungeonEntity dungeonEntity : dungeonEntityList){
         dungeonEntity.getItems().add(itemEntity);
         itemEntity.getDungeons().add(dungeonEntity);
         itemRepository.save(itemEntity);
         dungeonRepository.save(dungeonEntity);
     }
     item = modelMapper.map(itemEntity, ItemDto.class);
     item.setDungeonIdsList(dungeonEntityIdList);
     return item;
    }

    //update
    @Override
    public MapDto updateMap(MapDto map){
    ModelMapper modelMapper = new ModelMapper();
    MapEntity mapEntity = mapRepository.findById(map.getId());
    DungeonEntity dungeonEntity = dungeonRepository.findById(map.getCurrentDungeon().getId());
    mapEntity.setCurrentDungeon(dungeonEntity);
    mapEntity = mapRepository.save(mapEntity);
    return modelMapper.map(mapEntity, MapDto.class);
    }


    @Override
    public DungeonDto updateDungeon(DungeonDto dungeon) {
        if(dungeon.getId() == null){
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        DungeonEntity dungeonEntity = dungeonRepository.findById(dungeon.getId());
        List<ItemEntity> items = new ArrayList<>();
        for (ItemDto item : dungeon.getItems()){
            items.add(modelMapper.map(item, ItemEntity.class));
        }
        dungeonEntity.setItems(items);
        dungeonEntity = dungeonRepository.save(dungeonEntity);

        return modelMapper.map(dungeonEntity, DungeonDto.class);
    }

    @Override
    public PlayerDto updatePlayer(PlayerDto player) {
        ModelMapper modelMapper = new ModelMapper();
        PlayerEntity playerEntity = playerRepository.findById(player.getId());
        playerEntity.setDamage(player.getDamage());
        playerEntity.setHp(player.getHp());
        playerEntity.setHealthPots(player.getHealthPots());
        playerEntity = playerRepository.save(playerEntity);
        return modelMapper.map(playerEntity, PlayerDto.class);

    }

    @Override
    public MonsterDto updateMonster(MonsterDto monster) {
        ModelMapper modelMapper = new ModelMapper();
        MonsterEntity monsterEntity = monsterRepository.findById(monster.getId());
        monsterEntity.setHp(monster.getHp());
        return modelMapper.map(monsterEntity, MonsterDto.class);
    }

    @Override
    public ItemDto updateItem(ItemDto item) {
        ModelMapper modelMapper = new ModelMapper();
        ItemEntity itemEntity = itemRepository.findById(item.getId());
        itemEntity.setName(item.getName());
        return modelMapper.map(itemEntity, ItemDto.class);
    }

    @Override
    public MapDto setCurrentDungeon(Integer dungeonId) {
       ModelMapper modelMapper = new ModelMapper();
       MapEntity mapEntity = mapRepository.findById(1);
       List<DungeonEntity> dungeonEntityList = mapEntity.getDungeons();
       for(DungeonEntity dungeonEntity : dungeonEntityList){
           if(dungeonEntity.getId() == dungeonId){
               mapEntity.setCurrentDungeon(dungeonEntity);
           }
       }
       return modelMapper.map(mapEntity, MapDto.class);
    }

    @Override
    public DungeonDto findCurrentDungeon() {
        MapEntity mapEntity = mapRepository.findById(1);
        DungeonEntity dungeonEntity = mapEntity.getCurrentDungeon();
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dungeonEntity, DungeonDto.class);
    }
}
