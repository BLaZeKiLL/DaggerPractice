package org.blazekill.daggerpractice.di;

import android.app.Application;

import org.blazekill.daggerpractice.BaseApplication;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Named("BaseApp")
    static boolean checkBaseApp(BaseApplication application) {
        return application != null;
    }

}
