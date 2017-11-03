package com.morzhanov.boilerplate.databinding;

import android.databinding.InverseMethod;

public class BindingConversions {
    @InverseMethod("stringToInt")
    public static String intToString(int value) {
        return Integer.toString(value);
    }

    public static int stringToInt(String value) {
        return Integer.parseInt(value);
    }
}
