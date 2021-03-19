package ba.codecta.springlearning.services.model;


import lombok.Data;

@Data
public class PlayerDto {
    private Integer id;
    private String name;
    private Integer hp;
    private Integer damage;
    private Integer healthPots;
}
