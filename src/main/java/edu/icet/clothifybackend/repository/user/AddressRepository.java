package edu.icet.clothifybackend.repository.user;

import edu.icet.clothifybackend.entity.user.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    @Query(value = "SELECT * FROM address WHERE username = :username", nativeQuery = true)
    List<AddressEntity> getAddressListByUsername(@Param("username")String username);
}
