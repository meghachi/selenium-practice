package com.meghabassi.selenium_practice.testng.ch3_locator;
import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ByTagTest {

    static final Logger log =getLogger(lookup().lookupClass());

    WebDriver driver;

    @BeforeMethod
    public void setup(){
        FirefoxOptions options=new FirefoxOptions();
        //old way to do is options.setHeadless(true) which is not there any longer after Selenium 4.6
        options.addArguments("--headless");

        //if safari is available use safari to run tests
        //else use Chrome to run tests
        driver= new FirefoxDriver(options);

    }

    //Close all windows and quit browser
    @AfterMethod
    public void tearDown(){
        driver.quit();
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
