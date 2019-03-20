package com.udacity.capstoneinvest.data;

import com.google.firebase.database.DatabaseReference;

class Database {

    private DatabaseReference mDatabaseReference;

    DatabaseReference getDatabaseReference() {
        return mDatabaseReference;
    }

    void setDatabaseReference(DatabaseReference mDatabaseReference) {
        this.mDatabaseReference = mDatabaseReference;
    }

}
