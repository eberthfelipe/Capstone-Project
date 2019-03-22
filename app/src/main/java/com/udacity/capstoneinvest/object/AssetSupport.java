package com.udacity.capstoneinvest.object;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;

/**
 *  class: Represents the abstraction of financial assets applied in cycles of investments
 */
public class AssetSupport extends FinancialAsset{

    private double amount;
    private double weight;
    private int state;

    public AssetSupport(String name, double value, String investCategory) {
        super(name, value, investCategory);
    }

    public AssetSupport(DataSnapshot dataSnapshot) {
        super(dataSnapshot);
    }

    public AssetSupport(FinancialAsset financialAsset) {
        this(financialAsset.getName(), financialAsset.getValue(), financialAsset.getInvestCategory());
    }

    @NonNull
    @Override
    public String toString() {
        return "AssetSupport{" +
                "amount=" + amount +
                ", weight=" + weight +
                ", state=" + state +
                '}' + super.toString();
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
