package com.morzhanov.boilerplate.ui.base;

public interface BaseDialogCallback {

    byte RESULT_POSITIVE = 0;
    byte RESULT_NEGATIVE = 1;
    byte RESULT_CANCELED = 2;

    void onResult(byte res);
}
