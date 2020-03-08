package com.kritarthagrawal.coronamonitor.ui.report;

import android.view.View;

public interface ReportContract {
    public interface view{
        public void loadAds();
        public void toolbarOnNavigation();
        public void setTextViews();
        public void openPrecautions(View view);
    }
    public interface presenter{}
}
