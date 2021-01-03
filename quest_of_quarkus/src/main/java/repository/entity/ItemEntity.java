package repository.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "codecta", name = "ITEM")
public class ItemEntity extends ModelObject{
    @SequenceGenerator(
            name = "itemSeq",
            sequenceName = "ITEM_SEQ",
            schema = "codecta",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemSeq")
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    private String name;
    @ManyToMany(mappedBy = "items")
    private List<DungeonEntity> dungeons = new ArrayList<>();

    public List<DungeonEntity> getDungeons() {
        return dungeons;
    }

    public void setDungeons(List<DungeonEntity> dungeons) {
        this.dungeons = dungeons;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
