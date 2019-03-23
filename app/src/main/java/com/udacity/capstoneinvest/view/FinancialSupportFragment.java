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
import com.udacity.capstoneinvest.databinding.AssetSupportViewContentBinding;
import com.udacity.capstoneinvest.object.FinancialSupport;

public class FinancialSupportFragment extends Fragment
        implements FinancialSupportUI {

    private static final String TAG = FinancialSupportFragment.class.getName();
    private AssetSupportViewContentBinding mAssetSupportViewContentBinding;
    private FinancialSupport mFinancialSupport;

    public FinancialSupportFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mAssetSupportViewContentBinding = DataBindingUtil.inflate(inflater, R.layout.asset_support_view_content, container, false);
        mAssetSupportViewContentBinding.setShowProgress(true);
        updateUI();

        return mAssetSupportViewContentBinding.getRoot();
    }

    //region FinancialAssetUI
    @Override
    public void setFinancialSupportUI(FinancialSupport financialSupport) {
        if(financialSupport != null){
            mFinancialSupport = new FinancialSupport(financialSupport);
            if(mAssetSupportViewContentBinding != null){
                updateUI();
            }
        }
    }

    @Override
    public void showProgress(boolean show) {
        if(mAssetSupportViewContentBinding != null){
            mAssetSupportViewContentBinding.setShowProgress(show);
        }
    }

    private void updateUI(){
        if(mFinancialSupport == null){
            mAssetSupportViewContentBinding.setHasFinancialSupport(false);
        } else {
            mAssetSupportViewContentBinding.rvFinancialSupport
                    .setAdapter(new FinancialSupportRecyclerView(mFinancialSupport.getAssetSupports(), this));
            mAssetSupportViewContentBinding.tvFinancialSupportValue.setText(String.valueOf(mFinancialSupport.getValueSupport()));
            mAssetSupportViewContentBinding.setHasFinancialSupport(true);
        }
        mAssetSupportViewContentBinding.setShowProgress(false);
    }
    //endregion
}
