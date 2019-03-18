package com.udacity.capstoneinvest.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
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
import com.udacity.capstoneinvest.object.InvestCategory;
import com.udacity.capstoneinvest.presenter.DatabaseCategoryPresenterImpl;
import com.udacity.capstoneinvest.presenter.DatabasePortfolioPresenterImpl;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        InvestmentPortfolioUI, InvestCategoryUI {

    private ActivityMainBinding mActivityMainBinding;
    private DatabasePortfolioPresenterImpl mDatabasePortfolioPresenterImpl;
    private DatabaseCategoryPresenterImpl mDatabaseCategoryPresenterImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //enable local instance of firebase database
        //TODO save preference for persistence
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(mActivityMainBinding.appBarMain.toolbar);

        mActivityMainBinding.appBarMain.fab.
                setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CategoryDialogFragment categoryDialogFragment = new CategoryDialogFragment();
                categoryDialogFragment.show(getSupportFragmentManager(), categoryDialogFragment.getTag());
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mActivityMainBinding.drawerLayout, mActivityMainBinding.appBarMain.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mActivityMainBinding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //TODO Create user preference for visibility
        mActivityMainBinding.appBarMain.contentMain.setShowTotal(true);
        mActivityMainBinding.appBarMain.contentMain.ivViewValues.setOnClickListener(viewTotalInvestedClickListener());

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
        int id = item.getItemId();

        if (id == R.id.nav_account_balance) {
            // Handle the camera action
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        mActivityMainBinding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public View.OnClickListener viewTotalInvestedClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean show = mActivityMainBinding.appBarMain.contentMain.getShowTotal();
                mActivityMainBinding.appBarMain.contentMain.setShowTotal(!show);
                // TODO update preference value
            }
        };
    }

    private void init() {
        mActivityMainBinding.navView.setNavigationItemSelectedListener(this);
        mActivityMainBinding.navView.setCheckedItem(R.id.nav_account_balance);
        mDatabasePortfolioPresenterImpl = new DatabasePortfolioPresenterImpl(this);
        mDatabaseCategoryPresenterImpl = new DatabaseCategoryPresenterImpl(this);
    }

    public void callBackDialog(String categoryType){
        Log.d("TESTE", "callback: " + categoryType);
        mDatabaseCategoryPresenterImpl.addCategory(new InvestCategory(categoryType));
        // TODO save new category
    }


    //region DatabasePortfolioPresenterImpl to access InvestmentPortfolio object
    @Override
    public void setTotalInvestedUI(double value) {
        mActivityMainBinding.appBarMain.contentMain.setTotal(value);
    }
    //endregion

    //region DatabaseCategoryPresenterImpl to access InvestCategory object
    @Override
    public void setInvestCategoryUI(ArrayList<InvestCategory> databaseCategories) {
        if(databaseCategories == null){
            mActivityMainBinding.appBarMain.contentMain.categoryViewContent.setHasCategory(false);
        } else {
            mActivityMainBinding.appBarMain.contentMain.categoryViewContent.
                    rvCategories.setAdapter(new InvestCategoryRecyclerView(databaseCategories, this));
            mActivityMainBinding.appBarMain.contentMain.categoryViewContent.setHasCategory(true);
        }
    }

    @Override
    public void updateWeight(int position, int value) {
        if(!mDatabaseCategoryPresenterImpl.updateWeightValue(position, value)){
            mActivityMainBinding.appBarMain.contentMain.categoryViewContent.rvCategories.getAdapter().notifyItemChanged(position);
            showWarning(getString(R.string.update_weight), getString(R.string.warning_max_weight));
        }
    }
    //endregion

    public void showWarning(String action, String text){
        Snackbar.make(mActivityMainBinding.getRoot(),text, Snackbar.LENGTH_LONG)
                .setAction(action, null).show();
    }
}
