package com.example.myapplication.fragment;

    import android.annotation.SuppressLint;
    import android.os.Bundle;

    import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
    import android.widget.Button;
    import android.widget.ImageView;
import android.widget.TextView;
    import android.widget.Toast;

    import com.bumptech.glide.Glide;
    import com.example.myapplication.MainActivity;
    import com.example.myapplication.R;

public class DetailsFragment extends Fragment {



        private static final String ARG_PARAM1 = "param1";
        private static final String ARG_PARAM2 = "param2";
        Button btngiam;
        Button btntang,btnThemGioHang;
        TextView txtsoluong;
        int count=0;
        private String mParam1;
        private String mParam2;
        String name, price, image,mota;
        MainActivity mainActivity;
        public DetailsFragment() {

        }

        public DetailsFragment(String name, String price, String image,String mota) {
            this.name=name;
            this.price=price;
            this.image=image;
            this.mota=mota;
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

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            mainActivity= (MainActivity) getActivity();

            View view=inflater.inflate(R.layout.fragment_details, container, false);

            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView imageholder=view.findViewById(R.id.imageholder);
            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView nameholder=view.findViewById(R.id.nameholder);
            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView priceholder=view.findViewById(R.id.priceholder);
            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView txtmota=view.findViewById(R.id.txtmota);
            btngiam=view.findViewById(R.id.btngiam);
            btntang=view.findViewById(R.id.btntang);
            txtsoluong=view.findViewById(R.id.txtsoluong);
            btnThemGioHang=view.findViewById(R.id.btnthemgiohang);

            btnThemGioHang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (count == 0) {
                        Toast.makeText(mainActivity, "Vui lòng nhập số lượng", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(mainActivity, "Đã thêm sản phẩm vào giỏ hàng", Toast.LENGTH_SHORT).show();
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
            txtmota.setText(mota);
            Glide.with(getContext()).load(image ).into(imageholder);


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

//
    }
