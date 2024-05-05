package edu.icet.clothifybackend.repository;

import edu.icet.clothifybackend.entity.ItemImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemImageRepository extends JpaRepository<ItemImageEntity, Long> {
}
