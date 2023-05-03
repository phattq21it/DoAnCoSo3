package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.DatabaseHelper.DbHelper;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.Interface.model.Order;
import com.example.myapplication.fragment.CartFragment;
import com.example.myapplication.fragment.DetailsFragment;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.Holder> {
    List<Order> list;
    Context context;


    public CartItemAdapter(List<Order> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemcart, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Order order = list.get(position);
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");

        double price = Double.parseDouble(order.getPrice()) * Integer.parseInt(order.getQuantity());
        String replacedNumber = decimalFormat.format(price).replace(",", ".");

        holder.priceItem.setText(replacedNumber);
        holder.nameItem.setText(order.getProductName());
        holder.quantityItem.setText(String.valueOf(order.getQuantity()));
        Glide.with(holder.imageItem.getContext()).load(order.getImage()).into(holder.imageItem);

//        holder.cong.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                holder.quantityItem.setText(String.valueOf(count[0]++));
//            }
//        });
//        holder.tru.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(count[0] <=0){
//                    count[0] =0;
//                }else{
//                    --count[0];
//                }
//                holder.quantityItem.setText(String.valueOf(count[0]));
//            }
//        });
        holder.deleteitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DbHelper(v.getContext()).deleteItem(order.getProductName());
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.framlayoutman, new CartFragment())
                        .addToBackStack(null).commit();
                Toast.makeText(context, "Đã xoá sản phẩm khỏi giỏ hàng", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        CircleImageView imageItem;
        TextView nameItem, priceItem, quantityItem, tru;
        ImageView deleteitem;

        public Holder(@NonNull View itemView) {
            super(itemView);
            imageItem = itemView.findViewById(R.id.imageincart);
            nameItem = itemView.findViewById(R.id.nameincart);
            priceItem = itemView.findViewById(R.id.priceincart);
            quantityItem = itemView.findViewById(R.id.soluongitem);
            tru = itemView.findViewById(R.id.tru);
            deleteitem = itemView.findViewById(R.id.deleteitem);
        }
    }

}
