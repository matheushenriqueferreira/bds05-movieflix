package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.*;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Transactional(readOnly = true)
    public MovieDTO findById(Long id) {
        Movie entity = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        Genre genre = genreRepository.getReferenceById(entity.getGenre().getId());
        return new MovieDTO(entity, new GenreDTO(genre));
    }

    @Transactional(readOnly = true)
    public Page<MovieMinDTO> findByGenre(Long genreId, Pageable pageable) {
       PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("Title"));
       Page<Movie> result = genreId == 0 ? movieRepository.findAll(pageRequest) : movieRepository.findByGenre(genreId, pageable);
       return result.map(movie -> new MovieMinDTO(movie));
    }

    @Transactional(readOnly = true)
    public List<ReviewDTO> findMovieWithReviews(Long id) {
        List<Review> list = reviewRepository.findMovieWithReviews(id);
        return list.stream().map(review -> new ReviewDTO(review, review.getMovie().getId(), new UserDTO(review.getUser()))).collect(Collectors.toList());
    }

}
