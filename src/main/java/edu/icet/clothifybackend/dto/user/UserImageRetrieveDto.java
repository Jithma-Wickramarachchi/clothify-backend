package edu.icet.clothifybackend.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserImageRetrieveDto {
    private Long id;
    private byte[] data;
}
