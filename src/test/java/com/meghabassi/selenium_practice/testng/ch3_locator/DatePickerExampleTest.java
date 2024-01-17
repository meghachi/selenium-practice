package com.meghabassi.selenium_practice.testng.ch3_locator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class DatePickerExampleTest {
    public WebDriver driver;
    public static final Logger log= getLogger(lookup().lookupClass());

    @BeforeTest
    public void setup(){
        ChromeOptions options=new ChromeOptions();
        driver=new ChromeDriver(options);
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void testDatePicker(){
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        LocalDate todaysdate= LocalDate.now();
        int day=todaysdate.getDayOfMonth();
        int year=todaysdate.getYear();

        //Select the date picker
        //<input type="text" class="form-control" name="my-date">
        WebElement datePicker= driver.findElement(By.name("my-date"));
        //click on datePicker for calendar to show
        datePicker.click();

        // Click on the current month by searching by text 3
        WebElement monthElement = driver.findElement(By.xpath(
                String.format("//th[contains(text(),'%d')]", year)));
        monthElement.click();

    }
}
