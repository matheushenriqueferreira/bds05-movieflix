package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.DatabaseException;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private AuthService authService;

    @PreAuthorize("hasRole('MEMBER')")
    @Transactional
    public ReviewDTO insert(ReviewDTO dto) {
        try {
            Movie movieEntity = movieRepository.getReferenceById(dto.getMovieId());
            User user = authService.authenticated();
            Review entity = new Review(dto.getId(), dto.getText(), movieEntity, user);

            entity = repository.save(entity);

            return new ReviewDTO(entity, entity.getMovie().getId(), new UserDTO(entity.getUser()));
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation, movieId = " + dto.getMovieId() + " does not exist");
        }
    }
}
