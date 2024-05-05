package edu.icet.clothifybackend.controller;

import edu.icet.clothifybackend.service.ItemImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/item/image")
public class ItemImageController {
    private final ItemImageService service;
    @PostMapping
    public ResponseEntity<String> saveItemImage(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("controller posting works");
        return ResponseEntity.ok("Item image saved successfully! id:"+service.saveImage(file));
    }
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> retrieveItemImage(@PathVariable Long id) throws IOException {
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(service.retrieveImage(id).getData());
    }

}
