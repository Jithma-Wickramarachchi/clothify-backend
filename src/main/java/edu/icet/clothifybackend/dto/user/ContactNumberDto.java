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
public class ContactNumberDto {

    @NotBlank(message = "contact number cannot be blank or null")
    private String contactNumber;

    @NotBlank(message = "username cannot be blank or null")
    private String username;
}
