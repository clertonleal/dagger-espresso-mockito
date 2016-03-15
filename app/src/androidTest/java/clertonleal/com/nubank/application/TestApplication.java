package clertonleal.com.nubank.application;

import clertonleal.com.nubank.dagger.DaggerTestComponent;
import clertonleal.com.nubank.dagger.TestModule;
import clertonleal.com.nubank.dagger.component.Component;
import clertonleal.com.nubank.dagger.module.AndroidModule;

public class TestApplication extends Application {

    @Override
    protected Component createComponent() {
        return DaggerTestComponent.builder().
                androidModule(new AndroidModule(this)).
                testModule(new TestModule(this)).
                build();
    }
}
