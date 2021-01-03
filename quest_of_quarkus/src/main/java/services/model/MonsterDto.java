package services.model;

public class MonsterDto {

    private Integer id;
    private Integer dungeonId;
    private Integer hp;
    private Integer damage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDungeonId() {
        return dungeonId;
    }

    public void setDungeonId(Integer dungeonId) {
        this.dungeonId = dungeonId;
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
