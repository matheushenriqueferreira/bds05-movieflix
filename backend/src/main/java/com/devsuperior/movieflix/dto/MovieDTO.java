package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.entities.Movie;

public class MovieDTO {
    private Long id;
    private String title;
    private String subtitle;
    private Integer year;
    private String imgUrl;
    private String synopsis;

    private GenreDTO genre;

    public MovieDTO() {
    }

    public MovieDTO(Long id, String title, String subtitle, Integer year, String imgUrl, String synopsis, GenreDTO genre) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.year = year;
        this.imgUrl = imgUrl;
        this.synopsis = synopsis;
        this.genre = genre;
    }

    public MovieDTO(Movie entity, GenreDTO genreDto) {
        id = entity.getId();
        title = entity.getTitle();
        subtitle = entity.getSubTitle();
        year = entity.getYear();
        imgUrl = entity.getImgUrl();
        synopsis = entity.getSynopsis();
        genre = genreDto;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public Integer getYear() {
        return year;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public GenreDTO getGenre() {
        return genre;
    }
}
