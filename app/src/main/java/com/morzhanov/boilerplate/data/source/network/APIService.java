package com.morzhanov.boilerplate.data.source.network;

import com.morzhanov.boilerplate.data.User;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;

public interface APIService {

    @GET("user")
    Observable<User> getUser(@Header("x-access-token") String x_access_token);

    @PUT("user")
    Observable<Void> updateUser(@Header("x-access-token") String x_access_token,
            @Body User user);
}
