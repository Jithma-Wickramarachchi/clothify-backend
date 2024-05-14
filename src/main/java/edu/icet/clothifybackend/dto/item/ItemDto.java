package edu.icet.clothifybackend.dto.item;

import edu.icet.clothifybackend.util.Category;
import edu.icet.clothifybackend.util.Size;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @NotBlank(message = "item name cannot be blank or null")
    private String name;

    @NotBlank(message = "item description cannot be blank or null")
    private String description;

    @NotNull(message = "item price cannot be null")
    @Positive(message = "item price should be positive number")
    private Double price;

    @NotNull(message = "item category cannot be blank or null")
    private Category category;

    @NotNull(message = "item size cannot be null")
    @Positive(message = "item size should be positive number")
    private Size size;

    @NotNull(message = "item count cannot be null")
    @Positive(message = "item count should be positive number")
    private Integer count;

    @NotNull(message = "stockId cannot be null")
    private Long stockId;
}
