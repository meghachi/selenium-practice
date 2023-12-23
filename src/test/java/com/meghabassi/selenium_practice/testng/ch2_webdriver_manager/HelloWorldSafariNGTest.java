/*
 *@Author: Megha Bassi
 *Reference code from :https://github.com/bonigarcia/selenium-webdriver-java/
 *
 */
package com.meghabassi.selenium_practice.testng.ch2_webdriver_manager;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;
import static org.slf4j.LoggerFactory.getLogger;

import java.nio.file.Path;
import java.util.Optional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HelloWorldSafariNGTest {

    static final Logger log = getLogger(lookup().lookupClass());

    WebDriver driver;

    @BeforeClass
    public void setupClass() {
        Optional<Path> browserPath = WebDriverManager.safaridriver()
                .getBrowserPath();
        assumeThat(browserPath).isPresent();
    }

    @BeforeMethod
    public void setup() {
        driver = new SafariDriver();
    }

    @AfterTest
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
