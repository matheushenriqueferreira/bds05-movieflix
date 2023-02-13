package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.projections.MovieMinProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT obj " +
            "FROM Movie obj " +
            "INNER JOIN obj.genre genre " +
            "WHERE :genreId IS NULL OR genre.id = :genreId")
    Page<MovieMinProjection> findByGenre(Long genreId, Pageable pageable);
}
