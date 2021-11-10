package com.wf.lifecycle.databinding.basketballScore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.wf.lifecycle.R;
import com.wf.lifecycle.databinding.ActivityBasketballScoreBinding;

/**
 * @author wf
 */
public class BasketballScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basketball_score);
        ActivityBasketballScoreBinding basketballScoreBinding = DataBindingUtil.setContentView(this, R.layout.activity_basketball_score);
        MyBasketBallViewModel myBasketBallViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(MyBasketBallViewModel.class);
        basketballScoreBinding.setBasketScore(myBasketBallViewModel);
        basketballScoreBinding.setLifecycleOwner(this);
    }
}