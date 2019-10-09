package com.freshappbooks.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.freshappbooks.MovieAdapter;
import com.freshappbooks.movies.data.Movie;
import com.freshappbooks.movies.utils.JSONUtils;
import com.freshappbooks.movies.utils.NetworkUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MyApp";
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private Switch swichSort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swichSort = findViewById(R.id.switch_sort);
        recyclerView = findViewById(R.id.recycler_view);
        movieAdapter  = new MovieAdapter();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(movieAdapter);
        swichSort.setChecked(true);
        swichSort.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int methodOfSort;
                if (isChecked) {
                    methodOfSort = NetworkUtils.TOP_RATED;
                } else {
                    methodOfSort = NetworkUtils.POPULARITY;
                }
                JSONObject jsonObject = NetworkUtils.getJSONFromNetwork(methodOfSort, 1);
                ArrayList<Movie> movies = JSONUtils.getMoviesFromJSON(jsonObject);
                movieAdapter.setMovies(movies);
            }
        });
        swichSort.setChecked(false);


    }
}
