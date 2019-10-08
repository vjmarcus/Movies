package com.freshappbooks.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.freshappbooks.movies.data.Movie;
import com.freshappbooks.movies.utils.JSONUtils;
import com.freshappbooks.movies.utils.NetworkUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MyApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JSONObject jsonObject = NetworkUtils.getJSONFromNetwork(NetworkUtils.POPULARITY, 1);
        Log.v(TAG, jsonObject.toString());
        ArrayList<Movie> movies = JSONUtils.getMoviesFromJSON(jsonObject);
        StringBuilder builder = new StringBuilder();
        for (Movie movie : movies) {
            builder.append(movie.getTitle()).append("\n");
        }
        Log.v("MyApp", builder.toString());
    }
}
