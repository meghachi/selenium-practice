package com.meghabassi.selenium_practice.testng.ch7.page_objects.tests;

import com.meghabassi.selenium_practice.testng.ch7.page_objects.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicLoginTest {

    WebDriver driver;
    LoginPage login;

    @BeforeMethod
    public void setup(){
        driver=new ChromeDriver();
        login=new LoginPage(driver);
    }
    @AfterMethod
    public void teardown() {
        driver.quit();
    }
    @Test
    public void testSuccess(){
        login.with("user","user");
        assertThat(login.successBoxPresent()).isTrue();
    }

    @Test
    public void testLoginFailure(){
        login.with("user","badpassword");
        assertThat(login.successBoxPresent()).isFalse();
    }
}
