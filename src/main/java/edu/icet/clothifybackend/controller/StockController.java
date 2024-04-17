package edu.icet.clothifybackend.controller;

import edu.icet.clothifybackend.dto.StockDto;
import edu.icet.clothifybackend.exception.StockIdNotFoundException;
import edu.icet.clothifybackend.service.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/stock")
@RequiredArgsConstructor
@Slf4j
public class StockController {
    private final StockService service;

    @PostMapping
    public ResponseEntity<StockDto> createStock(@Valid @RequestBody StockDto dto){
        StockDto savedDto = service.saveStock(dto);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<StockDto> getStockByStockId(@PathVariable Long id){
        StockDto dto = service.getStockByStockId(id)
                .orElseThrow(() -> new StockIdNotFoundException("Stock id not found : "+ id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<StockDto>> getAllStocks(){
        List<StockDto> dtoList = service.getAllStocks();
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStockById(@PathVariable Long id){

        return Boolean.TRUE.equals(service.deleteStockById(id)) ?
                new ResponseEntity<>("Stock("+id+") has been deleted successfully!", HttpStatus.OK) :
                new ResponseEntity<>("Sorry, Something went wrong!", HttpStatus.BAD_REQUEST);
    }
    @PutMapping
    public ResponseEntity<Optional<StockDto>> updateStock(@Valid @RequestBody StockDto dto){
        Optional<StockDto> updatedDto = service.updateStock(dto);
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }
}