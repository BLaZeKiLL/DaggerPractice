package org.blazekill.daggerpractice.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.ViewModel;

import org.blazekill.daggerpractice.SessionManager;
import org.blazekill.daggerpractice.models.User;
import org.blazekill.daggerpractice.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";

    private final AuthApi authApi;

    private SessionManager sessionManager;

    @Inject
    public AuthViewModel(AuthApi authApi, SessionManager sessionManager) {
        this.authApi = authApi;
        this.sessionManager = sessionManager;
    }

    public void authenticateWithId(int userId) {
        Log.d(TAG, "authenticateWithId: Attempting to login");
        sessionManager.authenticateWithId(queryUserId(userId));
    }

    private LiveData<AuthResource<User>> queryUserId(int userId) {
        return LiveDataReactiveStreams
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
    }

    public LiveData<AuthResource<User>> observeAuthState() {
        return sessionManager.getAuthUser();
    }

}
