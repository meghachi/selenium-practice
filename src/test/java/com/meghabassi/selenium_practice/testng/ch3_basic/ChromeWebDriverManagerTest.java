package com.meghabassi.selenium_practice.testng.ch3_basic;
import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChromeWebDriverManagerTest {

    static final Logger log =getLogger(lookup().lookupClass());

    WebDriver driver;


    @BeforeClass
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeMethod
    void setup() {
        driver = WebDriverManager.chromedriver().create();
    }


    //Close all windows and quit browser
    @AfterMethod
    public void tearDown(){

        driver.quit();
    }
    @Test
    public void testingURL(){
        String sutURL = "https://meghachi.github.io/selenium-practice-site/";
        //load the webpage in current browser
        driver.get(sutURL);

        //using getCurrentURL function
        String testURL=driver.getCurrentUrl();
        log.debug("The expected URL is {} and the found URL is {}",sutURL,testURL);
        assertThat(sutURL).isEqualTo(testURL);



    }
    @Test
    public void testingPageSource(){
        String sutURL = "https://meghachi.github.io/selenium-practice-site/";
        //load the webpage in current browser
        driver.get(sutURL);

        String pageSource=driver.getPageSource();
        log.debug("Page source is {}",pageSource);
        assertThat(pageSource).contains("<html");

    }

    @Test
    public void testingTitle(){
        String sutURL = "https://meghachi.github.io/selenium-practice-site/";
        //load the webpage in current browser
        driver.get(sutURL);

        //get title of the webpage loaded in browser using driver
        String title=driver.getTitle();
        log.debug("Title of page is {}", title);
        assertThat(title).isEqualTo("Hands-On Selenium WebDriver with Java- Practice");
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
