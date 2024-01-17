package com.meghabassi.selenium_practice.testng.ch3_locator;
import static java.lang.invoke.MethodHandles.lookup;

import org.openqa.selenium.By;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.locators.RelativeLocator.RelativeBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.assertj.core.api.Assertions.assertThat;
import org.slf4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.slf4j.LoggerFactory.getLogger;

public class ByRelativeLocatorsTest {
    public WebDriver driver;
    public static final Logger log=getLogger(lookup().lookupClass());

    @BeforeMethod
    public void startUp(){
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--headless");
        driver=new ChromeDriver(options);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void testRelativeLocator(){
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        //find following link
        //<a href="./index.html">Return to index </a>
        WebElement element= driver.findElement(By.linkText("Return to index"));
        //find element with tagName input that is located relative to link
        //<input class="form-control" type="text" name="my-readonly" value="Readonly input" readonly="">
        RelativeBy relativeBy = RelativeLocator.with(By.tagName("input"));
        WebElement inputElement= driver.findElement(relativeBy.above(element));
        String expectedVal="my-readonly";
        String actualValue=inputElement.getAttribute("name");
        assertThat(actualValue).isEqualTo(expectedVal);
        log.debug("Expected Value of name is {} and actual value of name is {} ",expectedVal,actualValue);
    }
}
