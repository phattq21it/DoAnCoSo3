package com.example.myapplication.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.fragment.HomeFragMent;
import com.example.myapplication.fragment.NotificationFragment;
import com.example.myapplication.fragment.SettingFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
            return new HomeFragMent();
            case 1:
                return new SettingFragment();
            case 2  :
                return new NotificationFragment();
            default: return new HomeFragMent();
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
