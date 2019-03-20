package com.udacity.capstoneinvest.presenter;

import android.support.annotation.NonNull;

import com.udacity.capstoneinvest.data.DatabaseCategory;
import com.udacity.capstoneinvest.object.InvestCategory;
import com.udacity.capstoneinvest.view.ManagerUI;

import java.util.ArrayList;

public class CategoryPresenterImpl implements CategoryPresenter {

    private DatabaseCategory mDatabaseCategory;
    private ManagerUI mManagerUI;

    public CategoryPresenterImpl(ManagerUI managerUI) {
        this.mManagerUI = managerUI;
        this.mDatabaseCategory = new DatabaseCategory(this);
    }

    @Override
    public void setInvestCategoryUI(ArrayList<InvestCategory> databaseCategories) {
        mManagerUI.getInvestCategoryUi().setInvestCategoryUI(databaseCategories);
    }

    @Override
    public boolean updateWeightValue(int position, int value) {
        return mDatabaseCategory.updateWeightValue(position, value);
    }

    @Override
    public ArrayList<InvestCategory> getInvestCategories() {
        return mDatabaseCategory.getInvestCategories();
    }

    public void addCategory(@NonNull InvestCategory investCategory){
        mDatabaseCategory.addCategory(investCategory);

    }
}
