package com.udacity.capstoneinvest.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.udacity.capstoneinvest.BR;
import com.udacity.capstoneinvest.R;
import com.udacity.capstoneinvest.object.FinancialAsset;

import java.util.ArrayList;

public class FinancialAssetRecyclerView extends RecyclerView.Adapter<FinancialAssetRecyclerView.FinancialAssetAdapter>{

    private ArrayList<FinancialAsset> mFinancialAssets;

    public FinancialAssetRecyclerView(ArrayList<FinancialAsset> mFinancialAssets) {
        this.mFinancialAssets = mFinancialAssets;
    }

    @NonNull
    @Override
    public FinancialAssetAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.financial_asset_view_holder, viewGroup, false);
        return new FinancialAssetAdapter(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FinancialAssetAdapter financialAssetAdapter, int i) {
        financialAssetAdapter.bind(mFinancialAssets.get(i));
        financialAssetAdapter.itemView.setTag(i);
    }

    @Override
    public int getItemCount() {
        return mFinancialAssets.size();
    }

    public static class FinancialAssetAdapter extends RecyclerView.ViewHolder {
        ViewDataBinding viewDataBinding;

        public FinancialAssetAdapter(@NonNull ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            this.viewDataBinding = viewDataBinding;
        }

        private void bind(FinancialAsset financialAsset){
            viewDataBinding.setVariable(BR.financialAsset, financialAsset);
            viewDataBinding.executePendingBindings();
        }
    }
}
