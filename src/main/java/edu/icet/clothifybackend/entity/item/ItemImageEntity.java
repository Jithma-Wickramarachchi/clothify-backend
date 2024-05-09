package edu.icet.clothifybackend.entity.item;

import edu.icet.clothifybackend.entity.item.ItemEntity;
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
@Table(name = "item_image")
public class ItemImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] data;

    @OneToOne
    @JoinColumn(name = "item_id", nullable = false)
    private ItemEntity item;

}
