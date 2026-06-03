package com.example.urlshortener.dto;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CreateUrlRequest {
    @NotBlank(message = "URL cannot be empty")

    @Pattern(regexp = "^(https?:\\/\\/).+",
            message = "Please provide valid URL starting with http:// or https://"
    )
    private String url;

    @Pattern(
            regexp = "^[a-zA-Z0-9_-]*$",
            message = "Custom code can only contain letters, numbers, _ or -"
    )
    // optional field (NEW)
    private String customCode;

    // optional expiry time
    private LocalDateTime expiryDate;
}
