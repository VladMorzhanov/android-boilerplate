package com.morzhanov.boilerplate.ui.main;

import android.app.Application;
import com.morzhanov.boilerplate.data.RepositoryProvider;
import com.morzhanov.boilerplate.ui.base.BaseViewModel;
import com.morzhanov.boilerplate.ui.main.sample.SampleDialog;

/**
 * Exposes the data to be used in the main screen.
 */
public class MainViewModel extends BaseViewModel<MainNavigator> {

    public MainViewModel(Application application, RepositoryProvider provider) {
        super(application, provider);
    }

    public void onDialogClicked() {
        getNavigator().showDialogFragment(SampleDialog.newInstance(
                res -> getNavigator().showToast("Dialog closed")), "SampleDialog");
    }
}
