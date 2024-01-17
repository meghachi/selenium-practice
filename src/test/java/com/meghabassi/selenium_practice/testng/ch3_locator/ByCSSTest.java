package com.meghabassi.selenium_practice.testng.ch3_locator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ByCSSTest {
    private WebDriver driver;

    @BeforeMethod
    public void setup(){
        FirefoxOptions options=new FirefoxOptions();
        options.addArguments("--headless");
        driver= new FirefoxDriver(options);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void testByCSSHidden(){

       driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
       //<input type="hidden" name="my-hidden">
       WebElement el= driver.findElement(By.cssSelector("input[type=hidden]"));
        String actualName=el.getAttribute("name");
        assertThat(actualName).isEqualTo("my-hidden");
        assertThat(el.isDisplayed()).isFalse();
    }

    @Test
    public void testByCSSAdvanced(){
        String url="https://bonigarcia.dev/selenium-webdriver-java/web-form.html";
        driver.get(url);
        //<input class="form-check-input" type="checkbox" name="my-check" id="my-check-1"
        //        checked>
        //<input class="form-check-input" type="checkbox" name="my-check" id="my-check-2">
        WebElement el= driver.findElement(By.cssSelector("[type=checkbox]:checked"));
        String id1=el.getAttribute("id");
        assertThat(id1).isEqualTo("my-check-1");
        WebElement el2=driver.findElement(By.cssSelector("[type=checkbox]:not(:checked)"));
       
        assertThat(el2.isSelected()).isFalse();





    }
}
