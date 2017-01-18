package com.chuangjia.cyweather;

import android.app.Dialog;
import android.content.Context;

/**
 * Created by Melvin on 2017/1/18.
 */

public class WeatherDialog extends Dialog {
    public WeatherDialog(Context context) {
        super(context);
    }

    public WeatherDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected WeatherDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

}
