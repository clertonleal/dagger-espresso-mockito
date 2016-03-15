package clertonleal.com.nubank.dagger.module;

import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import clertonleal.com.nubank.network.Network;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

@dagger.Module
public class Module {

    @Provides
    @Singleton
    RestAdapter provideRestAdapter() {
        return new RestAdapter.Builder().
                setEndpoint("https://s3-sa-east-1.amazonaws.com/").
                setConverter(new GsonConverter(new GsonBuilder().setDateFormat("yyyy-MM-dd").create())).
                build();
    }

    @Provides
    Network provideMovieNetwork(RestAdapter restAdapter) {
        return restAdapter.create(Network.class);
    }

}
