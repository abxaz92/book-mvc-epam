package com.epam.david.repositories;

import com.epam.david.model.Genre;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by David_Chaava on 5/16/2017.
 */
public interface GenreRepository extends CrudRepository<Genre, Long> {
}
