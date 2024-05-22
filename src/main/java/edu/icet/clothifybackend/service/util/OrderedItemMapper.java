package edu.icet.clothifybackend.service.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothifybackend.dto.user.OrderedItemDto;
import edu.icet.clothifybackend.entity.user.OrderEntity;
import edu.icet.clothifybackend.entity.user.OrderedItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class OrderedItemMapper {
    private final ObjectMapper mapper;
    public OrderedItemDto convertEntityToDto(OrderedItemEntity orderedItemEntity){
        OrderedItemDto orderedItemDto = mapper.convertValue(orderedItemEntity, OrderedItemDto.class);
        orderedItemDto.setOrderId(orderedItemEntity.getId());
        return orderedItemDto;
    }

    public OrderedItemEntity convertDtoToEntity(OrderedItemDto orderedItemDto, OrderEntity order){
        OrderedItemEntity orderedItemEntity = mapper.convertValue(orderedItemDto, OrderedItemEntity.class);
        orderedItemEntity.setOrder(order);
        return orderedItemEntity;
    }
}
