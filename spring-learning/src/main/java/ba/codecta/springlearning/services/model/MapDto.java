package ba.codecta.springlearning.services.model;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MapDto {
    private Integer id;
    private List<DungeonDto> dungeons = new ArrayList<>();
    private DungeonDto currentDungeon;
    private PlayerDto currentPlayer;
}
