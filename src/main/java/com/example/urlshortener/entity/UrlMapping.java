package com.example.urlshortener.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="url_mapping")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UrlMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false,columnDefinition="TEXT")
    private String originalUrl;

    @Column(nullable=false,unique=true)
    private String shortCode;

    private LocalDateTime createdAt;

    private LocalDateTime expiryDate;

    private Long clickCount;

}