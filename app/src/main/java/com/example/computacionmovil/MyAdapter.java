package com.example.computacionmovil;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<Film> arrayFilm;
    private String posterURL = "https://image.tmdb.org/t/p/w500";
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public MyAdapter(ArrayList<Film> arrayFilm){
        this.arrayFilm = arrayFilm;
    }

    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fiml_list, null,
                false);
        return new MyViewHolder(view, mListener);
    }

    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.dataAssign(arrayFilm.get(position).getTitle());
        holder.scoreAssign(arrayFilm.get(position).getVote_average());
        holder.imageAssign(arrayFilm.get(position).getPoster_path());
    }

    public int getItemCount() {
        return arrayFilm.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView data_title;
        private ImageView poster_image;
        private TextView data_score;

        public MyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            data_title = itemView.findViewById(R.id.title);
            data_score = itemView.findViewById(R.id.score);
            poster_image = itemView.findViewById(R.id.posterView);
            itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

        public void dataAssign(String title){
            data_title.setText(title);
        }

        public void scoreAssign(Double score) {data_score.setText("Score: " + score.toString());}

        public void imageAssign(String path){
            if (path == "null"){
                Picasso.get().load("https://www.elcohetealaluna.com/wp-content/uploads/2019/10/ina.png")
                        .resize(300, 450)
                        .centerCrop()
                        .into(poster_image);
            } else {
                String url = posterURL + path;
                Picasso.get().load(url)
                        .resize(300, 450)
                        .centerCrop()
                        .into(poster_image);
            }
        }
    }
}