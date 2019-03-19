package com.udacity.capstoneinvest.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.udacity.capstoneinvest.object.InvestCategory;
import com.udacity.capstoneinvest.presenter.DatabaseCategoryPresenter;

import java.util.ArrayList;

public class DatabaseCategory {

    private static final String TAG = DatabaseCategory.class.getName();
    private DatabaseReference mDatabaseReference;
    private DatabaseCategoryPresenter mDatabaseCategoryPresenter;
    private ArrayList<InvestCategory> mInvestCategories;

    public DatabaseCategory(DatabaseCategoryPresenter databaseCategoryPresenter) {
        mInvestCategories = new ArrayList<>();
        mDatabaseCategoryPresenter = databaseCategoryPresenter;
        mDatabaseReference = FirebaseDatabase.getInstance().getReference(InvestCategory.class.getSimpleName())
                .orderByValue().getRef();
        // InvestCategory DB object
        mDatabaseReference.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            for (DataSnapshot child: dataSnapshot.getChildren()) {
                                Log.d(TAG, "child value: "+ String.valueOf(child.getValue()));
                                mInvestCategories.add(new InvestCategory(child));
                            }
                        }
                        mDatabaseCategoryPresenter.setInvestCategoryUI(mInvestCategories);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e(TAG, "error from DB: " + databaseError.getMessage());
                    }
                });
    }

    public void addCategory(InvestCategory investCategory){
        DatabaseReference child = mDatabaseReference.child(investCategory.getType());
        child.setValue(investCategory);
        String id = child.push().getKey();
        investCategory.setId(id);
        Log.d(TAG, "addCategory: " + investCategory.toString());
        child.setValue(investCategory);
        mInvestCategories.add(investCategory);
        mDatabaseCategoryPresenter.setInvestCategoryUI(mInvestCategories);
    }

    public boolean updateWeightValue(int position, int value) {
        Log.d(TAG, "updateWeightValue: " + mInvestCategories.get(position) + " | " + value);
        if(percentagePossible(position, value)){
            Query query = mDatabaseReference.child(mInvestCategories.get(position).getType())
                    .orderByChild(InvestCategory.DATABASE_ID_FIELD)
                    .equalTo(mInvestCategories.get(position).getId());
            mInvestCategories.get(position).setWeight(value);
            query.getRef().setValue(mInvestCategories.get(position));
            return true;
        }
        return false;
    }

    /**
     * The MAX value of all weight must be 100
     * @param position of InvestCategory in array list
     * @param value set by the user
     * @return boolean
     */
    private boolean percentagePossible(int position, int value){
        final int MAX = 100;
        int aux = 0;
        for (int i=0; i < mInvestCategories.size(); i++) {
            aux += position != i ? mInvestCategories.get(i).getWeight() : value;
        }
        return aux <= MAX;
    }
}
