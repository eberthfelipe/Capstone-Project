package com.udacity.capstoneinvest.view;

import com.udacity.capstoneinvest.presenter.CategoryPresenterImpl;

public interface ManagerUI {

    // UI
    InvestmentPortfolioUI getInvestmentPortfolioUi();
    InvestCategoryUI getInvestCategoryUi();
    FinancialAssetUI getFinancialAssetUi();

    // Presenter
    CategoryPresenterImpl getDatabaseCategoryPresenter();
}
