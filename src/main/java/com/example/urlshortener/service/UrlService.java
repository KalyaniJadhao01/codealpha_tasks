package com.example.urlshortener.service;

import com.example.urlshortener.dto.CreateUrlRequest;
import com.example.urlshortener.dto.CreateUrlResponse;
import com.example.urlshortener.dto.UrlAnalyticsResponse;

public interface UrlService {
    CreateUrlResponse createShortUrl(CreateUrlRequest request);
    String getOriginalUrl(String shortCode);

    UrlAnalyticsResponse getAnalytics(String shortCode);

}
