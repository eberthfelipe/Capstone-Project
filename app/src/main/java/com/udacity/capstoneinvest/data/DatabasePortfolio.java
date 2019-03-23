package com.udacity.capstoneinvest.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.udacity.capstoneinvest.object.InvestmentPortfolio;
import com.udacity.capstoneinvest.presenter.PortfolioPresenter;

import java.text.DecimalFormat;


public class DatabasePortfolio extends Database{

    private static final String TAG = DatabasePortfolio.class.getName();
    private InvestmentPortfolio mInvestmentPortfolio;
    private PortfolioPresenter mPortfolioPresenter;

    public DatabasePortfolio(PortfolioPresenter portfolioPresenter) {
        mPortfolioPresenter = portfolioPresenter;
        mInvestmentPortfolio = new InvestmentPortfolio();
        setDatabaseReference(FirebaseDatabase.getInstance().getReference(InvestmentPortfolio.class.getSimpleName()));

        // InvestmentPortfolio DB object
        getDatabaseReference().child(InvestmentPortfolio.DATABASE_VALUE_TOTAL_FIELD).addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            Double aux = dataSnapshot.getValue(Double.class);
                            mInvestmentPortfolio.setValueTotal(aux != null ? aux : 0);
                            Log.d(TAG, "getTotal - getValue from DB: " + mInvestmentPortfolio.getValueTotal());
                        } else {
                            String id = getDatabaseReference().push().getKey();
                            mInvestmentPortfolio.setValueTotal(0);
                            getDatabaseReference().setValue(mInvestmentPortfolio);
                            Log.d(TAG, "getTotal - onDataChange: CREATE DB OBJECT id=" + id);
                        }
                        mPortfolioPresenter.setTotalInvestedUI(mInvestmentPortfolio.getValueTotal());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e(TAG, "getTotal - error from DB: " + databaseError.getMessage());
                    }
                });



    }

    public void updateTotalPortfolio(double value){
        DecimalFormat decimal = new DecimalFormat("0.00");
        value = Double.parseDouble(decimal.format(value));
        double aux = mInvestmentPortfolio.getValueTotal() + value;
        mInvestmentPortfolio.setValueTotal(aux);
        DatabaseReference child = getDatabaseReference();
        Log.d(TAG, "updateTotalPortfolio: " + mInvestmentPortfolio.toString());
        child.setValue(mInvestmentPortfolio);
    }

}
