package com.naukri.androidmvpsample;

public class RegistrationPresenter {
    private final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private RegistrationView registrationView;
    private RegistrationServiceManager registrationServiceManager;

    public RegistrationPresenter(RegistrationView registrationView, RegistrationServiceManager registrationServiceManager) {
        this.registrationView = registrationView;
        this.registrationServiceManager = registrationServiceManager;
    }

    public void onRegistrationClicked(String email, String password) {
        if (email.isEmpty()) {
            registrationView.showEmailError(R.string.email_error);
            return;
        }

        if (!email.matches(emailPattern)) {
            registrationView.showEmailError(R.string.email_error);
            return;
        }

        if (password.isEmpty()) {
            registrationView.showPasswordError(R.string.password_error);
            return;
        }

        boolean registrationSuccess = registrationServiceManager.register(email, password);
        if (registrationSuccess) {
            registrationView.startMainActivity();
            return;
        }
        registrationView.showError(R.string.registration_failed);
    }
}
