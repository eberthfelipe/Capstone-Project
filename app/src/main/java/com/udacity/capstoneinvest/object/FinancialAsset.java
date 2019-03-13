package com.udacity.capstoneinvest.object;

/**
 *  class: Represents the abstraction of financial assets
 */
public class FinancialAsset {

    private String name;
    private double value;
    // Reference must be InvestCategory class
    private int investCategory;

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

    public int getInvestCategory() {
        return investCategory;
    }

    public void setInvestCategory(int investCategory) {
        this.investCategory = investCategory;
    }
}
