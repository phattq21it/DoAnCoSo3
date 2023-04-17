package com.example.myapplication.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.ItemRecyclerAdapter;
import com.example.myapplication.Adapter.SliderAdapter;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.Interface.model.Drink;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class CategoriesFragment extends Fragment {
    private RecyclerView rcvcafe,rcvanvat,rcvhotdog,rcvhaisan;
    ItemRecyclerAdapter adapterCafe,adapterAnVat,adapterHotDog,adapterHaiSan;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    RecyclerView recview;
    private MainActivity mainActivity;
    private MenuItem menuItem;
    private SearchView searchView;

    int[] images={R.drawable.screenshot_2023_04_10_095739,
            R.drawable.screenshot_2023_04_10_100148,R.drawable.banner3,R.drawable.banner4};

    SliderAdapter sliderAdapter;
    SliderView sliderView;
    public CategoriesFragment() {

    }

    public static CategoriesFragment newInstance(String param1, String param2) {
        CategoriesFragment fragment = new CategoriesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }



    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_categories,container,false);
        rcvcafe = view.findViewById(R.id.rcvcafe);
        rcvanvat = view.findViewById(R.id.rcvanvat);
        rcvhaisan=view.findViewById(R.id.rcvhaisan);
        rcvhotdog=view.findViewById(R.id.rcvhotdog);

        mainActivity= (MainActivity) getActivity();


        //slider
        sliderView= view.findViewById(R.id.image_slider_category);
        SliderAdapter sliderAdapter= new SliderAdapter(images);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINDEPTHTRANSFORMATION);
        sliderView.startAutoCycle();
        //list product
        LinearLayoutManager linearLayoutManager1= new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager2= new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager3= new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager4= new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        rcvcafe.setLayoutManager(linearLayoutManager1);
        rcvhaisan.setLayoutManager(linearLayoutManager2);
        rcvhotdog.setLayoutManager(linearLayoutManager3);
        rcvanvat.setLayoutManager(linearLayoutManager4);

        FirebaseRecyclerOptions<Drink> optionAnVat =
                new FirebaseRecyclerOptions.Builder<Drink>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Item").orderByChild("type").equalTo("Đồ ăn vặt"), Drink.class)
                        .build();
        FirebaseRecyclerOptions<Drink> optionHaiSan =
                new FirebaseRecyclerOptions.Builder<Drink>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Item").orderByChild("type").equalTo("Hải Sản"), Drink.class)
                        .build();
        FirebaseRecyclerOptions<Drink> optionHotDog =
                new FirebaseRecyclerOptions.Builder<Drink>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Item").orderByChild("type").equalTo("Hot Dog"), Drink.class)
                        .build();
        FirebaseRecyclerOptions<Drink> optionCafe =
                new FirebaseRecyclerOptions.Builder<Drink>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Item").orderByChild("type").equalTo("Cà Phê"), Drink.class)
                        .build();


        adapterAnVat = new ItemRecyclerAdapter(optionAnVat);
        adapterHaiSan = new ItemRecyclerAdapter(optionHaiSan);
        adapterCafe = new ItemRecyclerAdapter(optionCafe);
        adapterHotDog = new ItemRecyclerAdapter(optionHotDog);



        rcvhotdog.setAdapter(adapterHotDog);
        rcvcafe.setAdapter(adapterCafe);
        rcvanvat.setAdapter(adapterAnVat);
        rcvhaisan.setAdapter(adapterHaiSan);

        //setlistener
//        itemRecycleAdapterFood.setData(new ItemRecyclerAdapter.IClickAddToCartListener() {
//            @SuppressLint("ResourceAsColor")
//            @Override
//            public void onClickAddToCart(ImageView imageView, Drink drink) {
//                mainActivity.setCountProductInCart(mainActivity.getmCountProduct()+1);
////                if(imageView.)
//                imageView.setEnabled(false);
//                imageView.setBackgroundColor(com.nex3z.notificationbadge.R.color.red);
//            }
//        });
//        itemRecycleAdapterDrink.setData(new ItemRecyclerAdapter.IClickAddToCartListener() {
//            @SuppressLint("ResourceAsColor")
//            @Override
//            public void onClickAddToCart(ImageView imageView, Drink drink) {
//                mainActivity.setCountProductInCart(mainActivity.getmCountProduct()+1);
////                if(imageView.)
//                imageView.setEnabled(false);
//                imageView.setBackgroundColor(com.nex3z.notificationbadge.R.color.red);
//            }
//        });


        return view;

    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.findViewById(android.R.id.content);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        adapterHotDog.startListening();
        adapterAnVat.startListening();
        adapterHaiSan.startListening();
        adapterCafe.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        adapterHotDog.stopListening();
        adapterAnVat.stopListening();
        adapterHaiSan.stopListening();
        adapterCafe.stopListening();
    }
    private void replaceFragment(Fragment fragment) {

        mainActivity= (MainActivity) getActivity();
        FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framlayoutman, fragment);
        fragmentTransaction.commit();
    }

}


/////////////////////////