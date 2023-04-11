package com.example.myapplication.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.ItemRecyclerAdapter;
import com.example.myapplication.R;
import com.example.myapplication.Interface.model.Drink;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ResultSearchFragment extends Fragment {
    RecyclerView recyclerViewSearch;
    ItemRecyclerAdapter itemRecyclerAdapter;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resultsearch, container, false);
        recyclerViewSearch = view.findViewById(R.id.rcvresultsearch);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getContext(), 2);
        recyclerViewSearch.setLayoutManager(gridLayoutManager2);

        getParentFragmentManager().setFragmentResultListener("datasearch", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                    result.getString("df1");

                String s=result.toString().trim().substring(12, result.toString().lastIndexOf("}"));
                FirebaseRecyclerOptions<Drink> options = new FirebaseRecyclerOptions.Builder<Drink>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Drink").orderByChild("name").startAt(s).endAt(s + "\uf8ff"), Drink.class)
                        .build();
                itemRecyclerAdapter = new ItemRecyclerAdapter(options);
                itemRecyclerAdapter.startListening();
                recyclerViewSearch.setAdapter(itemRecyclerAdapter);
            }
        });

        return view;
    }
}
