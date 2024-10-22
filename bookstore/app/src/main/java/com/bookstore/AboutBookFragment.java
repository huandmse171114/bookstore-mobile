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
        tvOverview.setText("Quyen sách mang đến một cái nhìn toàn diện về mọi mặt trong cuộc ống, cung cấp cho người đọc những hướng dẫn rõ ràng về cuộc sống. Thông qua những lời khuyên sâu sắc, tác giả , biến cuốn sách này thành một lựa chọn không thể bỏ qua " +
                "cho những ai muốn cải thiện lĩnh vực liên quan trong cuộc sống hoặc công việc.\"");
        return view;
    }
}