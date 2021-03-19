package ba.codecta.springlearning.repository.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ba.codecta.springlearning.repository.automap.MapAutoMapped;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(schema = "codecta", name = "MAP")
@EqualsAndHashCode(callSuper = true)
public class MapEntity extends MapAutoMapped {
    @SequenceGenerator(
            name = "mapSeq",
            sequenceName = "MAP_SEQ",
            schema = "codecta",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mapSeq")
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @OneToOne
    private PlayerEntity currentPlayer;
    @OneToOne
    private DungeonEntity currentDungeon;
    @OneToMany(mappedBy = "map")
    private List<DungeonEntity> dungeons = new ArrayList<>();


}
