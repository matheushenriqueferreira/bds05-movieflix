package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieMinDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.projections.MovieMinProjection;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Transactional(readOnly = true)
    public MovieDTO findById(Long id) {
        Movie entity = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        Genre genre = genreRepository.getReferenceById(entity.getGenre().getId());
        return new MovieDTO(entity, new GenreDTO(genre));
    }

    @Transactional(readOnly = true)
    public Page<MovieMinDTO> findByGenre(Long genreId, Pageable pageable) {
       Page<MovieMinProjection> projections = movieRepository.findByGenre(genreId, pageable);
       return projections.map(projection -> new MovieMinDTO(projection));
    }
}
