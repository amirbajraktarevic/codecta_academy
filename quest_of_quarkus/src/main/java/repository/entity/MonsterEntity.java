package repository.entity;


import javax.persistence.*;

@Entity
@Table(schema = "codecta", name = "MONSTER")
public class MonsterEntity extends ModelObject {
    @SequenceGenerator(
            name = "monsterSeq",
            sequenceName = "MONSTER_SEQ",
            schema = "codecta",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "monsterSeq")
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    private String name;
    private Integer damage;
    private Integer hp;
    @ManyToOne
    private DungeonEntity dungeon;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public DungeonEntity getDungeon() {
        return dungeon;
    }

    public void setDungeon(DungeonEntity dungeon) {
        this.dungeon = dungeon;
    }
}
