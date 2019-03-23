package com.udacity.capstoneinvest.object;

/**
 *  class: Represents the abstraction of the total value of portfolio
 */
public class InvestmentPortfolio {

    public static String DATABASE_VALUE_TOTAL_FIELD = "valueTotal";
    private double valueTotal;

    public double getValueTotal() {
        return valueTotal;
    }

    public void setValueTotal(double valueTotal) {
        this.valueTotal = valueTotal;
    }

    @Override
    public String toString() {
        return "InvestmentPortfolio{" +
                "valueTotal=" + valueTotal +
                '}';
    }
}
