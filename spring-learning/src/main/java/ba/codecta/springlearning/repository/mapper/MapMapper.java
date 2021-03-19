package ba.codecta.springlearning.repository.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import ba.codecta.springlearning.repository.automap.MapAutoMapped;
import ba.codecta.springlearning.repository.entity.MapEntity;
import ba.codecta.springlearning.services.model.MapDto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class MapMapper {
    public MapEntity toEntity(MapDto Map){
        var entity = new MapEntity();
        BeanUtils.copyProperties(Map, entity, MapAutoMapped.class);
        return entity;
    }

    public MapDto toDto(MapEntity entity){
        var dto = new MapDto();
        BeanUtils.copyProperties(entity, dto, MapAutoMapped.class);
        return dto;
    }

    public List<MapDto> toDtoList(Iterable<MapEntity> entities){
        return StreamSupport.stream(entities.spliterator(), false)
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
