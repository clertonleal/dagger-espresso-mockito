package clertonleal.com.nubank.application;



import clertonleal.com.nubank.dagger.component.Component;
import clertonleal.com.nubank.dagger.component.DaggerApplicationComponent;
import clertonleal.com.nubank.dagger.module.AndroidModule;
import clertonleal.com.nubank.dagger.module.Module;

public class Application extends android.app.Application {

    private static Component component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = createComponent();
    }

    protected Component createComponent() {
        return DaggerApplicationComponent.
                builder().
                module(new Module()).
                androidModule(new AndroidModule(this)).
                build();
    }

    public static Component getComponent(){
        return component;
    }

    public void setComponent(Component components) {
        component = components;
    }
}
