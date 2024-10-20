package com.bookstore.model;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.bookstore.AboutBookFragment;
import com.bookstore.OverviewFragment;
import com.bookstore.SynopsisFragment;

public class TabAdapter extends FragmentStateAdapter {

    public static final String[] TAB_TITLES = new String[]{"Overview", "About Book", "Synopsis"};

    public TabAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new OverviewFragment();
            case 1:
                return new AboutBookFragment();
            case 2:
                return new SynopsisFragment();
            default:
                throw new IllegalStateException("Unexpected position: " + position);
        }
    }

    @Override
    public int getItemCount() {
        return TAB_TITLES.length;
    }
}
