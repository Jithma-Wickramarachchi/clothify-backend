package edu.icet.clothifybackend.dto.order;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;

    @NotNull(message = "total cost cannot be null")
    @Positive(message = "total cost should be positive")
    private Double totalCost;

    @NotNull(message = "user address id cannot be null")
    @Positive(message = "user address id should be positive")
    private Long userAddressId;

    @NotNull(message = "payment details id cannot be null")
    @Positive(message = "payment details id should be positive")
    private Long paymentDetailsId;

    @NotNull(message = "contact number id cannot be null")
    @Positive(message = "contact number id should be positive")
    private Long contactNumberId;

    @NotNull(message = "date cannot be null")
    private LocalDateTime date;

    @NotNull(message = "user id cannot be null")
    @Positive(message = "user id should be positive")
    private Long userId;
}