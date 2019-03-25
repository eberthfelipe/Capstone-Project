package com.udacity.capstoneinvest.object;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

/**
 *  class: Represents the abstraction of financial support (investments cycle)
 */
public class FinancialSupport implements Parcelable {

    private static final String DATABASE_ID_FIELD = "id";
    private static final String DATABASE_ASSET_SUPPORT_FIELD = "assetSupports";
    private static final String DATABASE_VALUE_SUPPORT_FIELD = "valueSupport";
    private static final String DATABASE_STATE_FIELD = "state";
    private String id;
    private ArrayList<AssetSupport> assetSupports;
    private double valueSupport;
    //Active cycle will receive true, past cycle will receive false
    private boolean state;

    public FinancialSupport() {
    }

    public FinancialSupport(DataSnapshot dataSnapshot) {
        this.id = dataSnapshot.child(DATABASE_ID_FIELD).getValue(String.class);
        this.assetSupports = getArrayOfAssetSupport(dataSnapshot.child(DATABASE_ASSET_SUPPORT_FIELD));
        this.valueSupport = dataSnapshot.child(DATABASE_VALUE_SUPPORT_FIELD).getValue(Double.class);
        this.state = dataSnapshot.child(DATABASE_STATE_FIELD).getValue(Boolean.class);
    }

    private ArrayList<AssetSupport> getArrayOfAssetSupport(DataSnapshot dataSnapshot){
        ArrayList<AssetSupport> assetSupports = new ArrayList<>();
        for (DataSnapshot child: dataSnapshot.getChildren()) {
            assetSupports.add(new AssetSupport(child));
        }
        return assetSupports;
    }

    public FinancialSupport(FinancialSupport financialSupport) {
        this.id = financialSupport.id;
        this.assetSupports = financialSupport.assetSupports;
        this.valueSupport = financialSupport.valueSupport;
        this.state = financialSupport.state;
    }

    @NonNull
    @Override
    public String toString() {
        return "FinancialSupport{" +
                "id='" + id + '\'' +
                ", assetSupports=" + assetSupports +
                ", valueSupport=" + valueSupport +
                ", state=" + state +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<AssetSupport> getAssetSupports() {
        return assetSupports;
    }

    public void setAssetSupports(ArrayList<AssetSupport> assetSupports) {
        this.assetSupports = assetSupports;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean isState() {
        return state;
    }

    public double getValueSupport() {
        return valueSupport;
    }

    public void setValueSupport(double valueSupport) {
        this.valueSupport = valueSupport;
    }

    protected FinancialSupport(Parcel in) {
        id = in.readString();
        assetSupports = in.createTypedArrayList(AssetSupport.CREATOR);
        valueSupport = in.readDouble();
        state = in.readByte() != 0;
    }

    public static final Creator<FinancialSupport> CREATOR = new Creator<FinancialSupport>() {
        @Override
        public FinancialSupport createFromParcel(Parcel in) {
            return new FinancialSupport(in);
        }

        @Override
        public FinancialSupport[] newArray(int size) {
            return new FinancialSupport[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeTypedList(assetSupports);
        dest.writeDouble(valueSupport);
        dest.writeByte((byte) (state ? 1 : 0));
    }
}
