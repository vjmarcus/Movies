package com.freshappbooks.movies.data;


import androidx.room.Entity;

@Entity(tableName = "favorite_movies")
public class FavouriteMovie extends Movie{
    public FavouriteMovie(int id, int voteCount, String title, String originTitle, String overview, String posterPath, String bigPosterPath, String backDropPath, double voteAverage, String releaseDate) {
        super(id, voteCount, title, originTitle, overview, posterPath, bigPosterPath, backDropPath, voteAverage, releaseDate);
    }
}
