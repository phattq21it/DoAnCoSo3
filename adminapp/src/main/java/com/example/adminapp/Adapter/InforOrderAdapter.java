package com.example.adminapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.adminapp.R;
import com.example.adminapp.fragment.DetailsFragment;
import com.example.adminapp.model.Drink;
import com.example.adminapp.model.Request;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.text.DecimalFormat;

public class InforOrderAdapter extends FirebaseRecyclerAdapter<Request, InforOrderAdapter.DriverItemViewHolder> {



    public InforOrderAdapter(@NonNull FirebaseRecyclerOptions<Request> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull DriverItemViewHolder holder, int position, @NonNull Request model) {
        holder.diachi.setText(model.getAddress());
        holder.ten.setText(model.getName());
        holder.thoigiandat.setText(model.getTime());
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        String replacedNumber = decimalFormat.format(Integer.parseInt(model.getTotal())).replace(",", ".");

        holder.tongtien.setText(replacedNumber);

        String maDHtext = String.valueOf(model.getCurrentTimeMillis()); // hoặc Long.toString(myLong);
        holder.madonhang.setText(maDHtext);


    }

    @NonNull
    @Override
    public DriverItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.infor_orderad, parent, false);
        return new DriverItemViewHolder(view);
    }

    public class DriverItemViewHolder extends RecyclerView.ViewHolder {
        private TextView madonhang,ten,thoigiandat,tongtien,diachi;


        public DriverItemViewHolder(@NonNull View itemView) {
            super(itemView);
           this.diachi=itemView.findViewById(R.id.txtAddress);
           this.ten=itemView.findViewById(R.id.txtNameUser);
           this.thoigiandat=itemView.findViewById(R.id.txttime);
           this.tongtien=itemView.findViewById(R.id.txtTotalPrice);
           this.madonhang=itemView.findViewById(R.id.txtIDdonhang);
        }
    }
}

