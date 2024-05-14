package edu.icet.clothifybackend.entity;

import edu.icet.clothifybackend.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "otp")
@AllArgsConstructor
@NoArgsConstructor
public class OtpEntity {
    @Id
    private String username;

    @Column(nullable = false)
    private Integer otp;

    @Column(name = "expire_time", nullable = false)
    private LocalDateTime expirationTime;

    @OneToOne
    private User user;
}
