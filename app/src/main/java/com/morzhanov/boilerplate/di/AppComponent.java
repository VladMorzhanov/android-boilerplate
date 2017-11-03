package com.morzhanov.boilerplate.di;

import android.app.Application;
import com.morzhanov.boilerplate.App;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import javax.inject.Singleton;

@Component(modules = {AndroidInjectionModule.class, ActivityBuilder.class, AppModule.class})
@Singleton
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();

    }

    void inject(App app);
}
