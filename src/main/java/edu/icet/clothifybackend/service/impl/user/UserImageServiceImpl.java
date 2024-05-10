package edu.icet.clothifybackend.service.impl.user;

import edu.icet.clothifybackend.dto.user.UserImageRetrieveDto;
import edu.icet.clothifybackend.dto.user.UserImageSaveDto;
import edu.icet.clothifybackend.entity.user.User;
import edu.icet.clothifybackend.entity.user.UserImageEntity;
import edu.icet.clothifybackend.exception.user.UserImageNotFoundException;
import edu.icet.clothifybackend.repository.user.UserImageRepository;
import edu.icet.clothifybackend.repository.user.UserRepository;
import edu.icet.clothifybackend.service.user.UserImageService;
import edu.icet.clothifybackend.service.util.UserImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
@RequiredArgsConstructor
public class UserImageServiceImpl implements UserImageService {
    private final UserRepository userRepository;
    private final UserImageRepository imageRepository;
    private final UserImageMapper mapper;
    @Override
    public Long saveImage(UserImageSaveDto dto) throws IOException {
        //Check that user is available
        User user = userRepository.findUserByUsername(dto.getUsername())
                .orElseThrow(() ->
                        new UsernameNotFoundException(dto.getUsername()));

        //Convert image to entity
        UserImageEntity entity = mapper.convertDtoToEntity(dto, user);

        //Save the entity and return its id.
        UserImageEntity savedEntity = imageRepository.save(entity);
        return savedEntity.getId();
    }

    @Override
    public UserImageRetrieveDto retrieveImage(String username) throws IOException {
        //Throws exception when image not found
        UserImageEntity entity = imageRepository.findItemImageByUsername(username)
                .orElseThrow(() -> new UserImageNotFoundException(username));

        //Return dto within the image
        return mapper.convertEntityToDto(entity);
    }
}
