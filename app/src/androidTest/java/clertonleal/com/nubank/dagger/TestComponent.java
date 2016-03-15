package clertonleal.com.nubank.dagger;

import javax.inject.Singleton;

import clertonleal.com.nubank.dagger.component.Component;
import clertonleal.com.nubank.dagger.module.AndroidModule;
import clertonleal.com.nubank.tests.android_tests.OfflineActivityTest;
import clertonleal.com.nubank.tests.jvm_tests.ServiceTest;

@Singleton
@dagger.Component(modules = {TestModule.class, AndroidModule.class})
public interface TestComponent extends Component {
    void inject(ServiceTest serviceTest);
    void inject(OfflineActivityTest offlineActivityTest);
}
