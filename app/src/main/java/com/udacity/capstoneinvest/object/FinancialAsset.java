package com.udacity.capstoneinvest.object;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;

/**
 *  class: Represents the abstraction of financial assets
 */
public class FinancialAsset {

    public static String DATABASE_NAME_FIELD = "name";
    public static String DATABASE_VALUE_FIELD = "value";
    public static String DATABASE_INVEST_CATEGORY_FIELD = "investCategory";
    private String name;
    private double value;
    // Reference must be InvestCategory class
    private String investCategory;

    public FinancialAsset(String name, double value, String investCategory) {
        this.name = name;
        this.value = value;
        this.investCategory = investCategory;
    }

    public FinancialAsset(DataSnapshot dataSnapshot) {
        this.name = dataSnapshot.child(DATABASE_NAME_FIELD).getValue(String.class);
        this.value = dataSnapshot.child(DATABASE_VALUE_FIELD).getValue(Double.class);
        this.investCategory = dataSnapshot.child(DATABASE_INVEST_CATEGORY_FIELD).getValue(String.class);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getInvestCategory() {
        return investCategory;
    }

    public void setInvestCategory(String investCategory) {
        this.investCategory = investCategory;
    }

    @NonNull
    @Override
    public String toString() {
        return "FinancialAsset{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", investCategory='" + investCategory + '\'' +
                '}';
    }
}
