package com.example.computacionmovil;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class FilmSearch extends AppCompatActivity {

    private RequestQueue queue;

    private RecyclerView recyclerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_film_search);

        ImageButton buttonParse = findViewById(R.id.imageButton);

        queue = Volley.newRequestQueue(this);

        recyclerView = findViewById(R.id.recyclerId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));

        buttonParse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String film = ((TextView)findViewById(R.id.searchText)).getText().toString();
                new DownloadFilmData().execute(film);
            }
        });
    }

    public void showFilms(final ArrayList<Film> films){
        recyclerView.findViewById(R.id.recyclerId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        MyAdapter adapter = new MyAdapter(films);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            public void onItemClick(int position) {
                Intent intent = new Intent(FilmSearch.this, FilmDetail.class);
                intent.putExtra("Film", (Parcelable) films.get(position));

                startActivity(intent);
            }
        });
    }

    private class DownloadFilmData extends AsyncTask<String, Void, ArrayList<Film>> {
        protected ArrayList<Film> doInBackground(String... strings) {
            return Helpers.parseFilms(strings[0], queue);
        }

        protected void onPostExecute(ArrayList<Film> result) {
            showFilms(result);
            (new Handler()).postDelayed(FilmSearch.this::hideKeyBoard, 500);
        }
    }

    public void hideKeyBoard() {
        View view1 = this.getCurrentFocus();
        if(view1!= null){
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view1.getWindowToken(), 0);
        }
    }
}
