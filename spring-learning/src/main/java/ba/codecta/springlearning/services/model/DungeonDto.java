package ba.codecta.springlearning.services.model;

import lombok.Data;


import java.util.ArrayList;
import java.util.List;

@Data
public class DungeonDto {
    private Integer id;
    private Integer mapId;
    private String name;
    private List<MonsterDto> monsters = new ArrayList<>();
    private List<ItemDto> items = new ArrayList<>();


}
