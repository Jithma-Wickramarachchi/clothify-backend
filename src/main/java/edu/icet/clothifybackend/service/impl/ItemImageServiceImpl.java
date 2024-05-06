
package edu.icet.clothifybackend.service.impl;

import edu.icet.clothifybackend.dto.ItemImageRetrieveDto;
import edu.icet.clothifybackend.dto.ItemImageSaveDto;
import edu.icet.clothifybackend.entity.ItemEntity;
import edu.icet.clothifybackend.entity.ItemImageEntity;
import edu.icet.clothifybackend.exception.ItemIdNotFoundException;
import edu.icet.clothifybackend.exception.ItemImageNotFoundException;
import edu.icet.clothifybackend.repository.ItemImageRepository;
import edu.icet.clothifybackend.repository.ItemRepository;
import edu.icet.clothifybackend.service.ItemImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemImageServiceImpl implements ItemImageService {
    private final ItemImageRepository imageRepository;
    private final ItemRepository itemRepository;

    @Override
    public Long saveImage(ItemImageSaveDto dto) throws IOException {
        log.info("itemImage service runs...");
        //Check that itemId is available
        ItemEntity itemEntity = itemRepository.getItemByItemId(dto.getItemId())
                .orElseThrow(() ->
                        new ItemIdNotFoundException(dto.getItemId()));

        //Convert image to byte[]
        ItemImageEntity entity = ItemImageEntity.builder()
                .item(itemEntity)
                .name(dto.getFile().getName())
                .data(dto.getFile().getBytes()).build();

        //Save the byte[] and return its id.
        ItemImageEntity savedEntity = imageRepository.save(entity);
        return savedEntity.getId();
    }

    @Override
    public ItemImageRetrieveDto retrieveImage(Long id) {
        //Throws exception when image not found
        ItemImageEntity entity = imageRepository.findItemImageByItemId(id)
                .orElseThrow(() -> new ItemImageNotFoundException(id));

        //Return byte[] of the image
        return ItemImageRetrieveDto.builder()
                .data(entity.getData())
                .build();
    }
}