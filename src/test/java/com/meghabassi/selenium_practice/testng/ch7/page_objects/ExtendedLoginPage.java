package com.meghabassi.selenium_practice.testng.ch7.page_objects;

import org.openqa.selenium.By;

public class ExtendedLoginPage extends ExtendedBasePage{

    By usernameInput=By.id("username");
    By passwordInput= By.id("password");

    By submitBtn=By.cssSelector("button");
    By successBox=By.id("success");
    String URL="https://bonigarcia.dev/selenium-webdriver-java/login-form.html";
    public ExtendedLoginPage(String browser){
        super(browser);
        visit(URL);
    }

    public void with(String username,String password){
        type(usernameInput,username);
        type(passwordInput,password);
        click(submitBtn);
    }

    public boolean  isDisplaySuccess(){
        return isDisplayed(successBox);
    }
}
