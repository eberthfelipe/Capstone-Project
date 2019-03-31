package com.udacity.capstoneinvest.view.dialog;

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
import com.udacity.capstoneinvest.view.MainActivity;

public class CategoryDialogFragment extends DialogFragment
        implements com.udacity.capstoneinvest.view.dialog.DialogFragment {

    private DialogInvestmentCategoryBinding mDialogInvestmentCategoryBinding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDialogInvestmentCategoryBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),R.layout.dialog_investment_category, container, false);
        mDialogInvestmentCategoryBinding.viewButtonsCreateCancel.btDialogCreateCategory.setOnClickListener(getPositiveDialogClick());
        mDialogInvestmentCategoryBinding.viewButtonsCreateCancel.btDialogCancelCategory.setOnClickListener(getNegativeDialogClick());
        return mDialogInvestmentCategoryBinding.getRoot();
    }

    @Override
    public View.OnClickListener getPositiveDialogClick(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoryType = mDialogInvestmentCategoryBinding.etDialogCategory.getText().toString();
                if (TextUtils.isEmpty(categoryType)) {
                    showToast(getString(R.string.empty_category));
                } else {
                    if(getActivity() != null)
                        ((MainActivity)getActivity()).callBackInvestCategoryDialog(categoryType);
                    dismiss();
                }
            }
        };
    }

    @Override
    public View.OnClickListener getNegativeDialogClick(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        };
    }

    @Override
    public void showToast(String text){
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }
}
