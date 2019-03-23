package com.udacity.capstoneinvest.view.dialog;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.udacity.capstoneinvest.R;
import com.udacity.capstoneinvest.databinding.DialogFinancialSupportCycleBinding;
import com.udacity.capstoneinvest.view.MainActivity;

public class FinancialSupportDialogFragment extends android.support.v4.app.DialogFragment implements DialogFragment {

    private DialogFinancialSupportCycleBinding mDialogFinancialSupportCycleBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDialogFinancialSupportCycleBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_financial_support_cycle, container, false);
        mDialogFinancialSupportCycleBinding.viewButtonsCreateCancelFinancialSupport.btDialogCreateCategory.setOnClickListener(getPositiveDialogClick());
        mDialogFinancialSupportCycleBinding.viewButtonsCreateCancelFinancialSupport.btDialogCancelCategory.setOnClickListener(getNegativeDialogClick());
        return mDialogFinancialSupportCycleBinding.getRoot();
    }

    @Override
    public View.OnClickListener getPositiveDialogClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String valueTxt = mDialogFinancialSupportCycleBinding.etDialogFinancialSupportCycleValue.getText().toString();
                if(TextUtils.isEmpty(valueTxt)){
                    showToast(getString(R.string.fill_all_fields));
                } else {
                    final double value = Double.valueOf(valueTxt);
                    if(value > 0){
                        ((MainActivity)getActivity()).callBackFinancialSupportDialog(value);
                        dismiss();
                    } else {
                        showToast(getString(R.string.warning_financial_support_value));
                    }
                }
            }
        };
    }

    @Override
    public View.OnClickListener getNegativeDialogClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        };
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }
}
