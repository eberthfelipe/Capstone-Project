package com.udacity.capstoneinvest.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
    private static final String ARG_FINANCIAL_ASSETS = "financial_assets";
    private FinancialAssetViewContentBinding mFinancialAssetViewContentBinding;
    private ArrayList<FinancialAsset> mFinancialAssets;

    public FinancialAssetFragment() {
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList(ARG_FINANCIAL_ASSETS, mFinancialAssets);
        super.onSaveInstanceState(outState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFinancialAssetViewContentBinding = DataBindingUtil.inflate(inflater, R.layout.financial_asset_view_content, container, false);

        if(savedInstanceState != null && !savedInstanceState.isEmpty())
            mFinancialAssets = savedInstanceState.getParcelableArrayList(ARG_FINANCIAL_ASSETS);
        else
            mFinancialAssetViewContentBinding.setShowProgress(true);
        updateUI();

        return mFinancialAssetViewContentBinding.getRoot();
    }

    //region FinancialAssetUI

    @Override
    public void setFinancialAssetUI(ArrayList<FinancialAsset> financialAssets) {
        if(financialAssets != null && !financialAssets.isEmpty()){
            mFinancialAssets = new ArrayList<>(financialAssets);
        } else {
            mFinancialAssets = null;
        }
        if(mFinancialAssetViewContentBinding != null){
            updateUI();
            mFinancialAssetViewContentBinding.setShowProgress(false);
        }
    }

    private void updateUI() {
        if(mFinancialAssets == null){
            mFinancialAssetViewContentBinding.setHasFinancialAsset(false);
        } else {
            mFinancialAssetViewContentBinding.rvFinancialAssets
                    .setAdapter(new FinancialAssetRecyclerView(mFinancialAssets));
            mFinancialAssetViewContentBinding.setHasFinancialAsset(true);
        }
    }

    //endregion
}
