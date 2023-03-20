package com.example.myapplication.fragment;

    import android.annotation.SuppressLint;
    import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
    import com.example.myapplication.R;

public class DetailsFragment extends Fragment {

        private static final String ARG_PARAM1 = "param1";
        private static final String ARG_PARAM2 = "param2";

        private String mParam1;
        private String mParam2;
        String name, price, image;
        public DetailsFragment() {

        }

        public DetailsFragment(String name, String price, String image) {
            this.name=name;
            this.price=price;
            this.image=image;
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

            View view=inflater.inflate(R.layout.fragment_details, container, false);

            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView imageholder=view.findViewById(R.id.imageholder);
            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView nameholder=view.findViewById(R.id.nameholder);
            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView priceholder=view.findViewById(R.id.priceholder);

            nameholder.setText(name);
            priceholder.setText(price);
            Glide.with(getContext()).load(image ).into(imageholder);


            return  view;
        }

        public void onBackPressed()
        {
            AppCompatActivity activity=(AppCompatActivity)getContext();
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.framelayoutac,new HomeFragMent()).addToBackStack(null).commit();

        }
    }
