package com.morzhanov.boilerplate.util;

import javax.inject.Inject;

public class ConvertUtils {

    public static int stringToInt(String value) {
        return Integer.parseInt(value);
    }

    @Inject
    public ConvertUtils() {

    }
}