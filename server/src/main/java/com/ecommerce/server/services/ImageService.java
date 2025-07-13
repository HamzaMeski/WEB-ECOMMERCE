package com.ecommerce.server.services;

import com.ecommerce.server.entities.Image;
import com.ecommerce.server.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;


@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final Path uploadDir;

    // Constructor injection of upload path from config
    public ImageService(ImageRepository imageRepository,
                        @Value("${application.uploads.dir}") String uploadPath) {
        this.imageRepository = imageRepository;
        this.uploadDir = Paths.get(uploadPath);
    }

    public Image saveImage(MultipartFile file) throws IOException {
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = uploadDir.resolve(filename);

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        Image image = new Image();
        image.setFilename(filename);
        image.setOriginalFilename(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());

        return imageRepository.save(image);
    }

    public Resource loadImage(Long id) throws IOException {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new FileNotFoundException("Image not found with ID: " + id));

        Path filePath = uploadDir.resolve(image.getFilename());
        Resource resource = new UrlResource(filePath.toUri());

        if (!resource.exists()) {
            throw new FileNotFoundException("File not found on disk: " + image.getFilename());
        }

        return resource;
    }

    public String getContentType(Resource resource) throws IOException {
        Path filePath = uploadDir.resolve(((UrlResource) resource).getFilename());
        return Files.probeContentType(filePath);
    }
}