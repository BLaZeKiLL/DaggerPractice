package org.blazekill.daggerpractice.di;

import org.blazekill.daggerpractice.BaseApplication;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(
    modules = {
        AndroidSupportInjectionModule.class,
        ActivityBuildersModule.class,
        AppModule.class
    }
)
public interface AppComponent extends AndroidInjector<BaseApplication> {

    /**
     * create method is already added as we are extending Factory interface from
     * AndroidInjector.Factory which binds the BaseApplication (@BindsInstance) so
     * we can use BaseApplication as a dependency
     */
    @Component.Factory
    interface Factory extends AndroidInjector.Factory<BaseApplication> { }

}
