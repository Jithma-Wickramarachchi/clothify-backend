package edu.icet.clothifybackend.service.impl.user;

import edu.icet.clothifybackend.dto.user.OrderDto;
import edu.icet.clothifybackend.entity.user.OrderEntity;
import edu.icet.clothifybackend.entity.user.User;
import edu.icet.clothifybackend.exception.user.OrderNotFoundException;
import edu.icet.clothifybackend.exception.user.UserNotFoundException;
import edu.icet.clothifybackend.repository.user.OrderRepository;
import edu.icet.clothifybackend.repository.user.UserRepository;
import edu.icet.clothifybackend.service.user.OrderService;
import edu.icet.clothifybackend.service.util.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;
    
    @Override
    public OrderDto saveOrder(OrderDto dto) {
        //Throw exception when user not found
        User user = userRepository.findUserByUsername(dto.getUsername())
                .orElseThrow(()-> new UserNotFoundException(dto.getUsername()));

        //convert order dto to entity and save
        OrderEntity savedOrder = orderRepository.save(mapper.convertDtoToEntity(dto, user));

        //Convert saved entity into dto and return
        return mapper.convertEntityToDto(savedOrder);
    }

    @Override
    public List<OrderDto> getOrderListByUsername(String username) {
        List<OrderEntity> entityList = orderRepository.getOrderListByUsername(username);
        Iterator<OrderEntity> iterator = entityList.iterator();

        //convert entities into dto and add one by one
        ArrayList<OrderDto> dtoList = new ArrayList<>();
        while (iterator.hasNext()) {
            OrderDto dto = mapper.convertEntityToDto(iterator.next());
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public Long deleteOrder(Long id) {
        //check id available the database
        if (orderRepository.findById(id).isEmpty()) {
            throw new OrderNotFoundException(id);
        }
        orderRepository.deleteById(id);
        return id;
    }

    @Override
    public OrderDto updateOrder(OrderDto dto) {
        //check whether user in database
        User user = userRepository.findUserByUsername(dto.getUsername())
                .orElseThrow(()-> new UserNotFoundException(dto.getUsername()));

        //check whether order in database
        if (orderRepository.findById(dto.getId()).isEmpty()) {
            throw new OrderNotFoundException(dto.getId());
        }
        //convert dto into entity and update
        OrderEntity savedEntity = orderRepository.save(mapper.convertDtoToEntity(dto, user));

        //convert entity into dto and return
        return mapper.convertEntityToDto(savedEntity);
    }
}
