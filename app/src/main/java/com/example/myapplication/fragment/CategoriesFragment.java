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
import com.example.myapplication.model.Drink;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class CategoriesFragment extends Fragment {
    private RecyclerView recyclerView,recyclerView2;
    ItemRecyclerAdapter itemRecycleAdapter;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    RecyclerView recview;
    private MainActivity mainActivity;
    private MenuItem menuItem;
    private SearchView searchView;


    int[] images={R.drawable.one,
            R.drawable.two,
            R.drawable.three,
            R.drawable.four,
            R.drawable.five,
            R.drawable.six};

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

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menusearch,menu);
        menuItem=menu.findItem(R.id.search_view);
        searchView= (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setIconified(true);

        SearchManager searchManager= (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Bundle bundle= new Bundle();
                bundle.putString("df2",query);
                getParentFragmentManager().setFragmentResult("datasearch",bundle);
                hideKeyboard(mainActivity);
                replaceFragment(new ResultSearchFragment());
                Log.d(query,query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_categories,container,false);
        recyclerView = view.findViewById(R.id.rcv_category_list1);
        recyclerView2 = view.findViewById(R.id.rcv_category_list2);

        mainActivity= (MainActivity) getActivity();


        //slider
        sliderView= view.findViewById(R.id.image_slider_category);
        SliderAdapter sliderAdapter= new SliderAdapter(images);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINDEPTHTRANSFORMATION);
        sliderView.startAutoCycle();
        //list product
        GridLayoutManager gridLayoutManager= new GridLayoutManager(getContext(),2);
        GridLayoutManager gridLayoutManager2= new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView2.setLayoutManager(gridLayoutManager2);

        FirebaseRecyclerOptions<Drink> options =
                new FirebaseRecyclerOptions.Builder<Drink>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Drink"), Drink.class)
                        .build();


        itemRecycleAdapter = new ItemRecyclerAdapter(options);
        recyclerView.setAdapter(itemRecycleAdapter);
        recyclerView2.setAdapter(itemRecycleAdapter);

        //setlistener
        itemRecycleAdapter.setData(new ItemRecyclerAdapter.IClickAddToCartListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClickAddToCart(ImageView imageView, Drink drink) {
                mainActivity.setCountProductInCart(mainActivity.getmCountProduct()+1);
//                if(imageView.)
                imageView.setEnabled(false);
                imageView.setBackgroundColor(com.nex3z.notificationbadge.R.color.red);
            }
        });

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
        itemRecycleAdapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        itemRecycleAdapter.stopListening();
    }
    private void replaceFragment(Fragment fragment) {

        mainActivity= (MainActivity) getActivity();
        FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framlayoutman, fragment);
        fragmentTransaction.commit();
    }

}
