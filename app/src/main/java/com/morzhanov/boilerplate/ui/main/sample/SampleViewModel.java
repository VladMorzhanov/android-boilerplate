package com.morzhanov.boilerplate.ui.main.sample;

import android.app.Application;
import com.morzhanov.boilerplate.data.RepositoryProvider;
import com.morzhanov.boilerplate.ui.base.BaseViewModel;
import javax.inject.Inject;

/**
 * Exposes the data to be used in the settings screen.
 */
public class SampleViewModel extends BaseViewModel<SampleNavigator> {

    @Inject
    public SampleViewModel(Application context, RepositoryProvider provider) {
        super(context, provider);
    }

    public void onTextClicked() {
        getNavigator().showToast("On text clicked");
    }
}
