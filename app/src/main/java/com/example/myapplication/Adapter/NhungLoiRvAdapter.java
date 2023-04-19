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
import com.example.myapplication.Interface.model.Comments;
import com.example.myapplication.Interface.model.Drink;
import com.example.myapplication.R;
import com.example.myapplication.fragment.DetailsFragment;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class NhungLoiRvAdapter extends FirebaseRecyclerAdapter<Comments, NhungLoiRvAdapter.Holder> {
    String text;

    public NhungLoiRvAdapter(@NonNull FirebaseRecyclerOptions<Comments> options, String text) {
        super(options);
        this.text = text;
    }

    public NhungLoiRvAdapter(@NonNull FirebaseRecyclerOptions<Comments> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull NhungLoiRvAdapter.Holder holder, int position, @NonNull Comments model) {
        holder.user.setText(model.getUser());
        holder.danhgia.setText(model.getComment());
//            Glide.with(holder.image.getContext()).load(model.getImage()).into(holder.image);

//            holder.image.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
//                    activity.getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.framlayoutman, new DetailsFragment(model.getName(), model.getPrice(), model.getImage(),model.getDescription(),model.getquantityPurchased()))
//                            .addToBackStack(null).commit();
//                }
//            });
//            holder.daban.setText("Đã bán "+model.getquantityPurchased());
//            holder.textSale.setText(text);
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
    public NhungLoiRvAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nhungloirv, parent, false);
        return new Holder(view);
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView user, danhgia;
        private ImageView image;


        public Holder(@NonNull View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.imguserr);
            this.user = itemView.findViewById(R.id.usernamerv);
            this.danhgia = itemView.findViewById(R.id.loidanhgia);
        }


    }
}
