package com.morzhanov.boilerplate.data.source.local;

import com.morzhanov.boilerplate.data.User;
import com.morzhanov.boilerplate.data.source.UserDataSource;
import com.morzhanov.boilerplate.util.NetworkUtils;
import io.reactivex.Observable;
import io.realm.Realm;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Concrete implementation of a User's data source as a Realm db.
 */
@Singleton
public class UserLocalDataSource implements UserDataSource {

    private final long ID_USER = 0L;

    private NetworkUtils mNetworkUtils;

    @Inject
    public UserLocalDataSource(NetworkUtils networkUtils) {
        mNetworkUtils = networkUtils;
    }

    @Override
    public void deleteUser() {
        Realm.getDefaultInstance().executeTransaction(realm -> {
            User u = realm.where(User.class).equalTo("id", ID_USER).findFirst();
            if (u != null) {
                u.deleteFromRealm();
            }
        });
    }

    /**
     * get user ID from local db
     *
     * @return id;
     */
    @Override
    public long getId() {
        Realm realm = Realm.getDefaultInstance();
        User u = realm.where(User.class).equalTo("id", ID_USER).findFirst();
        return u.getId();
    }

    /**
     * set UserID to local db
     *
     * @param id - User id
     */
    @Override
    public void setId(long id) {
        Realm.getDefaultInstance().executeTransaction(realm -> {
            User u = realm.where(User.class).equalTo("id", ID_USER).findFirst();
            u.setId(id);
        });
    }

    /**
     * get user name from local db
     *
     * @return name
     */
    @Override
    public String getName() {
        Realm realm = Realm.getDefaultInstance();
        User u = realm.where(User.class).equalTo("id", ID_USER).findFirst();
        return u.getName();
    }

    /**
     * set username to local db
     *
     * @param name - user name
     */
    @Override
    public void setName(String name) {
        Realm.getDefaultInstance().executeTransaction(realm -> {
            User u = realm.where(User.class).equalTo("id", ID_USER).findFirst();
            u.setName(name);
        });
    }

    @Override
    public String getToken() {
        Realm realm = Realm.getDefaultInstance();
        User u = realm.where(User.class).equalTo("id", ID_USER).findFirst();
        mNetworkUtils.setAccessToken(u.getToken());
        return u.getToken();
    }

    @Override
    public void setToken(String token) {
        Realm.getDefaultInstance().executeTransaction(realm -> {
            mNetworkUtils.setAccessToken(token);
            User u = realm.where(User.class).equalTo("id", ID_USER).findFirst();
            u.setToken(token);
        });
    }

    /**
     * get user
     */
    @Override
    public Observable<User> getUser() {
        return Observable.create(subscriber -> Realm.getDefaultInstance().executeTransaction
                (realm -> {
                    User u = realm.where(User.class).equalTo("id", ID_USER).findFirst();
                    u = realm.copyFromRealm(u);
                    if (u == null) {
                        realm.commitTransaction();
                        subscriber.onError(new Error("User not found."));
                    } else {
                        mNetworkUtils.setAccessToken(u.getToken());
                        realm.commitTransaction();
                        subscriber.onNext(u);
                    }
                }));
    }

    /**
     * user local saving
     *
     * @param user - object of User.class
     */
    @Override
    public void saveUser(User user) {
        Realm.getDefaultInstance().executeTransaction(realm -> realm.copyToRealmOrUpdate(new User(ID_USER)));
    }

    /**
     * user updating in local db
     *
     * @param user - object of User.class
     */
    @Override
    public Observable<Void> updateUser(User user) {
        return Observable.create(subscriber -> Realm.getDefaultInstance().executeTransaction
                (realm -> {
                    mNetworkUtils.setAccessToken(user.getToken());
                    realm.copyToRealmOrUpdate(user);
                    subscriber.onNext(null);
                }));
    }
}
