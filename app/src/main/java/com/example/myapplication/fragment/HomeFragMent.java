package com.example.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.ItemRecyclerAdapter;
import com.example.myapplication.Adapter.SliderAdapter;
import com.example.myapplication.R;
import com.example.myapplication.model.Drink;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class HomeFragMent extends Fragment {
    private RecyclerView recyclerView,recyclerView2;
    ItemRecyclerAdapter itemRecycleAdapter;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    RecyclerView recview;

    int[] images={R.drawable.one,
            R.drawable.two,
            R.drawable.three,
            R.drawable.four,
            R.drawable.five,
            R.drawable.six};

    SliderAdapter sliderAdapter;
    SliderView sliderView;
    public HomeFragMent() {

    }

    public static HomeFragMent newInstance(String param1, String param2) {
        HomeFragMent fragment = new HomeFragMent();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_home,container,false);
        recyclerView = view.findViewById(R.id.recycler_drink);
        recyclerView2 = view.findViewById(R.id.recycler_drink2);

        //slider
        sliderView= view.findViewById(R.id.imageSlider);
        SliderAdapter sliderAdapter= new SliderAdapter(images);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINDEPTHTRANSFORMATION);
        sliderView.startAutoCycle();
        //list product
        LinearLayoutManager gridLayoutManager= new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        LinearLayoutManager gridLayoutManager2= new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView2.setLayoutManager(gridLayoutManager2);

        FirebaseRecyclerOptions<Drink> options =
                new FirebaseRecyclerOptions.Builder<Drink>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Drink"), Drink.class)
                        .build();

        itemRecycleAdapter = new ItemRecyclerAdapter(options);
        recyclerView.setAdapter(itemRecycleAdapter);
        recyclerView2.setAdapter(itemRecycleAdapter);
        return view;

    }
    @Override
    public void onStart() {
        super.onStart();
        itemRecycleAdapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        itemRecycleAdapter.stopListening();
    }

}
