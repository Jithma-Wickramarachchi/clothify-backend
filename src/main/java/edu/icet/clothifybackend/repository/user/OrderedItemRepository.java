package edu.icet.clothifybackend.repository.user;

import edu.icet.clothifybackend.entity.user.OrderedItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderedItemRepository extends JpaRepository<OrderedItemEntity, Long> {
    @Query(value = "SELECT * FROM ordered_item WHERE order_id = :id", nativeQuery = true)
    List<OrderedItemEntity> getOrderedItemListByOrderId(Long id);
}
