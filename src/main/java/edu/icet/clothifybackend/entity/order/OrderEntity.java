package edu.icet.clothifybackend.entity.order;

import edu.icet.clothifybackend.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "orders")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "total_cost")
    private Double totalCost;

    @Column(name = "user_address_id")
    private Long userAddressId;

    @Column(name = "payment_details_id")
    private Long paymentDetailsId;

    @Column(name = "contact_number_id")
    private Long contactNumberId;

    private LocalDateTime date;

    @JoinColumn(name = "user", nullable = false)
    private User user;
}
