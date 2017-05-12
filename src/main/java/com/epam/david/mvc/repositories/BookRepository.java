package com.epam.david.mvc.repositories;

import com.epam.david.mvc.entities.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by David_Chaava on 5/12/2017.
 */
public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByAuthor(String author);

    Book findByName(String name);

//    @Query("SELECT b FORM books b WHERE b.author = :author")
//    Stream<Book> findByAuthorReturnStream(@Param("author") String author);
}
