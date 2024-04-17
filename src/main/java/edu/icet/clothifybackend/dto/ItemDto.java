package edu.icet.clothifybackend.dto;

import edu.icet.clothifybackend.util.Category;
import edu.icet.clothifybackend.util.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDto {
    private Long itemId;
    private String name;
    private String description;
    private Double price;
    private Category category;
    private Size size;
    private Integer count;
    private Long stockId;
}
