package edu.icet.clothifybackend.controller.user;

import edu.icet.clothifybackend.dto.user.OrderedItemDto;
import edu.icet.clothifybackend.service.user.OrderedItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/ordered-item")
public class OrderedItemController {
    private final OrderedItemService service;
    @PostMapping
    public ResponseEntity<OrderedItemDto> saveOrderedItem(@Valid @RequestBody OrderedItemDto dto){
        return new ResponseEntity<>(service.saveOrderedItem(dto), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<OrderedItemDto>> getOrderedItemListByOrderId(@PathVariable Long id){
        return new ResponseEntity<>(service.getOrderedItemListByOrderId(id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderedItem(@PathVariable Long id){
        Long deletedOrderedItemId = service.deleteOrderedItem(id);
        return new ResponseEntity<>("OrderedItem("+deletedOrderedItemId+") has been deleted successfully!", HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<OrderedItemDto> updateOrderedItem(@Valid @RequestBody OrderedItemDto dto){
        return new ResponseEntity<>(service.updateOrderedItem(dto), HttpStatus.OK);
    }
}
