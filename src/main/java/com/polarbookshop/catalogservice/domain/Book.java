package com.polarbookshop.catalogservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record Book(
        @NotBlank(message = "{book.isbn.not-blank}")
        @Pattern(regexp = "^([0-9]{10}|[0-9]{13})$", message = "{book.isbn.invalid}")
        String isbn,
        @NotBlank(message = "{book.title.not-blank}")
        String title,
        @NotBlank(message = "{book.author.not-blank}")
        String author,
        @Positive(message = "{book.price.positive}")
        Double price
) {
}
