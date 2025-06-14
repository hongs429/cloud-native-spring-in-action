package com.polarbookshop.catalogservice.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.entity.BookEntity;
import com.polarbookshop.catalogservice.persistence.BookRepository;
import com.polarbookshop.catalogservice.util.IntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;


@IntegrationTest
public class CatalogServiceIntegrationTests {

    @Autowired
    private WebTestClient webClient;

    @Autowired
    private BookRepository bookRepository;

    @AfterEach
    void afterEach() {
        bookRepository.deleteAll();
    }

    @Test
    void addBookToCatalog() {
        Book expectedBook = new Book(
                "1231231231",
                "title",
                "author",
                "publisher",
                20.22
        );

        webClient.post()
                .uri("/books")
                .body(Mono.just(expectedBook), Book.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(BookEntity.class).value(book -> {
                    assertThat(book).isNotNull();
                    assertThat(book.isbn()).isEqualTo(expectedBook.isbn());
                });
    }
}
