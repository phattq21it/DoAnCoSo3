package com.example.adminapp.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminapp.Adapter.ItemRecyclerAdapter;
import com.example.adminapp.AdminActivity;
import com.example.adminapp.R;
import com.example.adminapp.model.Drink;
import com.example.adminapp.model.Request;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CategoriesFragment extends Fragment {
    private RecyclerView recyclerView,recyclerView2;
    ItemRecyclerAdapter itemRecycleAdapter;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    RecyclerView recview;
    private AdminActivity adminactivity;
    private MenuItem menuItem;
    private SearchView searchView;
//
//
//    int[] images={R.drawable.one,
//            R.drawable.two,
//            R.drawable.three,
//            R.drawable.four,
//            R.drawable.five,
//            R.drawable.six};

    public CategoriesFragment() {

    }

    public static CategoriesFragment newInstance(String param1, String param2) {
        CategoriesFragment fragment = new CategoriesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        inflater.inflate(R.menu.menusearch,menu);
//        menuItem=menu.findItem(R.id.search_view);
//        searchView= (SearchView) MenuItemCompat.getActionView(menuItem);
//
//        searchView.setIconified(true);
//
//        SearchManager searchManager= (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//                Bundle bundle= new Bundle();
//                bundle.putString("df2",query);
//                getParentFragmentManager().setFragmentResult("datasearch",bundle);
//                hideKeyboard(adminactivity);
//                replaceFragment(new ResultSearchFragment());
//                Log.d(query,query);
//
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return true;
//            }
//        });
//
//        super.onCreateOptionsMenu(menu, inflater);
//    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_categoriesad,container,false);


        adminactivity= (AdminActivity) getActivity();
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat dateFormatDesosanh = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(date);
        String dateStringDeSoSanh = dateFormatDesosanh.format(date);
        TextView ngayhomnay=view.findViewById(R.id.tvNgayHomNay);
        ngayhomnay.setText(dateString);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ordersRef = database.getReference("Request");
        Query query = ordersRef.orderByChild("time").startAt(dateStringDeSoSanh);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int orderCount = (int) snapshot.getChildrenCount();
                String soluong = Integer.toString(orderCount);
                int tongtien = 0;
                TextView soluongdonhang=view.findViewById(R.id.tvSoLuongSanPham);
                soluongdonhang.setText(soluong);
                for (DataSnapshot orderSnapshot : snapshot.getChildren()) {
                    Request order = orderSnapshot.getValue(Request.class);
                    int orderAmount = Integer.parseInt(order.getTotal().toString());
                    tongtien += orderAmount;
                }
                TextView doanhthuhomnay=view.findViewById(R.id.tvDoanhThuHomNay);
                doanhthuhomnay.setText(Integer.toString(tongtien)+"đ");
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference productsRef = database.getReference("Item");
                Query query = productsRef.orderByChild("quantityPurchased");
                query.limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            // Lấy sản phẩm có lượt bán cao nhất
                            for (DataSnapshot productSnapshot : snapshot.getChildren()) {
                                String productName = productSnapshot.child("name").getValue(String.class);
                                // Sử dụng tên sản phẩm theo ý muốn
                                TextView sanphammuanhieu=view.findViewById(R.id.tvSanPhamMuaNhieu);
                                sanphammuanhieu.setText(productName);

                            }
                        } else {
                            // Không có sản phẩm nào trong bảng
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        TextView doanhthuhomnay=view.findViewById(R.id.tvDoanhThuHomNay);
        TextView sanphammuanhieu=view.findViewById(R.id.tvSanPhamMuaNhieu);



        return view;

    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.findViewById(android.R.id.content);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        itemRecycleAdapter.startListening();
//    }
//    @Override
//    public void onStop() {
//        super.onStop();
//        itemRecycleAdapter.stopListening();
//    }
    private void replaceFragment(Fragment fragment) {

        adminactivity= (AdminActivity) getActivity();
        FragmentManager fragmentManager = adminactivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framlayoutman, fragment);
        fragmentTransaction.commit();
    }

}
