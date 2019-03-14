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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.FirebaseDatabase;
import com.udacity.capstoneinvest.R;
import com.udacity.capstoneinvest.databinding.ActivityMainBinding;
import com.udacity.capstoneinvest.presenter.DatabasePortfolioPresenterImpl;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        InvestmentPortfolioUI {

    private ActivityMainBinding mActivityMainBinding;
    private DatabasePortfolioPresenterImpl mDatabasePortfolioPresenterImpl;

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                CategoryDialogFragment categoryDialogFragment = new CategoryDialogFragment();
                categoryDialogFragment.show(getSupportFragmentManager(), categoryDialogFragment.getTag());
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mActivityMainBinding.drawerLayout, mActivityMainBinding.appBarMain.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mActivityMainBinding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mActivityMainBinding.navView.setNavigationItemSelectedListener(this);

        //TODO Create user preference for visibility
        mActivityMainBinding.appBarMain.contentMain.setShowTotal(true);
        mActivityMainBinding.appBarMain.contentMain.ivViewValues.setOnClickListener(getTotalInvestClickListener());

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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        mActivityMainBinding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public View.OnClickListener getTotalInvestClickListener(){
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
        mDatabasePortfolioPresenterImpl = new DatabasePortfolioPresenterImpl(this);
    }

    @Override
    public void setTotalInvestedUI(double value) {
        mActivityMainBinding.appBarMain.contentMain.setTotal(value);
    }
}
