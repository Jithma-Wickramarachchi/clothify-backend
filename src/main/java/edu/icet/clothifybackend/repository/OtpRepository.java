package edu.icet.clothifybackend.repository;

import edu.icet.clothifybackend.entity.OtpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<OtpEntity, String> {
    @Query(value = "SELECT * FROM otp WHERE username = :username", nativeQuery = true)
    Optional<OtpEntity> getOtpByUsername(String username);
}
