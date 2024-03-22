package com.meghabassi.selenium_practice.testng.ch7.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasicLoginPage {

    WebDriver driver;
    By usernameInput=By.id("username");
    By passwordInput=By.id("password");
    By submitBtn= By.cssSelector("button");
    By successBox= By.id("success");
    String  URL="https://bonigarcia.dev/selenium-webdriver-java/login-form.html";
    public BasicLoginPage(WebDriver driver){
        this.driver =driver;
        driver.get( URL);

    }

    public void with(String username,String password){
        driver.findElement(usernameInput).sendKeys(username);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(submitBtn).click();
    }

    public boolean successBoxPresent(){
        return driver.findElement(successBox).isDisplayed();
    }


}
