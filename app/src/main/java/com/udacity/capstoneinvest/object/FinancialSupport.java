package com.udacity.capstoneinvest.object;

/**
 *  class: Represents the abstraction of financial support (investments cycle)
 */
public class FinancialSupport {

    private String id;
    private AssetSupport[] assetSupports;
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

    public AssetSupport[] getAssetSupports() {
        return assetSupports;
    }

    public void setAssetSupports(AssetSupport[] assetSupports) {
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
