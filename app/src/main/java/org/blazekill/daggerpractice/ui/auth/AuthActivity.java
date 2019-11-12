package org.blazekill.daggerpractice.ui.auth;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.RequestManager;

import org.blazekill.daggerpractice.R;
import org.blazekill.daggerpractice.di.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity {

    private static final String TAG = "AuthActivity";

    private AuthViewModel viewModel;

    private EditText userId;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    Drawable logo;

    // Glide instance
    @Inject
    RequestManager requestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        userId = findViewById(R.id.user_id_input);

        findViewById(R.id.login_button).setOnClickListener(v -> {
            attemptLogin();
        });

        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel.class);

        setLogo();

        subscribeObservers();
    }

    private void subscribeObservers() {
        viewModel.observeUser().observe(this, user -> {
            if (user != null) {
                Log.d(TAG, "subscribeObservers: " + user.getEmail());
            }
        });
    }

    private void attemptLogin() {
        if (TextUtils.isEmpty(userId.getText().toString())) {
            return;
        }
        viewModel.authenticateWithId(Integer.parseInt(userId.getText().toString()));
    }

    private void setLogo() {
        requestManager
                .load(logo)
                .into((ImageView) findViewById(R.id.login_logo));
    }

}
