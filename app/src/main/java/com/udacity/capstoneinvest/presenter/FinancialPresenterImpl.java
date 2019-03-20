package com.udacity.capstoneinvest.presenter;

import android.support.annotation.NonNull;

import com.udacity.capstoneinvest.data.DatabaseFinancialAsset;
import com.udacity.capstoneinvest.object.FinancialAsset;
import com.udacity.capstoneinvest.view.ManagerUI;

import java.util.ArrayList;

public class FinancialPresenterImpl implements FinancialAssetPresenter{

    private DatabaseFinancialAsset mDatabaseFinancialAsset;
    private ManagerUI mManagerUI;

    public FinancialPresenterImpl(ManagerUI mManagerUI) {
        this.mManagerUI = mManagerUI;
        mDatabaseFinancialAsset = new DatabaseFinancialAsset(this);
    }

    @Override
    public void setFinancialAssetUI(ArrayList<FinancialAsset> financialAssets) {

    }

    public void addFinancialAsset(@NonNull FinancialAsset financialAsset){
        mDatabaseFinancialAsset.addFinancialAsset(financialAsset);
    }
}
