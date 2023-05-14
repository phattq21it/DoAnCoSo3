package com.example.adminapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminapp.R;
import com.example.adminapp.model.Categories;
import com.example.adminapp.model.Drink;

import java.util.ArrayList;

public class ManageCategoriesAdapter extends RecyclerView.Adapter<ManageCategoriesAdapter.Holder> {
    private ArrayList<Categories> mListCategories;
    private Context mContext;
    public ManageCategoriesAdapter(Context mContext, ArrayList<Categories> mListCategories) {
        this.mContext = mContext;
        this.mListCategories=mListCategories;
    }

    public void setData(ArrayList<Categories> list){
        this.mListCategories=list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ManageCategoriesAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_manager,parent,false);

        return new ManageCategoriesAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ManageCategoriesAdapter.Holder holder, int position) {
        Categories categories=mListCategories.get(position);
        if(categories==null){
            return;
        }
        holder.txtNameCategory.setText(categories.getName());

    }

    @Override
    public int getItemCount() {
        if(mListCategories!=null){
            return mListCategories.size();
        }
        return 0;
    }


    public class Holder extends RecyclerView.ViewHolder{
        TextView txtNameCategory;

        public Holder(@NonNull View itemView) {
            super(itemView);
            txtNameCategory= itemView.findViewById(R.id.txtuser);

        }
    }
}
