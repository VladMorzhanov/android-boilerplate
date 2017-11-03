package com.morzhanov.boilerplate.databinding;

import android.graphics.Typeface;
import android.widget.TextView;

public abstract class BindingAdapter {
    @android.databinding.BindingAdapter("android:typeface")
    public static void setTypeface(TextView v, String style) {
        switch (style) {
            case "bold":
                v.setTypeface(null, Typeface.BOLD);
                break;
            default:
                v.setTypeface(null, Typeface.NORMAL);
                break;
        }
    }
}
