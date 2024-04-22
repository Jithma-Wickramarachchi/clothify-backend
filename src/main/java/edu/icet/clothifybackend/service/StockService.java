package edu.icet.clothifybackend.service;

import edu.icet.clothifybackend.dto.StockDto;

import java.util.List;

public interface StockService {
    StockDto saveStock(StockDto stock);
    StockDto getStockByStockId(Long stockId);
    List<StockDto> getAllStocks();
    void deleteStockById(Long stockId);
    StockDto updateStock(StockDto stockDto);
}
