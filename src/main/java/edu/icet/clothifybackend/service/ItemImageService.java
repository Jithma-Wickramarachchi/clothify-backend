package edu.icet.clothifybackend.service;

import edu.icet.clothifybackend.dto.ItemImageRetrieveDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ItemImageService {
    Long saveImage(MultipartFile file) throws IOException;
    ItemImageRetrieveDto retrieveImage(Long id) throws IOException;

}
