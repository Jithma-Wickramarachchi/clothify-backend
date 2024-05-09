package edu.icet.clothifybackend.repository;

import edu.icet.clothifybackend.entity.AddressEntity;
import edu.icet.clothifybackend.entity.PaymentDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentDetailsRepository extends JpaRepository<PaymentDetailsEntity, Long> {
    @Query(value = "SELECT * FROM payment_details WHERE username = :username", nativeQuery = true)
    List<AddressEntity> getPaymentDetailsListByUsername(@Param("username")String username);
}
