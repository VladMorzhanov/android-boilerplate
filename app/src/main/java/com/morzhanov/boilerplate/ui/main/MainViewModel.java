package com.morzhanov.boilerplate.ui.main;

import android.app.Application;
import com.morzhanov.boilerplate.data.RepositoryProvider;
import com.morzhanov.boilerplate.ui.base.BaseDialogCallback;
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
        BaseDialogCallback callback = res -> {
            switch (res) {
                case BaseDialogCallback.RESULT_POSITIVE:
                    getNavigator().showToast("Positive");
                    break;
                case BaseDialogCallback.RESULT_NEGATIVE:
                    getNavigator().showToast("Negative");
                    break;
                case BaseDialogCallback.RESULT_CANCELED:
                    getNavigator().showToast("Canceled");
                    break;
            }
        };
        getNavigator().showDialogFragment(SampleDialog.newInstance(callback), "SampleDialog");
    }
}
