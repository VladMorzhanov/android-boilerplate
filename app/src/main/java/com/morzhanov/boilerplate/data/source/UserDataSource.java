package com.morzhanov.boilerplate.data.source;

import com.morzhanov.boilerplate.data.User;
import io.reactivex.Observable;

/**
 * Main entry point for accessing User's data.
 */
public interface UserDataSource {

    void deleteUser();

    String getToken();

    void setToken(String token);

    long getId();

    void setId(long id);

    String getName();

    void setName(String name);

    Observable<User> getUser();

    void saveUser(User user);

    Observable<Void> updateUser(User user);
}
