package com.udacity.capstoneinvest.object;

/**
 *  class: Represents the abstraction of quantity of investments types
 */
public class InvestCategoryCount {

    // Reference must be InvestCategory class
    private String id;
    private int count;

    public InvestCategoryCount(String id) {
        this.id = id;
        this.count = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
