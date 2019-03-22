package com.udacity.capstoneinvest.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.udacity.capstoneinvest.R;
import com.udacity.capstoneinvest.databinding.ActivityMainBinding;
import com.udacity.capstoneinvest.object.FinancialAsset;
import com.udacity.capstoneinvest.object.InvestCategory;
import com.udacity.capstoneinvest.presenter.CategoryPresenterImpl;
import com.udacity.capstoneinvest.presenter.FinancialAssetPresenterImpl;
import com.udacity.capstoneinvest.presenter.FinancialSupportPresenterImpl;
import com.udacity.capstoneinvest.presenter.PortfolioPresenterImpl;
import com.udacity.capstoneinvest.view.dialog.CategoryDialogFragment;
import com.udacity.capstoneinvest.view.dialog.FinancialAssetDialogFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ManagerUI {

    private static final String TAG = MainActivity.class.getName();
    private ActivityMainBinding mActivityMainBinding;
    private PortfolioPresenterImpl mPortfolioPresenterImpl;
    private CategoryPresenterImpl mCategoryPresenterImpl;
    private FinancialAssetPresenterImpl mFinancialAssetPresenterImpl;
    private FinancialSupportPresenterImpl mFinancialSupportPresenterImpl;
    private Fragment mCurrentFragment;
    private InvestCategoryFragment mInvestCategoryFragment;
    private FinancialAssetFragment mFinancialAssetFragment;
    private FinancialSupportFragment mFinancialSupportFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //enable local instance of firebase database
        //TODO save preference for persistence
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        //TODO maintain instance onSaveInstanceState/onRestoreInstanceState

        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(mActivityMainBinding.appBarMain.toolbar);

        mActivityMainBinding.appBarMain.fab.
                setOnClickListener(handleFabClick());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mActivityMainBinding.drawerLayout, mActivityMainBinding.appBarMain.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mActivityMainBinding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        init();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = mActivityMainBinding.drawerLayout;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        handleNavigationClickItem(item.getItemId());

        mActivityMainBinding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void init() {
        mActivityMainBinding.navView.setNavigationItemSelectedListener(this);
        //TODO save current fragment
        mActivityMainBinding.navView.setCheckedItem(R.id.nav_account_balance);
        setCurrentFragment(getInvestCategoryFragment());
        setupPresenter();
    }

    public View.OnClickListener handleFabClick(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment;
                if(mCurrentFragment instanceof InvestCategoryFragment){
                    dialogFragment = new CategoryDialogFragment();
                    dialogFragment.show(getSupportFragmentManager(), dialogFragment.getTag());
                } else if (mCurrentFragment instanceof FinancialAssetFragment){
                    dialogFragment = new FinancialAssetDialogFragment();
                    dialogFragment.show(getSupportFragmentManager(), dialogFragment.getTag());
                } else if(mCurrentFragment instanceof FinancialSupportFragment){
                    //TODO validate if there is no active cycle
                    createInvestmentCycle(mCategoryPresenterImpl.getInvestCategories(), mFinancialAssetPresenterImpl.getFinancialAssets());
                }
            }
        };
    }

    private void createInvestmentCycle(ArrayList<InvestCategory> investCategories, ArrayList<FinancialAsset> financialAssets) {
        // TODO get value of support
        mFinancialSupportPresenterImpl.createFinancialSupport(investCategories, financialAssets,1000);
    }

    public void handleNavigationClickItem(int id){
        Log.d(TAG, "handleNavigationClickItem: " + mCurrentFragment.toString());
        switch (id){
            case R.id.nav_account_balance:
                if(!(mCurrentFragment instanceof InvestCategoryFragment)){
                    setCurrentFragment(getInvestCategoryFragment());
                }
                break;
            case R.id.nav_financial_assets:
                if(!(mCurrentFragment instanceof FinancialAssetFragment)){
                    setCurrentFragment(getFinancialAssetFragment());
                }
            case R.id.nav_financial_support:
                if(!(mCurrentFragment instanceof FinancialSupportFragment)){
                    setCurrentFragment(getFinancialSupportFragment());
                }
                break;
        }
    }

    //region ManagerUI

    public void callBackInvestCategoryDialog(String categoryType){
        Log.d(TAG, "callBackInvestCategoryDialog: " + categoryType);
        mCategoryPresenterImpl.addCategory(new InvestCategory(categoryType));
    }

    public void callBackFinancialAssetDialog(String name, double value, String investCategory){
        FinancialAsset financialAsset = new FinancialAsset(name, value, investCategory);
        Log.d(TAG, "callBackFinancialAssetDialog: " + financialAsset.toString());
        mFinancialAssetPresenterImpl.addFinancialAsset(financialAsset);
    }

    private void setupPresenter(){
        if(mCurrentFragment instanceof InvestCategoryFragment){
            mPortfolioPresenterImpl = new PortfolioPresenterImpl(this);
            mCategoryPresenterImpl = new CategoryPresenterImpl(this);
        } else if(mCurrentFragment instanceof FinancialAssetFragment){
            mFinancialAssetPresenterImpl = new FinancialAssetPresenterImpl(this);
        } else if(mCurrentFragment instanceof FinancialSupportFragment){
            mFinancialSupportPresenterImpl = new FinancialSupportPresenterImpl(this);
        }
    }

    @Override
    public FinancialAssetUI getFinancialAssetUi() {
        return getFinancialAssetFragment();
    }

    @Override
    public FinancialSupportUI getFinancialSupportUi() {
        return getFinancialSupportFragment();
    }

    @Override
    public InvestCategoryUI getInvestCategoryUi() {
        return getInvestCategoryFragment();
    }

    @Override
    public InvestmentPortfolioUI getInvestmentPortfolioUi() {
        return getInvestCategoryFragment();
    }

    public CategoryPresenterImpl getDatabaseCategoryPresenter(){
        return mCategoryPresenterImpl;
    }
    //endregion

    //region Fragments
    private void setCurrentFragment(@NonNull Fragment fragment){
        mCurrentFragment = fragment;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_main, mCurrentFragment)
                .commit();
        setupPresenter();
    }

    private InvestCategoryFragment getInvestCategoryFragment(){
        if(mInvestCategoryFragment == null)
            mInvestCategoryFragment = new InvestCategoryFragment();
        return mInvestCategoryFragment;
    }

    private FinancialAssetFragment getFinancialAssetFragment(){
        if(mFinancialAssetFragment == null)
            mFinancialAssetFragment = new FinancialAssetFragment();
        return mFinancialAssetFragment;
    }

    private FinancialSupportFragment getFinancialSupportFragment(){
        if(mFinancialSupportFragment == null)
            mFinancialSupportFragment = new FinancialSupportFragment();
        return mFinancialSupportFragment;
    }

    //endregion

}
