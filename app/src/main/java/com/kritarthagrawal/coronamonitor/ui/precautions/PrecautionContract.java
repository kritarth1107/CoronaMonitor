package com.kritarthagrawal.coronamonitor.ui.precautions;

public interface PrecautionContract {
    public interface  view{
        public void toolbarOnNavigation();
        public void loadAds();
        public void addDotIndicator(int position);
        public void setupCards();

    }

    public interface presenter{}
}
