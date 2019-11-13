package org.blazekill.daggerpractice.ui.main;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import org.blazekill.daggerpractice.BaseActivity;
import org.blazekill.daggerpractice.R;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "Main Activity", Toast.LENGTH_SHORT).show();
    }

}
