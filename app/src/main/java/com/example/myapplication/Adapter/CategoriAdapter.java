package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.Interface.model.Category;

import java.util.List;

public class CategoriAdapter extends RecyclerView.Adapter<CategoriAdapter.Holder> {
    List<Category> categoryList;
    Context context;

    public CategoriAdapter(List<Category> categoryList, Context context) {
        this.categoryList = categoryList;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Category ca= categoryList.get(position);
        if(ca==null){
            return;
        }
        holder.txtNameCa.setText(ca.getName());
        Glide.with(holder.logo.getContext()).load(ca.getImage()).into(holder.logo);

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        ImageView logo;
        TextView txtNameCa;
        public Holder(@NonNull View itemView) {
            super(itemView);
            logo= itemView.findViewById(R.id.logo_category);
            txtNameCa= itemView.findViewById(R.id.namecategory);
        }
    }
}
