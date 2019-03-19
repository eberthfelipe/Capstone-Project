package com.udacity.capstoneinvest.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
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
import com.udacity.capstoneinvest.presenter.DatabaseCategoryPresenterImpl;
import com.udacity.capstoneinvest.presenter.DatabasePortfolioPresenterImpl;

import java.util.ArrayList;

public class InvestCategoryFragment extends Fragment 
        implements InvestmentPortfolioUI, InvestCategoryUI {

    private static final String TAG = InvestCategoryFragment.class.getName();
    private ContentMainBinding mContentMainBinding;
    private DatabasePortfolioPresenterImpl mDatabasePortfolioPresenterImpl;
    private DatabaseCategoryPresenterImpl mDatabaseCategoryPresenterImpl;

    public InvestCategoryFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mDatabasePortfolioPresenterImpl = new DatabasePortfolioPresenterImpl(this);
        mDatabaseCategoryPresenterImpl = new DatabaseCategoryPresenterImpl(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentMainBinding = DataBindingUtil.inflate(inflater, R.layout.content_main, container, false);

        //TODO Create user preference for visibility
        mContentMainBinding.setShowTotal(true);
        mContentMainBinding.ivViewValues.setOnClickListener(viewTotalInvestedClickListener());

        return mContentMainBinding.getRoot();
    }

    public View.OnClickListener viewTotalInvestedClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean show = mContentMainBinding.getShowTotal();
                mContentMainBinding.setShowTotal(!show);
                // TODO update preference value
            }
        };
    }

    public void showWarning(String action, String text){
        Snackbar.make(mContentMainBinding.getRoot(),text, Snackbar.LENGTH_LONG)
                .setAction(action, null).show();
    }

    public void callBackDialog(String categoryType){
        Log.d(TAG, "callback: " + categoryType);
        mDatabaseCategoryPresenterImpl.addCategory(new InvestCategory(categoryType));
    }

    //region DatabasePortfolioPresenterImpl to access InvestmentPortfolio object
    @Override
    public void setTotalInvestedUI(double value) {
        mContentMainBinding.setTotal(value);
    }
    //endregion

    //region DatabaseCategoryPresenterImpl to access InvestCategory object
    @Override
    public void setInvestCategoryUI(ArrayList<InvestCategory> databaseCategories) {
        if(databaseCategories == null){
            mContentMainBinding.categoryViewContent.setHasCategory(false);
        } else {
            mContentMainBinding.categoryViewContent.
                    rvCategories.setAdapter(new InvestCategoryRecyclerView(databaseCategories, this));
            mContentMainBinding.categoryViewContent.setHasCategory(true);
        }
    }

    @Override
    public void updateWeight(int position, int value) {
        if(!mDatabaseCategoryPresenterImpl.updateWeightValue(position, value)){
            mContentMainBinding.categoryViewContent.rvCategories.getAdapter().notifyItemChanged(position);
            showWarning(getString(R.string.update_weight), getString(R.string.warning_max_weight));
        }
    }
    //endregion
}
