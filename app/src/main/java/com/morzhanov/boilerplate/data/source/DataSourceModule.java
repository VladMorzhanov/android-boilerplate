package com.morzhanov.boilerplate.data.source;

import com.morzhanov.boilerplate.data.source.local.UserLocalDataSource;
import com.morzhanov.boilerplate.data.source.network.UserNetworkDataSource;
import com.morzhanov.boilerplate.util.NetworkUtils;
import dagger.Module;
import dagger.Provides;

@Module
public class DataSourceModule {

    public DataSourceModule() {
    }

    @Provides
    public UserLocalDataSource provideUserLocalDataSource(NetworkUtils utils) {
        return new UserLocalDataSource(utils);
    }

    @Provides
    public UserNetworkDataSource provideUserNetworkDataSource(NetworkUtils utils) {
        return new UserNetworkDataSource(utils);
    }
}
