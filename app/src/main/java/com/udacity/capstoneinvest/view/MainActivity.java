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
import com.udacity.capstoneinvest.view.dialog.CategoryDialogFragment;
import com.udacity.capstoneinvest.view.dialog.FinancialAssetDialogFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getName();
    private ActivityMainBinding mActivityMainBinding;
    private Fragment mCurrentFragment;

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
        mActivityMainBinding.navView.setCheckedItem(R.id.nav_account_balance);

        //TODO save current fragment
        mCurrentFragment = new InvestCategoryFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_main, mCurrentFragment)
                .commit();
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
                    mCurrentFragment = new InvestCategoryFragment();
                }
                break;
            case R.id.nav_financial_assets:
                if(!(mCurrentFragment instanceof FinancialAssetFragment)){
                    mCurrentFragment = new FinancialAssetFragment();
                }
                break;
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_main, mCurrentFragment)
                .commit();
    }

}
