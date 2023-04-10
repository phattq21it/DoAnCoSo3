package com.example.myapplication.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Order;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.Holder>{
    List<Order> list;
    Context context;

    public CartItemAdapter(List<Order> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemcart,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Order order= list.get(position);
        Locale locale= new Locale("en","US");
        NumberFormat fmt= NumberFormat.getCurrencyInstance(locale);
        double price= Double.parseDouble(order.getPrice())*Integer.parseInt(order.getQuantity());
        holder.priceItem.setText(fmt.format(price));

        holder.nameItem.setText(order.getProductName());
        holder.quantityItem.setText(order.getQuantity());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        CircleImageView imageItem;
        TextView nameItem,priceItem,quantityItem;
        public Holder(@NonNull View itemView) {
            super(itemView);
            imageItem= itemView.findViewById(R.id.imageincart);
            nameItem= itemView.findViewById(R.id.nameincart);
            priceItem= itemView.findViewById(R.id.priceincart);
            quantityItem= itemView.findViewById(R.id.quantityincart);
        }
    }
}
