package org.blazekill.daggerpractice.ui.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import org.blazekill.daggerpractice.models.User;
import org.blazekill.daggerpractice.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";

    private final AuthApi authApi;

    // I guess mediator holds the data (intermediate live data) checkout live data
    private MediatorLiveData<User> authUser = new MediatorLiveData<>();

    @Inject
    public AuthViewModel(AuthApi authApi) {
        this.authApi = authApi;
    }

    public void authenticateWithId(int userId) {
        final LiveData<User> source = LiveDataReactiveStreams
            .fromPublisher(
                authApi.getUser(userId).subscribeOn(Schedulers.io())
            );

        authUser.addSource(source, user -> {
            authUser.setValue(user);
            authUser.removeSource(source);
        });
    }

    public LiveData<User> observeUser() {
        return authUser;
    }

}
