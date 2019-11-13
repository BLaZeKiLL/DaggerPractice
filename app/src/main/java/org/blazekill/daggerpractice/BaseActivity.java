package org.blazekill.daggerpractice;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import org.blazekill.daggerpractice.ui.auth.AuthActivity;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    private static final String TAG = "BaseActivity";

    @Inject
    public SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscribeObservers();
    }

    private void subscribeObservers() {
        sessionManager.getAuthUser()
            .observe(this, userAuthResource -> {
                if (userAuthResource != null) {
                    switch (userAuthResource.status) {
                        case AUTHENTICATED:
                            Log.d(TAG, "subscribeObservers: LOGIN SUCCESS: " + userAuthResource.data.getEmail());
                            break;
                        case ERROR:
                            Log.e(TAG, "subscribeObservers: " + userAuthResource.message);
                            break;
                        case LOADING:
                            break;
                        case NOT_AUTHENTICATED:
                            navLoginScreen();
                            break;
                    }
                }
            });
    }

    private void navLoginScreen() {
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
    }

}
