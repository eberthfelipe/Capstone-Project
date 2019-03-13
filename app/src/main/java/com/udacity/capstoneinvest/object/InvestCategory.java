package com.udacity.capstoneinvest.object;

/**
 *  class: Represents the abstraction of investments types
 */
public class InvestCategory {

    private String id;
    private String type;
    private double weight;

    public InvestCategory(String type) {
        this.type = type;
        this.weight = 0;
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

}
