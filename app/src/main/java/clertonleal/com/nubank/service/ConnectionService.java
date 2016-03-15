package clertonleal.com.nubank.service;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ConnectionService {

    @Inject
    ConnectivityManager connectivityManager;

    @Inject
    public ConnectionService() {
        Log.e("", "");
    }

    public boolean hasConnection() {
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

}
