package com.udacity.capstoneinvest.presenter;

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
}
