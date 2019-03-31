package com.udacity.capstoneinvest.view;

import com.udacity.capstoneinvest.object.FinancialSupport;

public interface FinancialSupportUI {

    void setFinancialSupportUI(FinancialSupport financialSupport);
    void showProgress(boolean show);
    void updateTotalValue(int item, int action);

}
