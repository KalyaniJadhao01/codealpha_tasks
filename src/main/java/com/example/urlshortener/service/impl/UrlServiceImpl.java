package com.example.urlshortener.service.impl;

import com.example.urlshortener.dto.CreateUrlRequest;
import com.example.urlshortener.dto.CreateUrlResponse;
import com.example.urlshortener.dto.UrlAnalyticsResponse;
import com.example.urlshortener.entity.UrlMapping;
import com.example.urlshortener.exception.UrlNotFoundException;
import com.example.urlshortener.repository.UrlMappingRepository;
import com.example.urlshortener.service.UrlService;
import com.example.urlshortener.util.ShortCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {

    private final UrlMappingRepository repository;

    @Override
    public CreateUrlResponse createShortUrl(CreateUrlRequest request) {

        String code;

        // =========================
        // CUSTOM CODE HANDLING
        // =========================
        if (request.getCustomCode() != null && !request.getCustomCode().isBlank()) {

            code = request.getCustomCode().trim();

            if (repository.existsByShortCode(code)) {
                throw new IllegalArgumentException("Custom code already taken");
            }

        } else {
            // =========================
            // AUTO GENERATION
            // =========================
            do {
                code = ShortCodeGenerator.generateCode();
            } while (repository.existsByShortCode(code));
        }

        UrlMapping mapping = UrlMapping.builder()
                .originalUrl(request.getUrl())
                .shortCode(code)
                .createdAt(LocalDateTime.now())
                .expiryDate(request.getExpiryDate())
                .clickCount(0L)
                .build();

        repository.save(mapping);

        return CreateUrlResponse.builder()
                .originalUrl(mapping.getOriginalUrl())
                .shortCode(mapping.getShortCode())
                .shortUrl("http://localhost:8080/r/" + code)
                .build();
    }

    @Override
    public String getOriginalUrl(String shortCode) {

        UrlMapping mapping = repository.findByShortCode(shortCode)
                .orElseThrow(() -> new UrlNotFoundException("URL not found"));

        // =========================
        // EXPIRY CHECK
        // =========================
        if (mapping.getExpiryDate() != null
                && mapping.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("URL has expired");
        }

        mapping.setClickCount(mapping.getClickCount() + 1);
        repository.save(mapping);

        return mapping.getOriginalUrl();
    }

    @Override
    public UrlAnalyticsResponse getAnalytics(String shortCode) {

        UrlMapping mapping = repository.findByShortCode(shortCode)
                .orElseThrow(() -> new UrlNotFoundException("URL not found"));

        return UrlAnalyticsResponse.builder()
                .originalUrl(mapping.getOriginalUrl())
                .shortCode(mapping.getShortCode())
                .clickCount(mapping.getClickCount())
                .createdAt(mapping.getCreatedAt())
                .build();
    }
}