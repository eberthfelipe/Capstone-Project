package com.udacity.capstoneinvest.presenter;

import com.udacity.capstoneinvest.data.DatabasePortfolio;
import com.udacity.capstoneinvest.view.InvestmentPortfolioUI;

public class DatabasePortfolioPresenterImpl implements DatabasePortfolioPresenter {

    private DatabasePortfolio mDatabasePortfolio;
    private InvestmentPortfolioUI mInvestmentPortfolioUI;

    public DatabasePortfolioPresenterImpl(InvestmentPortfolioUI mInvestmentPortfolioUI) {
        this.mInvestmentPortfolioUI = mInvestmentPortfolioUI;
        mDatabasePortfolio = new DatabasePortfolio(this);
    }

    @Override
    public void setTotalInvestedUI(double value) {
        mInvestmentPortfolioUI.setTotalInvestedUI(value);
    }

}
