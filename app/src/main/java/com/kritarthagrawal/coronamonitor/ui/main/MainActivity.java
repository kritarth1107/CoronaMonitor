package com.kritarthagrawal.coronamonitor.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.navigation.NavigationView;
import com.kritarthagrawal.coronamonitor.R;
import com.kritarthagrawal.coronamonitor.ui.about.AboutActivity;
import com.kritarthagrawal.coronamonitor.ui.list.ListActivity;
import com.kritarthagrawal.coronamonitor.ui.precautions.PrecautionsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,MainContract.view {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.adView)
    AdView adView;


    MainContract.presenter mPresenter;
    public InterstitialAd interstitialAd,interstitialAd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPresenter = new MainActivityPresenter(this);

        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        MobileAds.initialize(this,getString(R.string.ads_application_id));
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.ads_unit_id_int));
        interstitialAd.loadAd(adRequest);
        interstitialAd2 = new InterstitialAd(this);
        interstitialAd2.setAdUnitId(getString(R.string.ads_unit_id_int2));
        interstitialAd2.loadAd(adRequest);
    }

    @Override
    public void onBackPressed() {
        if(interstitialAd2.isLoaded()){interstitialAd2.show();}
        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_about:
                startActivity(new Intent(this, AboutActivity.class));
                break;
            case R.id.nav_support:
                startActivity(new Intent(this, AboutActivity.class));
                break;
                case R.id.nav_corona:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.who.int/health-topics/coronavirus"));
                browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(browserIntent);
                break;
                case R.id.nav_qna:
                    Intent browserIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.who.int/news-room/q-a-detail/q-a-coronaviruses"));
                    browserIntent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(browserIntent2);
                break;
            case R.id.nav_share:
                try{
                    Intent share = new Intent(Intent.ACTION_SEND);
                    share.setType("text/plain");
                    share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                    share.putExtra(Intent.EXTRA_SUBJECT,"Download Corona Monitor!");
                    share.putExtra(Intent.EXTRA_TEXT,"*Hey my Friends!*\nHave a look at the stats of corona virus on affected countries, from this amazing app called *Corona Monitor*\n\nhttps://play.google.com/store/apps/details?id=com.kritarthagrawal.coronamonitor");
                    startActivity(Intent.createChooser(share,"Share on"));
                }catch (Exception e){
                    Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return false;
    }

    @Override
    public void startListActivity(View view) {
        if(interstitialAd.isLoaded()){interstitialAd.show();}
        Intent i = new Intent(MainActivity.this, ListActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.fadein,R.anim.fadeout);
    }

    @Override
    public void openPrecautions(View view) {
        if(interstitialAd.isLoaded()){interstitialAd.show();}
        startActivity(new Intent(this, PrecautionsActivity.class));
    }
}
