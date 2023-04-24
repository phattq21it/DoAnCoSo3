package com.example.myapplication.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Adapter.InforOrder;
import com.example.myapplication.Adapter.InforOrderDaDanhGia;
import com.example.myapplication.Common.Common;
import com.example.myapplication.Interface.model.Request;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link YourOrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class YourOrderFragment extends Fragment {
    private RecyclerView recyclerView,recyclerView2;
    private InforOrder inforOrder;
    private InforOrderDaDanhGia inforOrder2;
    MainActivity mainActivity;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public YourOrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment YourOrderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static YourOrderFragment newInstance(String param1, String param2) {
        YourOrderFragment fragment = new YourOrderFragment();
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

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_your_order, container, false);
        recyclerView = view.findViewById(R.id.recycler_drink);
        recyclerView2 = view.findViewById(R.id.recycler_DaDanhGiadrink);
//        recyclerView2 = view.findViewById(R.id.recycler_drink2);

        mainActivity= (MainActivity) getActivity();


        //slider
//        sliderView= view.findViewById(R.id.imageSlider);
//        SliderAdapter sliderAdapter= new SliderAdapter(images);
//        sliderView.setSliderAdapter(sliderAdapter);
//        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
//        sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINDEPTHTRANSFORMATION);
//        sliderView.startAutoCycle();
        //list product
        LinearLayoutManager gridLayoutManager= new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        LinearLayoutManager gridLayoutManager2= new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        gridLayoutManager.setReverseLayout(true);
        gridLayoutManager2.setReverseLayout(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView2.setLayoutManager(gridLayoutManager2);
//        recyclerView2.setLayoutManager(gridLayoutManager2);
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = dateFormat.format(date);
        FirebaseRecyclerOptions<Request> options =
                new FirebaseRecyclerOptions.Builder<Request>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Request").orderByChild("note").equalTo(Common.currentUser.getPhone()+"_"+"Chưa đánh giá")
                                        .limitToLast(20)
                      , Request.class)
                        .build();
        FirebaseRecyclerOptions<Request> options2 =
                new FirebaseRecyclerOptions.Builder<Request>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Request").orderByChild("note").equalTo(Common.currentUser.getPhone()+"_"+"Đã đánh giá")
                                        .limitToLast(20)
                                , Request.class)
                        .build();




        inforOrder = new InforOrder(options);
        recyclerView.setAdapter(inforOrder);
        inforOrder2 = new InforOrderDaDanhGia(options2);
        recyclerView2.setAdapter(inforOrder2);

        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        inforOrder.startListening();
        inforOrder2.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        inforOrder.stopListening();
        inforOrder2.stopListening();
    }
}