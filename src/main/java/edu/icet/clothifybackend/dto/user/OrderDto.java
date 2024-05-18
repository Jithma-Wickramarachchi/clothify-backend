package edu.icet.clothifybackend.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    @NotBlank(message = "username cannot be blank or null")
    private String username;
}