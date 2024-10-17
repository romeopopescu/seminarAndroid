package eu.ase.ro.lab3;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddMovieActivity extends AppCompatActivity {
    Spinner spinnerGenre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        spinnerGenre = findViewById(R.id.spnGenre);

        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(getApplicationContext(), R.array.arrayMovieGenre,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

        spinnerGenre.setAdapter(adapterSpinner);

    }
}