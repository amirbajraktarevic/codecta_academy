package repository.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "codecta", name = "DUNGEON")
public class DungeonEntity extends ModelObject {
    @SequenceGenerator(
            name = "dungeonSeq",
            sequenceName = "DUNGEON_SEQ",
            schema = "codecta",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dungeonSeq")
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    private String name;
    @ManyToOne
    private MapEntity map;
    @OneToOne(mappedBy = "currentDungeon", cascade = CascadeType.ALL)
    private MapEntity currentMapRef;
    @OneToMany(mappedBy = "dungeon")
    private List<MonsterEntity> monsters = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "DUNGEON_ITEMS",
            joinColumns = @JoinColumn(name = "DUNGEON_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID"))
    private List<ItemEntity> items = new ArrayList<>();


    public List<MonsterEntity> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<MonsterEntity> monsters) {
        this.monsters = monsters;
    }

    public List<ItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MapEntity getMap() {
        return map;
    }

    public void setMap(MapEntity map) {
        this.map = map;
    }

    public MapEntity getCurrentMapRef() {
        return currentMapRef;
    }

    public void setCurrentMapRef(MapEntity currentMapRef) {
        this.currentMapRef = currentMapRef;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
