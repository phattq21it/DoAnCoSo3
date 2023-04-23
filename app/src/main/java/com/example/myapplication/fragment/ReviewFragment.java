package com.example.myapplication.fragment;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.ReviewAdapter;
import com.example.myapplication.Common.Common;
import com.example.myapplication.Interface.model.Drink;
import com.example.myapplication.Interface.model.Order;
import com.example.myapplication.Interface.model.Request;
import com.example.myapplication.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReviewFragment extends Fragment {
    List<Order> orders;
    Long currentTimeMillis;

    private RecyclerView rcv_rv;
    ReviewAdapter reviewAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReviewFragment() {
        // Required empty public constructor
    }
      public ReviewFragment(List<Order> orders,Long currentTimeMillis) {
        this.orders=orders;
        this.currentTimeMillis = currentTimeMillis;
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReviewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReviewFragment newInstance(String param1, String param2) {
        ReviewFragment fragment = new ReviewFragment();
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
        View view= inflater.inflate(R.layout.fragment_review, container, false);


        rcv_rv=view.findViewById(R.id.rcv_rv);
        Button btnHoanThanhDanhGia = view.findViewById(R.id.btnHoanThanhDanhGia);

        TextView maDonHang = view.findViewById(R.id.tvMaDonHang);
        maDonHang.setText(currentTimeMillis.toString());
        btnHoanThanhDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Request");
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot orderSnapshot : snapshot.getChildren()) {
                            // Lấy key của đơn hàng hiện tại
                            String orderKey = orderSnapshot.getKey();

                            // Lấy mã đơn hàng của đơn hàng hiện tại
                            Long orderCode = orderSnapshot.child("currentTimeMillis").getValue(Long.class);

                            // So sánh với mã đơn hàng của đơn hàng đang lấy
                            if (orderCode.equals(currentTimeMillis)) {
                                // Cập nhật thuộc tính note của đơn hàng hiện tại thành "Đã đánh giá"
                                orderSnapshot.getRef().child("note").setValue("Đã đánh giá");
                                Toast.makeText(getContext(), "Đã hoàn thành đánh giá đơn hàng", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });




            }
        });
//        FirebaseRecyclerOptions<Request> itemRv =
//                new FirebaseRecyclerOptions.Builder<Request>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Request"),Request.class)
//                        .build();
        reviewAdapter= new ReviewAdapter(orders);
        rcv_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rcv_rv.setAdapter(reviewAdapter);

        return  view;
    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        reviewAdapter.startListening();
//    }
//    @Override
//    public void onStop() {
//        super.onStop();
//        reviewAdapter.stopListening();
//    }
}