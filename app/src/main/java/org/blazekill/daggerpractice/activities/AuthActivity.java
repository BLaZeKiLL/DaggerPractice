package org.blazekill.daggerpractice.activities;

import android.os.Bundle;
import android.util.Log;

import org.blazekill.daggerpractice.BaseApplication;
import org.blazekill.daggerpractice.R;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity {

    private static final String TAG = "AuthActivity";

    @Inject
    @Named("BaseApp")
    boolean baseAppStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        Log.d(TAG, "onCreate: Base app status: " + baseAppStatus);
    }

}
