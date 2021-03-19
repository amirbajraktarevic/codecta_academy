package ba.codecta.springlearning.repository.automap;

import lombok.Data;
import ba.codecta.springlearning.repository.entity.DungeonEntity;
import ba.codecta.springlearning.repository.entity.ModelObject;

@Data
public class MonsterAutoMapped extends ModelObject {
    private Integer id;
    private String name;
    private Integer damage;
    private Integer hp;
    private DungeonEntity dungeon;

    @Override
    public Integer getId() {
        return id;
    }
}
