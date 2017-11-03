package com.morzhanov.boilerplate.data;

import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


/**
 * Model class for a User extends RealmObject and supports gson annotations for Retrofit.
 */
public class User extends RealmObject {

    @PrimaryKey
    private long id;

    @SerializedName("name")
    private String name;

    @SerializedName("token")
    private String token;

    public User() {
    }

    public User(long id) {
        this.id = id;
        this.token = "";
        this.name = "";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
