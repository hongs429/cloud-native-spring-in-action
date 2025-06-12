package com.polarbookshop.catalogservice.demo;

import com.polarbookshop.catalogservice.entity.BookEntity;
import com.polarbookshop.catalogservice.persistence.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("testdata")
@RequiredArgsConstructor
public class BookDataLoader {
    private final BookRepository bookRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
        bookRepository.deleteAll();
        BookEntity book1 = BookEntity.of("1231231231", "1Title", "1Author", "publisher1", 9.9);
        BookEntity book2 = BookEntity.of("1231231230", "2Title", "2Author", "publisher1", 10.1);

        bookRepository.save(book1);
        bookRepository.save(book2);

    }
}
