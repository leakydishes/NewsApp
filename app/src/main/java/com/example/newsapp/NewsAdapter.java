package com.example.newsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

// recycler view adapter
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder>{
    // variables
    ArrayList<NewsModel> newsModels; // holds all the objects
    private ItemClickListener clickListener; // interface global

    // constructor with two arguments
    public NewsAdapter(ArrayList<NewsModel> newsModels, NewsFragment clickListener) {
        this.newsModels = newsModels;
        this.clickListener = clickListener;
    }
    // gets the layout for each row
    @NonNull
    @Override
    public NewsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate the layout -> giving a look to the rows
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent,false);
        return new MyViewHolder(view); //return view instance of the recycler
    }

    // based on the position
    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.MyViewHolder holder, int position) {
        // assigning row value, based on position in array
        holder.imageView.setImageResource(newsModels.get(position).getImage()); //image
        holder.textViewName.setText(newsModels.get(position).getNewsTitle()); //title
        holder.textViewDescription.setText(newsModels.get(position).getNewsDescription()); //description

        // set onclick listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(newsModels.get(holder.getAdapterPosition()));
            }
        });
    }

    // amount of data to push to view
    @Override
    public int getItemCount() {
        // what data to update each view and find length of all the data from new models
        return newsModels.size();
    }

    // using objects to set views
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        // similar to on create method, grabbing all objects assigning to variables
        ImageView imageView;
        TextView textViewName, textViewDescription;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            // set views
            imageView = itemView.findViewById(R.id.news_image);
            textViewName = itemView.findViewById(R.id.news_title);
            textViewDescription = itemView.findViewById(R.id.news_description);
        }
    }
    // add click method interface to go into next fragment (NewsDetailFragment)
    // passes the news model object
    public interface ItemClickListener {
        void onItemClick(NewsModel newModels);
    }
}
