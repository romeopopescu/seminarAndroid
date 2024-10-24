package eu.ase.ro.lab3;

import android.graphics.Movie;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Date;

public class AddMovieActivity extends AppCompatActivity {
    Spinner spinnerGenre;

    EditText etTitle, etReleaseDate, etProfit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        etTitle = findViewById(R.id.etMovieTitle);
        etReleaseDate = findViewById(R.id.etMovieDate);
        etProfit = findViewById(R.id.etMovieProfit);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        spinnerGenre = findViewById(R.id.spnGenre);

        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(getApplicationContext(), R.array.arrayMovieGenre,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

        spinnerGenre.setAdapter(adapterSpinner);

    }

    private boolean isValid() {
        if (etTitle.getText() == null || etTitle.getText().toString().trim().isEmpty()) {

        }
    }
    private Movie buildMovieFromComponents() {
        String title = etTitle.getText().toString();

        String dateString = etReleaseDate.getText().toString();
        Date releaseDate = DateConverter.fromString(dateString);


    }
}