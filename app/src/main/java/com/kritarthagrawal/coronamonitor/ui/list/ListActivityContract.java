package com.kritarthagrawal.coronamonitor.ui.list;

import android.view.View;

import java.util.List;

public interface ListActivityContract {
    interface view{
        public void toolbarOnNavigation();
        public void loadingAnimations(boolean b);
        public void listActivityCallBack(String error, List<CountriesStat> list,String takkenAt);
        public void loadAds();
    }
    interface presenter{
        public void getList();
    }
}
