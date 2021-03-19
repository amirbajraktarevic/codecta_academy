package ba.codecta.springlearning.repository.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import ba.codecta.springlearning.repository.automap.MonsterAutoMapped;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "codecta", name = "MONSTER")
@EqualsAndHashCode(callSuper = true)
public class MonsterEntity extends MonsterAutoMapped {
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
}
