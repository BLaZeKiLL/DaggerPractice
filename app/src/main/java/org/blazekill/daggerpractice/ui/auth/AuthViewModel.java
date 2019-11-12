package org.blazekill.daggerpractice.ui.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import org.blazekill.daggerpractice.models.User;
import org.blazekill.daggerpractice.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";

    private final AuthApi authApi;

    // I guess mediator holds the data (intermediate live data) checkout live data
    private MediatorLiveData<AuthResource<User>> authUser = new MediatorLiveData<>();

    @Inject
    public AuthViewModel(AuthApi authApi) {
        this.authApi = authApi;
    }

    public void authenticateWithId(int userId) {
        authUser.setValue(AuthResource.loading(null));

        final LiveData<AuthResource<User>> source = LiveDataReactiveStreams
            .fromPublisher(
                authApi.getUser(userId)
                    // Error handler
                    .onErrorReturn(throwable -> {
                        User errorUser = new User();
                        errorUser.setId(-1);
                        return errorUser;
                    })
                    // First bracket specifies the return type
                    .map((Function<User, AuthResource<User>>) user -> {
                        if (user.getId() == -1) {
                            return AuthResource.error("Could Not Authenticate", null);
                        }
                        return AuthResource.authenticated(user);
                    })
                    .subscribeOn(Schedulers.io())
            );

        authUser.addSource(source, user -> {
            authUser.setValue(user);
            authUser.removeSource(source);
        });
    }

    public LiveData<AuthResource<User>> observeUser() {
        return authUser;
    }

}
