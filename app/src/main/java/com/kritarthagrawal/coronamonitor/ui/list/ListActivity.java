package com.kritarthagrawal.coronamonitor.ui.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.kritarthagrawal.coronamonitor.R;
import com.kritarthagrawal.coronamonitor.ui.main.MainActivityPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListActivity extends AppCompatActivity implements ListActivityContract.view {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ripple_anim)
    LottieAnimationView lottieAnimationView;
    @BindView(R.id.lastTextView)
    TextView lastTextView;
    @BindView(R.id.detailsLayout)
    LinearLayout detailsLayout;
    @BindView(R.id.adView)
    AdView adView;

    ListActivityContract.presenter mPresenter;
    ListAdapter listAdapter;
    public InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        mPresenter = new ListActivityPresenter(this);
        setSupportActionBar(toolbar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mPresenter.getList();
        toolbarOnNavigation();
        loadAds();

    }

    @Override
    public void toolbarOnNavigation() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void loadingAnimations(boolean b) {
        if(b){
            lottieAnimationView.setVisibility(View.VISIBLE);
            detailsLayout.setVisibility(View.INVISIBLE);}
        else{
            lottieAnimationView.setVisibility(View.GONE);
            detailsLayout.setVisibility(View.VISIBLE);}

    }

    @Override
    public void listActivityCallBack(String error, List<CountriesStat> list,String takkenAt) {
        if(error.equals("success")){
            lastTextView.setText(takkenAt);
            listAdapter = new ListAdapter(getApplicationContext(),list) ;
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            recyclerView.setAdapter(listAdapter);
        }
        else{
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void loadAds() {
        MobileAds.initialize(this,getString(R.string.ads_application_id));
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.ads_unit_id_int));
        interstitialAd.loadAd(adRequest);
        if(interstitialAd.isLoaded()){interstitialAd.show();}
    }
}
