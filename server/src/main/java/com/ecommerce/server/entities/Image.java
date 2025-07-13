package com.ecommerce.server.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;

    private String originalFilename;

    private String contentType;

    private Long size;

    private LocalDateTime uploadedAt = LocalDateTime.now();

    @PrePersist
    private void onCreate() {
        uploadedAt  = LocalDateTime.now();
    }
}