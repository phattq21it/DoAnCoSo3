package com.example.myapplication.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.CartItemAdapter;
import com.example.myapplication.Common.Common;
import com.example.myapplication.DatabaseHelper.DbHelper;
import com.example.myapplication.R;
import com.example.myapplication.Interface.model.Order;
import com.example.myapplication.Interface.model.Request;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class CartFragment extends Fragment {
    TextView txtMuaHang,txtTongTien;
    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();;
    DatabaseReference requestt=firebaseDatabase.getReference("Request");;
    List<Order> cart= new ArrayList<>();
    CartItemAdapter cartItemAdapter;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_cart,container,false);


        // init
        txtTongTien=view.findViewById(R.id.txttongtien);
        recyclerView= view.findViewById(R.id.rcv_cart);
        txtMuaHang= view.findViewById(R.id.txtmuahang);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        cart= new DbHelper(getContext()).getAllOrder();
        cartItemAdapter= new CartItemAdapter(cart,getContext());
        recyclerView.setAdapter(cartItemAdapter);
        double total=0;
        for(Order order : cart) {
            if (order != null && order.getPrice() != null && order.getQuantity() != null) {
                double price = Double.parseDouble(order.getPrice());
                int quantity = Integer.parseInt(order.getQuantity());
                total += price * quantity;
            }
        }
        Locale locale = new Locale("en", "US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        txtTongTien.setText(fmt.format(total));

        txtMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cart.isEmpty()){
                    Toast.makeText(getContext(), "Giỏ hàng của bạn trống", Toast.LENGTH_SHORT).show();
                }else{
                    showArlertDialog();
                }

            }
        });
        return view;
    }
    private void showArlertDialog() {
        AlertDialog.Builder alertDialog= new AlertDialog.Builder(getContext());
        alertDialog.setTitle("One more step!");
        alertDialog.setMessage("Enter your address");


        final EditText edtAddress= new EditText(getContext());
        LinearLayout.LayoutParams lp= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);

        edtAddress.setLayoutParams(lp);
        alertDialog.setView(edtAddress);
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateString = dateFormat.format(date);

                Request request= new Request(Common.currentUser.getPhone(),
                        Common.currentUser.getName(),
                        edtAddress.getText().toString(),
                        txtTongTien.getText().toString(),
                        cart,dateString);
                 requestt.child(String.valueOf(System.currentTimeMillis())).setValue(request);
                for (int i = 0; i < cart.size(); i++) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference productsRef = database.getReference("Item");
                    String nameProductBought = cart.get(i).getProductName();
                    productsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot productSnapshot : snapshot.getChildren()) {
                                String firebaseProductName = productSnapshot.child("name").getValue(String.class);
                                int firebaseProductSales = productSnapshot.child("quantityPurchased").getValue(Integer.class);

                                // So sánh tên sản phẩm mới với tên sản phẩm trong danh sách Firebase
                                if (firebaseProductName.equals(nameProductBought)) {
                                    // Tăng giá trị lượt mua lên 1 và cập nhật giá trị mới vào Firebase
                                    int newSales = firebaseProductSales + 1;
                                    productSnapshot.getRef().child("quantityPurchased").setValue(newSales);
                                    break;
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                 // deletecart
                new DbHelper(getContext()).deleteAllDataInOrderDetailTable();
                cart= new DbHelper(getContext()).getAllOrder();
                cartItemAdapter= new CartItemAdapter(cart,getContext());
                recyclerView.setAdapter(cartItemAdapter);
                txtTongTien.setText("$0.00");



                Toast.makeText(getContext(), "Bạn đã mua hàng thành công" , Toast.LENGTH_SHORT).show();



            }
        });
//        alertDialog.setPositiveButton("NO", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//               dialog.dismiss();
//            }
//        });
        alertDialog.show();

    }
    private void loadListFood() {



        //calculate


    }
}
