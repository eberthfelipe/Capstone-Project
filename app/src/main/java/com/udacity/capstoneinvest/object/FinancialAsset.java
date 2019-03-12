package com.udacity.capstoneinvest.object;

/**
 *  class: Represents the abstraction of financial assets
 */
public class FinancialAsset {

    private String name;
    private Double value;
    private int amount;
    private InvestCategory investCategory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public InvestCategory getInvestCategory() {
        return investCategory;
    }

    public void setInvestCategory(InvestCategory investCategory) {
        this.investCategory = investCategory;
    }
}
