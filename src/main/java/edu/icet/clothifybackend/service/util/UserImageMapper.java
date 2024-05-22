package edu.icet.clothifybackend.service.util;

import edu.icet.clothifybackend.dto.user.UserImageRetrieveDto;
import edu.icet.clothifybackend.dto.user.UserImageSaveDto;
import edu.icet.clothifybackend.entity.user.User;
import edu.icet.clothifybackend.entity.user.UserImageEntity;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class UserImageMapper {
    public UserImageRetrieveDto convertEntityToDto(UserImageEntity imageEntity){
        return UserImageRetrieveDto.builder()
                .id(imageEntity.getId())
                .data(imageEntity.getData())
                .build();
    }

    public UserImageEntity convertDtoToEntity(UserImageSaveDto imageDto, User user) throws IOException {
        return UserImageEntity.builder()
                .name(imageDto.getFile().getName())
                .data(imageDto.getFile().getBytes())
                .user(user)
                .build();
    }
}
