package edu.icet.clothifybackend.repository;

import edu.icet.clothifybackend.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
}
