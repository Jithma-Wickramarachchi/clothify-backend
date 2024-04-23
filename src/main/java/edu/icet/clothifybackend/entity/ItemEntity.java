package edu.icet.clothifybackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "item_id")
    private Long itemId;
    private String name;
    private String description;
    private Double price;
    private Category category;
    private Size size;
    private Integer count;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "stock",nullable = false)
    private StockEntity stock;
}
