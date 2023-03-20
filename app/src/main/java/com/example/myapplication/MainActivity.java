package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.Adapter.SliderAdapter;
import com.example.myapplication.Adapter.ViewPagerAdapter;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.fragment.HomeFragMent;
import com.example.myapplication.fragment.NotificationFragment;
import com.example.myapplication.fragment.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.smarteist.autoimageslider.SliderView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ZoomOutPageTransformer zoomOutPageTransformer= new ZoomOutPageTransformer();

    @SuppressLint("MissingInflatedId")



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragMent());

//SLider

//  Navigation Bottom
        binding.bottomNav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    replaceFragment(new HomeFragMent());
                    break;
                case R.id.nav_setting:
                    replaceFragment(new SettingFragment());
                    break;
                case R.id.nav_notification:
                    replaceFragment(new NotificationFragment());
                    break;
            }
        return true;
                }




        );
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
//        ViewPagerAdapter viewPagerAdapter= new ViewPagerAdapter(MainActivity.this);
//        framelayout.setAdapter(viewPagerAdapter);
//        framelayout.setPageTransformer (zoomOutPageTransformer);

        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayoutac,fragment);
        fragmentTransaction.commit();
    }

}
