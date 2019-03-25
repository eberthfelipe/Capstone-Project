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

import com.google.android.gms.ads.AdRequest;
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
import com.udacity.capstoneinvest.view.dialog.FinancialSupportDialogFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ManagerUI {

    private static final String TAG = MainActivity.class.getName();
    public static final String PORTFOLIO_CURRENT_FRAGMENT = "portfolio_current_fragment";
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
        //TODO maintain instance onSaveInstanceState/onRestoreInstanceState for fragments

        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(mActivityMainBinding.appBarMain.toolbar);

        mActivityMainBinding.appBarMain.fab.
                setOnClickListener(handleFabClick());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mActivityMainBinding.drawerLayout, mActivityMainBinding.appBarMain.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mActivityMainBinding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        init();
        if(savedInstanceState == null || savedInstanceState.isEmpty()){
            setCurrentFragment(getInvestCategoryFragment());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if(mCurrentFragment != null){
            Log.d(TAG, "onSaveInstanceState: Fragment -> "+mCurrentFragment.getClass().getSimpleName());
            outState.putString(PORTFOLIO_CURRENT_FRAGMENT, mCurrentFragment.getClass().getSimpleName());
            super.onSaveInstanceState(outState);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(!savedInstanceState.isEmpty()){
            String fragment = savedInstanceState.getString(PORTFOLIO_CURRENT_FRAGMENT);
            if (fragment != null) {
                mCurrentFragment = getCurrentFragment(fragment);
            }
        }
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
        mActivityMainBinding.appBarMain.adView.loadAd(getAdRequest());
        mActivityMainBinding.navView.setCheckedItem(R.id.nav_account_balance);
        setupFragments();
        setupPresenter();
    }

    public View.OnClickListener handleFabClick(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = null;
                if(mCurrentFragment instanceof InvestCategoryFragment){
                    dialogFragment = new CategoryDialogFragment();
                } else if (mCurrentFragment instanceof FinancialAssetFragment){
                    dialogFragment = new FinancialAssetDialogFragment();
                } else if(mCurrentFragment instanceof FinancialSupportFragment){
                    dialogFragment = new FinancialSupportDialogFragment();
                }
                dialogFragment.show(getSupportFragmentManager(), dialogFragment.getTag());
            }
        };
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
                break;
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

    public void callBackFinancialSupportDialog(double value) {
        Log.d(TAG, "callBackFinancialSupportDialog: " + value);
        getFinancialSupportFragment().showProgress(true);
        mFinancialSupportPresenterImpl.createFinancialSupport(mCategoryPresenterImpl.getInvestCategories(),
                mFinancialAssetPresenterImpl.getFinancialAssets(),
                value);
    }

    private void setupPresenter(){
        //TODO: cleanup
//        if(mCurrentFragment instanceof InvestCategoryFragment){
//            mPortfolioPresenterImpl = new PortfolioPresenterImpl(this);
//            mCategoryPresenterImpl = new CategoryPresenterImpl(this);
//        } else if(mCurrentFragment instanceof FinancialAssetFragment){
//            mFinancialAssetPresenterImpl = new FinancialAssetPresenterImpl(this);
//        } else if(mCurrentFragment instanceof FinancialSupportFragment){
//            mFinancialSupportPresenterImpl = new FinancialSupportPresenterImpl(this);
//        }
        mPortfolioPresenterImpl = new PortfolioPresenterImpl(this, this);
        mCategoryPresenterImpl = new CategoryPresenterImpl(this);
        mFinancialAssetPresenterImpl = new FinancialAssetPresenterImpl(this);
        mFinancialSupportPresenterImpl = new FinancialSupportPresenterImpl(this);
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

    public FinancialSupportPresenterImpl getFinancialSupportPresenter(){
        return mFinancialSupportPresenterImpl;
    }

    public PortfolioPresenterImpl getPortfolioPresenter(){
        return mPortfolioPresenterImpl;
    }
    //endregion

    //region Fragments
    private void setCurrentFragment(@NonNull Fragment fragment){
        mCurrentFragment = fragment;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_main, mCurrentFragment, mCurrentFragment.getClass().getSimpleName())
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

    private void setupFragments(){
        getInvestCategoryFragment();
        getFinancialAssetFragment();
        getFinancialSupportFragment();
    }

    private Fragment getCurrentFragment(String fragmentTag) {
        if(fragmentTag.equals(FinancialAssetFragment.class.getSimpleName()))
            return getFinancialAssetFragment();
        else if(fragmentTag.equals(FinancialSupportFragment.class.getSimpleName()))
            return getFinancialSupportFragment();
        else
            return getInvestCategoryFragment();
    }
    //endregion

    private AdRequest getAdRequest(){
        return new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
    }
}
