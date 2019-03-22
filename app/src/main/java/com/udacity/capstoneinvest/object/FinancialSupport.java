package com.udacity.capstoneinvest.object;

import java.util.ArrayList;

/**
 *  class: Represents the abstraction of financial support (investments cycle)
 */
public class FinancialSupport {

    private String id;
    private ArrayList<AssetSupport> assetSupports;
    private double valueSupport;
    //Active cycle will receive true, past cycle will receive false
    private boolean state;

    public FinancialSupport() {
        //TODO 1: get ID from database and create assetSupports array based on last cycle
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<AssetSupport> getAssetSupports() {
        return assetSupports;
    }

    public void setAssetSupports(ArrayList<AssetSupport> assetSupports) {
        this.assetSupports = assetSupports;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean isState() {
        return state;
    }

    public double getValueSupport() {
        return valueSupport;
    }

    public void setValueSupport(double valueSupport) {
        this.valueSupport = valueSupport;
    }
}
