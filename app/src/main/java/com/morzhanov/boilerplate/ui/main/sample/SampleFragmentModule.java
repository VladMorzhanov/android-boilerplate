package com.morzhanov.boilerplate.ui.main.sample;

import android.app.Application;
import com.morzhanov.boilerplate.data.RepositoryProvider;
import dagger.Module;
import dagger.Provides;

@Module
public class SampleFragmentModule {

    @Provides
    SampleViewModel provideDashboardViewModel(Application app, RepositoryProvider provider) {
        return new SampleViewModel(app, provider);
    }
}
