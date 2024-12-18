package com.example.seminarfromzero;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.seminarfromzero.utils.User;

public class AboutActivity extends AppCompatActivity {
    TextView userInfo;
    TextView username;
    TextView userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        userInfo = findViewById(R.id.userInfo);
        username = findViewById(R.id.tvUserName);
        userId = findViewById(R.id.tvUserId);
        Intent intent = getIntent();

        if (intent.hasExtra(MainActivity.KEY_STRING)) {
            String userInfoString = intent.getStringExtra(MainActivity.KEY_STRING);
            userInfo.setText(userInfoString);
        }
        if (intent.hasExtra(MainActivity.KEY_USER)) {
            User userDetailString = (User) intent.getSerializableExtra(MainActivity.KEY_USER);
            username.setText(userDetailString.getUsername());
            userId.setText(" " + userDetailString.getUserId());
        }

    }
}