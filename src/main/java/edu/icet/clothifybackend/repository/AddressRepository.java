package edu.icet.clothifybackend.repository;

import edu.icet.clothifybackend.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    @Query(value = "SELECT * FROM address WHERE user_id = :id")
    List<AddressEntity> getAddressListByUserId(@Param("id")Long id);
}
