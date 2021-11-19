package com.wf.lifecycle.navgation.simple;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.wf.lifecycle.R;

/**
 * @author wf
 */
public class NavgationActivity extends AppCompatActivity {

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