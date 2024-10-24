package eu.ase.ro.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AboutActivity extends AppCompatActivity {

    TextView tvUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        tvUserInfo = findViewById(R.id.tvUserInfo);
        Intent intent = getIntent();

        if (intent.hasExtra(MainActivity.KEY_STRING)) {
            String userInfo = intent.getStringExtra(MainActivity.KEY_STRING);
            tvUserInfo.setText(userInfo);
        }


    }

}