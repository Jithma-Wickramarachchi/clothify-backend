package edu.icet.clothifybackend.repository;

import edu.icet.clothifybackend.dto.StockDto;
import edu.icet.clothifybackend.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository< StockEntity, Long> {
    @Query(value = "SELECT * FROM stock WHERE stock_id = :id ", nativeQuery = true)
    Optional<StockEntity> getStockByStockId(@Param("id") Long stockId);
    @Query(value = "SELECT * FROM stock", nativeQuery = true)
    List<StockDto> getAllStocks();
}
