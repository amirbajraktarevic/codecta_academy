package services.model;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class DungeonDto {
    private Integer id;
    private Integer mapId;
    private String name;
    private List<MonsterDto> monsters = new ArrayList<>();
    private List<ItemDto> items = new ArrayList<>();

    //default
    public DungeonDto(){
        setMapId(1);
    }

    @Override
    public String toString(){
        return id + name + " " + items + " " + monsters + " " + mapId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMapId() {
        return mapId;
    }

    public void setMapId(Integer mapId) {
        this.mapId = mapId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MonsterDto> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<MonsterDto> monsters) {
        this.monsters = monsters;
    }

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }

}
