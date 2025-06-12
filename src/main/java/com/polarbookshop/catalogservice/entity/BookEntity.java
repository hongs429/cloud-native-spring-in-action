package com.polarbookshop.catalogservice.entity;

import java.time.Instant;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "book")
public record BookEntity(
        @Id
        Long id,

        String isbn,

        String title,

        String author,

        Double price,

        @CreatedDate
        Instant createdDate,

        @LastModifiedDate
        Instant lastModifiedDate,

        @Version
        Long version
) {

    public static BookEntity of(String isbn, String title, String author, Double price) {
        return new BookEntity(
                null, isbn, title, author, price, null, null, null
        );
    }
}
