package com.meghabassi.selenium_practice.testng.ch6;


import static org.assertj.core.api.Assertions.assertThat;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.grid.Main;
import org.openqa.selenium.net.PortProber;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



    public class StandaloneRemoteNGTest {

        WebDriver driver;

        URL seleniumServerUrl;

        @BeforeClass
        public void setupAll() throws MalformedURLException {
            int port = PortProber.findFreePort();
            //WebDriverManager.chromedriver().setup();
            Main.main(
                    new String[]{"standalone", "--port", String.valueOf(port)});

            seleniumServerUrl = new URL(
                    String.format("http://localhost:%d/", port));
        }

        @BeforeMethod
        public void setup() {
            driver = new RemoteWebDriver(seleniumServerUrl, new ChromeOptions());
        }

        @AfterMethod
        public void teardown() {
            driver.quit();
        }

        @Test
        public void testStandaloneRemote() {
            driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
            assertThat(driver.getTitle()).contains("Selenium WebDriver");
        }

    }