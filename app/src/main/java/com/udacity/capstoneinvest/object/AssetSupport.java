package com.udacity.capstoneinvest.object;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;

/**
 *  class: Represents the abstraction of financial assets applied in cycles of investments
 */
public class AssetSupport extends FinancialAsset implements Parcelable {

    private static final String DATABASE_AMOUNT_FIELD = "amount";
    private static final String DATABASE_WEIGHT_FIELD = "weight";
    private static final String DATABASE_STATE_FIELD = "state";
    private double amount;
    private double weight;
    private int state;
    /**
     * state :
     *  0 - none
     *  1 - purchase
     *  2 - bid
     *  3 - cancel
     */

    public AssetSupport(String name, double value, String investCategory) {
        super(name, value, investCategory);
    }

    public AssetSupport(DataSnapshot dataSnapshot) {
        super(dataSnapshot);
        this.amount = dataSnapshot.child(DATABASE_AMOUNT_FIELD).getValue(Double.class);
        this.weight = dataSnapshot.child(DATABASE_WEIGHT_FIELD).getValue(Double.class);
        this.state = dataSnapshot.child(DATABASE_STATE_FIELD).getValue(Integer.class);
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

    protected AssetSupport(Parcel in) {
        super(in);
        amount = in.readDouble();
        weight = in.readDouble();
        state = in.readInt();
    }

    public static final Creator<AssetSupport> CREATOR = new Creator<AssetSupport>() {
        @Override
        public AssetSupport createFromParcel(Parcel in) {
            return new AssetSupport(in);
        }

        @Override
        public AssetSupport[] newArray(int size) {
            return new AssetSupport[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(amount);
        dest.writeDouble(weight);
        dest.writeInt(state);
    }
}
