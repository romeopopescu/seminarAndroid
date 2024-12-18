package com.example.test2_songclass;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.test2_songclass.db.SongDB;
import com.example.test2_songclass.db.SongDao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity {
    ListView listView;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        listView = findViewById(R.id.listView);

        SongDB songDB = SongDB.getInstance(getApplicationContext());

        SongDao songDao = songDB.getSongDao();

        List<Song> songs = songDao.getAllSongs();

        List<String> stringList = new ArrayList<>();
        for (Song s : songs) {
            stringList.add(s.toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stringList);
        listView.setAdapter(adapter);
        List<Song> sortedSongs = songDao.getSortedSongs();

        List<String> sortedStrings = new ArrayList<>();
        for (Song s : sortedSongs) {
            sortedStrings.add(s.toString());
        }
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sortedStrings);

        floatingActionButton = findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                listView.setAdapter(adapter1);
            }
        });
    }
}