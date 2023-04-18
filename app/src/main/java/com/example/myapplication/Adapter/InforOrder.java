package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Interface.model.Order;
import com.example.myapplication.Interface.model.Request;
import com.example.myapplication.R;
import com.example.myapplication.fragment.DetailsFragment;
import com.example.myapplication.fragment.ReviewFragment;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

public class InforOrder extends FirebaseRecyclerAdapter<Request,InforOrder.DriverItemViewHolder> {


    public InforOrder(@NonNull FirebaseRecyclerOptions<Request> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull InforOrder.DriverItemViewHolder holder, int position, @NonNull Request model) {
        holder.diachi.setText(model.getAddress());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < model.getFood().size(); i++) {
            sb.append(model.getFood().get(i).getProductName());
            if (i < model.getFood().size() - 1) {
                sb.append(",");
            }
        }



        holder.ten.setText(sb);
        holder.thoigiandat.setText(model.getTime());
        holder.tongtien.setText(model.getTotal());
        holder.btnDanhgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.framlayoutman, new ReviewFragment(model.getFood()))
                        .addToBackStack(null).commit();
            }
        });


    }

    @NonNull
    @Override
    public InforOrder.DriverItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.infor_order, parent, false);
        return new InforOrder.DriverItemViewHolder(view);
    }

    public class DriverItemViewHolder extends RecyclerView.ViewHolder {
        private TextView ten,thoigiandat,tongtien,diachi;
        Button btnDanhgia;


        public DriverItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.diachi=itemView.findViewById(R.id.txtAddress);
            this.ten=itemView.findViewById(R.id.txtNameUser);
            this.thoigiandat=itemView.findViewById(R.id.txttime);
            this.tongtien=itemView.findViewById(R.id.txtTotalPrice);
            btnDanhgia=itemView.findViewById(R.id.btnDanhGiaSanPham);
        }
    }
}
