package com.udacity.capstoneinvest.widget;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.udacity.capstoneinvest.object.InvestmentPortfolio;

import java.lang.reflect.Type;

public class WidgetPreference {

    private static final String TAG = WidgetPreference.class.getName();

    public void savePortfolio(Context context, InvestmentPortfolio investmentPortfolio) {
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();

        Gson gson = new Gson();
        String json = gson.toJson(investmentPortfolio);
        prefsEditor.putString(PortfolioWidget.WIDGET_PORTFOLIO_TOTAL_INVESTED, json);
        prefsEditor.apply();
    }

    public double getPortfolioSaved(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        InvestmentPortfolio investmentPortfolio;
        Gson gson = new Gson();
        Type type = new TypeToken<InvestmentPortfolio>() {}.getType();
        String gsonString = sharedPreferences.getString(PortfolioWidget.WIDGET_PORTFOLIO_TOTAL_INVESTED, "");
        investmentPortfolio = gson.fromJson(gsonString, type);

        if (investmentPortfolio != null) {
            Log.d(TAG, "getPortfolioSaved: " + investmentPortfolio.toString());
            return investmentPortfolio.getValueTotal();
        }
        return 0;
    }
}
