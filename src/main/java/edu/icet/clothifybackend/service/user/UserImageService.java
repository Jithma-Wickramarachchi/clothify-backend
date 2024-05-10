package edu.icet.clothifybackend.service.user;

import edu.icet.clothifybackend.dto.user.UserImageRetrieveDto;
import edu.icet.clothifybackend.dto.user.UserImageSaveDto;

import java.io.IOException;

public interface UserImageService {
    Long saveImage(UserImageSaveDto dto) throws IOException;
    UserImageRetrieveDto retrieveImage(String username) throws IOException;

}
