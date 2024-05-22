package edu.icet.clothifybackend.dto.user;

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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderedItemDto {
    private Long id;

    @NotBlank(message = "name cannot be blank or null")
    private String name;

    @NotBlank(message = "description cannot be blank or null")
    private String description;

    @NotNull(message = "price cannot be null")
    @Positive(message = "price should be a positive number")
    private Double price;

    @NotNull(message = "category cannot be null")
    private Category category;

    @NotNull(message = "count cannot be null")
    @Positive(message = "count should be a positive number")
    private Integer count;

    @NotNull(message = "size cannot be null")
    private Size size;

    @NotNull(message = "orderId cannot be null")
    @Positive(message = "orderId should be a positive number")
    private Long orderId;
}
