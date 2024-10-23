package com.bookstore;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class AboutBookFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview, container, false);
        TextView tvOverview = view.findViewById(R.id.tvOverview);
        tvOverview.setText("The book offers a comprehensive view of all aspects of life, providing readers with clear guidelines for life. Through insightful advice, the author, makes this book a must-have choice" +
                "For those who want to improve a related area in their life or work.");
        return view;
    }
}