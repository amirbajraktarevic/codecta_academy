package ba.codecta.springlearning.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ba.codecta.springlearning.services.QoQService;
import ba.codecta.springlearning.services.model.*;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping("game")
public class QoQController {


    @Autowired
    private final QoQService qoQService;
    private final UriInfo uriInfo;


    @GetMapping(value = "map")
    private List<MapDto> getAllMaps(){
        return qoQService.findAllMaps();
    }


    @PostMapping(value = "map")
    private MapDto createMap(@RequestBody MapDto dto){
        return (qoQService.addMap(dto));
    }

    @GetMapping(value = "dungeon")
    private List<DungeonDto> getAllDungeons(){
        return qoQService.findAllDungeons();
    }

    @PostMapping(value = "dungeon")
    private DungeonDto createDungeon(@RequestBody DungeonDto dto){
        return (qoQService.addDungeon(dto));
    }

    @GetMapping(value = "player")
    private List<PlayerDto> getAllPlayers(){
        return qoQService.findAllPlayers();
    }

    @PostMapping(value = "player")
    private PlayerDto createPlayer(@RequestBody PlayerDto dto){
        return (qoQService.addPlayer(dto));
    }

    @GetMapping(value = "item")
    private List<ItemDto> getAllItems(){
        return qoQService.findAllItems();
    }

    @PostMapping(value = "item")
    private ItemDto createItem(@RequestBody ItemDto dto){
        return (qoQService.addItem(dto));
    }

    @GetMapping(value = "monster")
    private List<MonsterDto> getAllMonsters(){
        return qoQService.findAllMonsters();
    }

    @PostMapping(value = "monster")
    private MonsterDto createMonster(@RequestBody MonsterDto dto){
        return (qoQService.addMonster(dto));
    }

