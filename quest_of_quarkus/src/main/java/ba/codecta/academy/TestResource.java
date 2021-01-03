package ba.codecta.academy;

import services.QoQService;
import services.model.*;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;

@Path("/game")
public class TestResource {

    public class Error {
        public String code;
        public String description;

        public Error(String code, String description){
            this.code = code;
            this.description = description;
        }
    }

    @Inject
    QoQService qoQService;


    @POST
    @Path("/start")
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
            return Response.status(Response.Status.BAD_REQUEST).entity(new Error("B4D", "Can't make a new map, because there is a map already made.")).build();
    }

    @POST
    @Path("{id}/move")
    public Response fight(@PathParam("id") Integer id){
        if(qoQService.findAllMaps() == null || qoQService.findAllMaps().isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).entity(new Error("B4D","There are no maps.")).build();
        }
        if(qoQService.findMapById(id) == null){
            return Response.status(Response.Status.NOT_FOUND).entity(new Error("B4D", "There is no map with such ID")).build();
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


    @POST
    @Path("/{id}/fight")
    public Response move(@PathParam("id") Integer id){
        String lostMsg = "You are dead.";
        String wonMsg = "You won the fight!";
        if(qoQService.findAllMaps() == null || qoQService.findAllMaps().isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).entity(new Error("B4D","There is no map.")).build();
        }
        if(qoQService.findMapById(id) == null){
            return Response.status(Response.Status.NOT_FOUND).entity(new Error("B4D","There is no map with that ID.")).build();
        }
        MapDto map = qoQService.findMapById(id);
        DungeonDto currentDungeon = map.getCurrentDungeon();
        if(currentDungeon.getMonsters() == null || currentDungeon.getMonsters().isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).entity(new Error("B4D", "There are no monsters in this dungeon.")).build();
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

    @POST
    @Path("{id}/take")
    public Response pickUp(@PathParam("id") Integer id){
        if(qoQService.findAllMaps() == null || qoQService.findAllMaps().isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).entity(new Error("B4D", "There is no map found")).build();
        }
        MapDto map = qoQService.findMapById(id);
        DungeonDto currentDungeon = map.getCurrentDungeon();

        if(currentDungeon.getItems().isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).entity(new Error("B4D", "No items in this dungeon.")).build();
        }
        if (!currentDungeon.getMonsters().isEmpty() && currentDungeon.getMonsters().get(0).getHp() > 0){
            return Response.status(Response.Status.BAD_REQUEST).entity(new Error("B4D", "You can't pick up items when monsters are around.")).build();
        }

        Integer monsterHP = 0;
        String winningMsg = "You found the Orb of Quarkus. We shall fear bad API calls no more!";
        List<DungeonDto> dungeons = qoQService.findAllDungeons();
        for(DungeonDto dungeon : dungeons){
            if (!dungeon.getMonsters().isEmpty()){
                monsterHP = monsterHP + dungeon.getMonsters().get(0).getHp();
            }
        }
        if(currentDungeon.getItems().get(0).getName().equals("Healing potion")){
            List<ItemDto> items = new ArrayList<>();
            map.getCurrentPlayer().setHealthPots(40 + map.getCurrentPlayer().getHealthPots());
            currentDungeon.setItems(items);
            qoQService.updateDungeon(currentDungeon);
            qoQService.updatePlayer(map.getCurrentPlayer());
            System.out.println("Healing time!");
        }else if(currentDungeon.getItems().get(0).getName().equals("Orb of Quarkus") && monsterHP <= 0.0){
            return Response.status(Response.Status.OK).entity(winningMsg).build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).entity(new Error("B4D", "You need to kill the big guy to win the game...")).build();
        }

        return Response.ok(map).build();
    }


    //Manual insertion
    @POST
    @Path("/map")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createMap(MapDto map, @Context UriInfo uriInfo){
        MapDto mapDto = qoQService.addMap(map);
        if(mapDto != null){
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(Integer.toString(mapDto.getId()));
            return Response.created(uriBuilder.build()).entity(mapDto).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(new Error("CDT-001", "Invalid request. Unknown map in request")).build();
    }

    @GET
    @Path("/map")
    public Response getMap(){
        if(qoQService.findMapById(1) == null){
            return Response.status(Response.Status.BAD_REQUEST).entity(new Error("B4D", "Bad request.")).build();
        }
        return Response.ok(qoQService.findMapById(1)).build();
    }

    @POST
    @Path("/dungeon")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createDungeon(DungeonDto dungeon, @Context UriInfo uriInfo){
        DungeonDto dungeonDto = qoQService.addDungeon(dungeon);
        if(dungeonDto != null){
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(Integer.toString(dungeonDto.getId()));
            return Response.created(uriBuilder.build()).entity(dungeonDto).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(new Error("CDT-001", "Invalid request. Unknown dungeon in request")).build();
    }

    @GET
    @Path("/dungeon")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDungeons(){
        List<DungeonDto> dungeonDtoList = qoQService.findAllDungeons();
        if (dungeonDtoList == null || dungeonDtoList.isEmpty()){
            return Response.noContent().build();
        }
        return Response.ok(dungeonDtoList).build();
    }

    @POST
    @Path("/player")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createPlayer(PlayerDto player, @Context UriInfo uriInfo){
        PlayerDto playerDto = qoQService.addPlayer(player);
        if(playerDto != null){
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(Integer.toString(playerDto.getId()));
            return Response.created(uriBuilder.build()).entity(playerDto).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(new Error("CDT-001", "Invalid request. Unknown player in request")).build();
    }


    @GET
    @Path("/player")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlayers(){
        List<PlayerDto> playerDtoList = qoQService.findAllPlayers();
        if (playerDtoList == null || playerDtoList.isEmpty()){
            return Response.noContent().build();
        }
        return Response.ok(playerDtoList).build();
    }


    @POST
    @Path("/monster")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createMonster(MonsterDto monster, @Context UriInfo uriInfo){
        MonsterDto monsterDto = qoQService.addMonster(monster);
        if(monsterDto != null){
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(Integer.toString(monsterDto.getId()));
            return Response.created(uriBuilder.build()).entity(monsterDto).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(new Error("CDT-001", "Invalid request. Unknown monster in request")).build();
    }

    @POST
    @Path("/item")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createItme(ItemDto item, @Context UriInfo uriInfo) {
        ItemDto itemDto = qoQService.addItem(item);
        if (itemDto != null) {
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(Integer.toString(itemDto.getId()));
            return Response.created(uriBuilder.build()).entity(itemDto).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(new Error("CDT-001", "Invalid request. Unknown item in request")).build();

    }

    @GET
    @Path("/monster")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMonsters(){
        List<MonsterDto> monsterDtoList = qoQService.findAllMonsters();
        if (monsterDtoList == null || monsterDtoList.isEmpty()){
            return Response.noContent().build();
        }
        return Response.ok(monsterDtoList).build();
    }


    @GET
    @Path("/items")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllWeapons(){
        List<ItemDto> itemDtoList = qoQService.findAllItems();
        if (itemDtoList == null || itemDtoList.isEmpty()){
            return Response.noContent().build();
        }
        return Response.ok(itemDtoList).build();
    }









}