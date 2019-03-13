package com.udacity.capstoneinvest.object;

/**
 *  class: Represents the abstraction of financial support (investments cycle)
 */
public class FinancialSupport {

    private int id;
    private AssetSupport[] assetSupports;
    //Active cycle will receive true, past cycle will receive false
    private boolean state;

    public FinancialSupport() {
        //TODO 1: get ID from database and create assetSupports array based on last cycle
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AssetSupport[] getAssetSupports() {
        return assetSupports;
    }

    public void setAssetSupports(AssetSupport[] assetSupports) {
        this.assetSupports = assetSupports;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
