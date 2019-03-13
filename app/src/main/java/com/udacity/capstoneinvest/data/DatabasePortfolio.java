package com.udacity.capstoneinvest.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.udacity.capstoneinvest.object.InvestmentPortfolio;
import com.udacity.capstoneinvest.presenter.DatabasePortfolioPresenter;

public class DatabasePortfolio {

    public static String TAG = DatabasePortfolio.class.getName();
    public static String DATABASE_INVESTMENT_PORTFOLIO_VALUE_TOTAL_FIELD = "valueTotal";
//    private static DatabasePortfolio mInstance;
    private DatabaseReference mDatabaseReference;
    private InvestmentPortfolio mInvestmentPortfolio;
    private DatabasePortfolioPresenter mDatabasePortfolioPresenter;

    public DatabasePortfolio(DatabasePortfolioPresenter databasePortfolioPresenter) {
        mDatabasePortfolioPresenter = databasePortfolioPresenter;
        mInvestmentPortfolio = new InvestmentPortfolio();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference(InvestmentPortfolio.class.getSimpleName());
        mDatabaseReference.child(DATABASE_INVESTMENT_PORTFOLIO_VALUE_TOTAL_FIELD).addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            Long aux = (Long) dataSnapshot.getValue();
                            mInvestmentPortfolio.setValueTotal(aux.doubleValue());
                            Log.d(TAG, "getTotal - getValue from DB: " + mInvestmentPortfolio.getValueTotal());
                        } else {
                            String id = mDatabaseReference.push().getKey();
                            mInvestmentPortfolio.setValueTotal(0);
                            mDatabaseReference.setValue(mInvestmentPortfolio);
                            Log.d(TAG, "getTotal - onDataChange: CREATE DB OBJECT id=" + id);
                        }
                        mDatabasePortfolioPresenter.setTotalInvestedUI(mInvestmentPortfolio.getValueTotal());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }

//    public static DatabasePortfolio getInstance(){
//        if(mInstance == null)
//            mInstance = new DatabasePortfolio();
//        return mInstance;
//    }

}
