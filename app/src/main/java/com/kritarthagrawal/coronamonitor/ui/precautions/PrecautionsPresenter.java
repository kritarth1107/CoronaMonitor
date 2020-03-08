package com.kritarthagrawal.coronamonitor.ui.precautions;

public class PrecautionsPresenter implements PrecautionContract.presenter {
    PrecautionContract.view precautionView;

    public PrecautionsPresenter(PrecautionContract.view precautionView) {
        this.precautionView = precautionView;
    }
}
