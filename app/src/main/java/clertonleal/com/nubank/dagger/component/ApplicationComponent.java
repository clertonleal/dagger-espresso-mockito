package clertonleal.com.nubank.dagger.component;

import javax.inject.Singleton;

import clertonleal.com.nubank.dagger.module.AndroidModule;
import clertonleal.com.nubank.dagger.module.Module;

@Singleton
@dagger.Component(modules = {Module.class, AndroidModule.class})
public interface ApplicationComponent extends Component {}
