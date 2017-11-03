package com.morzhanov.boilerplate.ui.main;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import com.morzhanov.boilerplate.data.RepositoryProvider;
import com.morzhanov.boilerplate.device.MevicsDeviceManager;
import com.morzhanov.boilerplate.di.ViewModelProviderFactory;
import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    ViewModelProvider.Factory mainViewModelProvider(MainViewModel mainViewModel) {
        return new ViewModelProviderFactory<>(mainViewModel);
    }

    @Provides
    MainViewModel provideViewModel(Application application, RepositoryProvider pro, MevicsDeviceManager manager) {
        return new MainViewModel(application, pro, manager);
    }
}
