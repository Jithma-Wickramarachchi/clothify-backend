package edu.icet.clothifybackend.repository.user;

import edu.icet.clothifybackend.entity.user.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    @Query(value = "SELECT * FROM orders WHERE username = :username", nativeQuery = true)
    List<OrderEntity> getOrderListByUsername(String username);
}
