package com.example.computacionmovil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class TVShowSearch extends AppCompatActivity {

    private RequestQueue queue;

    private RecyclerView recyclerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_tv_show_search);

        ImageButton buttonParse = findViewById(R.id.imageButton);

        queue = Volley.newRequestQueue(this);

        recyclerView = findViewById(R.id.recyclerId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));

        buttonParse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String tvshow = ((TextView)findViewById(R.id.searchText)).getText().toString();
                new DownloadTVShowData().execute(tvshow);
            }
        });
    }

    public void showFilms(final ArrayList<TVShow> tvshows){
        recyclerView.findViewById(R.id.recyclerId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        TVAdapter adapter = new TVAdapter(tvshows);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new TVAdapter.OnItemClickListener() {
            public void onItemClick(int position) {
                Intent intent = new Intent(TVShowSearch.this, TVShowDetail.class);
                intent.putExtra("Show", (Parcelable) tvshows.get(position));

                startActivity(intent);
            }
        });
    }

    private class DownloadTVShowData extends AsyncTask<String, Void, ArrayList<TVShow>> {
        protected ArrayList<TVShow> doInBackground(String... strings) {
            return Helpers.parseTVShows(strings[0], queue);
        }

        protected void onPostExecute(ArrayList<TVShow> result) {
            showFilms(result);
            (new Handler()).postDelayed(TVShowSearch.this::hideKeyBoard, 500);
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
