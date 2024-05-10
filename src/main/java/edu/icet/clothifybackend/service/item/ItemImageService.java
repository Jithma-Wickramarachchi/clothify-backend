package edu.icet.clothifybackend.service.item;

import edu.icet.clothifybackend.dto.item.ItemImageRetrieveDto;
import edu.icet.clothifybackend.dto.item.ItemImageSaveDto;

import java.io.IOException;

public interface ItemImageService {
    Long saveImage(ItemImageSaveDto dto) throws IOException;
    ItemImageRetrieveDto retrieveImage(Long id) throws IOException;
}
