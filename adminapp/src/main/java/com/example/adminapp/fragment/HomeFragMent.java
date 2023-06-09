package com.example.adminapp.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminapp.Adapter.InforOrderAdapter;
import com.example.adminapp.Adapter.ItemRecyclerAdapter;
import com.example.adminapp.AdminActivity;
import com.example.adminapp.R;
import com.example.adminapp.model.Drink;
import com.example.adminapp.model.Request;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class HomeFragMent extends Fragment {
    private RecyclerView recyclerView,recyclerView2;
    InforOrderAdapter inforOrderAdapter;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    RecyclerView recview;
    private AdminActivity adminActivity;
    private MenuItem menuItem;
    private SearchView searchView;


//    int[] images={R.drawable.one,
//            R.drawable.two,
//            R.drawable.three,
//            R.drawable.four,
//            R.drawable.five,
//            R.drawable.six};
//
//    SliderAdapter sliderAdapter;
//    SliderView sliderView;
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
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        inflater.inflate(R.menu.menusearch,menu);
//        menuItem=menu.findItem(R.id.search_view);
//        searchView= (SearchView) MenuItemCompat.getActionView(menuItem);
//
//        searchView.setIconified(true);
//
//        SearchManager searchManager= (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//                Bundle bundle= new Bundle();
//                bundle.putString("df2",query);
//                getParentFragmentManager().setFragmentResult("datasearch",bundle);
//                hideKeyboard(adminActivity);
//                replaceFragment(new ResultSearchFragment());
//                Log.d(query,query);
//
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return true;
//            }
//        });
//
//        super.onCreateOptionsMenu(menu, inflater);
//    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_homead,container,false);
        recyclerView = view.findViewById(R.id.recycler_drink);
//        recyclerView2 = view.findViewById(R.id.recycler_drink2);

        adminActivity= (AdminActivity) getActivity();


        //slider
//        sliderView= view.findViewById(R.id.imageSlider);
//        SliderAdapter sliderAdapter= new SliderAdapter(images);
//        sliderView.setSliderAdapter(sliderAdapter);
//        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
//        sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINDEPTHTRANSFORMATION);
//        sliderView.startAutoCycle();
        //list product
        LinearLayoutManager gridLayoutManager= new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        GridLayoutManager gridLayoutManager2= new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
//        recyclerView2.setLayoutManager(gridLayoutManager2);

        FirebaseRecyclerOptions<Request> options =
                new FirebaseRecyclerOptions.Builder<Request>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Request"), Request.class)
                        .build();


        inforOrderAdapter = new InforOrderAdapter(options);
        recyclerView.setAdapter(inforOrderAdapter);
//        recyclerView2.setAdapter(inforOrderAdapter);

        //setlistener
//        itemRecycleAdapter.setData(new ItemRecyclerAdapter.IClickAddToCartListener() {
//            @SuppressLint("ResourceAsColor")
//            @Override
//            public void onClickAddToCart(ImageView imageView, Drink drink) {
//                adminActivity.setCountProductInCart(adminActivity.getmCountProduct()+1);
////                if(imageView.)
//                imageView.setEnabled(false);
//                imageView.setColorFilter(ContextCompat.getColor(view.getContext(),R.color.DimGray), PorterDuff.Mode.SRC_IN);
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
        inforOrderAdapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        inforOrderAdapter.stopListening();
    }
    private void replaceFragment(Fragment fragment) {

        adminActivity= (AdminActivity) getActivity();
        FragmentManager fragmentManager = adminActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framlayoutman, fragment);
        fragmentTransaction.commit();
    }
}
