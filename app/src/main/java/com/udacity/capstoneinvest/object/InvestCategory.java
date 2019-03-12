package com.udacity.capstoneinvest.object;

/**
 *  class: Represents the abstraction of investments types
 */
public class InvestCategory {

    private String type;

    public InvestCategory(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
