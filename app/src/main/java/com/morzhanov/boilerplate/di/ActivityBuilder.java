package com.morzhanov.boilerplate.di;

import com.morzhanov.boilerplate.ui.main.MainActivity;
import com.morzhanov.boilerplate.ui.main.MainActivityModule;
import com.morzhanov.boilerplate.ui.main.sample.SampleFragmentProvider;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {MainActivityModule.class,
            SampleFragmentProvider.class
    })
    abstract MainActivity bindMainActivity();
}
