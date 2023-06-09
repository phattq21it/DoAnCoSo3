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
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ItemRecyclerAdapter extends FirebaseRecyclerAdapter<Drink, ItemRecyclerAdapter.DriverItemViewHolder> {
    private IClickAddToCartListener iClickAddToCartListener;

    public interface IClickAddToCartListener {
        void onClickAddToCart(ImageView imageView, Drink drink);
    }

    public void setData(IClickAddToCartListener iClickAddToCartListener) {
        this.iClickAddToCartListener = iClickAddToCartListener;

    }

    public ItemRecyclerAdapter(@NonNull FirebaseRecyclerOptions<Drink> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull DriverItemViewHolder holder, int position, @NonNull Drink model) {
        holder.name.setText(model.getName());

        holder.price.setText(model.getPrice());
        Glide.with(holder.image.getContext()).load(model.getImage()).into(holder.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.framlayoutman, new DetailsFragment(model.getName(), model.getPrice(), model.getImage()))
                        .addToBackStack(null).commit();
            }
        });
        holder.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!model.isAddToCart()) {
                    iClickAddToCartListener.onClickAddToCart(holder.cart, model);
                }
            }
        });
    }

    @NonNull
    @Override
    public DriverItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drinkitemad, parent, false);
        return new DriverItemViewHolder(view);
    }

    public class DriverItemViewHolder extends RecyclerView.ViewHolder {
        private TextView price;
        private TextView name;
        private ImageView image, cart;
        TextView soluongsanpham,doanhthuhomnay,sanphammuanhieu;

        public DriverItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.cart = itemView.findViewById(R.id.imgcart);
            this.price = itemView.findViewById(R.id.txtPrice);
            this.name = itemView.findViewById(R.id.txtName);
            this.image = itemView.findViewById(R.id.imageview);

        }
    }
}

