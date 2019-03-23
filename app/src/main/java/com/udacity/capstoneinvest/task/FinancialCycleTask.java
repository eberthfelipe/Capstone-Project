package com.udacity.capstoneinvest.task;

import android.os.AsyncTask;
import android.util.Log;

import com.udacity.capstoneinvest.object.AssetSupport;
import com.udacity.capstoneinvest.object.FinancialAsset;
import com.udacity.capstoneinvest.object.FinancialSupport;
import com.udacity.capstoneinvest.object.InvestCategory;
import com.udacity.capstoneinvest.presenter.FinancialSupportPresenter;

import java.util.ArrayList;

public class FinancialCycleTask extends AsyncTask<Void, Void, FinancialSupport> {

    private static final String TAG = FinancialCycleTask.class.getName();
    private FinancialSupportPresenter mFinancialSupportPresenter;
    private ArrayList<InvestCategory> mInvestCategories;
    private ArrayList<FinancialAsset> mFinancialAssets;
    private double mValueSupport;

    public FinancialCycleTask(FinancialSupportPresenter financialSupportPresenter,
                              ArrayList<InvestCategory> investCategories,
                              ArrayList<FinancialAsset> financialAssets,
                              double valueSupport) {
        this.mFinancialSupportPresenter = financialSupportPresenter;
        this.mInvestCategories = new ArrayList<>(investCategories);
        this.mFinancialAssets = new ArrayList<>(financialAssets);
        this.mValueSupport = valueSupport;
    }

    @Override
    protected void onPreExecute() {
        mFinancialSupportPresenter.showProgress(true);
    }

    @Override
    protected FinancialSupport doInBackground(Void... voids) {
        FinancialSupport financialSupport = new FinancialSupport();
        ArrayList<AssetSupport> assetSupports = new ArrayList<>();
        for (InvestCategory investCategory: mInvestCategories) {
            if(investCategory.getWeight()>0){
                int investCategoryCount = getInvestCategoryCount(investCategory.getType());
                for(FinancialAsset financialAsset: mFinancialAssets){
                    if(isSameInvestCategory(financialAsset, investCategory.getType())){
                        AssetSupport assetSupport = new AssetSupport(financialAsset);
                        calculateValues(assetSupport, investCategoryCount, investCategory.getWeight());
                        Log.d(TAG, "doInBackground create new cycle: \n" + assetSupport.toString());
                        assetSupports.add(assetSupport);
                    }
                }
            }
        }
        financialSupport.setAssetSupports(assetSupports);
        financialSupport.setValueSupport(mValueSupport);
        financialSupport.setState(true);
        Log.d(TAG, "doInBackground financialSupport: \n" + financialSupport.toString());
        return financialSupport;
    }

    @Override
    protected void onPostExecute(FinancialSupport financialSupport) {
        mFinancialSupportPresenter.saveFinancialSupport(financialSupport);
        mFinancialSupportPresenter.showProgress(false);
    }

    private int getInvestCategoryCount(String category){
        int investCategoryCount = 0;
        for (FinancialAsset financialAsset: mFinancialAssets) {
            if(isSameInvestCategory(financialAsset, category)){
                investCategoryCount++;
            }
        }
        return investCategoryCount;
    }

    private boolean isSameInvestCategory(FinancialAsset financialAsset, String category){
        return financialAsset.getInvestCategory().equals(category);
    }

    private void calculateValues(AssetSupport assetSupport,
                                 int investCategoryCount,
                                 int categoryWeight){
        Log.d(TAG, "calculateValues categoryWeight/investCategoryCount: " + categoryWeight/investCategoryCount);
        assetSupport.setWeight(categoryWeight/investCategoryCount);
        double maxValue = mValueSupport*assetSupport.getWeight()/100;
        assetSupport.setAmount(maxValue/assetSupport.getValue());
        assetSupport.setState(0);
    }

}
