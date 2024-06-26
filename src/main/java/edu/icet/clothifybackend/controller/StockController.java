package edu.icet.clothifybackend.controller;

import edu.icet.clothifybackend.dto.StockDto;
import edu.icet.clothifybackend.service.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stock")
@RequiredArgsConstructor
@Slf4j
public class StockController {
    private final StockService service;

    @PostMapping
    public ResponseEntity<StockDto> createStock(@Valid @RequestBody StockDto dto){
        return new ResponseEntity<>(service.saveStock(dto), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<StockDto> getStockByStockId(@PathVariable Long id){
        return new ResponseEntity<>(service.getStockByStockId(id), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<StockDto>> getAllStocks(){
        return new ResponseEntity<>(service.getAllStocks(), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStockById(@PathVariable Long id){
        Long deletedStockId = service.deleteStockById(id);
        return new ResponseEntity<>("Stock("+deletedStockId+") has been deleted successfully!", HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<StockDto> updateStock(@Valid @RequestBody StockDto dto){
        return new ResponseEntity<>(service.updateStock(dto), HttpStatus.OK);
    }
}