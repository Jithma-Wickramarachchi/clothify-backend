package edu.icet.clothifybackend.controller.user;

import edu.icet.clothifybackend.dto.user.UserImageSaveDto;
import edu.icet.clothifybackend.service.user.UserImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/image")
public class UserImageController {
    private final UserImageService service;
    @PostMapping("/{username}")
    public ResponseEntity<String> saveImage(@RequestParam("file") MultipartFile file,
                                            @PathVariable String username ) throws IOException {
        UserImageSaveDto dto = UserImageSaveDto.builder()
                .username(username)
                .file(file)
                .build();
        return ResponseEntity.ok("User image saved successfully! id:"+service.saveImage(dto));
    }
    @GetMapping("/{username}")
    public ResponseEntity<byte[]> retrieveItemImage(@PathVariable String username) throws IOException {
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(service.retrieveImage(username).getData());
    }
}
