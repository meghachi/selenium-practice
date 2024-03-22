package com.meghabassi.selenium_practice.testng.ch7.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    By usernameInput=By.id("username");
    By passwordInput=By.id("password");
    By submitBtn= By.cssSelector("button");
    By successBox=By.id("success");


    String URL="https://bonigarcia.dev/selenium-webdriver-java/login-form.html";
    public LoginPage(WebDriver driver){
        super(driver);
        visit(URL);
    }

    public void with(String username,String password){
        type(usernameInput,username);
        type(passwordInput,password);
        click(submitBtn);
    }

    public boolean successBoxPresent(){
        return isDisplayed(successBox);
    }
}
