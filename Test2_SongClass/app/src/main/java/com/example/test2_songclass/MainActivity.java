package com.example.test2_songclass;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.test2_songclass.db.SongDB;
import com.example.test2_songclass.db.SongDao;
import com.example.test2_songclass.utils.Constants;

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
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String KEY_STRING = "sendString";
    public List<Song> songList = new ArrayList<>();
    Button btnSync;
    Button btnSave;
    Button btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSync = findViewById(R.id.btnSyncData);

        btnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread jsonParser = new Thread(new JSONParser());
                jsonParser.start();
            }
        });

        btnSave = findViewById(R.id.btnSaveData);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (songList != null) {
                    insertSongs();
                    Toast.makeText(getApplicationContext(), "Insertion completed", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "There is no data", Toast.LENGTH_LONG).show();
                }
                for (int i = 0; i < songList.size(); i++) {
                    getIntent().putExtra(KEY_STRING, songList.get(i));
                }

            }
        });

        btnView = findViewById(R.id.btnViewData);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewActivity.class);
                startActivity(intent);
            }
        });


    }
    private void insertSongs() {
        SongDB songDB = SongDB.getInstance(getApplicationContext());
        for (Song s : songList) {
            songDB.getSongDao().insert(s);
        }
    }
    public class JSONParser implements Runnable{

        @Override
        public void run() {
            try {
                URL url = new URL("https://pastebin.com/raw/uinVni64");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line = bufferedReader.readLine();

                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                getSongFromJson(line);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "SUCCESS", Toast.LENGTH_LONG).show();
                    }
                });

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void getSongFromJson(String json) {
            if (json != null) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray jsonArray = jsonObject.getJSONArray(Constants.KEY_ARRAY);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject song = jsonArray.getJSONObject(i);
                        String songTitle = song.getString(Constants.KEY_SONG_TITLE);
                        String artist = song.getString(Constants.KEY_ARTIST);
                        int views = Integer.parseInt(song.getString(Constants.KEY_VIEWS));
                        String date = song.getString(Constants.KEY_RELEASE_DATE);

                        Song songObject = new Song(songTitle, artist, views, Constants.simpleDateFormat.parse(date));
                        songList.add(songObject);
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            } else {
                Toast.makeText(MainActivity.this, "FAILURE", Toast.LENGTH_LONG).show();
            }
        }
    }

}
