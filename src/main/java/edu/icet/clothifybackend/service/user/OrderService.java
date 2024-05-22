package edu.icet.clothifybackend.service.user;

import edu.icet.clothifybackend.dto.user.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto saveOrder(OrderDto dto);
    List<OrderDto> getOrderListByUsername(String username);
    Long deleteOrder(Long id);
    OrderDto updateOrder(OrderDto dto);
}
