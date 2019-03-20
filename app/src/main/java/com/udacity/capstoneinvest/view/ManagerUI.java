package com.udacity.capstoneinvest.view;

import com.udacity.capstoneinvest.presenter.DatabaseCategoryPresenterImpl;

public interface ManagerUI {

    // UI
    InvestCategoryUI getInvestCategoryUi();
    InvestmentPortfolioUI getInvestmentPortfolioUi();

    // Presenter
    DatabaseCategoryPresenterImpl getDatabaseCategoryPresenter();
}
