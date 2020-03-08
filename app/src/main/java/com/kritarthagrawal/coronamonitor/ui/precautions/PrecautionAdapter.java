package com.kritarthagrawal.coronamonitor.ui.precautions;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kritarthagrawal.coronamonitor.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class PrecautionAdapter extends PagerAdapter {


    Context context;
    LayoutInflater layoutInflater;
    RequestOptions option;

    public PrecautionAdapter(Context context) {
        this.context = context;
        //option = new RequestOptions().centerCrop().placeholder(R.drawable.cg_govt_logo).error(R.drawable.cg_govt_logo);
    }

    private int[] imagesUrl ={
            R.drawable.blue1,
            R.drawable.blue2,
            R.drawable.blue3,
            R.drawable.blue4,
            R.drawable.blue5,
            R.drawable.blue6,
            R.drawable.blue7,
            R.drawable.blue8,
            R.drawable.blue9,
            R.drawable.blue10,
            R.drawable.yellow1,
            R.drawable.yellow2,
            R.drawable.yellow3,
            R.drawable.yellow4,
    };
    @Override
    public int getCount() {
        return imagesUrl.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (LinearLayout) o;
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.precautioncard,container,false);

        ImageView slideimageview = (ImageView) view.findViewById(R.id.thumbnail);
        slideimageview.setImageResource(imagesUrl[position]);



        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}