package com.example.adminapp.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminapp.R;
import com.example.adminapp.model.Drink;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ManageItemAdapter extends RecyclerView.Adapter<ManageItemAdapter.Holder> {
    private ArrayList<Drink> mListDrink;
    private Context mContext;
    private String productName;
    FirebaseDatabase database;
    DatabaseReference productRef;
    private String productId;


    public ManageItemAdapter(Context mContext, ArrayList<Drink> mListDrink) {
        this.mContext = mContext;
        this.mListDrink=mListDrink;
    }

    public void setData(ArrayList<Drink> list){
        this.mListDrink=list;
//        notifyDataSetChanged();
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
        holder.txtDrink.setText(drink.getName()) ;
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                productName = drink.getName();
                builder.setMessage("Bạn có muốn xóa sản phẩm "+productName+" không ?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        database = FirebaseDatabase.getInstance();
                        productRef = database.getReference("Item");
                        Query query = productRef.orderByChild("name").equalTo(productName);
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot productSnapshot : snapshot.getChildren()) {
                                    String productId = productSnapshot.getKey();
                                    // xóa sản phẩm từ productId
                                    productRef.child(productId).removeValue();

                                    Toast.makeText(mContext, "Xóa thành công", Toast.LENGTH_SHORT).show();


                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();








            }
        });
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
        Button btnAddItem;
        RecyclerView recyclerView;
        public Holder(@NonNull View itemView) {
            super(itemView);
            btnAddItem= itemView.findViewById(R.id.btnAddItem);
            txtDrink=itemView.findViewById(R.id.txtuser);
            imgEdit=itemView.findViewById(R.id.edituser);
            imgDelete=itemView.findViewById(R.id.deleteuser);
            recyclerView= itemView.findViewById(R.id.rcvitem);

        }
    }
}