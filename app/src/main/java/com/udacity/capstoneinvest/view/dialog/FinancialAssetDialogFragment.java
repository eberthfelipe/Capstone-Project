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
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.udacity.capstoneinvest.R;
import com.udacity.capstoneinvest.databinding.DialogFinancialAssetBinding;
import com.udacity.capstoneinvest.object.InvestCategory;
import com.udacity.capstoneinvest.view.MainActivity;

import java.util.ArrayList;

import static android.R.layout.simple_spinner_dropdown_item;

public class FinancialAssetDialogFragment extends DialogFragment
        implements com.udacity.capstoneinvest.view.dialog.DialogFragment {

    private DialogFinancialAssetBinding mDialogFinancialAssetBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDialogFinancialAssetBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_financial_asset, container, false);
        mDialogFinancialAssetBinding.spFinancialAsset.setAdapter(new ArrayAdapter<>(getActivity(), simple_spinner_dropdown_item, getInvestCategoryItems()));
        mDialogFinancialAssetBinding.viewButtonsCreateCancel.btDialogCreateCategory.setOnClickListener(getPositiveDialogClick());
        mDialogFinancialAssetBinding.viewButtonsCreateCancel.btDialogCancelCategory.setOnClickListener(getNegativeDialogClick());
        return mDialogFinancialAssetBinding.getRoot();
    }

    @Override
    public View.OnClickListener getPositiveDialogClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = mDialogFinancialAssetBinding.etDialogFinancialAssetName.getText().toString();
                final String valueTxt = mDialogFinancialAssetBinding.etDialogFinancialAssetValue.getText().toString();
                final String investCategory = mDialogFinancialAssetBinding.spFinancialAsset.getSelectedItem().toString();
                if (validFields(name, valueTxt, investCategory)) {
                    double value = Double.parseDouble(valueTxt);
                    if(value <= 0) {
                        showToast(getString(R.string.warning_financial_asset_value));
                    } else {
                        showToast(String.format("FUNFOU: \n %s \n %s \n %s",name, valueTxt, investCategory));
                        dismiss();
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

    public boolean validFields(String name, String value, String investCategory){
        if (TextUtils.isEmpty(name)
                || TextUtils.isEmpty(value)
                || TextUtils.isEmpty(investCategory)
                ) {
            showToast(getString(R.string.fill_all_fields));
            return false;
        }
        return true;
    }

    public String[] getInvestCategoryItems(){
        ArrayList<InvestCategory> investCategories = ((MainActivity)getActivity()).getDatabaseCategoryPresenter().getInvestCategories();
        String[] items = new String[investCategories.size()];
        for (int i=0; i < items.length; i++) {
            items[i] = investCategories.get(i).getType();
        }
        return items;
    }
}
