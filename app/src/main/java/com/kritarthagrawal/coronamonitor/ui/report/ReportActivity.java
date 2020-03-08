package com.kritarthagrawal.coronamonitor.ui.report;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.kritarthagrawal.coronamonitor.R;
import com.kritarthagrawal.coronamonitor.ui.list.ListActivityPresenter;
import com.kritarthagrawal.coronamonitor.ui.precautions.PrecautionsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReportActivity extends AppCompatActivity implements ReportContract.view{

    @BindView(R.id.adView)
    AdView adView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.countryName)
    TextView countryName;
    @BindView(R.id.cases)
    TextView casesTv;
    @BindView(R.id.deaths)
    TextView deathsTv;
    @BindView(R.id.recovered)
    TextView recoveredTv;
    @BindView(R.id.critical)
    TextView criticalTv;
    @BindView(R.id.newdeaths)
    TextView newdeathsTv;
    @BindView(R.id.newcase)
    TextView newcaseTv;
    @BindView(R.id.circleLayout)
    LinearLayout circleLayout;
    @BindView(R.id.countryFlag)
    ImageView countryFlag;

    ReportActivityPresenter mPresenter;
    String country,cases,newcase,deaths,recovered,critical,newdeaths;
    Intent intent;
    int casesInt;

    int[] flags = {R.drawable.ic_china,R.drawable.ic_south_korea,R.drawable.ic_italy,R.drawable.ic_iran,R.drawable.defaultflag,R.drawable.germany,R.drawable.france,R.drawable.japan,R.drawable.spain,R.drawable.ic_usa,R.drawable.singapore,R.drawable.uk,R.drawable.switzerland,R.drawable.hong_kong,R.drawable.sweden,R.drawable.norway,R.drawable.netherlands,R.drawable.kuwait,R.drawable.bahrain,R.drawable.malaysia,R.drawable.australia,R.drawable.belgium,R.drawable.thailand,R.drawable.taiwan,R.drawable.austria,R.drawable.canada,R.drawable.iraq,R.drawable.iceland,R.drawable.greece,R.drawable.ic_india,R.drawable.united_arab_emirates,R.drawable.san_marino,R.drawable.denmark,R.drawable.algeria,R.drawable.israel,R.drawable.lebanon,R.drawable.oman,R.drawable.vietnam,R.drawable.ecuador,R.drawable.czech_republic,R.drawable.finland,R.drawable.macao,R.drawable.croatia,R.drawable.portugal,R.drawable.qatar,R.drawable.palestine,R.drawable.azerbaijan,R.drawable.belarus,R.drawable.ireland,R.drawable.mexico,R.drawable.romania,R.drawable.pakistan,R.drawable.saudi_arabia,R.drawable.brazil,R.drawable.georgia,R.drawable.russia,R.drawable.senegal,R.drawable.philippines,R.drawable.egypt,R.drawable.estonia,R.drawable.new_zealand,R.drawable.chile,R.drawable.slovenia,R.drawable.indonesia,R.drawable.morocco,R.drawable.bosnia_and_herzegovina,R.drawable.hungary,R.drawable.defaultflag,R.drawable.andorra,R.drawable.armenia,R.drawable.cambodia,R.drawable.dominican_republic,R.drawable.jordan,R.drawable.latvia,R.drawable.lithuania,R.drawable.luxembourg,R.drawable.republic_of_macedonia,R.drawable.monaco,R.drawable.nepal,R.drawable.nigeria,R.drawable.sri_lanka,R.drawable.tunisia,R.drawable.ukraine,R.drawable.argentina,R.drawable.liechtenstein,R.drawable.poland,R.drawable.south_africa};
    String[] countryString = {"China","S. Korea","Italy","Iran","Diamond Princess","Germany","France","Japan","Spain","USA","Singapore","UK","Switzerland","Hong Kong","Sweden","Norway","Netherlands","Kuwait","Bahrain","Malaysia","Australia","Belgium","Thailand","Taiwan","Austria","Canada","Iraq","Iceland","Greece","India","UAE","San Marino","Denmark","Algeria","Israel","Lebanon","Oman","Vietnam","Ecuador","Czechia","Finland","Macao","Croatia","Portugal","Qatar","Palestine","Azerbaijan","Belarus","Ireland","Mexico","Romania","Pakistan","Saudi Arabia","Brazil","Georgia","Russia","Senegal","Philippines","Egypt","Estonia","New Zealand","Chile","Slovenia","Indonesia","Morocco","Bosnia and Herzegovina","Hungary","Afghanistan","Andorra","Armenia","Cambodia","Dominican Republic","Jordan","Latvia","Lithuania","Luxembourg","North Macedonia","Monaco","Nepal","Nigeria","Sri Lanka","Tunisia","Ukraine","Argentina","Liechtenstein","Poland","South Africa"};
    public InterstitialAd interstitialAd,interstitialAd2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);
        mPresenter = new ReportActivityPresenter(this);
        setSupportActionBar(toolbar);
        intent=getIntent();
        country = intent.getStringExtra("country");
        cases = intent.getStringExtra("cases");
        deaths = intent.getStringExtra("deaths");
        recovered = intent.getStringExtra("recovered");
        newcase = intent.getStringExtra("newcase");
        critical = intent.getStringExtra("critical");
        newdeaths = intent.getStringExtra("newdeaths");
        loadAds();
        toolbarOnNavigation();
        setTextViews();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.share:
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

                //Toast.makeText(this, "Share Tapped", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
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
    public void setTextViews() {
        for(int i=0;i<flags.length;i++){
            if(country.equals(countryString[i])){
                countryFlag.setImageResource(flags[i]);
            }
        }

        countryName.setText(country);
        casesTv.setText(cases);
        deathsTv.setText(deaths);
        recoveredTv.setText(recovered);
        newcaseTv.setText(newcase);
        criticalTv.setText(critical);
        newdeathsTv.setText(newdeaths);

        try{casesInt = Integer.parseInt(cases);}catch (Exception e){casesInt=-1;}
        if(casesInt>=150){
            circleLayout.setBackground(getDrawable(R.drawable.circlered));
        }
        else if(casesInt<150 && casesInt>=50){
            circleLayout.setBackground(getDrawable(R.drawable.circleorange));
        }
        else if(casesInt<50 && casesInt>=0){
            circleLayout.setBackground(getDrawable(R.drawable.circleyellow));
        }
        else{
            circleLayout.setBackground(getDrawable(R.drawable.circlered));
        }
    }

    @Override
    public void openPrecautions(View view) {
        if(interstitialAd.isLoaded()){interstitialAd.show();}
        startActivity(new Intent(this, PrecautionsActivity.class));
    }

    @Override
    public void loadAds() {
        MobileAds.initialize(this,getString(R.string.ads_application_id));
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.ads_unit_id_int));
        interstitialAd.loadAd(adRequest);


        interstitialAd2 = new InterstitialAd(this);
        interstitialAd2.setAdUnitId(getString(R.string.ads_unit_id_int2));
        interstitialAd2.loadAd(adRequest);

        if(interstitialAd.isLoaded()){interstitialAd.show();}
    }

    @Override
    public void onBackPressed() {
        if(interstitialAd2.isLoaded()){interstitialAd2.show();}
        super.onBackPressed();
    }
}
