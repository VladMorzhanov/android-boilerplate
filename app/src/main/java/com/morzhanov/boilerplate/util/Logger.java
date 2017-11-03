package com.morzhanov.boilerplate.util;


import android.util.Log;
import javax.inject.Inject;

/**
 * class uses for logging
 */
public class Logger {

    private static final String LOG_TAG = "mevics_main_logs";

    @Inject
    public Logger() {
    }

    /**
     * access to logs service
     *
     * @param message - log message
     */
    public void writeLog(String className, String methodName, String message) {
        Log.d(LOG_TAG, "[" + className + "]" + "[" + methodName + "] : " + message + "\n");
    }
}
