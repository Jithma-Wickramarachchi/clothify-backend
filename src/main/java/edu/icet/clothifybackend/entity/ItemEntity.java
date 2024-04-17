package edu.icet.clothifybackend.entity;

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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Item")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemId;
    private String name;
    private String description;
    private Double price;
    private Category category;
    private Size size;
    private Integer count;
    @Column(name = "stock_id")
    private Long stockId;
}
