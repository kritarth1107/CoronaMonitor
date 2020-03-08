package com.kritarthagrawal.coronamonitor.ui.list;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountriesStat {

    @SerializedName("country_name")
    @Expose
    private String countryName;
    @SerializedName("cases")
    @Expose
    private String cases;
    @SerializedName("deaths")
    @Expose
    private String deaths;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("total_recovered")
    @Expose
    private String totalRecovered;
    @SerializedName("new_deaths")
    @Expose
    private String newDeaths;
    @SerializedName("new_cases")
    @Expose
    private String newCases;
    @SerializedName("serious_critical")
    @Expose
    private String seriousCritical;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(String totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    public String getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(String newDeaths) {
        this.newDeaths = newDeaths;
    }

    public String getNewCases() {
        return newCases;
    }

    public void setNewCases(String newCases) {
        this.newCases = newCases;
    }

    public String getSeriousCritical() {
        return seriousCritical;
    }

    public void setSeriousCritical(String seriousCritical) {
        this.seriousCritical = seriousCritical;
    }

}