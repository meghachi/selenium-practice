package com.meghabassi.selenium_practice.testng.ch6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.grid.Main;
import org.openqa.selenium.net.PortProber;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

public class GridCmdTest {
    WebDriver driver;
    static final Logger log =getLogger(lookup().lookupClass());
    URL seleniumServerUrl;

    @BeforeClass
    public void setupAll() throws MalformedURLException {
        int port = PortProber.findFreePort();
        seleniumServerUrl = new URL(
                "http://192.168.0.129:4455");
    }

    @BeforeMethod
    @Parameters("browserType")
    public void setup( @Optional("chrome") String browserType) {
        switch(browserType){
            case "chrome":
                driver = new RemoteWebDriver(seleniumServerUrl, new ChromeOptions());
                break;

            case "firefox":
                driver = new RemoteWebDriver(seleniumServerUrl, new FirefoxOptions());
                break;

            default :
                driver = new RemoteWebDriver(seleniumServerUrl, new EdgeOptions());
                break;

        }

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
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

    @Test
    public void testingTag(){
        String sutURL = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";
        //load the webpage in current browser
        driver.get(sutURL);
        // extracting attribute rows value from element with html tag textareafollowing
        //<textarea class="form-control" name="my-textarea" rows="3"></textarea>
        WebElement textarea = driver.findElement(By.tagName("textarea"));
        assertThat(textarea.getDomAttribute("rows")).isEqualTo("3");




    }

    @Test
    public void testingURL(){
        String sutURL = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";
        //load the webpage in current browser
        driver.get(sutURL);

        //using getCurrentURL function
        String testURL=driver.getCurrentUrl();
        log.debug("The expected URL is {} and the found URL is {}",sutURL,testURL);
        assertThat(sutURL).isEqualTo(testURL);


    }
}
