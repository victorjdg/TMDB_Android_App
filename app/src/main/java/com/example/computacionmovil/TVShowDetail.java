package com.example.computacionmovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class TVShowDetail extends AppCompatActivity {

    private String posterURL = "https://image.tmdb.org/t/p/w780";

    private ImageView poster_image;
    private TextView data_name;
    private TextView data_score;
    private TextView data_first_air_date;
    private TextView data_overview;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_detail);

        Intent intent = getIntent();
        TVShow tvs = intent.getParcelableExtra("Show");

        String posterPath = tvs.getPoster_path();
        String name = tvs.getName();
        Double score = tvs.getVote_average();
        String first_air_date = tvs.getFirst_air_date();
        String overview = tvs.getOverview();

        poster_image = findViewById(R.id.poster);
        imageAssign(posterPath);

        data_name = findViewById(R.id.name);
        data_name.setText(name);

        data_score = findViewById(R.id.score);
        data_score.setText(score.toString());

        data_first_air_date = findViewById(R.id.first_air_date);
        data_first_air_date.setText("First Air Date: " + first_air_date);

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
