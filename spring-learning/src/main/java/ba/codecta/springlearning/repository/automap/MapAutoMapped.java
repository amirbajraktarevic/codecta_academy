package ba.codecta.springlearning.repository.automap;

import lombok.Data;
import ba.codecta.springlearning.repository.entity.DungeonEntity;
import ba.codecta.springlearning.repository.entity.ModelObject;
import ba.codecta.springlearning.repository.entity.PlayerEntity;

import java.util.ArrayList;
import java.util.List;

@Data
public class MapAutoMapped extends ModelObject {
    private Integer id;
    private PlayerEntity currentPlayer;
    private DungeonEntity currentDungeon;
    private List<DungeonEntity> dungeons = new ArrayList<>();

    @Override
    public Integer getId() {
        return id;
    }
}
