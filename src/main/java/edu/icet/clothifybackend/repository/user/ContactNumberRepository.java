package edu.icet.clothifybackend.repository.user;

import edu.icet.clothifybackend.entity.user.AddressEntity;
import edu.icet.clothifybackend.entity.user.ContactNumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactNumberRepository extends JpaRepository<ContactNumberEntity, String> {
    @Query(value = "SELECT * FROM contact_number WHERE username = :username", nativeQuery = true)
    List<AddressEntity> getContactListByUsername(@Param("username")String username);
}
