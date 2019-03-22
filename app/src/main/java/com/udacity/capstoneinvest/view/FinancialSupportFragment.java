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

    public FinancialSupportFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mAssetSupportViewContentBinding = DataBindingUtil.inflate(inflater, R.layout.asset_support_view_content, container, false);

        mAssetSupportViewContentBinding.setHasFinancialSupport(false);

        return mAssetSupportViewContentBinding.getRoot();
    }

    //region FinancialAssetUI
    @Override
    public void setFinancialSupportUI(FinancialSupport financialSupport) {
        if(financialSupport == null){
            mAssetSupportViewContentBinding.setHasFinancialSupport(false);
        } else {
            mAssetSupportViewContentBinding.rvFinancialSupport
                    .setAdapter(new FinancialSupportRecyclerView(financialSupport.getAssetSupports(), this));
            mAssetSupportViewContentBinding.setHasFinancialSupport(true);
        }
    }

    //endregion
}
