package com.bookstore;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class SynopsisFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview, container, false);
        TextView tvOverview = view.findViewById(R.id.tvOverview);
        tvOverview.setText("This book explores new horizons, breaking down complex concepts into easy-to-understand ideas. Whether you are a beginner or already experienced in philosophy, the book will help you better understand life and apply it to [personal or professional context]. With practical advice, this book aims to increase productivity, develop creative thinking, and solve problems.");
        return view;
    }
}