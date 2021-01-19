package com.example.se_implementation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class PartsFragment extends Fragment {

    private View PartsFragmentView;
    private RecyclerView myPartsList;

    private DatabaseReference PartsDetailsRef;


    public PartsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        PartsFragmentView = inflater.inflate(R.layout.fragment_parts, container);

        PartsDetailsRef = FirebaseDatabase.getInstance().getReference().child("Parts Details");

        myPartsList = (RecyclerView) PartsFragmentView.findViewById(R.id.recyclerViewStatistics);
        myPartsList.setLayoutManager(new LinearLayoutManager(getContext()));
        return PartsFragmentView;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Parts> options =
                new FirebaseRecyclerOptions.Builder<Parts>()
                        .setQuery(PartsDetailsRef, Parts.class).build();
    }
}