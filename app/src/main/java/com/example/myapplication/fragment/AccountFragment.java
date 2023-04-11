package com.example.myapplication.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Common.Common;
import com.example.myapplication.Interface.model.User;
import com.example.myapplication.R;
import com.example.myapplication.WelcomeActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AccountFragment extends Fragment {
    TextView txtDangXuat;
    TextView txtName;
    TextView txtMail;
    TextView txtDiaChi;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_notification,container,false);
        txtDangXuat= view.findViewById(R.id.btndangxuat);
        txtName= view.findViewById(R.id.ac_txt_name);
        txtMail= view.findViewById(R.id.ac_txt_mail);
        txtDiaChi= view.findViewById(R.id.ac_txt_diachi);
        txtDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent i= new Intent(getActivity(), WelcomeActivity.class);
              startActivity(i);
            }
        });
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("User");

// Create a query to find users with phone = 1
        Query query = usersRef.orderByKey().equalTo(Common.currentUser.getPhone());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    User user = userSnapshot.getValue(User.class);
                    txtName.setText(user.getName());
                    txtMail.setText(user.getMail());
                    txtDiaChi.setText(user.getAddress());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}
