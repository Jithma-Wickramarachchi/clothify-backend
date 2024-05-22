
package edu.icet.clothifybackend.service.impl.item;

import edu.icet.clothifybackend.dto.item.ItemImageRetrieveDto;
import edu.icet.clothifybackend.dto.item.ItemImageSaveDto;
import edu.icet.clothifybackend.entity.item.ItemEntity;
import edu.icet.clothifybackend.entity.item.ItemImageEntity;
import edu.icet.clothifybackend.exception.item.ItemIdNotFoundException;
import edu.icet.clothifybackend.exception.item.ItemImageNotFoundException;
import edu.icet.clothifybackend.repository.item.ItemImageRepository;
import edu.icet.clothifybackend.repository.item.ItemRepository;
import edu.icet.clothifybackend.service.item.ItemImageService;
import edu.icet.clothifybackend.service.util.ItemImageMapper;
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
    private final ItemImageMapper mapper;

    @Override
    public Long saveImage(ItemImageSaveDto dto) throws IOException {
        //Check that itemId is available
        ItemEntity itemEntity = itemRepository.getItemByItemId(dto.getItemId())
                .orElseThrow(() ->
                        new ItemIdNotFoundException(dto.getItemId()));

        //Convert image to entity
        ItemImageEntity entity = mapper.convertDtoToEntity(dto, itemEntity);

        //Save the entity and return its id.
        ItemImageEntity savedEntity = imageRepository.save(entity);
        return savedEntity.getId();
    }

    @Override
    public ItemImageRetrieveDto retrieveImage(Long id) {
        //Throws exception when image not found
        ItemImageEntity entity = imageRepository.findItemImageByItemId(id)
                .orElseThrow(() -> new ItemImageNotFoundException(id));

        //Return dto within the image
        return mapper.convertEntityToDto(entity);
    }
}
