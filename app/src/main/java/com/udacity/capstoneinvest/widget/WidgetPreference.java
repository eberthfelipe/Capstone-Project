package com.udacity.capstoneinvest.widget;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class WidgetPreference {

    private static final String TAG = WidgetPreference.class.getName();

    public void savePortfolio(Context context, double valueTotal) {
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();

        prefsEditor.putFloat(PortfolioWidget.WIDGET_PORTFOLIO_TOTAL_INVESTED, (float) valueTotal);
        prefsEditor.apply();
    }

    public float getPortfolioSaved(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        float valueTotal = sharedPreferences.getFloat(PortfolioWidget.WIDGET_PORTFOLIO_TOTAL_INVESTED, 0);
        Log.d(TAG, "getPortfolioSaved: " + valueTotal);
        return valueTotal;
    }
}
