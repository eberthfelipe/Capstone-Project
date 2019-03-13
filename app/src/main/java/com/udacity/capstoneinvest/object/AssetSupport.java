package com.udacity.capstoneinvest.object;

/**
 *  class: Represents the abstraction of financial assets applied in cycles of investments
 */
public class AssetSupport extends FinancialAsset{

    private double amount;
    private double weight;
    private boolean state;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
