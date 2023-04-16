package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Interface.model.Request;
import com.example.myapplication.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
public class InforOrder extends FirebaseRecyclerAdapter<Request,InforOrder.DriverItemViewHolder> {



    public InforOrder(@NonNull FirebaseRecyclerOptions<Request> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull InforOrder.DriverItemViewHolder holder, int position, @NonNull Request model) {
        holder.diachi.setText(model.getAddress());
        holder.ten.setText(model.getName());
        holder.thoigiandat.setText(model.getTime());
        holder.tongtien.setText(model.getTotal());


    }

    @NonNull
    @Override
    public InforOrder.DriverItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.infor_order, parent, false);
        return new InforOrder.DriverItemViewHolder(view);
    }

    public class DriverItemViewHolder extends RecyclerView.ViewHolder {
        private TextView ten,thoigiandat,tongtien,diachi;


        public DriverItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.diachi=itemView.findViewById(R.id.txtAddress);
            this.ten=itemView.findViewById(R.id.txtNameUser);
            this.thoigiandat=itemView.findViewById(R.id.txttime);
            this.tongtien=itemView.findViewById(R.id.txtTotalPrice);
        }
    }
}
