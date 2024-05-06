package edu.icet.clothifybackend.repository;

import edu.icet.clothifybackend.entity.ItemImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ItemImageRepository extends JpaRepository<ItemImageEntity, Long> {
    @Query(value = "SELECT * FROM item_image WHERE item_id = :id", nativeQuery = true)
    public Optional<ItemImageEntity> findItemImageByItemId(@Param("id") Long id);
}