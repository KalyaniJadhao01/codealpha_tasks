package com.example.urlshortener.controller;

import com.example.urlshortener.dto.CreateUrlRequest;
import com.example.urlshortener.dto.CreateUrlResponse;
import com.example.urlshortener.service.UrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import com.example.urlshortener.dto.UrlAnalyticsResponse;
import org.springframework.stereotype.Controller;

@RestController
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @GetMapping("/")
    public RedirectView home(){

        return new RedirectView(
                "/index.html"
        );

    }


    @PostMapping("/api/urls")
    public CreateUrlResponse createShortUrl(

            @Valid
            @RequestBody
            CreateUrlRequest request

    ){

        return urlService.createShortUrl(
                request
        );

    }


    @GetMapping("/r/{shortCode}")
    public RedirectView redirect(

            @PathVariable
            String shortCode

    ){

        String originalUrl =
                urlService.getOriginalUrl(
                        shortCode
                );

        RedirectView redirectView =
                new RedirectView();

        redirectView.setUrl(
                originalUrl
        );

        return redirectView;

    }

    @GetMapping("/api/urls/{shortCode}/analytics")
    public UrlAnalyticsResponse analytics(

            @PathVariable
            String shortCode

    ){

        return urlService.getAnalytics(
                shortCode
        );

    }

}