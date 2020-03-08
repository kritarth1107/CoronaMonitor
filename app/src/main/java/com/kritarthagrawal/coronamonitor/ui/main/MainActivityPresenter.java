package com.kritarthagrawal.coronamonitor.ui.main;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivityPresenter implements MainContract.presenter {

    private MainContract.view mainContactView;

    public MainActivityPresenter(MainContract.view mainContactView) {
        this.mainContactView = mainContactView;
    }


}
