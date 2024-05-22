package edu.icet.clothifybackend.entity.user;

import edu.icet.clothifybackend.util.Category;
import edu.icet.clothifybackend.util.Size;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@Table(name = "ordered_item")
@AllArgsConstructor
@NoArgsConstructor
public class OrderedItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Category category;
    private Integer count;
    private Size size;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;
}
