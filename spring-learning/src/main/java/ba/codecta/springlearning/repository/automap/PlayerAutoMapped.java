package ba.codecta.springlearning.repository.automap;

import lombok.Data;
import ba.codecta.springlearning.repository.entity.MapEntity;
import ba.codecta.springlearning.repository.entity.ModelObject;

@Data
public class PlayerAutoMapped extends ModelObject {
    private Integer id;
    private String name;
    private Integer damage;
    private Integer hp;
    private Integer healthPots;
    private MapEntity map;

    @Override
    public Integer getId() {
        return id;
    }
}
