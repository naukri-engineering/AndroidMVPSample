package com.naukri.androidmvpsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationInMVP extends AppCompatActivity implements RegistrationView {
    private EditText email;
    private EditText password;
    private RegistrationPresenter registrationPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_in_mvp);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        registrationPresenter = new RegistrationPresenter(this, new RegistrationServiceManager());
    }

    public void onRegisterClicked(View view) {
        String email = this.email.getText().toString();
        String password = this.password.getText().toString();
        registrationPresenter.onRegistrationClicked(email, password);
    }

    @Override
    public void showEmailError(int resId) {
        email.setError(getString(resId));
    }

    @Override
    public void showPasswordError(int resId) {
        password.setError(getString(resId));
    }

    @Override
    public void startMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void showError(int resId) {
        Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show();
    }
}
