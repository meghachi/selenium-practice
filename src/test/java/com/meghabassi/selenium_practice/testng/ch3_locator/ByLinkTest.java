package com.meghabassi.selenium_practice.testng.ch3_locator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class ByLinkTest {
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
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void testByLink(){
        String url="https://bonigarcia.dev/selenium-webdriver-java/web-form.html";
        driver.get(url);
        //test for this <a href="./index.html">Return to index</a>
        WebElement link= driver.findElement(By.linkText("Return to index"));
        String expectedHrefVal="https://bonigarcia.dev/selenium-webdriver-java"+"/index.html";
        String actualHrefVal=link.getAttribute("href");
        validate("href",expectedHrefVal,actualHrefVal);
        validate("tag","a",link.getTagName());
        validate("css value of cursor ","pointer",link.getCssValue("cursor"));

        WebElement partialLink=driver.findElement(By.partialLinkText("index"));
        assertThat(link.getLocation()).isEqualTo(partialLink.getLocation());
        assertThat(link.getRect()).isEqualTo(partialLink.getRect());

    }

    public void validate(String text,String actualVal,String expectedVal){
        assertThat(actualVal).isEqualTo(expectedVal);
        log.debug("The expected {} value is {} and actual href val is {} ",text,expectedVal,actualVal);
    }
}
