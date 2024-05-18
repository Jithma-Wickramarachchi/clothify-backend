package edu.icet.clothifybackend.service.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothifybackend.dto.order.OrderDto;
import edu.icet.clothifybackend.entity.order.OrderEntity;
import edu.icet.clothifybackend.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class OrderMapper {
    private final ObjectMapper mapper;
    public OrderDto convertEntityToDto(OrderEntity orderEntity){
        OrderDto orderDto = mapper.convertValue(orderEntity, OrderDto.class);
        orderDto.setUsername(orderEntity.getUser().getUsername());
        return orderDto;
    }

    public OrderEntity convertDtoToEntity(OrderDto orderDto, User user){
        OrderEntity orderEntity = mapper.convertValue(orderDto, OrderEntity.class);
        orderEntity.setUser(user);
        return orderEntity;
    }
}
