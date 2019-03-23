package com.udacity.capstoneinvest.object;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

/**
 *  class: Represents the abstraction of financial support (investments cycle)
 */
public class FinancialSupport {

    public static final String DATABASE_ID_FIELD = "id";
    public static final String DATABASE_ASSET_SUPPORT_FIELD = "assetSupport";
    public static final String DATABASE_VALUE_SUPPORT_FIELD = "valueSupport";
    public static final String DATABASE_STATE_FIELD = "state";
    private String id;
    private ArrayList<AssetSupport> assetSupports;
    private double valueSupport;
    //Active cycle will receive true, past cycle will receive false
    private boolean state;

    public FinancialSupport() {
        //TODO 1: get ID from database and create assetSupports array based on last cycle
    }

    public FinancialSupport(DataSnapshot dataSnapshot) {
        this.id = dataSnapshot.child(DATABASE_ID_FIELD).getValue(String.class);
        this.assetSupports = (ArrayList<AssetSupport>) dataSnapshot.child(DATABASE_ASSET_SUPPORT_FIELD).getValue();
        this.valueSupport = dataSnapshot.child(DATABASE_VALUE_SUPPORT_FIELD).getValue(Double.class);
        this.state = dataSnapshot.child(DATABASE_STATE_FIELD).getValue(Boolean.class);
    }

    public FinancialSupport(FinancialSupport financialSupport) {
        this.id = financialSupport.id;
        this.assetSupports = financialSupport.assetSupports;
        this.valueSupport = financialSupport.valueSupport;
        this.state = financialSupport.state;
    }

    @NonNull
    @Override
    public String toString() {
        return "FinancialSupport{" +
                "id='" + id + '\'' +
                ", assetSupports=" + assetSupports +
                ", valueSupport=" + valueSupport +
                ", state=" + state +
                '}';
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
