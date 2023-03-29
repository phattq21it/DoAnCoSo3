package com.example.adminapp.fragment;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.adminapp.AdminActivity;
import com.example.adminapp.R;


public class ManagerFragment extends Fragment {
    AdminActivity adminActivity= (AdminActivity) getActivity();
    ImageView viewUser,viewItem,viewCategories,addUser,addItem,addCategories;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_manage,container,false);

        viewUser=view.findViewById(R.id.imgviewuser);
        viewItem= view.findViewById(R.id.imgviewproduct);
        viewCategories= view.findViewById(R.id.imgviewcategories);

        addUser= view.findViewById(R.id.imgadduser);
        addItem= view.findViewById(R.id.imgaddproduct);
        addCategories= view.findViewById(R.id.imgaddcategories);
        //them user
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new AddUserFragment());
            }
        });
        //them item
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new AddItemFragment());
            }
        });
        //hien thi danh sach user
        viewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new ManageUserFragment());
            }
        });
        //hien thi danh sach item
        viewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new ManageItemFragment());
            }
        });
        //hien thi danh sach categories
        viewCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new ManageItemFragment());
            }
        });
        return view;
    }
    private void replaceFragment(Fragment fragment) {

        adminActivity= (AdminActivity) getActivity();
        FragmentManager fragmentManager = adminActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framlayoutman, fragment);
        fragmentTransaction.commit();
    }
}
