package com.wufanfirstkotlin.customView;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;

import com.wufanfirstkotlin.R;

/**
 * @author C3415551
 */
public class CustomActivity extends AppCompatActivity implements View.OnClickListener {

    private Button title_text;
    private QQStepView mQqStepView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        title_text = findViewById(R.id.text_title);
        mQqStepView = findViewById(R.id.step_view);

        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 60);
        valueAnimator.setDuration(600);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                mQqStepView.setStepMax((int) animatedValue);
            }
        });
        valueAnimator.start();

        title_text.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent= new Intent();
        intent.setAction("com.wf.action.sa");
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}