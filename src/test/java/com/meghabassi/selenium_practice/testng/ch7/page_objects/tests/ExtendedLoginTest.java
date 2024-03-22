package com.meghabassi.selenium_practice.testng.ch7.page_objects.tests;

import com.meghabassi.selenium_practice.testng.ch7.page_objects.ExtendedLoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ExtendedLoginTest {

    ExtendedLoginPage extendedLoginPage;
    @BeforeMethod
    public void setup(){
        extendedLoginPage=new ExtendedLoginPage("chrome");

    }

    @Test
    public void testSuccessfulLogin(){
        extendedLoginPage.with("user","user");
        assertThat(extendedLoginPage.isDisplaySuccess()).isTrue();
    }

    @Test
    public void testFailedLogin(){
        extendedLoginPage.with("user","bad-password");
        assertThat(extendedLoginPage.isDisplaySuccess()).isFalse();
    }

    @AfterMethod
    public void quit(){
        extendedLoginPage.quit();
    }
}
