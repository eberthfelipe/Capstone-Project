package com.udacity.capstoneinvest.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.udacity.capstoneinvest.BR;
import com.udacity.capstoneinvest.R;
import com.udacity.capstoneinvest.object.InvestCategory;

import java.util.ArrayList;

public class InvestCategoryRecyclerView extends RecyclerView.Adapter<InvestCategoryRecyclerView.InvestCategoryAdapter> {

    private ArrayList<InvestCategory> mInvestCategories;
    private InvestCategoryUI mInvestCategoryUI;

    public InvestCategoryRecyclerView(ArrayList<InvestCategory> mInvestCategories, InvestCategoryUI investCategoryUI) {
        this.mInvestCategories = new ArrayList<>(mInvestCategories);
        this.mInvestCategoryUI = investCategoryUI;
    }

    @NonNull
    @Override
    public InvestCategoryAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.category_view_holder, viewGroup, false);
        return new InvestCategoryAdapter(viewDataBinding, mInvestCategoryUI);
    }

    @Override
    public void onBindViewHolder(@NonNull InvestCategoryAdapter investCategoryAdapter, int i) {
        investCategoryAdapter.bind(mInvestCategories.get(i));
        investCategoryAdapter.itemView.setTag(i);
    }

    @Override
    public int getItemCount() {
        return mInvestCategories.size();
    }

    public static class InvestCategoryAdapter extends RecyclerView.ViewHolder {
        ViewDataBinding viewDataBinding;
        InvestCategoryUI investCategoryUI;
        SeekBar mSeekBar;
        TextView mTextProgress;

        public InvestCategoryAdapter(@NonNull ViewDataBinding viewDataBinding, InvestCategoryUI investCategoryUI) {
            super(viewDataBinding.getRoot());
            this.viewDataBinding = viewDataBinding;
            this.investCategoryUI = investCategoryUI;
            mSeekBar = itemView.findViewById(R.id.sb_invest_category_weight);
            mTextProgress = itemView.findViewById(R.id.tv_invest_category_weight_value);
        }

        private void bind(InvestCategory investCategory){
            viewDataBinding.setVariable(BR.investCategory, investCategory);
            mSeekBar.setOnSeekBarChangeListener(setSeekBarChangeListener());
            viewDataBinding.executePendingBindings();
        }

        private SeekBar.OnSeekBarChangeListener setSeekBarChangeListener(){
            return new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    mTextProgress.setText(String.valueOf(progress));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    investCategoryUI.updateWeight((Integer) itemView.getTag(), mSeekBar.getProgress());
                }
            };
        }

    }
}
