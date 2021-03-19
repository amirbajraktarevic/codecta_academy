package ba.codecta.springlearning.repository.automap;

import lombok.Data;
import ba.codecta.springlearning.repository.entity.DungeonEntity;
import ba.codecta.springlearning.repository.entity.ModelObject;

import java.util.ArrayList;
import java.util.List;

@Data
public class ItemAutoMapped extends ModelObject {
    private Integer id;
    private String name;
    private List<DungeonEntity> dungeons = new ArrayList<>();

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
