package com.example.computacionmovil;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Film implements Serializable, Parcelable {

    private String poster_path;
    private String original_title;
    private Double vote_average;
    private String title;
    private String overview;
    private String release_date;

    public Film(String poster_path, String original_title, Double vote_average, String title,
                String overview, String release_date){
        this.poster_path = poster_path;
        this.original_title = original_title;
        this.vote_average = vote_average;
        this.title = title;
        this.overview = overview;
        this.release_date = release_date;
    }

    protected Film(Parcel in) {
        poster_path = in.readString();
        original_title = in.readString();
        if (in.readByte() == 0) {
            vote_average = null;
        } else {
            vote_average = in.readDouble();
        }
        title = in.readString();
        overview = in.readString();
        release_date = in.readString();
    }

    public static final Creator<Film> CREATOR = new Creator<Film>() {
        public Film createFromParcel(Parcel in) {
            return new Film(in);
        }

        public Film[] newArray(int size) {
            return new Film[size];
        }
    };

    public String getPoster_path(){
        return poster_path;
    }

    public String getOriginal_title(){
        return original_title;
    }

    public Double getVote_average(){
        return vote_average;
    }

    public String getTitle(){
        return title;
    }

    public String getOverview(){
        return overview;
    }

    public String getRelease_date(){
        return release_date;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(poster_path);
        parcel.writeString(original_title);
        if (vote_average == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(vote_average);
        }
        parcel.writeString(title);
        parcel.writeString(overview);
        parcel.writeString(release_date);
    }
}
