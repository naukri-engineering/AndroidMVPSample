package com.naukri.androidmvpsample;

public interface RegistrationView {
    void showEmailError(int resId);

    void showPasswordError(int resId);

    void startMainActivity();

    void showError(int resId);
}
