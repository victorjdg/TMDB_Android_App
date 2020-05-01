package com.example.computacionmovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class FilmDetail extends AppCompatActivity {
    private String posterURL = "https://image.tmdb.org/t/p/w780";

    private ImageView poster_image;
    private TextView data_title;
    private TextView data_score;
    private TextView data_date;
    private TextView data_overview;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);

        Intent intent = getIntent();
        Film f = intent.getParcelableExtra("Film");

        String posterPath = f.getPoster_path();
        String title = f.getTitle();
        Double score = f.getVote_average();
        String date = f.getRelease_date();
        String overview = f.getOverview();

        poster_image = findViewById(R.id.poster);
        imageAssign(posterPath);

        data_title = findViewById(R.id.title);
        data_title.setText(title);

        data_score = findViewById(R.id.score);
        data_score.setText(score.toString());

        data_date = findViewById(R.id.date);
        data_date.setText(date);

        data_overview = findViewById(R.id.overviewText);
        data_overview.setText(overview);

    }
    public void imageAssign(String path){
        if (path == "null"){
            Picasso.get().load("https://www.elcohetealaluna.com/wp-content/uploads/2019/10/ina.png")
                    .resize(1280, 1920)
                    .onlyScaleDown()
                    .centerCrop()
                    .into(poster_image);
        } else {
            String url = posterURL + path;
            Picasso.get().load(url).resize(1080, 1920)
                    .onlyScaleDown()
                    .centerCrop()
                    .into(poster_image);;
        }
    }
}
