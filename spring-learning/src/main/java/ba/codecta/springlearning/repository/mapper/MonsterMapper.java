package ba.codecta.springlearning.repository.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import ba.codecta.springlearning.repository.automap.MonsterAutoMapped;
import ba.codecta.springlearning.repository.entity.MonsterEntity;
import ba.codecta.springlearning.services.model.MonsterDto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class MonsterMapper {
    public MonsterEntity toEntity(MonsterDto Monster){
        var entity = new MonsterEntity();
        BeanUtils.copyProperties(Monster, entity, MonsterAutoMapped.class);
        return entity;
    }

    public MonsterDto toDto(MonsterEntity entity){
        var dto = new MonsterDto();
        BeanUtils.copyProperties(entity, dto, MonsterAutoMapped.class);
        return dto;
    }

    public List<MonsterDto> toDtoList(Iterable<MonsterEntity> entities){
        return StreamSupport.stream(entities.spliterator(), false)
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
