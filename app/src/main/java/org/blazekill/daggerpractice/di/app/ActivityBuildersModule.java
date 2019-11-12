package org.blazekill.daggerpractice.di.app;

import org.blazekill.daggerpractice.di.auth.AuthModule;
import org.blazekill.daggerpractice.di.auth.AuthViewModelModule;
import org.blazekill.daggerpractice.ui.auth.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * This module contains @ContributesAndroidInjector annotated abstract methods
 * the annotation requires the class to be abstract.
 * the annotation basically calls the inject method which injects the dependencies of the given activity
 * generates a sub component also
 */
@Module
public abstract class ActivityBuildersModule {

    /**
     * Since a sub component will be created which has AuthViewModelModule
     * Only that sub component will have access to AuthViewModel injection
     * @return Dependency injected AuthActivity
     */
    @ContributesAndroidInjector(
        modules = {
            AuthViewModelModule.class,
            AuthModule.class
        }
    )
    abstract AuthActivity contributeAuthActivity();

}
