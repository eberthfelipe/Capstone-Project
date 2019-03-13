package com.udacity.capstoneinvest.object;

/**
 *  class: Represents the abstraction of financial assets
 */
public class FinancialAsset {

    private String name;
    private double value;
    // Reference must be InvestCategory class
    private String investCategory;

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
}
