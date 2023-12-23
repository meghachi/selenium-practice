/*
 *@Author: Megha Bassi
 *Reference code from :https://github.com/bonigarcia/selenium-webdriver-java/
 *
 */
package com.meghabassi.selenium_practice.testng.ch2_selenium_manager;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * This class uses Firefox driver provided by Selenium so no extra dependency on Webdriver manager
 * @author megha
 *
 */
public class HelloWorldFirefoxNGTest {

    static final Logger log = getLogger(lookup().lookupClass());

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new FirefoxDriver();
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test
    public void test() {
        // Exercise
        String sutUrl = "https://meghachi.github.io/selenium-practice-site/";
        driver.get(sutUrl);
        String title = driver.getTitle();
        log.debug("The title of {} is {}", sutUrl, title);

        // Verify
        assertThat(title).isEqualTo("Hands-On Selenium WebDriver with Java- Practice");
    }

}
