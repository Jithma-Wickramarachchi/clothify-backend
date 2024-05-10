package edu.icet.clothifybackend.repository.user;

import edu.icet.clothifybackend.entity.user.UserImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserImageRepository extends JpaRepository<UserImageEntity, Long> {
    @Query(value = "SELECT * FROM user_image WHERE username = :username", nativeQuery = true)
    Optional<UserImageEntity> findItemImageByUsername(@Param("username") String username);
}