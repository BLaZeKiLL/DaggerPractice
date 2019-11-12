package org.blazekill.daggerpractice.di.auth;

import androidx.lifecycle.ViewModel;

import org.blazekill.daggerpractice.di.viewmodel.ViewModelKey;
import org.blazekill.daggerpractice.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);

}
