package org.blazekill.daggerpractice.util;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import org.blazekill.daggerpractice.models.User;
import org.blazekill.daggerpractice.ui.auth.AuthResource;

public class SessionManager {

    private static final String TAG = "SessionManager";

    private MediatorLiveData<AuthResource<User>> cachedUser = new MediatorLiveData<>();

    public void authenticateWithId(final LiveData<AuthResource<User>> source) {
        if (cachedUser != null) {
            cachedUser.setValue(AuthResource.loading(null));
            cachedUser.addSource(source, userAuthResource -> {
                cachedUser.setValue(userAuthResource);
                cachedUser.removeSource(source);
            });
        } else {
            Log.d(TAG, "authenticateWithId: previous session detected. Retrieving user from cache");
        }
    }

    public void logout() {
        Log.d(TAG, "logout: Logging out");
        cachedUser.setValue(AuthResource.logout());
    }

    public LiveData<AuthResource<User>> getAuthUser() {
        return cachedUser;
    }

}
