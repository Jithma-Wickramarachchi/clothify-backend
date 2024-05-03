package edu.icet.clothifybackend.repository;

import edu.icet.clothifybackend.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemEntity,Long> {
    @Query(value = "SELECT * FROM item WHERE item_id = :id ", nativeQuery = true)
    Optional<ItemEntity> getItemByItemId(@Param("id") Long itemId);
    @Query(value = "SELECT * FROM item", nativeQuery = true)
    List<ItemEntity> getAllItems();

}
