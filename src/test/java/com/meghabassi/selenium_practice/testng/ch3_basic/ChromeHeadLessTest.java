package com.meghabassi.selenium_practice.testng.ch3_basic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.safari.SafariOptions;
import org.slf4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

public class ChromeHeadLessTest {

    static final Logger log =getLogger(lookup().lookupClass());

    WebDriver driver;

    @BeforeMethod
    public void setup(){
        ChromeOptions options=new ChromeOptions();
        //old way to do is options.setHeadless(true) which is not there any longer after Selenium 4.6
        //Both --headles=new and --headless works for chrome
        options.addArguments("--headless=new");
        //if safari is available use safari to run tests
        //else use Chrome to run tests
        //we use a ChromeOptions/SafariOptions object as the capabilities argument (using the method oneOf()).
        driver= RemoteWebDriver.builder().oneOf(new SafariOptions())
                .addAlternative(options).build();
    }

    //Close all windows and quit browser
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void testSessionId(){
        String sutURL = "https://meghachi.github.io/selenium-practice-site/";
        //load the webpage in current browser
        driver.get(sutURL);
        SessionId sessionId= ((RemoteWebDriver) driver).getSessionId();
        log.debug("The sessionId is {}",sessionId.toString());
        assertThat(sessionId).isNotNull();

    }
}
