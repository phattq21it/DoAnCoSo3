package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;
import com.example.myapplication.Adapter.ViewPagerAdapter;
import com.example.myapplication.fragment.AccountFragment;
import com.example.myapplication.fragment.CartFragment;
import com.example.myapplication.fragment.CategoriesFragment;
import com.example.myapplication.fragment.HomeFragMent;

public class MainActivity extends AppCompatActivity {
    private int mCountProduct;

    FrameLayout frameLayout;

    private AHBottomNavigation ahBottomNavigation;
//    private AHBottomNavigationViewPager ahBottomNavigationViewPager;


    @SuppressLint("MissingInflatedId")


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding=ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        replaceFragment(new HomeFragMent());
        setContentView(R.layout.activity_main);


        ahBottomNavigation = (AHBottomNavigation) findViewById(R.id.AHBottomNavigation);
        frameLayout = findViewById(R.id.framlayoutman);
// Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.home_nav, R.drawable.ic_baseline_home_24, R.color.teal_200);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.categories_nav, R.drawable.ic_baseline_category_24, R.color.teal_200);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.cart_nav, R.drawable.ic_baseline_shopping_cart_24, R.color.teal_200);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.account_nav, R.drawable.ic_baseline_account_circle_24, R.color.teal_200);

// Add items
        ahBottomNavigation.addItem(item1);
        ahBottomNavigation.addItem(item2);
        ahBottomNavigation.addItem(item3);
        ahBottomNavigation.addItem(item4);


        replaceFragment(new HomeFragMent());
//SLider
        ahBottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                switch (position) {
                    case 0:
                        Log.d(String.valueOf(position),String.valueOf(position));
                        replaceFragment(new HomeFragMent());
                        break;
                    case 1:
                        Log.d(String.valueOf(position),String.valueOf(position));
                        replaceFragment(new CategoriesFragment());
                        break;
                    case 2:
                        Log.d(String.valueOf(position),String.valueOf(position));
                        replaceFragment(new CartFragment());
                        break;
                    case 3:
                        Log.d(String.valueOf(position),String.valueOf(position));
                        replaceFragment(new AccountFragment());
                        break;
                }
                return true;
            }
        });
//        ahBottomNavigationViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                ahBottomNavigation.setCurrentItem(position);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//  Navigation Bottom

//        binding.bottomNav.setOnItemSelectedListener(item -> {
//            switch (item.getItemId()) {
//                case R.id.nav_home:
//                    replaceFragment(new HomeFragMent());
//                    break;
//                case R.id.nav_setting:
//                    replaceFragment(new CartFragment());
//                    break;
//                case R.id.nav_notification:
//                    replaceFragment(new NotificationFragment());
//                    break;
//            }
//        return true;
//                }
//        );


//        cartButton= (Button) findViewById(R.id.cartButton);
//        cartButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent= new Intent(MainActivity.this, CartActivity.class);
////                startActivity(intent);
//
//            }
//        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framlayoutman, fragment);
        fragmentTransaction.commit();
    }
    public void setCountProductInCart(int count){
        mCountProduct=count;
        AHNotification notification = new AHNotification.Builder()
                .setText(String.valueOf(count))
                .setBackgroundColor(ContextCompat.getColor(MainActivity.this, com.nex3z.notificationbadge.R.color.red))
                .setTextColor(ContextCompat.getColor(MainActivity.this, com.nex3z.notificationbadge.R.color.white))
                .build();
        ahBottomNavigation.setNotification(notification, 2);
    }

    public int getmCountProduct() {
        return mCountProduct;
    }
}
