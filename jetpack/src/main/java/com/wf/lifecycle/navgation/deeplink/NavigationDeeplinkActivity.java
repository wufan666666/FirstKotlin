package com.wf.lifecycle.navgation.deeplink;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.wf.lifecycle.R;

/**
 * @author wf
 */
public class NavigationDeeplinkActivity extends AppCompatActivity {

    private NavController mNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navgation);

        mNavController = Navigation.findNavController(this, R.id.fragmentContainerView);
        NavigationUI.setupActionBarWithNavController(this, mNavController);

    }

    @Override
    public boolean onSupportNavigateUp() {
        return mNavController.navigateUp();
    }
}