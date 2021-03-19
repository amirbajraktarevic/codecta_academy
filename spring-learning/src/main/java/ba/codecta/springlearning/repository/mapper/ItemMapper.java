package ba.codecta.springlearning.repository.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import ba.codecta.springlearning.repository.automap.ItemAutoMapped;
import ba.codecta.springlearning.repository.entity.ItemEntity;
import ba.codecta.springlearning.services.model.ItemDto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class ItemMapper {
    public ItemEntity toEntity(ItemDto Item){
        var entity = new ItemEntity();
        BeanUtils.copyProperties(Item, entity, ItemAutoMapped.class);
        return entity;
    }

    public ItemDto toDto(ItemEntity entity){
        var dto = new ItemDto();
        BeanUtils.copyProperties(entity, dto, ItemAutoMapped.class);
        return dto;
    }

    public List<ItemDto> toDtoList(Iterable<ItemEntity> entities){
        return StreamSupport.stream(entities.spliterator(), false)
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
