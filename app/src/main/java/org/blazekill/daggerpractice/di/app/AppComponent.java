package org.blazekill.daggerpractice.di.app;

import org.blazekill.daggerpractice.BaseApplication;
import org.blazekill.daggerpractice.SessionManager;
import org.blazekill.daggerpractice.di.viewmodel.ViewModelFactoryModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(
    modules = {
        AndroidSupportInjectionModule.class,
        ActivityBuildersModule.class,
        AppModule.class,
        ViewModelFactoryModule.class
    }
)
public interface AppComponent extends AndroidInjector<BaseApplication> {

    /**
     * Can be in app module
     * @return Session manager
     */
    SessionManager sessionManager();

    /**
     * create method is already added as we are extending Factory interface from
     * AndroidInjector.Factory which binds the BaseApplication (@BindsInstance) so
     * we can use BaseApplication as a dependency
     */
    @Component.Factory
    interface Factory extends AndroidInjector.Factory<BaseApplication> { }

}
