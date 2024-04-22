package edu.icet.clothifybackend.service;

import edu.icet.clothifybackend.dto.StockDto;

import java.util.List;
import java.util.Optional;

public interface StockService {
    StockDto saveStock(StockDto stock);
    StockDto getStockByStockId(Long stockId);
    List<StockDto> getAllStocks();
    void deleteStockById(Long stockId);
    Optional<StockDto> updateStock(StockDto stockDto);
}
