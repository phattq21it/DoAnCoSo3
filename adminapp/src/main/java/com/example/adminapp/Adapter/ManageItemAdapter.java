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
import com.example.adminapp.model.Drink;

import java.util.ArrayList;

public class ManageItemAdapter extends RecyclerView.Adapter<ManageItemAdapter.Holder> {
    private ArrayList<Drink> mListDrink;
    private Context mContext;
    public ManageItemAdapter(Context mContext, ArrayList<Drink> mListDrink) {
        this.mContext = mContext;
        this.mListDrink=mListDrink;
    }

    public void setData(ArrayList<Drink> list){
        this.mListDrink=list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ManageItemAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_manager,parent,false);
        return new ManageItemAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ManageItemAdapter.Holder holder, int position) {
        Drink drink=mListDrink.get(position);
        if(drink==null){
            return;
        }
        holder.txtDrink.setText(drink.getName());
    }

    @Override
    public int getItemCount() {
        if(mListDrink!=null){
            return mListDrink.size();
        }
        return 0;
    }

    //    Context context;
//
//    public ManagerRecycleAdapter(Context context,@NonNull FirebaseRecyclerOptions<Drink> options) {
//
//        super(options);
//        this.context=context;
//    }
//
//    @Override
//    protected void onBindViewHolder(@NonNull Holder holder, int position, @NonNull Drink model) {
////        Toast.makeText(context, "Hihi", Toast.LENGTH_SHORT).show();
////        holder.txtDrink.setText(model.getName());
//    }
//
//    @NonNull
//    @Override
//    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_manager, parent, false);
//        return new Holder(view);
//    }
//
    public class Holder extends RecyclerView.ViewHolder{
        TextView txtDrink;
        ImageView imgEdit,imgDelete;
        public Holder(@NonNull View itemView) {
            super(itemView);

            txtDrink=itemView.findViewById(R.id.txtuser);
            imgEdit=itemView.findViewById(R.id.edituser);
            imgDelete=itemView.findViewById(R.id.deleteuser);
        }
    }
}
