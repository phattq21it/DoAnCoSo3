package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Common.Common;
import com.example.myapplication.Interface.model.Drink;
import com.example.myapplication.Interface.model.Order;
import com.example.myapplication.Interface.model.Request;
import com.example.myapplication.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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
        EditText edtDanhGia;
        Button btnRv;

        
        public Holderr(@NonNull View itemView) {
            super(itemView);
            rvgia=itemView.findViewById(R.id.rwgiasp);
            rvten=itemView.findViewById(R.id.rwtensp);
            btnRv=itemView.findViewById(R.id.btnreview);
            edtDanhGia = itemView.findViewById(R.id.rwdanhgia);

        }
    }
}
