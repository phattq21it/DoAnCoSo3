package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Common.Common;
import com.example.myapplication.Interface.model.Order;
import com.example.myapplication.Interface.model.Request;
import com.example.myapplication.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.Holderr> {
    List<Order> orders;

    public ReviewAdapter(List<Order> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public Holderr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holderr(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holderr holder, int position) {
        Order order=orders.get(position);
        holder.rvten.setText(order.getProductName());
        holder.rvgia.setText(order.getPrice());
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class Holderr extends RecyclerView.ViewHolder{
        TextView rvten,rvgia;
        Button btnRv;
        public Holderr(@NonNull View itemView) {
            super(itemView);
            rvgia=itemView.findViewById(R.id.rwgiasp);
            rvten=itemView.findViewById(R.id.rwtensp);
            btnRv=itemView.findViewById(R.id.btnreview);
        }
    }
}
