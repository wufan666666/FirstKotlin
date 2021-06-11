package com.wufanfirstkotlin.mvp;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.wufanfirstkotlin.R;
import com.wufanfirstkotlin.mvp.model.User;
import com.wufanfirstkotlin.mvp.presenter.Ipresenter;
import com.wufanfirstkotlin.mvp.presenter.MainPresenter;


/**
 * @author wf
 */
public class LoginMVPActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher, Iview {
    private Ipresenter mainPresenter;

    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton;
    ProgressBar loadingProgressBar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_login);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        loadingProgressBar = findViewById(R.id.loading);

        loginButton.setOnClickListener(this);

        usernameEditText.addTextChangedListener(this);
        passwordEditText.addTextChangedListener(this);
        mainPresenter = new MainPresenter(this);
    }

    @Override
    public void onClick(View v) {
        loadingProgressBar.setVisibility(View.VISIBLE);
        mainPresenter.login(usernameEditText.getText().toString(), passwordEditText.getText().toString());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        loginButton.setEnabled(usernameEditText.getText().length() != 0 && passwordEditText.getText().length() != 0);
    }

    @Override
    public void showProgress() {
        loadingProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        loadingProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgressFailure(String msg) {
        Toast.makeText(this,"账号密码错误",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressSuccess(User user) {
        Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
    }
}