package com.udacity.capstoneinvest.presenter;

import com.udacity.capstoneinvest.data.DatabasePortfolio;
import com.udacity.capstoneinvest.view.ManagerUI;

public class DatabasePortfolioPresenterImpl implements DatabasePortfolioPresenter {

    private DatabasePortfolio mDatabasePortfolio;
    private ManagerUI mManagerUI;

    public DatabasePortfolioPresenterImpl(ManagerUI managerUI) {
        this.mManagerUI = managerUI;
        mDatabasePortfolio = new DatabasePortfolio(this);
    }

    @Override
    public void setTotalInvestedUI(double value) {
        mManagerUI.getInvestmentPortfolioUi().setTotalInvestedUI(value);
    }

}
