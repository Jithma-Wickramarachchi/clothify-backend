package edu.icet.clothifybackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Stock")
public class StockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "stock_id")
    private Long stockId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "initial_item_count")
    private Integer initialItemCount;

    @Column(name = "available_item_count")
    private Integer availableItemCount;

    private LocalDate date;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
   // @JoinColumn(name = "item_list")
    private List<ItemEntity> itemList;
}
