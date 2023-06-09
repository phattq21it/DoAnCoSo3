package com.example.adminapp.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

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

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CategoriesFragment extends Fragment {
    private RecyclerView recyclerView,recyclerView2;
    ItemRecyclerAdapter itemRecycleAdapter;
    TextView soluongdonhang,ngayhomnay,doanhthuhomnay,sanphammuanhieu;
    Calendar selectedDate;
    Date date1;
    String ngaychon;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private int orderCount = 0;
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
        CalendarView calendarView = view.findViewById(R.id.calendarView);
        ngayhomnay=view.findViewById(R.id.tvNgayHomNay);
        soluongdonhang=view.findViewById(R.id.tvSoLuongSanPham);
        sanphammuanhieu=view.findViewById(R.id.tvSanPhamMuaNhieu);
        doanhthuhomnay=view.findViewById(R.id.tvDoanhThuHomNay);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

        SimpleDateFormat dateFormatDesosanh = new SimpleDateFormat("yyyy-MM-dd");

        selectedDate = Calendar.getInstance();
        date1= selectedDate.getTime();
        ngaychon= dateFormat.format(date1);
        String dateStart = dateFormatDesosanh.format(selectedDate.getTime())+" 00:00:00";
        String dateEnd = dateFormatDesosanh.format(selectedDate.getTime())+" 23:59:99";
        doanhthu(dateStart,dateEnd);
        ngayhomnay.setText(ngaychon);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {


                selectedDate.set(year, month, dayOfMonth);


                ngaychon= dateFormat.format(selectedDate.getTime());
                String dateStart = dateFormatDesosanh.format(selectedDate.getTime())+" 00:00:00";
                String dateEnd = dateFormatDesosanh.format(selectedDate.getTime())+" 23:59:99";

                orderCount = 0;
                ngayhomnay.setText(ngaychon);

                doanhthu(dateStart,dateEnd);
//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                DatabaseReference ordersRef = database.getReference("Request");
//                Query query = ordersRef.orderByChild("time").startAt(dateStart).endAt(dateEnd);
//
//                query.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                        orderCount = (int) snapshot.getChildrenCount();
//
//                        String soluong = Integer.toString(orderCount);
//
//
//                        int tongtien =0;
//                        soluongdonhang.setText(soluong);
//                        for (DataSnapshot orderSnapshot : snapshot.getChildren()) {
//                            Request order = orderSnapshot.getValue(Request.class);
//                            int orderAmount = Integer.parseInt(order.getTotal().toString());
//                            tongtien += orderAmount;
//                        }
//                        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
//                        String replacedNumber = decimalFormat.format(tongtien).replace(",", ".");
//                        doanhthuhomnay.setText(replacedNumber+"đ");
//
//                        FirebaseDatabase database = FirebaseDatabase.getInstance();
//                        DatabaseReference productsRef = database.getReference("Item");
//                        Query productquery = productsRef.orderByChild("quantityPurchased");
//                        productquery.limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                if (snapshot.exists()) {
//                                    // Lấy sản phẩm có lượt bán cao nhất
//                                    for (DataSnapshot productSnapshot : snapshot.getChildren()) {
//                                        String productName = productSnapshot.child("name").getValue(String.class);
//                                        // Sử dụng tên sản phẩm theo ý muốn
//                                        sanphammuanhieu.setText(productName);
//
//                                    }
//                                } else {
//                                    // Không có sản phẩm nào trong bảng
//                                }
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//
//                            }
//                        });
//
//
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });

            }
        });






        return view;

    }
    public void doanhthu(String dateStart,String dateEnd){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ordersRef = database.getReference("Request");
        Query query = ordersRef.orderByChild("time").startAt(dateStart).endAt(dateEnd);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                orderCount = (int) snapshot.getChildrenCount();

                String soluong = Integer.toString(orderCount);


                int tongtien =0;
                soluongdonhang.setText(soluong);
                for (DataSnapshot orderSnapshot : snapshot.getChildren()) {
                    Request order = orderSnapshot.getValue(Request.class);
                    int orderAmount = Integer.parseInt(order.getTotal().toString());
                    tongtien += orderAmount;
                }
                DecimalFormat decimalFormat = new DecimalFormat("#,##0");
                String replacedNumber = decimalFormat.format(tongtien).replace(",", ".");
                doanhthuhomnay.setText(replacedNumber+"đ");

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference productsRef = database.getReference("Item");
                Query productquery = productsRef.orderByChild("quantityPurchased");
                productquery.limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            // Lấy sản phẩm có lượt bán cao nhất
                            for (DataSnapshot productSnapshot : snapshot.getChildren()) {
                                String productName = productSnapshot.child("name").getValue(String.class);
                                // Sử dụng tên sản phẩm theo ý muốn
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
