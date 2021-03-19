package ba.codecta.springlearning.repository.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ba.codecta.springlearning.repository.automap.ItemAutoMapped;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(schema = "codecta", name = "ITEM")
@EqualsAndHashCode(callSuper = true)
public class ItemEntity extends ItemAutoMapped {
    @SequenceGenerator(
            name = "itemSeq",
            sequenceName = "ITEM_SEQ",
            schema = "codecta",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemSeq")
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    private String name;
    @ManyToMany(mappedBy = "items")
    private List<DungeonEntity> dungeons = new ArrayList<>();
}
