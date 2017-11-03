package com.morzhanov.boilerplate.ui.main.sample;

import android.app.Application;
import com.morzhanov.boilerplate.data.RepositoryProvider;
import com.morzhanov.boilerplate.device.BleMevicsDevice;
import com.morzhanov.boilerplate.device.MevicsDeviceManager;
import dagger.Module;
import dagger.Provides;

@Module
public class SampleFragmentModule {

    @Provides
    SampleViewModel provideDashboardViewModel(Application app, RepositoryProvider provider,
            MevicsDeviceManager manager) {
        return new SampleViewModel(app, provider, manager);
    }

}
