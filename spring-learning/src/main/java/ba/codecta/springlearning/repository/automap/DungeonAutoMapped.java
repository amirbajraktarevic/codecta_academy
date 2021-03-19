package ba.codecta.springlearning.repository.automap;

import lombok.Data;
import ba.codecta.springlearning.repository.entity.ItemEntity;
import ba.codecta.springlearning.repository.entity.MapEntity;
import ba.codecta.springlearning.repository.entity.ModelObject;
import ba.codecta.springlearning.repository.entity.MonsterEntity;

import java.util.ArrayList;
import java.util.List;

@Data
public class DungeonAutoMapped extends ModelObject {
    private Integer id;
    private String name;
    private MapEntity map;
    private MapEntity currentMapRef;
    private List<MonsterEntity> monsters = new ArrayList<>();
    private List<ItemEntity> items = new ArrayList<>();

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
