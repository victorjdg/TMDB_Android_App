package com.example.computacionmovil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    CardView filmsCard;
    CardView tvShowsCard;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filmsCard = findViewById(R.id.films);
        tvShowsCard = findViewById(R.id.tvshows);

        filmsCard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onClickFilm(view);
            }
        });

        tvShowsCard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onClickTVShows(view);
            }
        });
    }

    public void onClickFilm(View view) {
        Intent intent = new Intent(this, FilmSearch.class);
        startActivity(intent);
    }

    public void onClickTVShows(View view) {
        Intent intent = new Intent(this, TVShowSearch.class);
        startActivity(intent);
    }
}