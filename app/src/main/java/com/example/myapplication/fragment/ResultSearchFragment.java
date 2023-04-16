package com.example.myapplication.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.ItemRecyclerAdapter;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.Interface.model.Drink;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ResultSearchFragment extends Fragment {
    RecyclerView recyclerViewSearch;
    ItemRecyclerAdapter itemRecyclerAdapter;
    ImageView waybackhome2;
    EditText edtSearch;
    Button btnSearch;
    String keysearch;
    MainActivity mainActivity= (MainActivity) getActivity();
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resultsearch, container, false);
        recyclerViewSearch = view.findViewById(R.id.rcvresultsearch);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getContext(), 2);
        recyclerViewSearch.setLayoutManager(gridLayoutManager2);
        waybackhome2=view.findViewById(R.id.waybackhome2);
        edtSearch= view.findViewById(R.id.edtsearchinrs);
        btnSearch= view.findViewById(R.id.btnsearchinrs);




        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keysearch=edtSearch.getText().toString().trim();
                edtSearch.setText(keysearch);
                FirebaseRecyclerOptions<Drink> options = new FirebaseRecyclerOptions.Builder<Drink>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Item").orderByChild("name").startAt(keysearch).endAt(keysearch + "\uf8ff"), Drink.class)
                        .build();
                itemRecyclerAdapter = new ItemRecyclerAdapter(options);
                itemRecyclerAdapter.startListening();
                recyclerViewSearch.setAdapter(itemRecyclerAdapter);
//                Toast.makeText(getContext(), keysearch +"dsfsdf", Toast.LENGTH_SHORT).show();
            }
        });


        waybackhome2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new HomeFragMent());
            }
        });

        getParentFragmentManager().setFragmentResultListener("datasearch", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                    result.getString("df1");

                String s=result.toString().trim().substring(12, result.toString().lastIndexOf("}"));
                edtSearch.setText(s);
                FirebaseRecyclerOptions<Drink> options = new FirebaseRecyclerOptions.Builder<Drink>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Item").orderByChild("name").startAt(s).endAt(s + "\uf8ff"), Drink.class)
                        .build();
                itemRecyclerAdapter = new ItemRecyclerAdapter(options);
                itemRecyclerAdapter.startListening();
                recyclerViewSearch.setAdapter(itemRecyclerAdapter);
            }
        });

        return view;
    }
    private void replaceFragment(Fragment fragment) {

        mainActivity= (MainActivity) getActivity();
        FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framlayoutman, fragment);
        fragmentTransaction.commit();
    }
}
