package com.udacity.capstoneinvest.object;

import com.google.firebase.database.DataSnapshot;

/**
 *  class: Represents the abstraction of investments types
 */
public class InvestCategory {

    public static String DATABASE_ID_FIELD = "id";
    public static String DATABASE_TYPE_FIELD = "type";
    public static String DATABASE_WEIGHT_FIELD = "weight";
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

}

