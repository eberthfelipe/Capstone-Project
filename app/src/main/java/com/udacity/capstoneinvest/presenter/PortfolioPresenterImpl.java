package com.udacity.capstoneinvest.presenter;

import android.content.Context;

import com.udacity.capstoneinvest.data.DatabasePortfolio;
import com.udacity.capstoneinvest.view.ManagerUI;

public class PortfolioPresenterImpl implements PortfolioPresenter {

    private DatabasePortfolio mDatabasePortfolio;
    private ManagerUI mManagerUI;

    public DatabasePortfolio getDatabasePortfolio() {
        return mDatabasePortfolio;
    }

    public PortfolioPresenterImpl(ManagerUI managerUI, Context context) {
        this.mManagerUI = managerUI;
        mDatabasePortfolio = new DatabasePortfolio(this, context);
    }

    @Override
    public void setTotalInvestedUI(double value) {
        mManagerUI.getInvestmentPortfolioUi().setTotalInvestedUI(value);
    }

}
