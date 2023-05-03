package com.example.adminapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.adminapp.fragment.AccountFragment;
import com.example.adminapp.fragment.ManagerFragment;
import com.example.adminapp.fragment.CategoriesFragment;
import com.example.adminapp.fragment.HomeFragMent;

public class    AdminActivity extends AppCompatActivity {


    FrameLayout frameLayout;

    private AHBottomNavigation ahBottomNavigation;


    @SuppressLint("MissingInflatedId")


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        ahBottomNavigation = (AHBottomNavigation) findViewById(R.id.AHBottomNavigation);
        frameLayout = findViewById(R.id.framlayoutman);
// Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.home_nav, R.drawable.ic_baseline_home_24, R.color.teal_200);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.manager_nav, R.drawable.manager, R.color.teal_200);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.account_nav, R.drawable.ic_baseline_account_circle_24, R.color.teal_200);

// Add items
        ahBottomNavigation.addItem(item1);
        ahBottomNavigation.addItem(item3);
        ahBottomNavigation.addItem(item4);


        replaceFragment(new HomeFragMent());
//SLider
        ahBottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                switch (position) {
                    case 0:
                        Log.d(String.valueOf(position), String.valueOf(position));
                        replaceFragment(new HomeFragMent());
                        break;
                    case 1:
                        Log.d(String.valueOf(position), String.valueOf(position));
                        replaceFragment(new CategoriesFragment());
                        break;
                    case 2:
                        Log.d(String.valueOf(position), String.valueOf(position));
                        replaceFragment(new ManagerFragment());
                        break;
                    case 3:
                        Log.d(String.valueOf(position), String.valueOf(position));
                        replaceFragment(new AccountFragment());
                        break;
                }
                return true;
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framlayoutman, fragment);
        fragmentTransaction.commit();
    }
}