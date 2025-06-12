package com.polarbookshop.catalogservice.persistence;

import com.polarbookshop.catalogservice.entity.BookEntity;
import java.util.Optional;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface BookRepository extends CrudRepository<BookEntity, Long> {

    Optional<BookEntity> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);

    // Data jdbc에서는 기본적으로 데이터의 변경을 일으키는 작업에 대해서 @Modifying 어노테이션이 필수이다.
    @Modifying
    @Transactional
    @Query("delete from book where isbn = :isbn")
    void deleteByIsbn(String isbn);
}
