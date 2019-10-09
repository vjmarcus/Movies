package com.freshappbooks.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView textViewTopRated, textViewPopularity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPopularity = findViewById(R.id.textView_popularity);
        textViewTopRated = findViewById(R.id.textView_top_rated);
        swichSort = findViewById(R.id.switch_sort);
        recyclerView = findViewById(R.id.recycler_view);
        movieAdapter  = new MovieAdapter();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(movieAdapter);
        swichSort.setChecked(true);
        swichSort.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                methodOfSort(isChecked);
            }
        });
        swichSort.setChecked(false);
        movieAdapter.setOnPosterClickListener(new MovieAdapter.OnPosterClickListener() {
            @Override
            public void onPosterClick(int position) {
                Toast.makeText(MainActivity.this, "TOAST!!!", Toast.LENGTH_SHORT).show();
            }
        });
        movieAdapter.setOnReachAndListener(new MovieAdapter.OnReachAndListener() {
            @Override
            public void onReachEnd() {
                Toast.makeText(MainActivity.this, "GOGOOG!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void onClickSetPopularity(View view) {
        methodOfSort(false);
        swichSort.setChecked(false);
    }

    public void onClickSetTopRated(View view) {
        methodOfSort(true);
        swichSort.setChecked(true);
    }

    private void methodOfSort(boolean isTopRated) {
        int methodOfSort;
        if (isTopRated) {
            methodOfSort = NetworkUtils.TOP_RATED;
            textViewTopRated.setTextColor(getResources().getColor(R.color.colorAccent));
            textViewPopularity.setTextColor(getResources().getColor(R.color.colorWhite));
        } else {
            methodOfSort = NetworkUtils.POPULARITY;
            textViewPopularity.setTextColor(getResources().getColor(R.color.colorAccent));
            textViewTopRated.setTextColor(getResources().getColor(R.color.colorWhite));
        }
        JSONObject jsonObject = NetworkUtils.getJSONFromNetwork(methodOfSort, 1);
        ArrayList<Movie> movies = JSONUtils.getMoviesFromJSON(jsonObject);
        movieAdapter.setMovies(movies);
    }
}
