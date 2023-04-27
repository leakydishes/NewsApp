package com.example.newsapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Objects;

// handle click implementation
public class NewsFragment extends Fragment implements NewsAdapter.ItemClickListener{

    // variables for objects
    ArrayList<NewsModel> newsModels = new ArrayList<>(); // array for text
    int[] newsImages = {R.drawable.news1, R.drawable.news2, R.drawable.news3, R.drawable.news4,
            R.drawable.news5, R.drawable.news6, R.drawable.news7};

    public NewsFragment() {
        // Required empty public constructor
    }

    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }

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
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        SetUpNewsModels(); // populate data
        initRecyclerView(view); //view data
        return view;
    }

    // set up recycler view
    private void initRecyclerView(View view) {
        RecyclerView recyclerViewNews = view.findViewById(R.id.recyclerview); //news recycler
        RecyclerView recyclerViewTopNews = view.findViewById(R.id.recyclerviewTopNews); //top news recycler

        // add grid layout with two columns
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true);


        // set up news array with title, description and image
        SetUpNewsModels();

        // set up adapter
        NewsAdapter newsAdapter; // recycle view collects data for news
        TopNewsAdapter topNewsAdapter; // recycle view collects data for Top news

        // create new listener with adapter and layout
        newsAdapter = new NewsAdapter(newsModels, this); // adapter setup
        topNewsAdapter = new TopNewsAdapter(newsModels, this);

        // set up recycler view and adapter
        recyclerViewNews.setAdapter(newsAdapter);
        recyclerViewNews.setLayoutManager(layoutManager);
        recyclerViewTopNews.setAdapter(topNewsAdapter);
        recyclerViewTopNews.setLayoutManager(layoutManager2);
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

    // on click listener
    @Override
    public void onItemClick(NewsModel newModels) {
        // add image
        Fragment fragment = NewsDetailFragment.newInstance(newModels.getNewsTitle(), newModels.getNewsDescription(), newModels.getImage());

        //create new fragment and reduce reloading detail_fragment by tagging main/ news fragment
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.hide(Objects.requireNonNull(requireActivity().getSupportFragmentManager().findFragmentByTag("news_fragment"))); // hide previous fragment
        transaction.add(R.id.frame_container, fragment);
        transaction.addToBackStack(null); // come back to previous fragment
        transaction.commit();
    }
}