package edu.icet.clothifybackend.controller;

import edu.icet.clothifybackend.dto.ItemDto;
import edu.icet.clothifybackend.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/item")
@RequiredArgsConstructor
@Slf4j
public class ItemController {
    private final ItemService service;

    @PostMapping
    public ResponseEntity<ItemDto> createItem(@Valid @RequestBody ItemDto dto){
        return new ResponseEntity<>(service.createItem(dto), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getItemByItemId(@PathVariable Long id){
        return new ResponseEntity<>(service.getItemByItemId(id), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<ItemDto>> getAllItems(){
        return new ResponseEntity<>(service.getAllItems(), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItemById(@PathVariable Long id){
        service.deleteItemById(id);
        return new ResponseEntity<>("Item("+id+") has been deleted successfully!", HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<ItemDto> updateItem(@Valid @RequestBody ItemDto dto){
        return new ResponseEntity<>(service.updateItem(dto), HttpStatus.OK);
    }
}
