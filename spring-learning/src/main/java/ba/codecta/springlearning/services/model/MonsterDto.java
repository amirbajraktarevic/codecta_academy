package ba.codecta.springlearning.services.model;


import lombok.Data;

@Data
public class MonsterDto {
    private Integer id;
    private Integer dungeonId;
    private Integer hp;
    private Integer damage;
}
