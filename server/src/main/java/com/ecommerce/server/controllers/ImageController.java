package com.ecommerce.server.controllers;

import com.ecommerce.server.entities.Image;
import com.ecommerce.server.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<Image> saveImage(
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        Image savedImage = imageService.saveImage(file);
        return new ResponseEntity<>(savedImage, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getImage(@PathVariable Long id) throws IOException {
        Resource image = imageService.loadImage(id);
        String contentType = imageService.getContentType(image);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType != null ? contentType : "application/octet-stream"))
                .body(image);
    }
}
