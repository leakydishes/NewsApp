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

public class RelatedStoresAdapter extends RecyclerView.Adapter<RelatedStoresAdapter.MyViewHolder> {
    // variables
    ArrayList<NewsModel> newsModels; // holds all the objects

    public RelatedStoresAdapter(ArrayList<NewsModel> newsModels) {
        //this.context = context;
        this.newsModels = newsModels;
    }

    // gets the layout for each row
    @NonNull
    @Override
    public RelatedStoresAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate the layout -> giving a look to the rows
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.related_stories, parent, false);
        return new RelatedStoresAdapter.MyViewHolder(view); //return view instance of the recycler
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // assigning row value, based on position in array
        holder.imageView.setImageResource(newsModels.get(1).getImage()); //image
        holder.textViewName.setText(newsModels.get(1).getNewsTitle()); //title
        holder.textViewDescription.setText(newsModels.get(1).getNewsDescription()); //description
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
}
