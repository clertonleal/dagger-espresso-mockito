package clertonleal.com.nubank.dagger.module;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.view.LayoutInflater;

import dagger.Provides;

@dagger.Module
public class AndroidModule {

    private final Context context;

    public AndroidModule(Context context) {
        this.context = context;
    }

    @Provides
    Context provideContext() {
        return context;
    }

    @Provides
    SharedPreferences providePreferenceManager(Context context) {
        return context.getSharedPreferences("Nubank", 0);
    }

    @Provides
    ConnectivityManager provideConnectivityManager() {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Provides
    ContentResolver provideContentResolver() {
        return context.getContentResolver();
    }

    @Provides
    Resources provideResources(Context context) {
        return context.getResources();
    }

    @Provides
    LayoutInflater provideLayoutInflater(Context context) {
        return LayoutInflater.from(context);
    }

}
