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
import com.example.adminapp.model.User;

import java.util.ArrayList;


public class ManagerUserAdapter extends RecyclerView.Adapter<ManagerUserAdapter.Holder> {
     private ArrayList<User> mListUser;
    private Context mContext;
    public ManagerUserAdapter(Context mContext, ArrayList<User> mListUser) {
        this.mContext = mContext;
        this.mListUser=mListUser;
    }

    public void setData(ArrayList<User> list){
        this.mListUser=list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_manager,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        User user=mListUser.get(position);
        if(user==null){
            return;
        }
        holder.txtUser.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        if(mListUser!=null){
            return mListUser.size();
        }
        return 0;
    }

    //    Context context;
//
//    public ManagerRecycleAdapter(Context context,@NonNull FirebaseRecyclerOptions<User> options) {
//
//        super(options);
//        this.context=context;
//    }
//
//    @Override
//    protected void onBindViewHolder(@NonNull Holder holder, int position, @NonNull User model) {
////        Toast.makeText(context, "Hihi", Toast.LENGTH_SHORT).show();
////        holder.txtUser.setText(model.getName());
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
        TextView txtUser;
        ImageView imgEdit,imgDelete;
        public Holder(@NonNull View itemView) {
            super(itemView);

            txtUser=itemView.findViewById(R.id.txtuser);
            imgEdit=itemView.findViewById(R.id.edituser);
            imgDelete=itemView.findViewById(R.id.deleteuser);
        }
    }

}
