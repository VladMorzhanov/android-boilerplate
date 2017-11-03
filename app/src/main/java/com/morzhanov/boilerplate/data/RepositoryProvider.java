package com.morzhanov.boilerplate.data;

import com.morzhanov.boilerplate.data.source.UserRepository;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RepositoryProvider {

    private final UserRepository mUserRepository;

    @Inject
    public RepositoryProvider(UserRepository userRepository) {
        mUserRepository = userRepository;
    }

    public UserRepository getUserRepo() {
        return mUserRepository;
    }
}
