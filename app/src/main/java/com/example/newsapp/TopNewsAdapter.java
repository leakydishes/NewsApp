package com.example.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class TopNewsAdapter extends RecyclerView.Adapter<TopNewsAdapter.MyViewHolder> {
    // variables
    ArrayList<NewsModel> newsModels; // holds all the objects
    private NewsAdapter.ItemClickListener clickListener; // interface global

    public TopNewsAdapter(ArrayList<NewsModel> newsModels, NewsAdapter.ItemClickListener clickListener) {
        //this.context = context;
        this.newsModels = newsModels;
        this.clickListener = clickListener;
    }

    // gets the layout for each row
    @NonNull
    @Override
    public TopNewsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate the layout -> giving a look to the rows
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_top_items, parent, false);
        return new TopNewsAdapter.MyViewHolder(view); //return view instance of the recycler
    }

    @Override
    public void onBindViewHolder(@NonNull TopNewsAdapter.MyViewHolder holder, int position) {
        // assigning row value, based on position in array
        holder.topNewsImage1.setImageResource(newsModels.get(0).getImage()); //image1
        holder.topNewsImage2.setImageResource(newsModels.get(1).getImage()); //image2
        holder.topNewsImage3.setImageResource(newsModels.get(2).getImage()); //image3
        holder.topNewsImage4.setImageResource(newsModels.get(3).getImage()); //image3

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
        ImageView topNewsImage1, topNewsImage2, topNewsImage3, topNewsImage4;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            // set views
            topNewsImage1 = itemView.findViewById(R.id.topNewsImage1);
            topNewsImage2 = itemView.findViewById(R.id.topNewsImage2);
            topNewsImage3 = itemView.findViewById(R.id.topNewsImage3);
            topNewsImage4 = itemView.findViewById(R.id.topNewsImage4);
        }
    }
}
