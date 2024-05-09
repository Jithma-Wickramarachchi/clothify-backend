package edu.icet.clothifybackend.dto;

import edu.icet.clothifybackend.util.Role;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @Id
    private String username;
    private String password;
    private Role role;
}
