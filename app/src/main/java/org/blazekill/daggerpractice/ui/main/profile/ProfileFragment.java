package org.blazekill.daggerpractice.ui.main.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import org.blazekill.daggerpractice.R;
import org.blazekill.daggerpractice.di.viewmodel.ViewModelProviderFactory;
import org.blazekill.daggerpractice.models.User;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ProfileFragment extends DaggerFragment {

    private static final String TAG = "ProfileFragment";

    private ProfileViewModel viewModel;
    private TextView email, username, website;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this, providerFactory).get(ProfileViewModel.class);
        email = view.findViewById(R.id.email);
        username = view.findViewById(R.id.username);
        website = view.findViewById(R.id.website);
        subscribeObservers();
    }

    private void subscribeObservers() {
        viewModel.getAuthenticatedUser().removeObservers(getViewLifecycleOwner());
        viewModel.getAuthenticatedUser().observe(getViewLifecycleOwner(), userAuthResource -> {
            if (userAuthResource != null) {
                switch (userAuthResource.status) {
                    case ERROR: {
                        setErrorDetails(userAuthResource.message);
                        break;
                    }
                    case AUTHENTICATED: {
                        assert userAuthResource.data != null;
                        setUserDetails(userAuthResource.data);
                        break;
                    }
                }
            }
        });
    }

    private void setErrorDetails(String message) {
        email.setText(message);
        username.setText("error");
        website.setText("error");
    }

    private void setUserDetails(User data) {
        email.setText(data.getEmail());
        username.setText(data.getUsername());
        website.setText(data.getWebsite());
    }

}
