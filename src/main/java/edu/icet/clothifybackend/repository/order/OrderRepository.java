package edu.icet.clothifybackend.repository.order;

import edu.icet.clothifybackend.entity.order.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    @Query(value = "SELECT * FROM orders WHERE username = :username", nativeQuery = true)
    Optional<List<OrderEntity>> getOrderListByUsername(String username);
}
