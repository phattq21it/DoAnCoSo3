package com.example.adminapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adminapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddItemFragment extends Fragment {
    Button btnAddItem;
    TextView idItem,imgItem,nameItem,priceItem,typeItem,descriptionItem,discountItem;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String[] categories;
    Spinner spinnerCategories;
     ArrayAdapter<String> mAdapter;
     ArrayList<String> mCategoryList;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddItemFragment newInstance(String param1, String param2) {
        AddItemFragment fragment = new AddItemFragment();
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
        View view=  inflater.inflate(R.layout.fragment_add_item,container,false);
        btnAddItem = view.findViewById(R.id.btnAddItem);
        idItem = view.findViewById(R.id.edtIdItem);
        nameItem = view.findViewById(R.id.edtNameItem);
        imgItem = view.findViewById(R.id.edtImageItem);
        priceItem = view.findViewById(R.id.edtPriceItem);
        typeItem = view.findViewById(R.id.edtTypeItem);
        typeItem.setVisibility(View.INVISIBLE);
        descriptionItem = view.findViewById(R.id.edtDescriptionItem);
        discountItem = view.findViewById(R.id.edtDiscountItem);
        // Lấy danh mục
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference categoriesRef = database.getReference("Categories");
        mCategoryList = new ArrayList<>();

        spinnerCategories = view.findViewById(R.id.spinercategories);
        mAdapter = new ArrayAdapter<>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,mCategoryList);
        mAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinnerCategories.setAdapter(mAdapter);
        categoriesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mCategoryList.clear();
                for(DataSnapshot categorySnapshot : snapshot.getChildren()){
                    String categoryName = categorySnapshot.child("name").getValue(String.class);
                    mCategoryList.add(categoryName);
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        spinnerCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               String value = parent.getItemAtPosition(position).toString();
                typeItem.setText(value.toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateData()) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference productRef = database.getReference("Item");
                    DatabaseReference newProductRef = productRef.child(idItem.getText().toString());

                    newProductRef.child("name").setValue(nameItem.getText().toString());
                    newProductRef.child("description").setValue(descriptionItem.getText().toString());
                    newProductRef.child("price").setValue(priceItem.getText().toString());
                    newProductRef.child("type").setValue(typeItem.getText().toString());
                    newProductRef.child("discount").setValue(typeItem.getText().toString());
                    newProductRef.child("image").setValue(imgItem.getText().toString());
                    Toast.makeText(getContext(), "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
                }





            }
            private boolean validateData() {
                if (idItem.getText().toString().isEmpty()) {
                    idItem.setError("Vui lòng nhập id sản phẩm");
                    Toast.makeText(getContext(),"Vui lòng nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                    return false;

                }
                if (nameItem.getText().toString().isEmpty()) {
                    nameItem.setError("Vui lòng nhập  tên sản phẩm");
                    Toast.makeText(getContext(),"Vui lòng nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (priceItem.getText().toString().isEmpty()) {
                    priceItem.setError("Vui lòng nhập giá tiền");
                    Toast.makeText(getContext(),"Vui lòng nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                    return false;
                } if (descriptionItem.getText().toString().isEmpty()) {
                    descriptionItem.setError("Vui lòng nhập mô tả về sản phẩm");
                    Toast.makeText(getContext(),"Vui lòng nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                    return false;
                } if (typeItem.getText().toString().isEmpty()) {
                    typeItem.setError("Vui lòng nhập danh mục của sản phẩm");
                    Toast.makeText(getContext(),"Vui lòng nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                    return false;
                } if (imgItem.getText().toString().isEmpty()) {
                    imgItem.setError("Vui lòng đính kèm  hình ảnh của sản phẩm ");
                    Toast.makeText(getContext(),"Vui lòng nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                    return false;
                }
             if (discountItem.getText().toString().isEmpty()) {
                imgItem.setError("Vui lòng nhập giá giảm của sản phẩm ");
                Toast.makeText(getContext(),"Vui lòng nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                return false;
            }
                return true;
            }

        });

        return view;
    }
}