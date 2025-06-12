package com.polarbookshop.catalogservice.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.entity.BookEntity;
import com.polarbookshop.catalogservice.persistence.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest(
        webEnvironment = WebEnvironment.RANDOM_PORT // 완전한 스프링 웹 애플리케이션 콘텍스트와 임의의 포트를 듣는 서블릿 컨테이너를 로드
)
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
