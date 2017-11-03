package com.morzhanov.boilerplate;

import android.app.Activity;
import android.app.Application;
import com.morzhanov.boilerplate.di.Injector;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import javax.inject.Inject;

/**
 * Main application class
 */
public class App extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> mActivityDispatchingAndroidInjector;

    @Inject
    public App() {

    }

    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("mevics.realm").build();
        Realm.setDefaultConfiguration(config);

        Injector.initInjector(this);

        Injector.get().getAppComponent().inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return mActivityDispatchingAndroidInjector;
    }
}
