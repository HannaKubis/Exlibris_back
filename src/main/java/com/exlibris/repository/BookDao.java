package com.exlibris.repository;

import com.exlibris.domain.model.book.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface BookDao extends CrudRepository<Book, Integer> {

    @Override
    List<Book> findAll();

    @Override
    Book save(Book book);

    void deleteById(int id);

    Book findById(int id);

    @Query
    List<Book> getBooks(@Param("USER") long userId);
}
