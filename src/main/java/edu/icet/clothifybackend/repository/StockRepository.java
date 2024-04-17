package edu.icet.clothifybackend.repository;

import edu.icet.clothifybackend.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository< StockEntity, Long> {
}
