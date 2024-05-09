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
@Table(name = "address")
public class AddressEntity {
    @Id
    private Long id;
    private String getHouseNumber;
    private String street;
    private String city;
    private String state;
    private String postalCode;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
