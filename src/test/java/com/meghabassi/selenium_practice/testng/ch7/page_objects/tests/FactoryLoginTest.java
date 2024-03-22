package com.meghabassi.selenium_practice.testng.ch7.page_objects.tests;

import com.meghabassi.selenium_practice.testng.ch7.page_objects.FactoryLoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FactoryLoginTest {

    FactoryLoginPage login;

    @BeforeMethod
    public void setup(){
        login= new FactoryLoginPage("chrome");
    }

    @AfterMethod
    public void tearDown(){
        login.quit();
    }
    @Test
    public void successfulLoginTest(){
        login.with("user","user");
        assertThat(login.isSuccessDisplayed()).isTrue();
    }

    @Test
    public void failureLoginTest(){
        login.with("user","badPassword");
        assertThat(login.isSuccessDisplayed()).isFalse();
    }


}
