package repository.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "codecta", name = "MAP")
public class MapEntity extends ModelObject {
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

    public List<DungeonEntity> getDungeons() {
        return dungeons;
    }

    public void setDungeons(List<DungeonEntity> dungeons) {
        this.dungeons = dungeons;
    }

    public PlayerEntity getCurrentPlayer() {
        return currentPlayer;
    }


    public void setCurrentPlayer(PlayerEntity currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public DungeonEntity getCurrentDungeon() {
        return currentDungeon;
    }

    public void setCurrentDungeon(DungeonEntity currentDungeon) {
        this.currentDungeon = currentDungeon;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
