package com.naukri.androidmvpsample;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationPresenterTest {
    @Mock
    private RegistrationView registrationView;
    @Mock
    private RegistrationServiceManager registrationServiceManager;
    private RegistrationPresenter registrationPresenter;

    @Before
    public void setUp() throws Exception {
        registrationPresenter = new RegistrationPresenter(registrationView, registrationServiceManager);
    }

    @Test
    public void shouldShowErrorMessageWhenEmailIsInvalid() {
        String email = "abc";
        String password = "123";
        registrationPresenter.onRegistrationClicked(email, password);
        verify(registrationView).showEmailError(R.string.email_error);
    }

    @Test
    public void shouldShowErrorMessageWhenPasswordIsEmpty() {
        String email = "abc@naukri.com";
        String password = "";
        registrationPresenter.onRegistrationClicked(email, password);
        verify(registrationView).showPasswordError(R.string.password_error);
    }

    @Test
    public void shouldLaunchMainActivityWhenEmailAndPasswordAreValid() {
        String email = "abc@naukri.com";
        String password = "123";
        when(registrationServiceManager.register(email, password)).thenReturn(true);
        registrationPresenter.onRegistrationClicked(email, password);
        verify(registrationView).startMainActivity();
    }

    @Test
    public void shouldShowErrorWhenServiceFails() {
        String email = "abc@naukri.com";
        String password = "123";
        when(registrationServiceManager.register(email, password)).thenReturn(false);
        registrationPresenter.onRegistrationClicked(email, password);
        verify(registrationView).showError(R.string.registration_failed);
    }

}