package com.kritarthagrawal.coronamonitor.ui.precautions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.kritarthagrawal.coronamonitor.R;
import com.kritarthagrawal.coronamonitor.ui.list.ListActivityContract;
import com.kritarthagrawal.coronamonitor.ui.list.ListActivityPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PrecautionsActivity extends AppCompatActivity implements PrecautionContract.view{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.dotsLayout)
    LinearLayout mdotsLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    PrecautionContract.presenter mPresenter;
    private TextView[] mDots;
    PrecautionAdapter precautionAdapter;
    public InterstitialAd interstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precautions);
        ButterKnife.bind(this);
        mPresenter = new PrecautionsPresenter(this);
        setSupportActionBar(toolbar);
        setupCards();
        toolbarOnNavigation();
        loadAds();
        viewPager.setOnPageChangeListener(viewlistner);
    }
    ViewPager.OnPageChangeListener viewlistner = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotIndicator(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void toolbarOnNavigation() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(interstitialAd.isLoaded()){interstitialAd.show();}
                finish();
            }
        });
    }

    @Override
    public void loadAds() {
        MobileAds.initialize(this,getString(R.string.ads_application_id));
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.ads_unit_id_int));
        interstitialAd.loadAd(adRequest);

    }

    @Override
    public void addDotIndicator(int position) {
        mDots = new TextView[14];
        mdotsLayout.removeAllViews();
        for(int i=0;i<mDots.length;i++)
        {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorPrimary));
            mdotsLayout.addView(mDots[i]);
        }
        if(mDots.length>0){
            mDots[position].setTextColor(getResources().getColor(R.color.themeBlue));
        }
    }

    @Override
    public void setupCards() {
        precautionAdapter = new PrecautionAdapter(this) ;
        viewPager.setAdapter(precautionAdapter);
        addDotIndicator(0);
    }

    @Override
    public void onBackPressed() {
        if(interstitialAd.isLoaded()){interstitialAd.show();}
        super.onBackPressed();
    }
}
