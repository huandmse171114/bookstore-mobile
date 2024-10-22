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
        tvOverview.setText("Cuốn sách này khám phá chan troi moi, phân tích những khái niệm phức tạp thành những ý tưởng dễ hiểu. Cho dù bạn là người mới bắt đầu hay đã có kinh nghiệm trong triet hoc,quyen sach sẽ giúp bạn hiểu rõ hơn về doi song và áp dụng chúng vào [bối cảnh cá nhân hoặc nghề nghiệp]. Với những lời khuyên thực tiễn, cuốn sách này hướng tớimục tiêu cụ thể tăng năng suất, phát triển tư duy sáng tạo, giải quyết vấn đề");
        return view;
    }
}