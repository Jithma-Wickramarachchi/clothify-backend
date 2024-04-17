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
    @NotNull
    @NotBlank
    private String companyName;
    @NotNull
    @Positive
    private Integer initialItemCount;
    @NotNull
    @Positive
    private Integer availableItemCount;
    @NotNull
    private LocalDate date;
}
