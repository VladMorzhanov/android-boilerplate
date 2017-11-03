package com.morzhanov.boilerplate.di;

import android.app.Application;
import android.content.Context;
import com.morzhanov.boilerplate.data.RepositoryProvider;
import com.morzhanov.boilerplate.data.source.UserRepository;
import com.morzhanov.boilerplate.notification.NotificationManager;
import com.morzhanov.boilerplate.util.ConvertUtils;
import com.morzhanov.boilerplate.util.DatabaseUtils;
import com.morzhanov.boilerplate.util.DateUtils;
import com.morzhanov.boilerplate.util.Logger;
import com.morzhanov.boilerplate.util.MIMETypeUtils;
import com.morzhanov.boilerplate.util.NetworkUtils;
import com.morzhanov.boilerplate.util.ScreenUtils;
import com.morzhanov.boilerplate.util.TextValidator;
import com.morzhanov.boilerplate.util.ViewAnimationUtils;
import com.morzhanov.boilerplate.util.ViewUtils;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class AppModule {

    @Provides
    @Singleton
    public ConvertUtils provideConvertUtils() {
        return new ConvertUtils();
    }

    @Provides
    @Singleton
    public DatabaseUtils provideDatabaseUtils(RepositoryProvider provider) {
        return new DatabaseUtils(provider);
    }

    @Provides
    @Singleton
    public DateUtils provideDateUtils() {
        return new DateUtils();
    }

    @Provides
    @Singleton
    public Logger provideLogger() {
        return new Logger();
    }

    @Provides
    @Singleton
    public MIMETypeUtils provideMIMETypeUtils() {
        return new MIMETypeUtils();
    }

    @Provides
    @Singleton
    public NetworkUtils provideNetworkUtils() {
        return new NetworkUtils();
    }

    @Provides
    @Singleton
    public NotificationManager provideNotificationManager() {
        return new NotificationManager();
    }

    @Provides
    public RepositoryProvider provideRepositoryProvider(NotificationManager manager) {
        return new RepositoryProvider(new AuthRepository(),
                new DeviceRepository(),
                new SettingsRepository(manager),
                new StatsRepository(),
                new UserRepository());
    }

    @Provides
    @Singleton
    public ScreenUtils provideScreenUtils() {
        return new ScreenUtils();
    }

    @Provides
    @Singleton
    public TextValidator provideTextValidator() {
        return new TextValidator();
    }

    @Provides
    @Singleton
    public ViewAnimationUtils provideViewAnimationUtils() {
        return new ViewAnimationUtils();
    }

    @Provides
    @Singleton
    public ViewUtils provideViewUtils() {
        return new ViewUtils();
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

}
