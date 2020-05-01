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

public class TVAdapter extends RecyclerView.Adapter<TVAdapter.TVViewHolder>{

    private ArrayList<TVShow> arrayTVShow;
    private String posterURL = "https://image.tmdb.org/t/p/w500";
    private TVAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public TVAdapter(ArrayList<TVShow> arrayTVShow){
        this.arrayTVShow = arrayTVShow;
    }

    public TVAdapter.TVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tvshow_list, null,
                false);
        return new TVViewHolder(view, mListener);
    }

    public void onBindViewHolder(@NonNull TVAdapter.TVViewHolder holder, int position) {
        holder.dataAssign(arrayTVShow.get(position).getName());
        holder.scoreAssign(arrayTVShow.get(position).getVote_average());
        holder.imageAssign(arrayTVShow.get(position).getPoster_path());
    }

    public int getItemCount() {
        return arrayTVShow.size();
    }

    public class TVViewHolder extends RecyclerView.ViewHolder{

        private TextView data_name;
        private ImageView poster_image;
        private TextView data_score;

        public TVViewHolder(@NonNull View itemView, final TVAdapter.OnItemClickListener listener) {
            super(itemView);
            data_name = itemView.findViewById(R.id.name);
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
            data_name.setText(title);
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
