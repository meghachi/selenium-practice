package com.meghabassi.selenium_practice.testng.ch7.page_objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FactoryLoginPage extends ExtendedBasePage{
    String URL="https://bonigarcia.dev/selenium-webdriver-java/login-form.html";
    public FactoryLoginPage(String browser){
        super(browser);
        PageFactory.initElements(driver,this);
        visit(URL);
    }

    public FactoryLoginPage(String browser,int timeoutSec){
        this(browser);
        setTimeoutInSec(timeoutSec);
    }

    @FindBy(id="username")
    @CacheLookup
    WebElement usernameInput;

    @FindBy(id="password")
    @CacheLookup
    WebElement passwordInput;

    @FindBy(css="button")
    @CacheLookup
    WebElement submitBtn;

    @FindBy(id="success")
    @CacheLookup
    WebElement successBox;

    public void with(String username,String password){
        type(usernameInput,username);
        type(passwordInput,password);
        click(submitBtn);
    }

    public boolean isSuccessDisplayed(){
        return isDisplayed(successBox);
    }





}
