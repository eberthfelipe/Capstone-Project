package com.udacity.capstoneinvest.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.udacity.capstoneinvest.object.InvestCategory;
import com.udacity.capstoneinvest.presenter.DatabaseCategoryPresenter;

import java.util.ArrayList;

public class DatabaseCategory {

    private static final String TAG = DatabaseCategory.class.getName();
    private DatabaseReference mDatabaseReference;
    private DatabaseCategoryPresenter mDatabaseCategoryPresenter;
    private ArrayList<InvestCategory> mInvestCategory;

    public DatabaseCategory(DatabaseCategoryPresenter databaseCategoryPresenter) {
        mDatabaseCategoryPresenter = databaseCategoryPresenter;
        mDatabaseReference = FirebaseDatabase.getInstance().getReference(InvestCategory.class.getSimpleName());
        // InvestCategory DB object
        mDatabaseReference.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            dataSnapshot.getValue();
                            Log.d(TAG, dataSnapshot.toString());
                            Log.d(TAG, String.valueOf(dataSnapshot.getValue()));
                        } else {
                            mDatabaseCategoryPresenter.setInvestCategoryUI(null);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e(TAG, "error from DB: " + databaseError.getMessage());
                    }
                });
    }
}
