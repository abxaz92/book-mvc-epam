package com.epam.david.repositories;

import com.epam.david.model.Author;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by David_Chaava on 5/16/2017.
 */
public interface AuthorRepository extends CrudRepository<Author, Long> {
}
