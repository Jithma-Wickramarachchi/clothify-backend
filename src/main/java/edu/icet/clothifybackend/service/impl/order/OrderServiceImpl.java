package edu.icet.clothifybackend.service.impl.order;

import edu.icet.clothifybackend.dto.order.OrderDto;
import edu.icet.clothifybackend.entity.order.OrderEntity;
import edu.icet.clothifybackend.entity.user.User;
import edu.icet.clothifybackend.exception.user.UserNotFoundException;
import edu.icet.clothifybackend.repository.order.OrderRepository;
import edu.icet.clothifybackend.repository.user.UserRepository;
import edu.icet.clothifybackend.service.order.OrderService;
import edu.icet.clothifybackend.service.util.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public Long deleteOrder(Long id) {
        return null;
    }

    @Override
    public OrderDto updateOrder(OrderDto dto) {
        return null;
    }
}
