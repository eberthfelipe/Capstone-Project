package com.udacity.capstoneinvest.presenter;

import android.support.annotation.NonNull;

import com.udacity.capstoneinvest.data.DatabaseCategory;
import com.udacity.capstoneinvest.object.InvestCategory;
import com.udacity.capstoneinvest.view.InvestCategoryUI;

import java.util.ArrayList;

public class DatabaseCategoryPresenterImpl implements DatabaseCategoryPresenter{

    private DatabaseCategory mDatabaseCategory;
    private InvestCategoryUI mInvestCategoryUI;

    public DatabaseCategoryPresenterImpl(InvestCategoryUI mInvestCategoryUI) {
        this.mInvestCategoryUI = mInvestCategoryUI;
        this.mDatabaseCategory = new DatabaseCategory(this);
    }

    @Override
    public void setInvestCategoryUI(ArrayList<InvestCategory> databaseCategories) {
        mInvestCategoryUI.setInvestCategoryUI(databaseCategories);
    }

    @Override
    public boolean updateWeightValue(int position, int value) {
        return mDatabaseCategory.updateWeightValue(position, value);
    }

    public void addCategory(@NonNull InvestCategory investCategory){
        mDatabaseCategory.addCategory(investCategory);

    }
}
