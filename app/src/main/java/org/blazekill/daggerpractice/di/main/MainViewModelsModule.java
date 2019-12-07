package org.blazekill.daggerpractice.di.main;

import androidx.lifecycle.ViewModel;

import org.blazekill.daggerpractice.di.viewmodel.ViewModelKey;
import org.blazekill.daggerpractice.ui.main.profile.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindProfileViewModel(ProfileViewModel viewModel);

}
