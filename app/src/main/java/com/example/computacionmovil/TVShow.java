package com.example.computacionmovil;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class TVShow implements Serializable, Parcelable {

    private String poster_path;
    private Double vote_average;
    private String overview;
    private String first_air_date;
    private String name;

    public TVShow(String poster_path, Double vote_average, String overview, String first_air_date,
                  String name){
        this.poster_path = poster_path;
        this.vote_average = vote_average;
        this.overview = overview;
        this.first_air_date = first_air_date;
        this.name = name;
    }

    protected TVShow(Parcel in) {
        poster_path = in.readString();
        if (in.readByte() == 0) {
            vote_average = null;
        } else {
            vote_average = in.readDouble();
        }
        overview = in.readString();
        first_air_date = in.readString();
        name = in.readString();
    }

    public static final Creator<TVShow> CREATOR = new Creator<TVShow>() {
        public TVShow createFromParcel(Parcel in) {
            return new TVShow(in);
        }

        public TVShow[] newArray(int size) {
            return new TVShow[size];
        }
    };

    public String getPoster_path(){
        return poster_path;
    }

    public Double getVote_average(){
        return vote_average;
    }

    public String getOverview(){
        return overview;
    }

    public String getFirst_air_date(){
        return first_air_date;
    }

    public String getName(){
        return name;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(poster_path);
        if (vote_average == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(vote_average);
        }
        parcel.writeString(overview);
        parcel.writeString(first_air_date);
        parcel.writeString(name);
    }

}
