package edu.icet.clothifybackend.service;

import edu.icet.clothifybackend.dto.StockDto;

import java.util.List;
import java.util.Optional;

public interface StockService {
    StockDto saveStock(StockDto stock);
    Optional<StockDto> getStockByStockId(Long stockId);
    List<StockDto> getAllStocks();
    Boolean deleteStockById(Long stockId);
    Optional<StockDto> updateStock(StockDto stockDto);
}
