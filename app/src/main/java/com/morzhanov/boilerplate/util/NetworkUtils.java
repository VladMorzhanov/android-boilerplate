package com.morzhanov.boilerplate.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import javax.inject.Inject;

/**
 * Class provides HTTP request to server
 * All requests receives json response
 * All Methods returns JSONObject with server response
 * Also Class provides network availability checking
 */
public class NetworkUtils {

    //// TODO: 10/27/17 refactor this
    private String mToken;

    @Inject
    public NetworkUtils() {

    }

    /**
     * @param context - call mInstance
     * @return is network available
     */
    public boolean checkNetworkAvailability(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        return networkInfo != null && networkInfo.isConnected();
    }

    public String getAccessToken() {
        return mToken;
    }

    public void setAccessToken(String token) {
        mToken = token;
    }
}
