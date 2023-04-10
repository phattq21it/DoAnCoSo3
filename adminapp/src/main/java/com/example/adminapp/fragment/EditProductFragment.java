package com.example.adminapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adminapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditProductFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String name,image,price,description,discount,key;
    Button btnUpdateItem;
    Long priceLong;
    FirebaseDatabase database;
    DatabaseReference myRef,productRef;
    Spinner spinnerCategories;
    ArrayAdapter<String> mAdapter;
    ArrayList<String> mCategoryList;

    public EditProductFragment() {
        // Required empty public constructor
    }

    public EditProductFragment(String name) {
        this.name = name;
    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditProductFragment newInstance(String param1, String param2) {
        EditProductFragment fragment = new EditProductFragment();
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
        View view =   inflater.inflate(R.layout.fragment_edit_product, container, false);
         database = FirebaseDatabase.getInstance();
         myRef = database.getReference("Item");
        Query query = myRef.orderByChild("name").equalTo(name);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    key = ds.getKey();
                    if(key != null) {
                        productRef = myRef.child(key);
                        productRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                name = snapshot.child("name").getValue(String.class);
                                description = snapshot.child("description").getValue(String.class);
                                image = snapshot.child("image").getValue(String.class);
                                price = snapshot.child("price").getValue(String.class);

                               discount = snapshot.child("discount").getValue(String.class);

                 EditText editImage = view.findViewById(R.id.edtImageItem);
                 EditText editName = view.findViewById(R.id.edtNameItem);
                 EditText editPrice = view.findViewById(R.id.edtPriceItem);

                 EditText editDes = view.findViewById(R.id.edtDescriptionItem);
                 EditText editDiscount = view.findViewById(R.id.edtDiscountItem);


                 editName.setText(name);
                 editImage.setText(image);
                 editPrice.setText(price);
                 editDes.setText(description);

                 editDiscount.setText(discount);
                            }


                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                    else {
                        Toast.makeText(getContext(), "Không thể cập nhật", Toast.LENGTH_SHORT).show();
                    }
                    btnUpdateItem = view.findViewById(R.id.btnUpdateItem);
                    btnUpdateItem.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference productRef = database.getReference("Item").child(key);
                            EditText editImage = view.findViewById(R.id.edtImageItem);
                            EditText editName = view.findViewById(R.id.edtNameItem);
                            EditText editPrice = view.findViewById(R.id.edtPriceItem);

                            EditText editDes = view.findViewById(R.id.edtDescriptionItem);
                            EditText editDiscount = view.findViewById(R.id.edtDiscountItem);

                            HashMap<String, Object> updatedData = new HashMap<>();
                            updatedData.put("name",editName.getText().toString());
                            updatedData.put("description",editDes.getText().toString());
                            updatedData.put("price", editPrice.getText().toString());
                            updatedData.put("image",editImage.getText().toString());

                            updatedData.put("discount",editDiscount.getText().toString());


                            productRef.updateChildren(updatedData);
                            Toast.makeText(getContext(), "Cập nhật sản phầm thành công", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






        return view;
    }

}