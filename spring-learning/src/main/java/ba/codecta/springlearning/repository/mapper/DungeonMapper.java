package ba.codecta.springlearning.repository.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import ba.codecta.springlearning.repository.automap.DungeonAutoMapped;
import ba.codecta.springlearning.repository.entity.DungeonEntity;
import ba.codecta.springlearning.services.model.DungeonDto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class DungeonMapper {

    public DungeonEntity toEntity(DungeonDto dungeon){
        var entity = new DungeonEntity();
        BeanUtils.copyProperties(dungeon, entity, DungeonAutoMapped.class);
        return entity;
    }

    public DungeonDto toDto(DungeonEntity entity){
        var dto = new DungeonDto();
        BeanUtils.copyProperties(entity, dto, DungeonAutoMapped.class);
        return dto;
    }

    public List<DungeonDto> toDtoList(Iterable<DungeonEntity> entities){
        return StreamSupport.stream(entities.spliterator(), false)
                .map(this::toDto)
                .collect(Collectors.toList());
    }

}
