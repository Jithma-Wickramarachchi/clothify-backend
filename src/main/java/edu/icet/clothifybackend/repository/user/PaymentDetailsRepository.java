package edu.icet.clothifybackend.repository.user;

import edu.icet.clothifybackend.entity.user.PaymentDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentDetailsRepository extends JpaRepository<PaymentDetailsEntity, Long> {
    @Query(value = "SELECT * FROM payment_details WHERE username = :username", nativeQuery = true)
    List<PaymentDetailsEntity> getPaymentDetailsListByUsername(@Param("username")String username);
}
