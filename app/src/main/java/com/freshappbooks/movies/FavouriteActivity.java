package com.freshappbooks.movies;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.freshappbooks.movies.data.FavouriteMovie;
import com.freshappbooks.movies.data.MainViewModel;
import com.freshappbooks.movies.data.Movie;

import java.util.ArrayList;
import java.util.List;

public class FavouriteActivity extends AppCompatActivity {

    private RecyclerView recyclerViewFavouriteMovies;
    private MovieAdapter movieAdapter;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        recyclerViewFavouriteMovies = findViewById(R.id.recyclerView_favourite_movies);
        recyclerViewFavouriteMovies.setLayoutManager(new GridLayoutManager(this, 2));
        movieAdapter = new MovieAdapter();
        recyclerViewFavouriteMovies.setAdapter(movieAdapter);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        LiveData<List<FavouriteMovie>> favouriteMovies = viewModel.getFavouriteMovies();
        favouriteMovies.observe(this, new Observer<List<FavouriteMovie>>() {
            @Override
            public void onChanged(List<FavouriteMovie> favouriteMovies) {
                List <Movie> movies = new ArrayList<>();
                movies.addAll(favouriteMovies);
                movieAdapter.setMovies(movies);
            }
        });
    }
}
