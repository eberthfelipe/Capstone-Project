package com.udacity.capstoneinvest.presenter;

import com.udacity.capstoneinvest.object.FinancialAsset;
import com.udacity.capstoneinvest.object.FinancialSupport;
import com.udacity.capstoneinvest.object.InvestCategory;

import java.util.ArrayList;

public interface FinancialSupportPresenter {

    void setFinancialSupportUI(FinancialSupport financialSupport);
    void showProgress(boolean show);
    void createFinancialSupport(ArrayList<InvestCategory> investCategories, ArrayList<FinancialAsset> financialAssets, double valueSupport);
    void saveFinancialSupport(FinancialSupport financialSupport);
    void closeFinancialSupport();

}
