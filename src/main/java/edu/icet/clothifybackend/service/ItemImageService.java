package edu.icet.clothifybackend.service;

import edu.icet.clothifybackend.dto.ItemImageRetrieveDto;
import edu.icet.clothifybackend.dto.ItemImageSaveDto;

import java.io.IOException;

public interface ItemImageService {
    Long saveImage(ItemImageSaveDto dto) throws IOException;
    ItemImageRetrieveDto retrieveImage(Long id) throws IOException;

}
