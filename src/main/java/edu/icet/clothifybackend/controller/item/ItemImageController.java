package edu.icet.clothifybackend.controller.item;

import edu.icet.clothifybackend.dto.item.ItemImageSaveDto;
import edu.icet.clothifybackend.service.item.ItemImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/item/image")
public class ItemImageController {
    private final ItemImageService service;
    @PostMapping("/{itemId}")
    public ResponseEntity<String> saveItemImage(@RequestParam("file") MultipartFile file, @PathVariable Long itemId) throws IOException {
        ItemImageSaveDto dto = ItemImageSaveDto.builder()
                .itemId(itemId)
                .file(file)
                .build();
        return ResponseEntity.ok("Item image saved successfully! id:"+service.saveImage(dto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> retrieveItemImage(@PathVariable Long id) throws IOException {
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(service.retrieveImage(id).getData());
    }
}
