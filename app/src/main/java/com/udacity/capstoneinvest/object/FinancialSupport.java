package com.udacity.capstoneinvest.object;

/**
 *  class: Represents the abstraction of financial support (investments cycle)
 */
public class FinancialSupport {

    private int id;
    private FinancialAsset[] financialAssets;

    public FinancialSupport() {
        //TODO 1: get ID from database and create financialAssets array based on last cycle
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FinancialAsset[] getFinancialAssets() {
        return financialAssets;
    }

    public void setFinancialAssets(FinancialAsset[] financialAssets) {
        this.financialAssets = financialAssets;
    }
}
