package com.polarbookshop.catalogservice.domain;


import static org.assertj.core.api.Assertions.assertThat;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BookValidationTests {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    @DisplayName("book validation - 성공")
    void validateBooks() {
        Book book = new Book(
                "1234567890",
                "title",
                "author",
                20.22
        );

        Set<ConstraintViolation<Book>> validated = validator.validate(book);
        assertThat(validated).hasSize(0);
    }

    @Test
    @DisplayName("book validation - 실패 - ISBN pattern, not blank 이다")
    void validateIsbnPattern() {
        Book book = new Book(
                "111",
                "title",
                "author",
                22.0
        );

        Set<ConstraintViolation<Book>> validated = validator.validate(book);

        assertThat(validated).hasSize(1);
        assertThat(validated.iterator().next().getMessage()).isEqualTo("{book.isbn.invalid}");
    }
}
