package com.example.seminarfromzero;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seminarfromzero.adapter.MovieAdapter;
import com.example.seminarfromzero.db.MovieDB;
import com.example.seminarfromzero.utils.Constants;
import com.example.seminarfromzero.utils.Movie;
import com.example.seminarfromzero.utils.User;
import com.example.seminarfromzero.utils.XMLParser;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    TextView tvMainActivity;
    FloatingActionButton floatingActionButton;
    FloatingActionButton fabSend;
    public static final String KEY_STRING = "sendString";
    public static final String KEY_USER = "sendUser";
    public int REQUEST_CODE = 200;

    RecyclerView recyclerViewMovie;
    ArrayList<Movie> movieList;
    List<Movie> movieListFromXML = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MainActivity", "oncreate");

//        listView = findViewById(R.id.listViewMainActivity);
        recyclerViewMovie = findViewById(R.id.rvMainActivity);
        movieList = new ArrayList<>();

        Movie movie = new Movie("Singur Acasa", new Date(), 350, "Comedy", "Netflix");
        movieList.add(movie);
        MovieAdapter movieAdapter = new MovieAdapter(getApplicationContext(), movieList);
        recyclerViewMovie.setAdapter(movieAdapter);
        recyclerViewMovie.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        loadMovieList();
        deleteMovie("homealone");

        //JSON Parsing
//        Thread jsonParser = new Thread(new JSONParser());
//        jsonParser.start();

        //XML Parsing
//        ExecutorService service = Executors.newSingleThreadExecutor();
//        service.execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    URL url = new URL("https://pastebin.com/raw/BUXXtTfx");
//                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//
//                    InputStream inputStream = httpURLConnection.getInputStream();
//
//                    XMLParser parser = new XMLParser();
//                    movieListFromXML = parser.parse(inputStream);
//
//                    for (Movie m : movieListFromXML) {
//                        if (m != null) {
//                            movieList.add(m);
//                        }
//                    }
//                    inputStream.close();
//                    httpURLConnection.disconnect();
//
//                } catch (MalformedURLException e) {
//                    throw new RuntimeException(e);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        MovieAdapter movieAdapter1 = new MovieAdapter(getApplicationContext(), movieList);
//                        movieAdapter1.notifyDataSetChanged();
//                    }
//                });
//            }
//        });

        floatingActionButton = findViewById(R.id.fabMain);
//


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddMovieActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        fabSend = findViewById(R.id.fabSend);
        fabSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String userInfo = "These are the user's info";
                User user = new User("admin", 123);
                Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                intent.putExtra(KEY_STRING, userInfo);
                intent.putExtra(KEY_USER, user);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i("MainActivity", "menuuu");
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_option1) {
            Toast.makeText(getApplicationContext(), R.string.menu_option1, Toast.LENGTH_LONG).show();
        }
        else if (item.getItemId() == R.id.menu_option2) {
            Toast.makeText(getApplicationContext(), R.string.menu_option2, Toast.LENGTH_LONG).show();
        }
        return true;
    }
    private void loadMovieList() {
        MovieDB movieDB = MovieDB.getInstance(getApplicationContext());
        List<Movie> moviesFromDB = movieDB.getMovieDao().getAllMovies();
        for (Movie m : moviesFromDB) {
            movieList.add(m);
            recyclerViewMovie.getAdapter().notifyDataSetChanged();
        }
    }
    private void deleteMovie(String title) {
        MovieDB movieDB = MovieDB.getInstance(getApplicationContext());
        movieDB.getMovieDao().delete(title);
        recyclerViewMovie.getAdapter().notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Movie movie = data.getParcelableExtra(AddMovieActivity.KEY_MOVIE);
            if (movie != null) {
                movieList.add(movie);
                MovieAdapter movieAdapter = (MovieAdapter) recyclerViewMovie.getAdapter();
                movieAdapter.notifyDataSetChanged();
            }
//            ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listMovie);
//            listView.setAdapter(listAdapter);
//
        }
    }
//    https://pastebin.com/P7y6KfMZ
//    "title": "Zodiac",
//            "releaseDate": "12-12-2007",
//            "profit": 1234,
//            "movieGenre": "Drama",
//            "platform": "Netflix"
    public class JSONParser implements Runnable {
        @Override
        public void run() {
            try {
                URL url = new URL("https://pastebin.com/raw/P7y6KfMZ");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line = bufferedReader.readLine();

                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                getMovieFromJson(line);

                //varianta 1
//                Handler threadHandler = new Handler(Looper.getMainLooper());
//                threadHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        MovieAdapter movieAdapter = (MovieAdapter) recyclerViewMovie.getAdapter();
//                        movieAdapter.notifyDataSetChanged();
//                    }
//                });

                //varianta 2
//                recyclerViewMovie.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        MovieAdapter movieAdapter = (MovieAdapter) recyclerViewMovie.getAdapter();
//                        movieAdapter.notifyDataSetChanged();
//                    }
//                });

                //varianta 3
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MovieAdapter movieAdapter = (MovieAdapter) recyclerViewMovie.getAdapter();
                        movieAdapter.notifyDataSetChanged();
                    }
                });

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void getMovieFromJson(String json) {
        if (json != null) {
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray = jsonObject.getJSONArray(Constants.KEY_JSON_ARRAY);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject movieJson = jsonArray.getJSONObject(i);
                    String title = movieJson.getString(Constants.KEY_MOVIE_TITLE);
                    String releaseDate = movieJson.getString(Constants.KEY_RELEASE_DATE);
                    int profit = movieJson.getInt(Constants.KEY_MOVIE_PROFIT);
                    String movieGenre = movieJson.getString(Constants.KEY_MOVIE_GENRE);
                    String platform = movieJson.getString(Constants.KEY_MOVIE_PLATFORM);

                    Movie movie = new Movie(title, Constants.simpleDateFormat.parse(releaseDate), profit, movieGenre, platform);
                    movieList.add(movie);


                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            Log.e("parseJson", "JSON object is null");
        }
    }
}