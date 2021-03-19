package ba.codecta.springlearning.repository.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ba.codecta.springlearning.repository.automap.DungeonAutoMapped;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(schema = "codecta", name = "DUNGEON")
@EqualsAndHashCode(callSuper = true)
public class DungeonEntity extends DungeonAutoMapped {
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


}
