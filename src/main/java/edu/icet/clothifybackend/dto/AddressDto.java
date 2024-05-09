package edu.icet.clothifybackend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private Long id;

    @NotBlank(message = "house number cannot be blank or null")
    private String houseNumber;

    @NotBlank(message = "street cannot be blank or null")
    private String street;

    @NotBlank(message = "city cannot be blank or null")
    private String city;

    @NotBlank(message = "state cannot be blank or null")
    private String state;

    @NotBlank(message = "postal code cannot be blank or null")
    private String postalCode;

    @NotBlank(message = "username cannot be blank or null")
    private String username;
}
