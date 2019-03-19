package com.udacity.capstoneinvest.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.udacity.capstoneinvest.R;
import com.udacity.capstoneinvest.databinding.DialogInvestmentCategoryBinding;

public class CategoryDialogFragment extends DialogFragment {

    private DialogInvestmentCategoryBinding mDialogInvestmentCategoryBinding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDialogInvestmentCategoryBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),R.layout.dialog_investment_category, container, false);
        mDialogInvestmentCategoryBinding.btDialogCreateCategory.setOnClickListener(getPositiveDialogClick());
        mDialogInvestmentCategoryBinding.btDialogCancelCategory.setOnClickListener(getNegativeDialogClick());
        return mDialogInvestmentCategoryBinding.getRoot();
    }

    public View.OnClickListener getPositiveDialogClick(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoryType = mDialogInvestmentCategoryBinding.etDialogCategory.getText().toString();
                if (TextUtils.isEmpty(categoryType)) {
                    showToast();
                } else {
                    InvestCategoryFragment investCategoryFragment = (InvestCategoryFragment)getActivity().getSupportFragmentManager().getFragments().get(0);
                    if(investCategoryFragment != null)
                        investCategoryFragment.callBackDialog(categoryType);
                    dismiss();
                }
            }
        };
    }

    public View.OnClickListener getNegativeDialogClick(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        };
    }

    public void showToast(){
        Toast.makeText(getContext(), "Empty category!", Toast.LENGTH_SHORT).show();
    }
}
