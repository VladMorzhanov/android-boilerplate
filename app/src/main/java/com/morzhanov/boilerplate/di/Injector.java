package com.morzhanov.boilerplate.di;

import com.morzhanov.boilerplate.App;
import com.morzhanov.boilerplate.data.source.DataSourceComponent;
import com.morzhanov.boilerplate.data.source.DataSourceModule;

public class Injector {

    private static Injector mInjector;

    private AppComponent mAppComponent;

    private DataSourceComponent mDataSourceComponent;

    public static Injector get() {
        if (mInjector == null) {
            throw new ExceptionInInitializerError("Injector not initialized yet");
        }
        return mInjector;
    }

    public static void initInjector(App app) {
        if (mInjector == null) {
            mInjector = new Injector(app);
        }
    }

    private Injector(App app) {

        mDataSourceComponent = DaggerDataSourceComponent.builder()
                .dataSourceModule(new DataSourceModule())
                .build();

        mAppComponent = DaggerAppComponent.builder()
                .application(app)
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public DataSourceComponent getDataSourceComponent() {
        return mDataSourceComponent;
    }
}
