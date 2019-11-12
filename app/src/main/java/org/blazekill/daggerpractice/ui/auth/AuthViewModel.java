package org.blazekill.daggerpractice.ui.auth;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import org.blazekill.daggerpractice.network.auth.AuthApi;

import javax.inject.Inject;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";

    private final AuthApi authApi;

    @Inject
    public AuthViewModel(AuthApi authApi) {
        this.authApi = authApi;
        Log.d(TAG, "AuthViewModel: view model is working");

        if (this.authApi == null) {
            Log.d(TAG, "AuthViewModel: auth api is NULL");
        } else {
            Log.d(TAG, "AuthViewModel: auth api in NOT NULL");
        }
    }
}