    @PostMapping(value = "start")
    public Response startGame(@Context UriInfo uriInfo) {
        if (qoQService.findAllMaps() == null || qoQService.findAllMaps().isEmpty()) {
            MapDto map = new MapDto();
            PlayerDto player = new PlayerDto();
            player.setName("Savior of APIland");
            player.setHp(100);
            player.setDamage(60);
            player.setHealthPots(0);
            qoQService.addPlayer(player);
            MapDto newMap = qoQService.addMap(map);
            HashMap<Integer, String> levelNames = new HashMap<>();
            levelNames.put(1, "Land of the API Calls");
            levelNames.put(2, "The POSTland");
            levelNames.put(3, "The GETland");
            levelNames.put(4, "The PUTland");
            levelNames.put(5, "PATCHland");
            levelNames.put(6, "DELETEland");
            levelNames.put(7, "PATCHland");
            levelNames.put(8, "COPYland");
            levelNames.put(9, "PURGEland");
            levelNames.put(10, "The final battle");

            List<DungeonDto> dungeons = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                DungeonDto dungeon = new DungeonDto();
                dungeon.setName(levelNames.get(i + 1));
                dungeons.add(qoQService.addDungeon(dungeon));
            }

            List<Integer> itemsInDungeon = new ArrayList<>();
            itemsInDungeon.add(1);
            itemsInDungeon.add(3);
            itemsInDungeon.add(5);
            itemsInDungeon.add(7);
            itemsInDungeon.add(9);

            ItemDto healthPot = new ItemDto();
            healthPot.setName("StackOverflow Potion");
            healthPot.setDungeonIdsList(itemsInDungeon);
            ItemDto placedPot = qoQService.addItem(healthPot);

            ItemDto orbOfQuarkus = new ItemDto();
            List<Integer> orbInDungeon = new ArrayList<>();
            orbInDungeon.add(10);
            orbOfQuarkus.setName("Orb Of Quarkus");
            orbOfQuarkus.setDungeonIdsList(orbInDungeon);
            ItemDto placedOrb = qoQService.addItem(orbOfQuarkus);

            for (int j = 0; j < 10; j++) {
                MonsterDto monster = new MonsterDto();
                monster.setDungeonId(j + 1);
                monster.setHp(20 * j / 2);
                monster.setDamage(10 * j / 2);
                qoQService.addMonster(monster);
            }

            for (DungeonDto dungeon : dungeons) {
                List<ItemDto> items = new ArrayList<>();

                if (itemsInDungeon.contains(dungeon.getId())) {
                    items.add(placedPot);
                    dungeon.setItems(items);
                }

                if (orbInDungeon.contains(dungeon.getId())) {
                    items.add(placedOrb);
                    dungeon.setItems(items);
                }
            }

            DungeonDto currentDungeon = (qoQService.setCurrentDungeon(dungeons.get(0).getId())).getCurrentDungeon();
            newMap.setDungeons(dungeons);
            newMap.setCurrentDungeon(currentDungeon);
            newMap.setCurrentPlayer(player);
            qoQService.updateMap(newMap);

            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(" ");
            return Response.ok(uriBuilder.build()).entity(qoQService.findMapById(1)).build();
        } else
            return Response.status(Response.Status.BAD_REQUEST).entity(new Error("B4D")).build();
    }


    @PostMapping(value = "{id}/fight")
    public Response fight (@PathVariable("id") Integer id){
        String lostMsg = "You are dead.";
        String wonMsg = "You won the fight!";
        if(qoQService.findAllMaps() == null || qoQService.findAllMaps().isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).entity(new Error("B4D")).build();
        }
        if(qoQService.findMapById(id) == null){
            return Response.status(Response.Status.NOT_FOUND).entity(new Error("B4D")).build();
        }
        MapDto map = qoQService.findMapById(id);
        DungeonDto currentDungeon = map.getCurrentDungeon();
        if(currentDungeon.getMonsters() == null || currentDungeon.getMonsters().isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).entity(new Error("B4D")).build();
        }
        PlayerDto player = map.getCurrentPlayer();
        MonsterDto monster = currentDungeon.getMonsters().get(0);
        Random randomAttack = new Random();

        player.setHp(player.getHp() + player.getHealthPots());
        do {
            monster.setHp(monster.getHp()-(player.getDamage()*((randomAttack.nextInt(6)+1)/5)));
            player.setHp(player.getHp()-(monster.getDamage()*((randomAttack.nextInt(6)+1)/5)));
        }
        while (player.getHp() > 0 && monster.getHp() > 0);
        List<MonsterDto> monsters = new ArrayList<>();
        monsters.add(monster);
        currentDungeon.setMonsters(monsters);

        if(player.getHp() <= 0){
            qoQService.updateMonster(currentDungeon.getMonsters().get(0));
            map.setCurrentDungeon(currentDungeon);
            map.setCurrentPlayer(player);
            qoQService.updateMap(map);
            return Response.status(Response.Status.OK).entity(lostMsg).build();
        }

        if(monster.getHp() <= 0){
            qoQService.updateMonster(currentDungeon.getMonsters().get(0));
            map.setCurrentDungeon(currentDungeon);
            map.setCurrentPlayer(player);
            qoQService.updateMap(map);
            return Response.status(Response.Status.OK).entity(wonMsg).build();
        }

        return Response.ok(map).build();
    }




    @PostMapping(value = "{id}/move")
    public Response move(@PathVariable("id") Integer id){
        if(qoQService.findAllMaps() == null || qoQService.findAllMaps().isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).entity(new Error("B4D")).build();
        }
        if(qoQService.findMapById(id) == null){
            return Response.status(Response.Status.NOT_FOUND).entity(new Error("B4D")).build();
        }
        MapDto map = qoQService.findMapById(id);
        DungeonDto dungeon = qoQService.findCurrentDungeon();
        if(dungeon.getId() <= 9){
            map = qoQService.setCurrentDungeon(dungeon.getId() + 1);
        } else {
            map = qoQService.setCurrentDungeon(1);
        }
        return Response.ok(map).entity("You moved.").build();
    }

    @PostMapping(value = "{id}/take")
    public Response pickUp(@PathVariable("id") Integer id){
        if(qoQService.findAllMaps() == null || qoQService.findAllMaps().isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).entity(new Error("B4D")).build();
        }
        MapDto map = qoQService.findMapById(id);
        DungeonDto currentDungeon = map.getCurrentDungeon();

        if(currentDungeon.getItems().isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).entity(new Error("B4D")).build();
        }
        if (!currentDungeon.getMonsters().isEmpty() && currentDungeon.getMonsters().get(0).getHp() > 0){
            return Response.status(Response.Status.BAD_REQUEST).entity(new Error("B4D")).build();
        }

        Integer monsterHP = 0;
        String winningMsg = "You found the Orb of Quarkus. We shall fear bad API calls no more!";
        List<DungeonDto> dungeons = qoQService.findAllDungeons();
        for(DungeonDto dungeon : dungeons){
            if (!dungeon.getMonsters().isEmpty()){
                monsterHP = monsterHP + dungeon.getMonsters().get(0).getHp();
            }
        }
        if(currentDungeon.getItems().get(0).getName().equals("Healing potion") || currentDungeon.getItems().get(0).getId() == 1){
            List<ItemDto> items = new ArrayList<>();
            map.getCurrentPlayer().setHealthPots(40 + map.getCurrentPlayer().getHealthPots());
            currentDungeon.setItems(items);
            qoQService.updateDungeon(currentDungeon);
            qoQService.updatePlayer(map.getCurrentPlayer());
            System.out.println("Healing time!");
        }else if(currentDungeon.getItems().get(0).getName().equals("Orb of Quarkus") && monsterHP <= 0 || currentDungeon.getItems().get(0).getId() == 2 && monsterHP <= 0){
            System.out.println("Congratulations, you won the game!");
            return Response.status(Response.Status.OK).entity(winningMsg).build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).entity(new Error("B4D")).build();
        }

        return Response.ok(map).build();
    }

}
