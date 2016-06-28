package com.naukri.androidmvpsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

// The usual Activity
public class RegistrationInMVC extends AppCompatActivity {
    private final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private EditText email;
    private EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_in_mvc);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
    }

    public void onRegisterClicked(View view) {
        String email = this.email.getText().toString();
        String password = this.password.getText().toString();

        if (email.isEmpty()) {
            this.email.setError(getString(R.string.email_error));
            return;
        }
        if (!email.matches(emailPattern)) {
            this.email.setError(getString(R.string.email_error));
            return;
        }
        if (password.isEmpty()) {
            this.password.setError(getString(R.string.password_error));
            return;
        }
        boolean registrationSuccess = new RegistrationServiceManager().register(email, password);
        if (registrationSuccess) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            Toast.makeText(this, getString(R.string.registration_failed), Toast.LENGTH_SHORT).show();
        }
    }
}
