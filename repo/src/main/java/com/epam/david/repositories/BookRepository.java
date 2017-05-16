package com.epam.david.repositories;

import com.epam.david.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by David_Chaava on 5/12/2017.
 */
public interface BookRepository extends CrudRepository<Book, Long>, JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    List<Book> findByAuthorName(String author);

    Book findByName(String name);
}
