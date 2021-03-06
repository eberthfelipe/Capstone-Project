package com.udacity.capstoneinvest.object;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 *  class: Represents the abstraction of the total value of portfolio
 */
public class InvestmentPortfolio implements Parcelable {

    public static final String DATABASE_VALUE_TOTAL_FIELD = "valueTotal";

    @JsonProperty(DATABASE_VALUE_TOTAL_FIELD)
    private double valueTotal;

    public double getValueTotal() {
        return valueTotal;
    }

    public void setValueTotal(double valueTotal) {
        this.valueTotal = valueTotal;
    }

    @NonNull
    @Override
    public String toString() {
        return "InvestmentPortfolio{" +
                "valueTotal=" + valueTotal +
                '}';
    }

    public InvestmentPortfolio() {
    }

    protected InvestmentPortfolio(Parcel in) {
        valueTotal = in.readDouble();
    }

    public static final Creator<InvestmentPortfolio> CREATOR = new Creator<InvestmentPortfolio>() {
        @Override
        public InvestmentPortfolio createFromParcel(Parcel in) {
            return new InvestmentPortfolio(in);
        }

        @Override
        public InvestmentPortfolio[] newArray(int size) {
            return new InvestmentPortfolio[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(valueTotal);
    }
}
