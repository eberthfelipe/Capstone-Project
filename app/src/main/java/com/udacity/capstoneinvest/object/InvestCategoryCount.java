package com.udacity.capstoneinvest.object;

/**
 *  class: Represents the abstraction of quantity of investments types
 */
public class InvestCategoryCount {

    // Reference must be InvestCategory class
    private int id;
    private int count;

    public InvestCategoryCount(int id) {
        this.id = id;
        this.count = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
