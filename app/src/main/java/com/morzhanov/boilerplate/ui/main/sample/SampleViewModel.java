package com.morzhanov.boilerplate.ui.main.sample;

import android.app.Application;
import android.view.View;
import com.morzhanov.boilerplate.data.RepositoryProvider;
import com.morzhanov.boilerplate.device.IMevicsDeviceManager.OnPostureStateChangeListener;
import com.morzhanov.boilerplate.device.MevicsDeviceManager;
import com.morzhanov.boilerplate.ui.base.BaseViewModel;
import javax.inject.Inject;

/**
 * Exposes the data to be used in the settings screen.
 */
public class SampleViewModel extends BaseViewModel<SampleNavigator> {

    private MevicsDeviceManager mMevicsDeviceManager;

    private OnPostureStateChangeListener mOnPostureStateChangeListener;

    @Inject
    public SampleViewModel(Application context, RepositoryProvider provider, MevicsDeviceManager manager) {
        super(context, provider);
        mMevicsDeviceManager = manager;
        mOnPostureStateChangeListener = initOnPostureListener();
        mMevicsDeviceManager.addOnPostureStateChangeListener(mOnPostureStateChangeListener);
    }

    public void onSpineClicked(View v) {
        mMevicsDeviceManager.refreshPosition();
    }

    private OnPostureStateChangeListener initOnPostureListener() {
        return state -> {
            boolean postureState = getRepositoryProvider().getUserRepo().getPostureState();
            getNavigator().redrawSpine(postureState);
        };
    }
}
