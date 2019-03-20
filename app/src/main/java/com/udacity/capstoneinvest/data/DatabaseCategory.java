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
import com.udacity.capstoneinvest.presenter.CategoryPresenter;

import java.util.ArrayList;

public class DatabaseCategory extends Database{

    private static final String TAG = DatabaseCategory.class.getName();
    private CategoryPresenter mCategoryPresenter;
    private ArrayList<InvestCategory> mInvestCategories;

    public DatabaseCategory(CategoryPresenter categoryPresenter) {
        mInvestCategories = new ArrayList<>();
        mCategoryPresenter = categoryPresenter;
        setDatabaseReference(FirebaseDatabase.getInstance().getReference(InvestCategory.class.getSimpleName())
                .orderByValue().getRef());
        // InvestCategory DB object
        getDatabaseReference().addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            for (DataSnapshot child: dataSnapshot.getChildren()) {
                                Log.d(TAG, "child value: "+ String.valueOf(child.getValue()));
                                mInvestCategories.add(new InvestCategory(child));
                            }
                        }
                        mCategoryPresenter.setInvestCategoryUI(mInvestCategories);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e(TAG, "error from DB: " + databaseError.getMessage());
                    }
                });
    }

    public void addCategory(InvestCategory investCategory){
        DatabaseReference child = getDatabaseReference().child(investCategory.getType());
        child.setValue(investCategory);
        String id = child.push().getKey();
        investCategory.setId(id);
        Log.d(TAG, "addCategory: " + investCategory.toString());
        child.setValue(investCategory);
        mInvestCategories.add(investCategory);
        mCategoryPresenter.setInvestCategoryUI(mInvestCategories);
    }

    public boolean updateWeightValue(int position, int value) {
        Log.d(TAG, "updateWeightValue: " + mInvestCategories.get(position) + " | " + value);
        if(percentagePossible(position, value)){
            Query query = getDatabaseReference().child(mInvestCategories.get(position).getType())
                    .orderByChild(InvestCategory.DATABASE_ID_FIELD)
                    .equalTo(mInvestCategories.get(position).getId());
            mInvestCategories.get(position).setWeight(value);
            query.getRef().setValue(mInvestCategories.get(position));
            return true;
        }
        return false;
    }

    public ArrayList<InvestCategory> getInvestCategories(){
        return mInvestCategories;
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
