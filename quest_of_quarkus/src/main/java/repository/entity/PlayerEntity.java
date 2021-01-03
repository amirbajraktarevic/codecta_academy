package repository.entity;



import services.model.MapDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(schema = "codecta", name = "PLAYER")
public class PlayerEntity extends ModelObject {

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

    public Integer getHealthPots() {
        return healthPots;
    }

    public void setHealthPots(Integer healthPots) {
        this.healthPots = healthPots;
    }

    public MapEntity getMap() {
        return map;
    }

    public void setMap(MapEntity map) {
        this.map = map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setId(Integer id) {
        this.id = id;
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


    @Override
    public Integer getId(){
        return this.id;
    }
}
