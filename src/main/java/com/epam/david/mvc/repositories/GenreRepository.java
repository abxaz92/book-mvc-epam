package com.epam.david.mvc.repositories;

import com.epam.david.mvc.entities.Genre;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by David_Chaava on 5/16/2017.
 */
public interface GenreRepository extends CrudRepository<Genre, Long> {
}
