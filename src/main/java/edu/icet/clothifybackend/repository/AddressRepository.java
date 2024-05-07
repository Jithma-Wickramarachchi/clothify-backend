package edu.icet.clothifybackend.repository;

import edu.icet.clothifybackend.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    @Query(value = "SELECT * FROM address WHERE user_id = :id", nativeQuery = true)
    List<AddressEntity> getAddressListByUserId(@Param("id")Long id);
}
