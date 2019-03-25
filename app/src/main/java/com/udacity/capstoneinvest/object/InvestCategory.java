package com.udacity.capstoneinvest.object;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;

/**
 *  class: Represents the abstraction of investments types
 */
public class InvestCategory implements Parcelable {

    public static final String DATABASE_ID_FIELD = "id";
    private static final String DATABASE_TYPE_FIELD = "type";
    private static final String DATABASE_WEIGHT_FIELD = "weight";
    private String id;
    private String type;
//    private double weight;
    private int weight;

    public InvestCategory(String type) {
        this.id = "";
        this.type = type;
        this.weight = 0;
    }

    public InvestCategory(DataSnapshot dataSnapshot) {
        this.id = (String) dataSnapshot.child(DATABASE_ID_FIELD).getValue();
        this.type = (String) dataSnapshot.child(DATABASE_TYPE_FIELD).getValue();
        this.weight = dataSnapshot.child(DATABASE_WEIGHT_FIELD).getValue(Integer.class);
    }

    @NonNull
    @Override
    public String toString() {
        return "InvestCategory{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", weight=" + weight +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    protected InvestCategory(Parcel in) {
        id = in.readString();
        type = in.readString();
        weight = in.readInt();
    }

    public static final Creator<InvestCategory> CREATOR = new Creator<InvestCategory>() {
        @Override
        public InvestCategory createFromParcel(Parcel in) {
            return new InvestCategory(in);
        }

        @Override
        public InvestCategory[] newArray(int size) {
            return new InvestCategory[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(type);
        dest.writeInt(weight);
    }
}

