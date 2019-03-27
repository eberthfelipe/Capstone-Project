package com.udacity.capstoneinvest.view;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.capstoneinvest.R;
import com.udacity.capstoneinvest.databinding.ContentMainBinding;
import com.udacity.capstoneinvest.object.InvestCategory;
import com.udacity.capstoneinvest.presenter.CategoryPresenterImpl;

import java.util.ArrayList;

public class InvestCategoryFragment extends Fragment 
        implements InvestmentPortfolioUI, InvestCategoryUI {

    private static final String TAG = InvestCategoryFragment.class.getName();
    private static final String ARG_INVEST_CATEGORIES = "invest_categories";
    private static final String ARG_PORTFOLIO_VALUE = "portfolio_value";
    private static final String ARG_SHOW_PORTFOLIO_VALUE = "show_portfolio_value";
    private ContentMainBinding mContentMainBinding;
    private ArrayList<InvestCategory> mInvestCategories;
    private double mTotal = 0;
    private boolean mShowPortfolio;


    public InvestCategoryFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if(mInvestCategories != null){
            outState.putParcelableArrayList(ARG_INVEST_CATEGORIES, mInvestCategories);
            outState.putDouble(ARG_PORTFOLIO_VALUE, mTotal);
            outState.putBoolean(ARG_SHOW_PORTFOLIO_VALUE, mShowPortfolio);
            super.onSaveInstanceState(outState);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentMainBinding = DataBindingUtil.inflate(inflater, R.layout.content_main, container, false);

        mContentMainBinding.ivViewValues.setOnClickListener(viewTotalInvestedClickListener());

        if(savedInstanceState != null && !savedInstanceState.isEmpty()){
            mInvestCategories = savedInstanceState.getParcelableArrayList(ARG_INVEST_CATEGORIES);
            mTotal = savedInstanceState.getDouble(ARG_PORTFOLIO_VALUE);
            mShowPortfolio = savedInstanceState.getBoolean(ARG_SHOW_PORTFOLIO_VALUE);
        } else {
            mShowPortfolio = getPreferenceShowPortfolio();
            mContentMainBinding.categoryViewContent.setShowProgress(true);
            mContentMainBinding.setShowProgress(true);
        }
        mContentMainBinding.setShowTotal(mShowPortfolio);
        updateUI();
        mContentMainBinding.setTotal(mTotal);

        return mContentMainBinding.getRoot();
    }

    public View.OnClickListener viewTotalInvestedClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean show = mContentMainBinding.getShowTotal();
                mContentMainBinding.setShowTotal(!show);
                mShowPortfolio = !show;
                savePreferenceShowPortfolio();
            }
        };
    }

    public void showWarning(String action, String text){
        Snackbar.make(mContentMainBinding.getRoot(),text, Snackbar.LENGTH_LONG)
                .setAction(action, null).show();
    }

    //region PortfolioPresenterImpl to access InvestmentPortfolio object
    @Override
    public void setTotalInvestedUI(double value) {
        mTotal = value;
        if(mContentMainBinding != null)
            mContentMainBinding.setTotal(mTotal);
    }
    //endregion

    //region CategoryPresenterImpl to access InvestCategory object
    @Override
    public void setInvestCategoryUI(ArrayList<InvestCategory> databaseCategories) {
        if(databaseCategories != null){
            mInvestCategories = new ArrayList<>(databaseCategories);
            if(mContentMainBinding != null){
                updateUI();
                mContentMainBinding.categoryViewContent.setShowProgress(false);
                mContentMainBinding.setShowProgress(false);
            }
        }
    }

    private void updateUI(){
        if(mInvestCategories == null){
            mContentMainBinding.categoryViewContent.setHasCategory(false);
        } else {
            mContentMainBinding.categoryViewContent.
                    rvCategories.setAdapter(new InvestCategoryRecyclerView(mInvestCategories, this));
            mContentMainBinding.categoryViewContent.setHasCategory(true);
        }

    }

    @Override
    public void updateWeight(int position, int value) {
        CategoryPresenterImpl databaseCategoryPresenter = getMainActivity().getDatabaseCategoryPresenter();
        if(!databaseCategoryPresenter.updateWeightValue(position, value)){
            mContentMainBinding.categoryViewContent.rvCategories.getAdapter().notifyItemChanged(position);
            showWarning(getString(R.string.update_weight), getString(R.string.warning_max_weight));
        }
    }
    //endregion

    //region Preference
    public void savePreferenceShowPortfolio(){
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();

        prefsEditor.putBoolean(ARG_SHOW_PORTFOLIO_VALUE, mShowPortfolio);
        prefsEditor.apply();
    }

    public boolean getPreferenceShowPortfolio(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        boolean aux = sharedPreferences.getBoolean(ARG_SHOW_PORTFOLIO_VALUE, true);
        Log.d(TAG, "getPreferenceShowPortfolio: " + aux);
        return aux;
    }
    //endregion

    public MainActivity getMainActivity(){
        return (MainActivity) getActivity();
    }
}
