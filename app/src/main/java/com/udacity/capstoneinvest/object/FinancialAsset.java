package com.udacity.capstoneinvest.object;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;

/**
 *  class: Represents the abstraction of financial assets
 */
public class FinancialAsset implements Parcelable {

    private static final String DATABASE_NAME_FIELD = "name";
    private static final String DATABASE_VALUE_FIELD = "value";
    private static final String DATABASE_INVEST_CATEGORY_FIELD = "investCategory";
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

    protected FinancialAsset(Parcel in) {
        name = in.readString();
        value = in.readDouble();
        investCategory = in.readString();
    }

    public static final Creator<FinancialAsset> CREATOR = new Creator<FinancialAsset>() {
        @Override
        public FinancialAsset createFromParcel(Parcel in) {
            return new FinancialAsset(in);
        }

        @Override
        public FinancialAsset[] newArray(int size) {
            return new FinancialAsset[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeDouble(value);
        dest.writeString(investCategory);
    }
}
