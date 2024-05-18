package edu.icet.clothifybackend.controller.user;

import edu.icet.clothifybackend.dto.user.OrderDto;
import edu.icet.clothifybackend.service.user.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/order")
public class OrderController {
    private final OrderService service;
    @PostMapping
    public ResponseEntity<OrderDto> saveOrder(@Valid @RequestBody OrderDto dto){
        return new ResponseEntity<>(service.saveOrder(dto), HttpStatus.CREATED);
    }
    @GetMapping("/{username}")
    public ResponseEntity<List<OrderDto>> getOrderListByUsername(@PathVariable String username){
        return new ResponseEntity<>(service.getOrderListByUsername(username), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id){
        Long deletedOrderId = service.deleteOrder(id);
        return new ResponseEntity<>("Order("+deletedOrderId+") has been deleted successfully!", HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<OrderDto> updateOrder(@Valid @RequestBody OrderDto dto){
        return new ResponseEntity<>(service.updateOrder(dto), HttpStatus.OK);
    }
}
