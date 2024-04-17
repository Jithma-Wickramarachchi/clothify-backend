package edu.icet.clothifybackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockDto {
    private Long stockId;

    @NotNull(message = "companyName cannot be null")
    @NotBlank(message = "companyName cannot be blank")
    private String companyName;

    @NotNull(message = "initial item count cannot be null")
    @Positive(message = "companyName should be positive number")
    private Integer initialItemCount;

    @NotNull(message = "available item count cannot be null")
    @Positive(message = "available item count should be positive number")
    private Integer availableItemCount;

    @NotNull(message = "date cannot be null")
    private LocalDate date;
}
