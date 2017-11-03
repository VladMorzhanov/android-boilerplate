package com.morzhanov.boilerplate.ui.base;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import javax.annotation.Nullable;

public interface BaseNavigator {

    @TargetApi(Build.VERSION_CODES.M)
    boolean hasPermission(String permission);

    void hideKeyboard();

    void invokeIntent(@Nullable Intent intent, @Nullable final Bundle b, @Nullable final Class<?> c,
            final boolean forResult, final int requestCode);

    void replaceFragment(Fragment fragment, boolean addToBackStack);

    @TargetApi(Build.VERSION_CODES.M)
    void requestPermissionsSafely(String[] permissions, int requestCode);

    void showDialogFragment(@Nullable final DialogFragment fragment, @Nullable final String tag);

    void showToast(@Nullable final String message);

}
