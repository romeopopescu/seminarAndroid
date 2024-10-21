package eu.ase.ro.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fabAdd, fabSend;
    public static final String KEY_STRING = "sendString";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fabAdd = findViewById(R.id.fabAddMovie);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddMovieActivity.class);
                startActivity(intent);
            }
        }
        );

        fabSend = findViewById(R.id.fabSend);
        fabSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String userInfo = "These are the user's info";
                Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                intent.putExtra()
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_option_1) {
            Toast.makeText(getApplicationContext(),
                    R.string.option1, Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId() == R.id.menu_option_2) {
            Toast.makeText(getApplicationContext(),
                    R.string.option2, Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}