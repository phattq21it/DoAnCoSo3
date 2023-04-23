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

        String textComment = " Đã đánh giá :" +model.getText();
        holder.user.setText(model.getUsername() );
       holder.danhgia.setText(textComment);
       holder.time.setText(model.getTime());




    }

    @NonNull
    @Override
    public NhungLoiRvAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nhungloirv, parent, false);
        return new Holder(view);
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView user, danhgia,time;
        private ImageView image;


        public Holder(@NonNull View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.imguserr);
            this.user = itemView.findViewById(R.id.usernamerv);
            this.danhgia = itemView.findViewById(R.id.loidanhgia);
            this.time = itemView.findViewById(R.id.time);

        }


    }
}
