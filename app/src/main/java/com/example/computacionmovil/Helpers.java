package com.example.computacionmovil;

import android.os.LocaleList;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Helpers {

    static String searchFilmRequest(String film){
        String baseURL = "https://api.themoviedb.org/3";
        String searchBaseURL = "/search/movie?api_key=";
        String languageURL = "&language=";
        String pageAndAdultURL = "&page=1&include_adult=false";

        // TODO: Complete the API Key
        String apiKey = "Your API Key goes here";
        
        String defaultLang = LocaleList.getDefault().toString().substring(1,6);

        String[] stripped_film = film.split(" ");
        String query = "&query=" + stripped_film[0];
        for (int i=1; i < stripped_film.length; i++){
            query = query + "+" + stripped_film[i];
        }
        String request = baseURL + searchBaseURL + apiKey + languageURL +
                defaultLang + pageAndAdultURL + query;
        return request;
    }

    static ArrayList<Film> parseFilms(String film, RequestQueue queue){
        String url = searchFilmRequest(film);
        final ArrayList<Film> filmsArray = new ArrayList<>();
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            public void onResponse(JSONObject response) {
                JSONArray filmArray = null;
                try {
                    filmArray = response.getJSONArray("results");
                    for (int i = 0; i < filmArray.length(); i++) {
                        JSONObject filmObject = filmArray.getJSONObject(i);
                        Film film = new Film(
                                filmObject.getString("poster_path"),
                                filmObject.getString("original_title"),
                                filmObject.getDouble("vote_average"),
                                filmObject.getString("title"),
                                filmObject.getString("overview"),
                                filmObject.getString("release_date")
                        );
                        filmsArray.add(film);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        queue.add(jsonObjectRequest);
        return filmsArray;
    }


    static String searchTVShowRequest(String film){
        String baseURL = "https://api.themoviedb.org/3";
        String searchBaseURL = "/search/tv?api_key=";
        String languageURL = "&language=";
        String pageAndAdultURL = "&page=1&include_adult=false";

        String apiKey = "ad9f166a8a5bc52aab64cf572c572388";
        String defaultLang = LocaleList.getDefault().toString().substring(1,6);

        String[] stripped_film = film.split(" ");
        String query = "&query=" + stripped_film[0];
        for (int i=1; i < stripped_film.length; i++){
            query = query + "+" + stripped_film[i];
        }
        String request = baseURL + searchBaseURL + apiKey + languageURL +
                defaultLang + pageAndAdultURL + query;
        return request;
    }

    static ArrayList<TVShow> parseTVShows(String tvshow, RequestQueue queue){
        String url = searchTVShowRequest(tvshow);
        final ArrayList<TVShow> TVShowsArray = new ArrayList<>();
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            public void onResponse(JSONObject response) {
                JSONArray TVShowArray = null;
                try {
                    TVShowArray = response.getJSONArray("results");
                    for (int i = 0; i < TVShowArray.length(); i++) {
                        JSONObject tvshowObject = TVShowArray.getJSONObject(i);
                        TVShow tvShow = new TVShow(
                                tvshowObject.getString("poster_path"),
                                tvshowObject.getDouble("vote_average"),
                                tvshowObject.getString("overview"),
                                tvshowObject.getString("first_air_date"),
                                tvshowObject.getString("name")
                        );
                        TVShowsArray.add(tvShow);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        queue.add(jsonObjectRequest);
        return TVShowsArray;
    }

}
