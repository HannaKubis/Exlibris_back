package com.exlibris.repository;

import com.exlibris.domain.model.book.Book;
import org.springframework.data.repository.CrudRepository;
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

    Book deleteById(int id);

    Book findById(int id);
}
