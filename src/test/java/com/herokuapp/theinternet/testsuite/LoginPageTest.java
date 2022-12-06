package com.herokuapp.theinternet.testsuite;

import com.herokuapp.theinternet.pages.LoginPage;
import com.herokuapp.theinternet.testbase.BaseTestClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTestClass {

    LoginPage loginPage = new LoginPage();

    @Test
    public void UserSholdLoginSuccessfullyWithValidCredentials() {

        loginPage.enterUserName("tomsmith");
        loginPage.enterPassword("SuperSecretPassword!");
        loginPage.clickOnLoginButton();

        String expectedMessage = "Secure Area";
        Assert.assertEquals(loginPage.loginSucessfulText(), expectedMessage, "User not logged in");

    }

    @Test
    public void verifyTheUsernameErrorMessage() {

        loginPage.enterUserName("tomsmith1");
        loginPage.enterPassword("SuperSecretPassword!");
        loginPage.clickOnLoginButton();

        String expectedMessage = "Your username is invalid!";
        Assert.assertEquals(loginPage.inValidUserAndPasswordMessage(), expectedMessage, "User logged in successfully!!");

    }

    @Test
    public void verifyThePasswordErrorMessage() {

        loginPage.enterUserName("tomsmith");
        loginPage.enterPassword("SuperSecretPassword");
        loginPage.clickOnLoginButton();

        String expectedMessage = "Your password is invalid!";
        Assert.assertEquals(loginPage.inValidUserAndPasswordMessage(), expectedMessage, "User logged in successfully!!");

    }
}
