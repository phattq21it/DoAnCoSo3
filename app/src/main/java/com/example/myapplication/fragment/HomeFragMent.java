package com.example.myapplication.fragment;

import static com.example.myapplication.fragment.CartFragment.countCart;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.CartItemAdapter;
import com.example.myapplication.Adapter.CategoriAdapter;
import com.example.myapplication.Adapter.ItemRecyclerAdapter;
import com.example.myapplication.Adapter.SliderAdapter;
import com.example.myapplication.DatabaseHelper.DbHelper;
import com.example.myapplication.Interface.model.Order;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.Interface.model.Category;
import com.example.myapplication.Interface.model.Drink;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragMent extends Fragment {
    private RecyclerView recyclerNew,recyclerBanChay,rcvcategory,rcvAllItem;
    ItemRecyclerAdapter ItemMoiRaMat,ItemBanChay,AllItemAdapter;
    CategoriAdapter categoriAdapter;
    ArrayList<Category> categoryArrayList;
    DatabaseReference databaseReference;
    List<Order> cart= new ArrayList<>();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    private MainActivity mainActivity;
    private MenuItem menuItem;
    private EditText edtsearch;
    private Button imgsearch;


    int[] images={R.drawable.screenshot_2023_04_10_095739,
            R.drawable.screenshot_2023_04_10_100148,R.drawable.banner3,R.drawable.banner4};

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
        View view=  inflater.inflate(R.layout.fragment_home,container,false);
        recyclerNew = view.findViewById(R.id.recycler_new);
        recyclerBanChay = view.findViewById(R.id.recycler_banchay);
        rcvcategory= view.findViewById(R.id.recycler_category_home);
        rcvAllItem=view.findViewById(R.id.recycler_allitem);
        imgsearch= view.findViewById(R.id.imgsearch1);

        edtsearch= view.findViewById(R.id.edtsearch);
        mainActivity= (MainActivity) getActivity();


        cart= new DbHelper(getContext()).getAllOrder();

        int countc=0;
        for(Order order : cart) {
            if (order != null && order.getPrice() != null && Integer.parseInt(order.getQuantity()) >=1) {
                int quantity = Integer.parseInt(order.getQuantity());
                countc+=quantity;
            }
        }
        mainActivity.setCountProductInCart(countc);
        hideKeyboard(mainActivity);
        //search

        imgsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query= edtsearch.getText().toString();
                Bundle bundle= new Bundle();
                bundle.putString("df2",query);
                getParentFragmentManager().setFragmentResult("datasearch",bundle);
                hideKeyboard(mainActivity);
                replaceFragment(new ResultSearchFragment());
//                Toast.makeText(mainActivity, "cccc", Toast.LENGTH_SHORT).show();
            }
        });
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
        GridLayoutManager gridLayoutManager3= new GridLayoutManager(getContext(),2);
        recyclerNew.setLayoutManager(gridLayoutManager);
        recyclerBanChay.setLayoutManager(gridLayoutManager2);
        rcvAllItem.setLayoutManager(gridLayoutManager3);

        FirebaseRecyclerOptions<Drink> OptionNew =
                new FirebaseRecyclerOptions.Builder<Drink>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Item").orderByChild("created_at").limitToLast(5), Drink.class)
                        .build();
        FirebaseRecyclerOptions<Drink> optionBanChay =
                new FirebaseRecyclerOptions.Builder<Drink>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Item").orderByChild("quantityPurchased").startAt(5),Drink.class)
                        .build();
        FirebaseRecyclerOptions<Drink> optionAllitem =
                new FirebaseRecyclerOptions.Builder<Drink>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Item").limitToFirst(30),Drink.class)
                        .build();




        AllItemAdapter= new ItemRecyclerAdapter(optionAllitem,"Yêu thích");
        ItemMoiRaMat = new ItemRecyclerAdapter(OptionNew,"Mới ra mắt");
        ItemBanChay = new ItemRecyclerAdapter(optionBanChay,"Bán chạy nhất");
        recyclerNew.setAdapter(ItemMoiRaMat);
        recyclerBanChay.setAdapter(ItemBanChay);
        rcvAllItem.setAdapter(AllItemAdapter);

        //category
        databaseReference=FirebaseDatabase.getInstance().getReference("Categories");
        categoryArrayList= new ArrayList<>();
        categoriAdapter= new CategoriAdapter(categoryArrayList,getContext());
        rcvcategory.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        rcvcategory.setAdapter(categoriAdapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Category category= dataSnapshot.getValue(Category.class);
                    categoryArrayList.add(category);
                }
                categoriAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //setlistener
//        ItemMoiRaMat.setData(new ItemRecyclerAdapter.IClickAddToCartListener() {
//            @SuppressLint("ResourceAsColor")
//            @Override
//            public void onClickAddToCart(ImageView imageView, Drink drink) {
//                mainActivity.setCountProductInCart(mainActivity.getmCountProduct()+1);
////                if(imageView.)
//                imageView.setEnabled(false);
//                imageView.setColorFilter(ContextCompat.getColor(view.getContext(),R.color.DimGray), PorterDuff.Mode.SRC_IN);
//            }
//        });
//        ItemBanChay.setData(new ItemRecyclerAdapter.IClickAddToCartListener() {
//            @SuppressLint("ResourceAsColor")
//            @Override
//            public void onClickAddToCart(ImageView imageView, Drink drink) {
//                mainActivity.setCountProductInCart(mainActivity.getmCountProduct()+1);
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
        ItemMoiRaMat.startListening();
        ItemBanChay.startListening();
        AllItemAdapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        ItemMoiRaMat.stopListening();
        ItemBanChay.stopListening();
        AllItemAdapter.stopListening();
    }
    private void replaceFragment(Fragment fragment) {

        mainActivity= (MainActivity) getActivity();
        FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framlayoutman, fragment);
        fragmentTransaction.commit();
    }


}
