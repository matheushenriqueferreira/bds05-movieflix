package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.projections.MovieMinProjection;

public class MovieMinDTO {
    private String title;
    private String subtitle;
    private Integer year;
    private String imgUrl;

    public MovieMinDTO() {
    }

    public MovieMinDTO(String title, String subtitle, Integer year, String imgUrl) {
        this.title = title;
        this.subtitle = subtitle;
        this.year = year;
        this.imgUrl = imgUrl;
    }

    public MovieMinDTO(MovieMinProjection projection) {
        title = projection.getTitle();
        subtitle = projection.getSubTitle();
        year = projection.getYear();
        imgUrl = projection.getImgUrl();
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
}
