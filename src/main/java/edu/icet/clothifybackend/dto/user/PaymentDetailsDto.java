package edu.icet.clothifybackend.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetailsDto {
    private Long id;

    @NotBlank(message = "card holder's name cannot be blank or null")
    private String cardHolderName;

    @NotBlank(message = "card number cannot be blank or null")
    private String cardNumber;

    @NotBlank(message = "expire date cannot be blank or null")
    private String expireDate;

    @NotBlank(message = "username cannot be blank or null")
    private String username;
}