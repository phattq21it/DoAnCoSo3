package com.example.myapplication.fragment;

    import android.annotation.SuppressLint;
    import android.os.Bundle;

    import androidx.annotation.NonNull;
    import androidx.fragment.app.Fragment;
    import androidx.fragment.app.FragmentManager;
    import androidx.fragment.app.FragmentTransaction;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
    import android.widget.Button;
    import android.widget.ImageView;
import android.widget.TextView;
    import android.widget.Toast;

    import com.bumptech.glide.Glide;
    import com.example.myapplication.Adapter.NhungLoiRvAdapter;
    import com.example.myapplication.Common.Common;
    import com.example.myapplication.DatabaseHelper.DbHelper;
    import com.example.myapplication.Interface.model.Comments;
    import com.example.myapplication.Interface.model.Drink;
    import com.example.myapplication.MainActivity;
    import com.example.myapplication.R;
    import com.example.myapplication.Interface.model.Order;
    import com.firebase.ui.database.FirebaseRecyclerOptions;
    import com.google.firebase.database.DataSnapshot;
    import com.google.firebase.database.DatabaseError;
    import com.google.firebase.database.FirebaseDatabase;
    import com.google.firebase.database.ValueEventListener;

public class DetailsFragment extends Fragment {


        DbHelper dbHelper;
        private static final String ARG_PARAM1 = "param1";
        private static final String ARG_PARAM2 = "param2";
        TextView btngiam,btntang;
        Button btnThemGioHang;
        TextView txtsoluong;
        ImageView waybackhome;
        NhungLoiRvAdapter nhungLoiRvAdapter;
        int count=0;
        private String mParam1;
        private String mParam2;
        String name, price, image,description;
        int soluotban;
        RecyclerView rcvDanhGia;
        MainActivity mainActivity;
        public DetailsFragment() {

        }

        public DetailsFragment(String name, String price, String image,String description,int soluotban) {
            this.soluotban=soluotban;
            this.name=name;
            this.price=price;
            this.image=image;
            this.description=description;
        }

        public static DetailsFragment newInstance(String param1, String param2) {
            DetailsFragment fragment = new DetailsFragment();
            Bundle args = new Bundle();
            args.putString(ARG_PARAM1, param1);
            args.putString(ARG_PARAM2, param2);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                mParam1 = getArguments().getString(ARG_PARAM1);
                mParam2 = getArguments().getString(ARG_PARAM2);
            }

        }

        @SuppressLint("MissingInflatedId")
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            mainActivity= (MainActivity) getActivity();
            dbHelper= new DbHelper(getContext());
            View view=inflater.inflate(R.layout.fragment_details, container, false);

            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView imageholder=view.findViewById(R.id.imageholder);
            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView nameholder=view.findViewById(R.id.nameholder);
            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView priceholder=view.findViewById(R.id.priceholder);
            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView txtmota=view.findViewById(R.id.txtmota);
            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView txtluotban=view.findViewById(R.id.luotban);
            rcvDanhGia= view.findViewById(R.id.rcv_rvv);
            rcvDanhGia.setLayoutManager(new LinearLayoutManager(getContext()));


            FirebaseRecyclerOptions<Comments> options =
                    new FirebaseRecyclerOptions.Builder<Comments>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Comments").orderByChild(""), Comments.class)
                            .build();
            nhungLoiRvAdapter = new NhungLoiRvAdapter(options);
            rcvDanhGia.setAdapter(nhungLoiRvAdapter);
            btngiam=view.findViewById(R.id.btngiam);
            btntang=view.findViewById(R.id.btntang);
            txtsoluong=view.findViewById(R.id.txtsoluong);
            btnThemGioHang=view.findViewById(R.id.btnthemgiohang);
            waybackhome= view.findViewById(R.id.waybackhome);
            waybackhome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    replaceFragment(new HomeFragMent());
                }
            });
            btnThemGioHang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (count == 0) {
                        Toast.makeText(mainActivity, "Vui lòng nhập số lượng", Toast.LENGTH_SHORT).show();

                    } else {
                        Order order= new Order();
                        order.setProductName(name);
                        order.setQuantity(String.valueOf(count));
                        order.setPrice(String.valueOf(price));
                        order.setDiscount("1");
                        order.setPhone(Common.currentUser.getPhone());
                        order.setImage(image);
                        dbHelper.insertData(order);
                            Toast.makeText(getContext(),"Đã thêm sản phẩm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                            mainActivity.setCountProductInCart(mainActivity.getmCountProduct() + count);
                    }
                }
            });
            btntang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    increament(v);
                }
            });
            btngiam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    decrement(v);
                }
            });

            nameholder.setText(name);
            priceholder.setText(price);
            txtmota.setText(description);
            txtluotban.setText(String.valueOf(soluotban)+" Lượt bán");
            Glide.with(getContext()).load(image).into(imageholder);

            return  view;
        }
        public void increament(View v){
            ++count;
            txtsoluong.setText(String.valueOf(count));
        }
        public void decrement(View v){
            if(count<=0){
                count=0;
            }else{
                --count;
            }
            txtsoluong.setText(String.valueOf(count));
        }
    private void replaceFragment(Fragment fragment) {

        mainActivity= (MainActivity) getActivity();
        FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framlayoutman, fragment);
        fragmentTransaction.commit();
    }
//
@Override
public void onStart() {
    super.onStart();
    nhungLoiRvAdapter.startListening();
}
    @Override
    public void onStop() {
        super.onStop();
        nhungLoiRvAdapter.stopListening();
    }

    }
