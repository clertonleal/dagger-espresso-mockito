package clertonleal.com.nubank.dagger;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.inject.Singleton;

import clertonleal.com.nubank.entity.BillWrapper;
import clertonleal.com.nubank.network.Network;
import clertonleal.com.nubank.service.ConnectionService;
import dagger.Provides;
import rx.Observable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@dagger.Module
public class TestModule {

    private Context context;

    public TestModule(Context context) {
        this.context = context;
    }

    @Provides
    Network provideMovieNetwork() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Type listType = new TypeToken<ArrayList<BillWrapper>>() {}.getType();
        Network network = mock(Network.class);
        when(network.retrieveBills()).thenReturn(Observable.just(gson.fromJson(loadJSONFromAsset("bills_mock.json"), listType)));
        return network;
    }

    @Provides
    @Singleton
    ConnectionService provideConnectionService() {
        ConnectionService connectionService = mock(ConnectionService.class);
        when(connectionService.hasConnection()).thenReturn(true);
        return connectionService;
    }

    public String loadJSONFromAsset(String fileName) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            Log.e("loadJSONFromAsset", ex.getMessage());
        }

        return json;
    }

}
