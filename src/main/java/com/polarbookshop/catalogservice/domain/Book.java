package com.polarbookshop.catalogservice.domain;

import com.polarbookshop.catalogservice.entity.BookEntity;
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
        @NotBlank(message = "{book.publisher.not-blank}")
        String publisher,
        @Positive(message = "{book.price.positive}")
        Double price
) {

        public BookEntity toEntity() {
                return BookEntity.of(isbn, title, author, publisher, price);
        }
}
