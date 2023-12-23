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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HelloWorldFirefoxNGTest {

    static final Logger log = getLogger(lookup().lookupClass());

    // interface WebDriver. We use this variable in tests to control web browsers with Selenium WebDriver.
    WebDriver driver;

    //Perform the setup for webdriver once for webdriver manager to manage the driver 
    @BeforeClass
    public void setupClass() {
    	Optional<Path> browserPath = WebDriverManager.firefoxdriver()
                .getBrowserPath();
    	//Verify that browser path is indeed present.
        assumeThat(browserPath).isPresent();
        WebDriverManager.firefoxdriver().setup();
    }

    //before every test instantiate driver to be Firefox driver type
    @BeforeMethod
    public void setup() {
        driver = new FirefoxDriver();
    }
    
    //after every test we need to close the browser
    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test
    public void test() {
 
        String sutUrl = "https://meghachi.github.io/selenium-practice-site/";
        // Open the practice website in browser using webdriver.get(url) method
        driver.get(sutUrl);
        
        //use the driver to get webpage title 
        String title = driver.getTitle();
        
        //Log title using DEBUG level
        log.debug("The title of {} is {}", sutUrl, title);

        // Verify using AssertJ
        assertThat(title).isEqualTo("Hands-On Selenium WebDriver with Java- Practice");
    }

}
