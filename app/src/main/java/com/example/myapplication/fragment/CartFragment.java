package com.example.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.CartAdapter;
import com.example.myapplication.Database.Database;
import com.example.myapplication.R;
import com.example.myapplication.model.Order;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

//import com.example.myapplication.R;

public class CartFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference request;
    TextView txtTotalPrice;
    Button btnPlaceOrder;
    List<Order> cart = new ArrayList<>();
    CartAdapter adapter ;

    @Nullable

//    private void loadListFood(){
//        cart= new Database((View.OnClickListener) this).getCarts();
//        adapter = new CartAdapter(cart,this);
//        recyclerView.setAdapter(adapter);
//        // tinh tonhg tien
//        int total = 0;
//        for(Order order:cart)
//            total+=(Integer.parseInt(order.getPrice()))*(Integer.parseInt(order.getQuantity()));
//        Locale locale = new Locale("en","US");
//        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
//        txtTotalPrice.setText(fmt.format(total));
//    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_cart,container,false);
        database = FirebaseDatabase.getInstance();
        request = database.getReference("Requests");

        //init
        recyclerView = (RecyclerView)view.findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,true);
        recyclerView.setLayoutManager(layoutManager);

        txtTotalPrice = (TextView) view.findViewById(R.id.total);
        btnPlaceOrder = (Button)view.findViewById(R.id.btnPlaceOrder);

        loadListFood();

        return  view;



    }

        private void loadListFood(){
        cart= new Database(getContext()).getCarts();
        adapter = new CartAdapter(cart,getContext());
        recyclerView.setAdapter(adapter);
        // tinh tonhg tien
        int total = 0;
        for(Order order:cart)
            total+=(Integer.parseInt(order.getPrice()))*(Integer.parseInt(order.getQuantity()));
        Locale locale = new Locale("en","US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        txtTotalPrice.setText(fmt.format(total));
    }

}
