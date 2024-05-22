package edu.icet.clothifybackend.service.user;

import edu.icet.clothifybackend.dto.user.OrderedItemDto;

import java.util.List;

public interface OrderedItemService {
    OrderedItemDto saveOrderedItem(OrderedItemDto dto);
    List<OrderedItemDto> getOrderedItemListByOrderId(Long id);
    Long deleteOrderedItem(Long id);
    OrderedItemDto updateOrderedItem(OrderedItemDto dto);
}
