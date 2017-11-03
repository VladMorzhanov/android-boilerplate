package com.morzhanov.boilerplate.data.source;

import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {DataSourceModule.class})
public interface DataSourceComponent {

    void inject(UserRepository userRepository);
}
