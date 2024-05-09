package edu.icet.clothifybackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cardHolderName;
    private String cardNumber;
    private String expireDate;

    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private String username;
}
