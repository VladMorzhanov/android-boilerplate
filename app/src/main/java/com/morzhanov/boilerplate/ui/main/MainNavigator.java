package com.morzhanov.boilerplate.ui.main;


import android.support.v4.app.Fragment;
import com.morzhanov.boilerplate.ui.base.BaseNavigator;

public interface MainNavigator extends BaseNavigator {

    void changeFragment(Fragment fragment, boolean addToBackStack);

    void openDrawer();

    void replaceFragment(Fragment fragment, boolean addToBackstack);
}

