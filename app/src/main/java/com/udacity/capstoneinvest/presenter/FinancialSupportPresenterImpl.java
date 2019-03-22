package com.udacity.capstoneinvest.presenter;

import android.support.annotation.NonNull;

import com.udacity.capstoneinvest.data.DatabaseFinancialSupport;
import com.udacity.capstoneinvest.object.FinancialSupport;
import com.udacity.capstoneinvest.view.ManagerUI;

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

    public void createFinancialSupport(@NonNull FinancialSupport financialSupport){
        mDatabaseFinancialSupport.createFinancialSupport(financialSupport);
    }
}
