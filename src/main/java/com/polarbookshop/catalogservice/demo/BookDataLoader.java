package com.polarbookshop.catalogservice.demo;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookRepository;
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
        Book book1 = new Book("1231231231", "1Title", "1Author", 9.9);
        Book book2 = new Book("1231231230", "2Title", "2Author", 10.1);

        bookRepository.save(book1);
        bookRepository.save(book2);

    }
}
