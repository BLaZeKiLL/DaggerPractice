package org.blazekill.daggerpractice.di;

import org.blazekill.daggerpractice.activities.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * This module contains @ContributesAndroidInjector annotated abstract methods
 * the annotation requires the class to be abstract.
 * the annotation basically calls the inject method which injects the dependencies of the given activity
 */
@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract AuthActivity contributeAuthActivity();

}
