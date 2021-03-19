package ba.codecta.springlearning.repository.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ba.codecta.springlearning.repository.automap.PlayerAutoMapped;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "codecta", name = "PLAYER")
@EqualsAndHashCode(callSuper = true)
public class PlayerEntity extends PlayerAutoMapped {

    @SequenceGenerator(
            name = "playerSeq",
            sequenceName = "PLAYER_SEQ",
            schema = "codecta",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "playerSeq")
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    private String name;
    private Integer damage;
    private Integer hp;
    private Integer healthPots;
    @OneToOne(mappedBy = "currentPlayer")
    private MapEntity map;
}
