package edu.icet.clothifybackend.entity.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contact_number")
public class ContactNumberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "contact")
    private String contactNumber;

    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private User user;
}
