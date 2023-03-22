package com.example.myapplication.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.fragment.HomeFragMent;
import com.example.myapplication.fragment.AccountFragment;
import com.example.myapplication.fragment.CartFragment;

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
                return new CartFragment();
            case 2  :
                return new AccountFragment();
            default: return new HomeFragMent();
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
