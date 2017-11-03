package com.morzhanov.boilerplate.ui.main.sample;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SampleFragmentProvider {

    @ContributesAndroidInjector(modules = SampleFragmentModule.class)
    abstract SampleFragment provideDashboardFragmentFactory();
}
