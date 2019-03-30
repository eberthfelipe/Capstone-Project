package com.udacity.capstoneinvest.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.udacity.capstoneinvest.R;

/**
 * Implementation of App Widget functionality.
 */
public class PortfolioWidget extends AppWidgetProvider {

    public static final String WIDGET_PORTFOLIO_UPDATE = "com.udacity.capstoneinvest.widget.update";
    public static final String WIDGET_PORTFOLIO_TOTAL_INVESTED = "portfolio_total_invested";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        WidgetPreference widgetPreference = new WidgetPreference();
        double portfolioValue = widgetPreference.getPortfolioSaved(context);
        String widgetText = String.format(context.getString(R.string.appwidget_text), String.valueOf(portfolioValue));

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.portfolio_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if(WIDGET_PORTFOLIO_UPDATE.equals(intent.getAction())){
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            ComponentName thisAppWidget = new ComponentName(context.getPackageName(), PortfolioWidget.class.getName());
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidget);

            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.appwidget_text);
            for (int appWidgetId: appWidgetIds) {
                updateAppWidget(context, appWidgetManager, appWidgetId);
            }
        }
    }
}

