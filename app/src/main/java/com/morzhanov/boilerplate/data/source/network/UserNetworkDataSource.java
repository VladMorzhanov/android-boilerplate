package com.morzhanov.boilerplate.data.source.network;

import com.morzhanov.boilerplate.data.User;
import com.morzhanov.boilerplate.data.source.UserDataSource;
import com.morzhanov.boilerplate.util.NetworkUtils;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Implementation of the User's data source that works with Retrofit API.
 */
@Singleton
public class UserNetworkDataSource implements UserDataSource {

    private APIService mAPIService;

    private NetworkUtils mNetworkUtils;

    @Inject
    public UserNetworkDataSource(NetworkUtils networkUtils) {
        mNetworkUtils = networkUtils;
        mAPIService = RetrofitProvider.getMevicsAPIService();
    }

    @Override
    public void deleteUser() {
        throw new UnsupportedOperationException("This data source not contain such method");
    }

    @Override
    public long getId() {
        throw new UnsupportedOperationException("This data source not contain such method");
    }

    @Override
    public void setId(long id) {
        throw new UnsupportedOperationException("This data source not contain such method");
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("This data source not contain such method");
    }

    @Override
    public void setName(String name) {
        throw new UnsupportedOperationException("This data source not contain such method");
    }

    @Override
    public String getToken() {
        throw new UnsupportedOperationException("This data source not contain such method");
    }

    @Override
    public void setToken(String token) {
        throw new UnsupportedOperationException("This data source not contain such method");
    }

    @Override
    public Observable<User> getUser() {
        return mAPIService.getUser(mNetworkUtils.getAccessToken()).observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io());
    }

    @Override
    public void saveUser(User user) {
        throw new UnsupportedOperationException("This data source not contain such method");
    }

    @Override
    public Observable<Void> updateUser(User user) {
        return mAPIService.updateUser(mNetworkUtils.getAccessToken(), user).observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io());
    }
}
