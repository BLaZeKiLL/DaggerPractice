package org.blazekill.daggerpractice.di.app;

import org.blazekill.daggerpractice.di.auth.AuthViewModelsModule;
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

    @ContributesAndroidInjector(
        modules = {AuthViewModelsModule.class}
    )
    abstract AuthActivity contributeAuthActivity();

}
