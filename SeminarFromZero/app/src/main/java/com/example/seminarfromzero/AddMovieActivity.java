package com.example.seminarfromzero;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.seminarfromzero.db.MovieDB;
import com.example.seminarfromzero.utils.Cinema;
import com.example.seminarfromzero.utils.Movie;

import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class AddMovieActivity extends AppCompatActivity {

    EditText etTitle, etDate, etProfit;
    RadioGroup radioGroup;
    Button btnSave;
    Spinner spinner;
    public static final String KEY_MOVIE = "sendMovie";
    public static final String DATE_PATTERN = "dd-MM-yyyy";
    DateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN, Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        initComponent();
    }

    private void initComponent() {
        etTitle = findViewById(R.id.etMovieTitle);
        etDate = findViewById(R.id.etMovieDate);
        etProfit = findViewById(R.id.etMovieProfit);

        spinner = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.arrayGenre, android.R.layout.simple_spinner_item);

        spinner.setAdapter(adapterSpinner);

        radioGroup = findViewById(R.id.radioGroup);
        btnSave = findViewById(R.id.button);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    Movie movie = createMovie();
                    if (movie != null) {
                        insertMovieInDB(movie);
                        getIntent().putExtra(KEY_MOVIE, movie);
                        setResult(RESULT_OK, getIntent());
                        finish();
                    }
                }

            }
        });
    }

    private boolean validate() {
        if (etTitle.getText().toString().trim().isEmpty()) {
            etTitle.setError("Title is required");
            return false;
        }
        if (etProfit.getText().toString().trim().isEmpty()) {
            etProfit.setError("Profit is required");
            return false;
        }
        if (etDate.getText().toString().trim().isEmpty()) {
            etDate.setError("Date is required");
            return false;
        }
        return true;
    }


    private Movie createMovie() {
        try {
            String title = etTitle.getText().toString();
            Date releaseDate = simpleDateFormat.parse(etDate.getText().toString());
            int profit = Integer.parseInt(etProfit.getText().toString());
            String movieGenre = spinner.getSelectedItem().toString();
            RadioButton rbPlatform = findViewById(radioGroup.getCheckedRadioButtonId());
            String platform = rbPlatform.getText().toString();
            return new Movie(title, releaseDate, profit, movieGenre, platform);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

//        return null;
    }

    public void insertMovieInDB(Movie movie) {
        MovieDB movieDB = MovieDB.getInstance(getApplicationContext());

        Random random = new SecureRandom();
        Cinema cinema = new Cinema(random.nextInt(), "Cinema City", 10, "AFI Cotroceni");

        movie.setIdCinema(cinema.getId());

        movieDB.getCinemaDao().insert(cinema);

        movieDB.getMovieDao().insert(movie);
    }
}

