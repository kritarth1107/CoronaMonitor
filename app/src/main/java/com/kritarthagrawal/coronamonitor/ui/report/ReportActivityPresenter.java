package com.kritarthagrawal.coronamonitor.ui.report;

public class ReportActivityPresenter implements ReportContract.presenter {
    ReportContract.view reportView;

    public ReportActivityPresenter(ReportContract.view reportView) {
        this.reportView = reportView;
    }
}
