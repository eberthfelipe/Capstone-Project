package com.udacity.capstoneinvest.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.udacity.capstoneinvest.object.FinancialAsset;
import com.udacity.capstoneinvest.presenter.FinancialAssetPresenter;

import java.util.ArrayList;

public class DatabaseFinancialAsset extends Database{

    private static final String TAG = DatabaseFinancialAsset.class.getName();
    private FinancialAssetPresenter mFinancialAssetPresenter;
    private ArrayList<FinancialAsset> mFinancialAssets;

    public DatabaseFinancialAsset(FinancialAssetPresenter financialAssetPresenter) {
        this.mFinancialAssetPresenter = financialAssetPresenter;
        setDatabaseReference(FirebaseDatabase.getInstance().getReference(FinancialAsset.class.getSimpleName())
                .orderByValue().getRef());
        getDatabaseReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mFinancialAssets = new ArrayList<>();
                if(dataSnapshot.exists()){
                    for (DataSnapshot child: dataSnapshot.getChildren()) {
                        Log.d(TAG, "child value: "+ String.valueOf(child.getValue()));
                        mFinancialAssets.add(new FinancialAsset(child));
                    }
                }
                mFinancialAssetPresenter.setFinancialAssetUI(mFinancialAssets);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "error from DB: " + databaseError.getMessage());
            }
        });
    }

    public void addFinancialAsset(FinancialAsset financialAsset){
        DatabaseReference child = getDatabaseReference().child(financialAsset.getName());
        Log.d(TAG, "addFinancialAsset: " + financialAsset.toString());
        child.setValue(financialAsset);
        mFinancialAssets.add(financialAsset);
    }
}
