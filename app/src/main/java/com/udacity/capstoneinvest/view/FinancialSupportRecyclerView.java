package com.udacity.capstoneinvest.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.udacity.capstoneinvest.BR;
import com.udacity.capstoneinvest.R;
import com.udacity.capstoneinvest.object.AssetSupport;

import java.util.ArrayList;

class FinancialSupportRecyclerView extends RecyclerView.Adapter<FinancialSupportRecyclerView.FinancialSupportAdapter> {

    private ArrayList<AssetSupport> mAssetSupportArrayList;
    private FinancialSupportUI mFinancialSupportUI;

    public FinancialSupportRecyclerView(ArrayList<AssetSupport> mAssetSupportArrayList, FinancialSupportUI mFinancialSupportUI) {
        this.mAssetSupportArrayList = mAssetSupportArrayList;
        this.mFinancialSupportUI = mFinancialSupportUI;
    }

    @NonNull
    @Override
    public FinancialSupportAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.asset_support_view_holder, viewGroup, false);
        return new FinancialSupportAdapter(viewDataBinding, mFinancialSupportUI);
    }

    @Override
    public void onBindViewHolder(@NonNull FinancialSupportAdapter financialSupportAdapter, int i) {
        financialSupportAdapter.bind(mAssetSupportArrayList.get(i));
        financialSupportAdapter.itemView.setTag(i);
    }

    @Override
    public int getItemCount() {
        return mAssetSupportArrayList.size();
    }

    public static class FinancialSupportAdapter extends RecyclerView.ViewHolder {

        ViewDataBinding viewDataBinding;
        FinancialSupportUI financialSupportUI;
        TextView tvFinancialAssetValue;
        Button btCancel, btBid, btPurchase;

        public FinancialSupportAdapter(@NonNull ViewDataBinding viewDataBinding, @NonNull FinancialSupportUI financialSupportUI) {
            super(viewDataBinding.getRoot());
            this.viewDataBinding = viewDataBinding;
            this.financialSupportUI = financialSupportUI;
            tvFinancialAssetValue = itemView.findViewById(R.id.tv_asset_support_value);
            btCancel = itemView.findViewById(R.id.bt_cancel_asset_support);
            btBid = itemView.findViewById(R.id.bt_bid_asset_support);
            btPurchase = itemView.findViewById(R.id.bt_buy_asset_support);
        }

        private void bind(AssetSupport assetSupport){
            viewDataBinding.setVariable(BR.assetSupport, assetSupport);
            //TODO use variables before execute pending bindings
            viewDataBinding.executePendingBindings();
        }
    }
}
