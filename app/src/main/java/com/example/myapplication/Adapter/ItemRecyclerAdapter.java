package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.fragment.DetailsFragment;
import com.example.myapplication.Interface.model.Drink;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class ItemRecyclerAdapter extends FirebaseRecyclerAdapter<Drink, ItemRecyclerAdapter.DriverItemViewHolder> {
    String text;

    public ItemRecyclerAdapter(@NonNull FirebaseRecyclerOptions<Drink> options, String text) {
        super(options);
        this.text = text;
    }

    public ItemRecyclerAdapter(@NonNull FirebaseRecyclerOptions<Drink> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull DriverItemViewHolder holder, int position, @NonNull Drink model) {
        holder.name.setText(model.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###.###");
        decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.getDefault()));
        String replacedNumber = decimalFormat.format(Integer.parseInt(model.getPrice())).replace(",", ".");
        holder.price.setText(replacedNumber);
        Glide.with(holder.image.getContext()).load(model.getImage()).into(holder.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.framlayoutman, new DetailsFragment(model.getName(), model.getPrice(), model.getImage(),model.getDescription(),model.getquantityPurchased()))
                        .addToBackStack(null).commit();
            }
        });
        holder.daban.setText("Đã bán "+model.getquantityPurchased());
        holder.textSale.setText(text);
//        holder.cart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!model.isAddToCart()) {
//                    iClickAddToCartListener.onClickAddToCart(holder.cart, model);
//                }
//            }
//        });
    }

    @NonNull
    @Override
    public DriverItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drinkitem, parent, false);
        return new DriverItemViewHolder(view);
    }

    public class DriverItemViewHolder extends RecyclerView.ViewHolder {
        private TextView price,daban;
        private TextView name,textSale;
        private ImageView image;


        public DriverItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textSale = itemView.findViewById(R.id.sale);
            this.price = itemView.findViewById(R.id.txtPrice);
            this.name = itemView.findViewById(R.id.txtName);
            this.image = itemView.findViewById(R.id.imageview);
            this.daban=itemView.findViewById(R.id.txtdaban);
            this.textSale=itemView.findViewById(R.id.sale);
        }


    }
}

