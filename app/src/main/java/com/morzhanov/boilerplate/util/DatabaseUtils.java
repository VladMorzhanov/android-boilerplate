package com.morzhanov.boilerplate.util;

import com.morzhanov.boilerplate.data.RepositoryProvider;
import com.morzhanov.boilerplate.data.User;
import com.morzhanov.boilerplate.data.source.UserRepository;
import io.realm.Realm;
import javax.inject.Inject;

public class DatabaseUtils {

    private UserRepository mUserRepository;

    @Inject
    public DatabaseUtils(RepositoryProvider repositoryProvider) {
        mUserRepository = repositoryProvider.getUserRepo();
    }

    public void clearRepos() {
        Realm r = Realm.getDefaultInstance();
        r.executeTransaction(realm -> realm.deleteAll());
        r.close();
    }

    public void initState() {
        // init realm models
        mUserRepository.saveUser(new User());
    }
}
