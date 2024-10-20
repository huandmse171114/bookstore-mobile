package com.bookstore.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bookstore.MainActivity;
import com.bookstore.R;
import com.bookstore.databinding.SlideScreenBinding;
import com.bookstore.view.AuthActivity;
import com.bookstore.view.HomePageActivity;
import com.bookstore.view.SliderActivity;


public class SliderViewAdapter extends PagerAdapter {

    Context mContext;
    private SlideScreenBinding binding;


    public SliderViewAdapter(Context mContext) {
        this.mContext = mContext;

    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        binding = SlideScreenBinding.inflate(LayoutInflater.from(mContext), container, false);

        binding.btnGetStarted.setOnClickListener(v -> {
            //open main activity
            Intent intent = new Intent(mContext, HomePageActivity.class);
            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        });

//        binding.next.setOnClickListener(v -> {
//            //forwards to the next screen
//            SliderActivity.screenPager.setCurrentItem(position + 1);
//        });
//        binding.back.setOnClickListener(v -> {
//            //backwards to the next screen
//            SliderActivity.screenPager.setCurrentItem(position - 1);
//        });
        switch (position){
            case 0:
                binding.logo.setImageResource(R.drawable.bookslider);
                binding.ind1.setImageResource(R.drawable.selected);
                binding.ind2.setImageResource(R.drawable.unselected);
                binding.ind3.setImageResource(R.drawable.unselected);

                binding.title.setText("Variety of Books");
                binding.desc.setText("We have a wide range of books for you to choose from.");
                binding.back.setVisibility(View.GONE);
                binding.next.setVisibility(View.VISIBLE);
                break;
             case 1:
                 binding.logo.setImageResource(R.drawable.shipper);
                 binding.ind1.setImageResource(R.drawable.unselected);
                 binding.ind2.setImageResource(R.drawable.selected);
                 binding.ind3.setImageResource(R.drawable.unselected);

                 binding.title.setText("Delivery faster");
                 binding.desc.setText("We deliver your order as soon as possible.");
                 binding.back.setVisibility(View.VISIBLE);
                 binding.next.setVisibility(View.VISIBLE);
                break;
            case 2:
                binding.logo.setImageResource(R.drawable.payment);
                binding.ind1.setImageResource(R.drawable.unselected);
                binding.ind2.setImageResource(R.drawable.unselected);
                binding.ind3.setImageResource(R.drawable.selected);

                binding.title.setText("Easy Payment");
                binding.desc.setText("You can pay easily with various payment methods.");
                binding.next.setVisibility(View.GONE);
                binding.back.setVisibility(View.VISIBLE);
                break;
            }
        container.addView(binding.getRoot());

        return binding.getRoot();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
