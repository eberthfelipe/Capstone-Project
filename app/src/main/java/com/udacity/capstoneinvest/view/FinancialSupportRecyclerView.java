package com.udacity.capstoneinvest.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.design.card.MaterialCardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
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
    private Context mContext;

    public FinancialSupportRecyclerView(ArrayList<AssetSupport> mAssetSupportArrayList, FinancialSupportUI mFinancialSupportUI, Context context) {
        this.mAssetSupportArrayList = mAssetSupportArrayList;
        this.mFinancialSupportUI = mFinancialSupportUI;
        this.mContext = context;
    }

    @NonNull
    @Override
    public FinancialSupportAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.asset_support_view_holder, viewGroup, false);
        return new FinancialSupportAdapter(viewDataBinding, mFinancialSupportUI, mContext);
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
        MaterialCardView materialCardView;
        Context context;

        public FinancialSupportAdapter(@NonNull ViewDataBinding viewDataBinding, @NonNull FinancialSupportUI financialSupportUI, Context context) {
            super(viewDataBinding.getRoot());
            this.viewDataBinding = viewDataBinding;
            this.financialSupportUI = financialSupportUI;
            this.context = context;
            tvFinancialAssetValue = itemView.findViewById(R.id.tv_asset_support_value);
            btCancel = itemView.findViewById(R.id.bt_cancel_asset_support);
            btBid = itemView.findViewById(R.id.bt_bid_asset_support);
            btPurchase = itemView.findViewById(R.id.bt_buy_asset_support);
            materialCardView = itemView.findViewById(R.id.cv_asset_support_view_holder);
        }

        private void bind(AssetSupport assetSupport){
            viewDataBinding.setVariable(BR.assetSupport, assetSupport);
            /*
              action : Look at AssetSupport.java
             */
            btPurchase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    financialSupportUI.updateTotalValue((Integer) itemView.getTag(),1);
                }
            });
            btBid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    financialSupportUI.updateTotalValue((Integer) itemView.getTag(),2);
                }
            });
            btCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    financialSupportUI.updateTotalValue((Integer) itemView.getTag(),3);
                }
            });
            setBackgroundColor(assetSupport.getState());
            viewDataBinding.executePendingBindings();
        }

        private void setBackgroundColor(int state){
            switch (state){
                case 1:
                    materialCardView.setBackgroundColor(context.getColor(R.color.colorPurchase));
                    break;
                case 2:
                    materialCardView.setBackgroundColor(context.getColor(R.color.colorBid));
                    break;
                case 3:
                    materialCardView.setBackgroundColor(context.getColor(R.color.colorCancel));
                    break;
            }
        }
    }
}
