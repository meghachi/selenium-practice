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

import java.util.List;

public class ByHTMLAttributeTest {

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
    public void testByName(){
        String sutURL = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";
        //load the webpage in current browser
        driver.get(sutURL);
        String expectedVal="myvalue";

        //<input type="text" class="form-control" name="my-text" id="my-text-id" myprop="myvalue">
        // extracting element by attribute Name and comparing the value of attribute myprop
        WebElement textInput = driver.findElement(By.name("my-text"));
        String actualVal=textInput.getDomAttribute("myprop");
        assertThat(textInput.getDomAttribute("myprop")).isEqualTo("myvalue");
        assertThat(textInput.isEnabled()).isTrue();
        log.debug("The expected val is {} and the actual val is {}",expectedVal,actualVal);

    }

    @Test
    public void testById(){
        String sutURL = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";
        //load the webpage in current browser
        driver.get(sutURL);
        String expectedVal="myvalue";
        //<input type="text" class="form-control" name="my-text" id="my-text-id" myprop="myvalue">
        // extracting element by attribute Name and comparing the value of attribute myprop
        WebElement textInput=driver.findElement(By.id("my-text-id"));
        assertThat(textInput.getDomAttribute("myprop")).isEqualTo("myvalue");
        String actualVal=textInput.getAttribute("myprop");

        assertThat(textInput.getAttribute("myprop")).isEqualTo("myvalue");
        //This assertion (and the next two) return different values since the attribute myprop is not standard,
        // and for this reason, it is not available as a DOM property.
        assertThat(textInput.getDomProperty("myprop")).isNull();
        log.debug("The expected val is {} and the actual val is {}",expectedVal,actualVal);



    }

    @Test
    public void testByClassName(){
        String sutURL = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";
        //load the webpage in current browser
        driver.get(sutURL);
        String expectedVal="my-text";
        //<input type="text" class="form-control" name="my-text" id="my-text-id" myprop="myvalue">
        // extracting element by attribute Name and comparing the value of attribute myprop
        List<WebElement> textInputByClassNameList=driver.findElements(By.className("form-control"));
        assertThat(textInputByClassNameList.size()).isPositive();
        String actualVal=textInputByClassNameList.get(0).getAttribute("name");
      assertThat(textInputByClassNameList.get(0).getAttribute("name")).isEqualTo("my-text");
        log.debug("The expected val is {} and the actual val is {}",expectedVal,actualVal);
    }


}
