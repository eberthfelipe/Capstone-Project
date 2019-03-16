package com.udacity.capstoneinvest.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.udacity.capstoneinvest.BR;
import com.udacity.capstoneinvest.R;
import com.udacity.capstoneinvest.object.InvestCategory;

import java.util.ArrayList;

public class InvestCategoryRecyclerView extends RecyclerView.Adapter<InvestCategoryRecyclerView.InvestCategoryAdapter> {

    private ArrayList<InvestCategory> mInvestCategories;

    public InvestCategoryRecyclerView(ArrayList<InvestCategory> mInvestCategories) {
        this.mInvestCategories = new ArrayList<>(mInvestCategories);
    }

    @NonNull
    @Override
    public InvestCategoryAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.category_view_holder, viewGroup, false);
        return new InvestCategoryAdapter(viewDataBinding);
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

        public InvestCategoryAdapter(@NonNull ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            this.viewDataBinding = viewDataBinding;
        }

        private void bind(InvestCategory investCategory){
            viewDataBinding.setVariable(BR.investCategory, investCategory);
            viewDataBinding.executePendingBindings();
        }
    }
}
