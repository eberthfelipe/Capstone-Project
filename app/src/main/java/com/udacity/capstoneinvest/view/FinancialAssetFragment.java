package com.udacity.capstoneinvest.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.capstoneinvest.R;
import com.udacity.capstoneinvest.databinding.FinancialAssetViewContentBinding;
import com.udacity.capstoneinvest.object.FinancialAsset;

import java.util.ArrayList;

public class FinancialAssetFragment extends Fragment
        implements FinancialAssetUI {

    private static final String TAG = FinancialAssetFragment.class.getName();
    private FinancialAssetViewContentBinding mFinancialAssetViewContentBinding;

    public FinancialAssetFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFinancialAssetViewContentBinding = DataBindingUtil.inflate(inflater, R.layout.financial_asset_view_content, container, false);

        mFinancialAssetViewContentBinding.setHasFinancialAsset(false);

        return mFinancialAssetViewContentBinding.getRoot();
    }

    public void callBackDialog(String categoryType){
        Log.d(TAG, "callback: " + categoryType);
//        mDatabaseCategoryPresenterImpl.addCategory(new InvestCategory(categoryType));
    }

    //region FinancialAssetUI

    @Override
    public void setInvestCategoryUI(ArrayList<FinancialAsset> financialAssets) {

    }

    //endregion
}
