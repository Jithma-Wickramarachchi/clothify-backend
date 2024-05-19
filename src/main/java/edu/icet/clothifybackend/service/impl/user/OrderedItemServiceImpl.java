package edu.icet.clothifybackend.service.impl.user;

import edu.icet.clothifybackend.dto.user.OrderedItemDto;
import edu.icet.clothifybackend.entity.user.OrderEntity;
import edu.icet.clothifybackend.entity.user.OrderedItemEntity;
import edu.icet.clothifybackend.exception.user.OrderNotFoundException;
import edu.icet.clothifybackend.exception.user.OrderedItemNotFoundException;
import edu.icet.clothifybackend.repository.user.OrderRepository;
import edu.icet.clothifybackend.repository.user.OrderedItemRepository;
import edu.icet.clothifybackend.service.user.OrderedItemService;
import edu.icet.clothifybackend.service.util.OrderedItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Service
@RequiredArgsConstructor
public class OrderedItemServiceImpl implements OrderedItemService {
    private final OrderedItemRepository orderedItemRepository;
    private final OrderRepository orderRepository;
    private final OrderedItemMapper mapper;
    @Override
    public OrderedItemDto saveOrderedItem(OrderedItemDto dto) {
        //Throw exception when order not found
        OrderEntity order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(()-> new OrderNotFoundException(dto.getOrderId()));

        //convert orderedItem dto to entity and save
        OrderedItemEntity savedOrderedItem = orderedItemRepository.save(mapper.convertDtoToEntity(dto, order));

        //Convert saved entity into dto and return
        return mapper.convertEntityToDto(savedOrderedItem);
    }

    @Override
    public List<OrderedItemDto> getOrderedItemListByOrderId(Long id) {
        List<OrderedItemEntity> entityList = orderedItemRepository.getOrderedItemListByOrderId(id);
        Iterator<OrderedItemEntity> iterator = entityList.iterator();

        //convert entities into dto and add one by one
        ArrayList<OrderedItemDto> dtoList = new ArrayList<>();
        while (iterator.hasNext()) {
            OrderedItemDto dto = mapper.convertEntityToDto(iterator.next());
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public Long deleteOrderedItem(Long id) {

        //check id available the database
        if (orderedItemRepository.findById(id).isEmpty()) {
            throw new OrderedItemNotFoundException(id);
        }
        orderedItemRepository.deleteById(id);
        return id;
    }
    @Override
    public OrderedItemDto updateOrderedItem(OrderedItemDto dto) {
        //check whether order in database
        OrderEntity order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(()-> new OrderNotFoundException(dto.getOrderId()));

        //check whether ordered item in database
        if (orderedItemRepository.findById(dto.getId()).isEmpty()) {
            throw new OrderedItemNotFoundException(dto.getId());
        }
        //convert dto into entity and update
        OrderedItemEntity savedEntity = orderedItemRepository.save(mapper.convertDtoToEntity(dto, order));

        //convert entity into dto and return
        return mapper.convertEntityToDto(savedEntity);
    }
}
