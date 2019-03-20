package com.udacity.capstoneinvest.presenter;

import com.udacity.capstoneinvest.object.InvestCategory;

import java.util.ArrayList;

public interface DatabaseCategoryPresenter {

    void setInvestCategoryUI(ArrayList<InvestCategory> databaseCategories);
    boolean updateWeightValue(int position, int value);
    ArrayList<InvestCategory> getInvestCategories();
}
