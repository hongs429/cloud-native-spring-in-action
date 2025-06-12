package com.polarbookshop.catalogservice.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import com.polarbookshop.catalogservice.config.DataConfig;
import com.polarbookshop.catalogservice.entity.BookEntity;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;


@DataJdbcTest
@Import(DataConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class BookRepositoryJdbcTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JdbcAggregateTemplate aggregateTemplate;

    @Test
    void findBookByIsbnWhenExists() {
        String bookIsbn = "1231231231";
        BookEntity book = BookEntity.of(bookIsbn, "title", "author", 25.05);
        aggregateTemplate.insert(book);
        Optional<BookEntity> findBook = bookRepository.findByIsbn(bookIsbn);

        assertThat(findBook.isPresent()).isTrue();
        assertThat(findBook.get().isbn()).isEqualTo(bookIsbn);
    }

}