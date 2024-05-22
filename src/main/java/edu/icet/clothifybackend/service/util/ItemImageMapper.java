package edu.icet.clothifybackend.service.util;

import edu.icet.clothifybackend.dto.item.ItemImageRetrieveDto;
import edu.icet.clothifybackend.dto.item.ItemImageSaveDto;
import edu.icet.clothifybackend.entity.item.ItemEntity;
import edu.icet.clothifybackend.entity.item.ItemImageEntity;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
@Configuration
public class ItemImageMapper {
    public ItemImageRetrieveDto convertEntityToDto(ItemImageEntity imageEntity){
        return ItemImageRetrieveDto.builder()
                .data(imageEntity.getData())
                .build();
    }

    public ItemImageEntity convertDtoToEntity(ItemImageSaveDto imageDto, ItemEntity item) throws IOException {
        return ItemImageEntity.builder()
                .name(imageDto.getFile().getName())
                .data(imageDto.getFile().getBytes())
                .item(item)
                .build();
    }
}
