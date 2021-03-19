package ba.codecta.springlearning.services.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ItemDto {
    private Integer id;
    private String name;
    private List<Integer> dungeonIdsList = new ArrayList<>();
}
