package com.udacity.capstoneinvest.presenter;

import android.support.annotation.NonNull;

import com.udacity.capstoneinvest.data.DatabaseFinancialSupport;
import com.udacity.capstoneinvest.object.FinancialAsset;
import com.udacity.capstoneinvest.object.FinancialSupport;
import com.udacity.capstoneinvest.object.InvestCategory;
import com.udacity.capstoneinvest.task.FinancialCycleTask;
import com.udacity.capstoneinvest.view.ManagerUI;

import java.util.ArrayList;

public class FinancialSupportPresenterImpl implements FinancialSupportPresenter {

    private DatabaseFinancialSupport mDatabaseFinancialSupport;
    private ManagerUI mManagerUI;

    public FinancialSupportPresenterImpl(ManagerUI mManagerUI) {
        this.mDatabaseFinancialSupport = new DatabaseFinancialSupport(this);
        this.mManagerUI = mManagerUI;
    }

    @Override
    public void setFinancialSupportUI(FinancialSupport financialSupport) {
        mManagerUI.getFinancialSupportUi().setFinancialSupportUI(financialSupport);
    }

    @Override
    public void showProgress(boolean show) {
        mManagerUI.getFinancialSupportUi().showProgress(show);
    }

    @Override
    public void createFinancialSupport(@NonNull ArrayList<InvestCategory> investCategories,
                                       @NonNull ArrayList<FinancialAsset> financialAssets,
                                       double valueSupport) {
        FinancialCycleTask financialCycleTask = new FinancialCycleTask(this,
                investCategories, financialAssets, valueSupport);
        financialCycleTask.execute();
    }

    @Override
    public void saveFinancialSupport(FinancialSupport financialSupport) {
        mDatabaseFinancialSupport.saveFinancialSupport(financialSupport);
    }
}
