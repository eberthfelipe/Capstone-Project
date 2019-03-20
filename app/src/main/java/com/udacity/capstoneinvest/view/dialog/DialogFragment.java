package com.udacity.capstoneinvest.view.dialog;

import android.view.View;

public interface DialogFragment {

    View.OnClickListener getPositiveDialogClick();
    View.OnClickListener getNegativeDialogClick();
    void showToast(String text);
}
