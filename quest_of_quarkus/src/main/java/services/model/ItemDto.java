package services.model;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class ItemDto {
   private Integer id;
   private String name;
   private List<Integer> dungeonIdsList = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getDungeonIdsList() {
        return dungeonIdsList;
    }

    public void setDungeonIdsList(List<Integer> dungeonIdsList) {
        this.dungeonIdsList = dungeonIdsList;
    }
}
