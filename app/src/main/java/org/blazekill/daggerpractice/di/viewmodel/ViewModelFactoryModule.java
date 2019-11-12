package org.blazekill.daggerpractice.di.viewmodel;

import androidx.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;

/**
 * EVERYTHING IN THE VIEWMODEL PACKAGE CAN BE REUSED AS IT IS IN ALL PROJECTS
 */
@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory modelProviderFactory);

}
