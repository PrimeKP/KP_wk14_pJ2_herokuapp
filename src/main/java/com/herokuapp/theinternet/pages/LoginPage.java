package com.herokuapp.theinternet.pages;

import com.herokuapp.theinternet.utility.Utility;
import org.openqa.selenium.By;

public class LoginPage extends Utility {

    By userNameLink = By.id("username");
    By passwordLink = By.id("password");
    By loginButton = By.xpath("//button[@type='submit']");
    By loginSecureText = By.xpath("//h2[text()=' Secure Area']");

    By inValidCredentialMessage = By.xpath("//div[@id='flash-messages']");


    public void enterUserName(String userNameText){
        sendTextToElement(userNameLink, userNameText);
    }

    public void enterPassword(String passwordText){
        sendTextToElement(passwordLink, passwordText);
    }

    public void clickOnLoginButton(){
        clickOnElement(loginButton);
    }

    public String loginSucessfulText(){
        return getTextFromElement(loginSecureText);
    }

    public String inValidUserAndPasswordMessage(){
        return getTextFromElement(inValidCredentialMessage).substring(0,25);
    }

}
