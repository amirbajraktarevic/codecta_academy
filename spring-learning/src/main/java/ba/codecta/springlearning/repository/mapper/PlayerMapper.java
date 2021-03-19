package ba.codecta.springlearning.repository.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import ba.codecta.springlearning.repository.automap.PlayerAutoMapped;
import ba.codecta.springlearning.repository.entity.PlayerEntity;
import ba.codecta.springlearning.services.model.PlayerDto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Component
public class PlayerMapper {
    public PlayerEntity toEntity(PlayerDto Player){
        var entity = new PlayerEntity();
        BeanUtils.copyProperties(Player, entity, PlayerAutoMapped.class);
        return entity;
    }

    public PlayerDto toDto(PlayerEntity entity){
        var dto = new PlayerDto();
        BeanUtils.copyProperties(entity, dto, PlayerAutoMapped.class);
        return dto;
    }

    public List<PlayerDto> toDtoList(Iterable<PlayerEntity> entities){
        return StreamSupport.stream(entities.spliterator(), false)
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
