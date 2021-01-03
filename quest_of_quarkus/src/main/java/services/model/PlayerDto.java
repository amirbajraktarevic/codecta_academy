package services.model;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class PlayerDto {
    private Integer id;
    private String name;
    private Integer hp;
    private Integer damage;
    private Integer healthPots;

    public Integer getHealthPots() {
        return healthPots;
    }

    public void setHealthPots(Integer healthPots) {
        this.healthPots = healthPots;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }
}
