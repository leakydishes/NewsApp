package com.example.newsapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsDetailFragment extends Fragment {

    // variables for objects
    ImageView imageView;
    TextView titleTextView;
    TextView descriptionTextView;
    ArrayList<NewsModel> newsModels = new ArrayList<>(); // array for text
    int[] newsImages = {R.drawable.news1, R.drawable.news2, R.drawable.news3, R.drawable.news4,
            R.drawable.news5, R.drawable.news6, R.drawable.news7};
    public NewsDetailFragment() {
        // Required empty public constructor
    }

    // new instance of fragment
    public static NewsDetailFragment newInstance(String title, String description, int image) {
        NewsDetailFragment fragment = new NewsDetailFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("description", description);
        args.putInt("image", image);
        fragment.setArguments(args);
        return fragment;
    }

    // when initialised
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_detail, container, false);
        SetUpNewsModels(); // populate data
        initRecyclerView(view); //view data
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            imageView = getView().findViewById(R.id.newsImage);
            titleTextView = getView().findViewById(R.id.newsTitle);
            descriptionTextView = getView().findViewById(R.id.newsDescription);
            imageView.setImageResource(getArguments().getInt("image"));
            titleTextView.setText( getArguments().getString("title"));
            descriptionTextView.setText(getArguments().getString("description"));
        }
    }
    // set up recycler view
    private void initRecyclerView(View view) {
        RecyclerView recyclerViewRelatedNews = view.findViewById(R.id.recyclerviewRelatedStories); //news recycler

        // set up news array with title, description and image
        SetUpNewsModels();

        // set up adapter
        RelatedStoresAdapter relatedStoresAdapter; // recycle view collects data for news

        // create new listener with adapter and layout
        relatedStoresAdapter = new RelatedStoresAdapter(newsModels); // adapter setup
        RecyclerView.LayoutManager layoutManager3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,
                false);
        // set up recycler view and adapter
        recyclerViewRelatedNews.setAdapter(relatedStoresAdapter);
        // set up recycler view and adapter
        recyclerViewRelatedNews.setLayoutManager(layoutManager3);
    }
    // generate the data
    private void SetUpNewsModels() {
        // get news titles
        String[] newsNames = getResources().getStringArray(R.array.news_titles_full_text);
        // get news descriptions
        String[] newsDescription = getResources().getStringArray(R.array.news_description_full_text);

        // iterate through each string and int to array list to show image, title, description
        for (int i = 0; i < newsNames.length; i++) {
            newsModels.add(new NewsModel(newsNames[i], newsDescription[i], newsImages[i]));
        }
    }

}