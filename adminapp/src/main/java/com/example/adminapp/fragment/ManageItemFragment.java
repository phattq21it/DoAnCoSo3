package com.example.adminapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adminapp.Adapter.ManageItemAdapter;
import com.example.adminapp.Adapter.ManagerUserAdapter;
import com.example.adminapp.R;
import com.example.adminapp.model.Drink;
import com.example.adminapp.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ManageItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManageItemFragment extends Fragment {
    DatabaseReference database;
    RecyclerView rcv;
    ManageItemAdapter manageItemAdapter;
    ArrayList<Drink> drinkArrayList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ManageItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ManageItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ManageItemFragment newInstance(String param1, String param2) {
        ManageItemFragment fragment = new ManageItemFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_manage_item, container, false);
        rcv=view.findViewById(R.id.rcvitem);
        database= FirebaseDatabase.getInstance().getReference("Item");
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        drinkArrayList= new ArrayList<>();
        manageItemAdapter = new ManageItemAdapter(getContext(),drinkArrayList);
        rcv.setAdapter(manageItemAdapter);
        database.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Drink drink= dataSnapshot.getValue(Drink.class);
                    drinkArrayList.add(drink);
                }
                manageItemAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}