
package edu.icet.clothifybackend.service.impl;

import edu.icet.clothifybackend.dto.ItemImageRetrieveDto;
import edu.icet.clothifybackend.entity.ItemImageEntity;
import edu.icet.clothifybackend.exception.ItemImageNotFoundException;
import edu.icet.clothifybackend.repository.ItemImageRepository;
import edu.icet.clothifybackend.service.ItemImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ItemImageServiceImpl implements ItemImageService {
    private final ItemImageRepository repository;

    @Override
    public Long saveImage(MultipartFile file) throws IOException {
        //Convert image to byte[]
        ItemImageEntity entity = ItemImageEntity.builder()
                .name(file.getName())
                .data(file.getBytes()).build();

        //Save the byte[] and return its id.
        ItemImageEntity savedEntity = repository.save(entity);
        return savedEntity.getId();
    }

    @Override
    public ItemImageRetrieveDto retrieveImage(Long id){
        //Throws exception when image not found
        ItemImageEntity entity = repository.findById(id)
                .orElseThrow(()-> new ItemImageNotFoundException(id));

        //Return byte[] of the image
        return ItemImageRetrieveDto.builder()
                .data(entity.getData())
                .build();
    }
}
