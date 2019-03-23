package com.udacity.capstoneinvest.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.udacity.capstoneinvest.object.FinancialSupport;
import com.udacity.capstoneinvest.object.InvestmentPortfolio;
import com.udacity.capstoneinvest.presenter.FinancialSupportPresenter;

public class DatabaseFinancialSupport extends Database {

    private static final String TAG = DatabaseFinancialSupport.class.getName();
    private FinancialSupportPresenter mFinancialSupportPresenter;
    private FinancialSupport mFinancialSupport;

    public DatabaseFinancialSupport(FinancialSupportPresenter financialSupportPresenter) {
        this.mFinancialSupportPresenter = financialSupportPresenter;
        setDatabaseReference(FirebaseDatabase.getInstance().getReference(InvestmentPortfolio.class.getSimpleName())
                .child(FinancialSupport.class.getSimpleName())
                .orderByValue()
                .getRef());
        getDatabaseReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Log.d(TAG, "child value: "+ String.valueOf(dataSnapshot.getValue()));
                    mFinancialSupport = new FinancialSupport(dataSnapshot);
                }
                if(mFinancialSupport.isState()){
                    mFinancialSupportPresenter.setFinancialSupportUI(mFinancialSupport);
                } else {
                    mFinancialSupportPresenter.setFinancialSupportUI(null);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "error from DB: " + databaseError.getMessage());
            }
        });
    }

    public void saveFinancialSupport(FinancialSupport financialSupport){
        DatabaseReference child = getDatabaseReference();
        Log.d(TAG, "saveFinancialSupport: " + financialSupport.toString());
        child.setValue(financialSupport);
        mFinancialSupport = financialSupport;
    }
}
