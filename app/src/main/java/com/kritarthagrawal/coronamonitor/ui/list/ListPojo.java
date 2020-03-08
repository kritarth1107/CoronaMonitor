package com.kritarthagrawal.coronamonitor.ui.list;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListPojo {

    @SerializedName("countries_stat")
    @Expose
    private List<CountriesStat> countriesStat = null;
    @SerializedName("statistic_taken_at")
    @Expose
    private String statisticTakenAt;

    public List<CountriesStat> getCountriesStat() {
        return countriesStat;
    }

    public void setCountriesStat(List<CountriesStat> countriesStat) {
        this.countriesStat = countriesStat;
    }

    public String getStatisticTakenAt() {
        return statisticTakenAt;
    }

    public void setStatisticTakenAt(String statisticTakenAt) {
        this.statisticTakenAt = statisticTakenAt;
    }

}