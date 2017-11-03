package com.morzhanov.boilerplate.data.source;

import com.morzhanov.boilerplate.data.User;
import com.morzhanov.boilerplate.data.source.local.UserLocalDataSource;
import com.morzhanov.boilerplate.data.source.network.UserNetworkDataSource;
import com.morzhanov.boilerplate.di.Injector;
import io.reactivex.Observable;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Concrete implementation to load Users from the data sources into a cache.
 */
@Singleton
public class UserRepository implements UserDataSource {

    @Inject
    UserLocalDataSource mUserLocDS;

    @Inject
    UserNetworkDataSource mUserNetDS;

    @Inject
    public UserRepository() {
        Injector.get().getDataSourceComponent().inject(this);
    }

    @Override
    public void deleteUser() {
        mUserLocDS.deleteUser();
    }

    @Override
    public long getId() {
        return mUserLocDS.getId();
    }

    @Override
    public void setId(long id) {
        mUserLocDS.setId(id);
    }

    @Override
    public String getName() {
        return mUserLocDS.getName();
    }

    @Override
    public void setName(String name) {
        mUserLocDS.setName(name);
        mUserLocDS.getUser().subscribe(user -> mUserNetDS.updateUser(user));
    }

    @Override
    public String getToken() {
        return mUserLocDS.getToken();
    }

    @Override
    public void setToken(String token) {
        mUserLocDS.setToken(token);
    }

    @Override
    public Observable<User> getUser() {
        return Observable.create(subscriber -> mUserLocDS.getUser().subscribe(
                subscriber::onNext,
                e -> mUserNetDS.getUser().subscribe(
                        user -> {
                            mUserLocDS.saveUser(user);
                            subscriber.onNext(user);
                        },
                        subscriber::onError)));
    }

    @Override
    public void saveUser(User user) {
        mUserLocDS.saveUser(user);
    }

    @Override
    public Observable<Void> updateUser(User user) {
        return Observable.create(subscriber -> mUserLocDS.updateUser(user).subscribe(
                v -> {
                    mUserNetDS.updateUser(user);
                    subscriber.onNext(v);
                },
                subscriber::onError));
    }
}
