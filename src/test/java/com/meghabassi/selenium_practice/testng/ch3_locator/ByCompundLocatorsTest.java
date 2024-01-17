package com.meghabassi.selenium_practice.testng.ch3_locator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.pagefactory.ByAll;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ByCompundLocatorsTest {
    public WebDriver driver;

    @BeforeMethod
    public void setup(){
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--headless=true");
        driver=new ChromeDriver(options);

    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }

    @Test
    public void testByNameOrId(){
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        WebElement element= driver.findElement(new ByIdOrName("my-file"));
        assertThat(element.getAttribute("id")).isBlank();
        assertThat(element.getAttribute("name")).isNotBlank();

    }


    @Test
    public void testByChained(){
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        // <form method="get" action="submitted-form.html">
        //        <div class="row">
        List<WebElement> elementList= driver.findElements(new ByChained(By.tagName("form"),By.className("row")));
        assertThat(elementList.size()).isEqualTo(1);
        assertThat(elementList.get(0).getAttribute("name")).isBlank();

    }
    @Test
    public void testByAll(){
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        // <form method="get" action="submitted-form.html">
        //        <div class="row">
        List<WebElement> elementList= driver.findElements(new ByAll(By.tagName("form"),By.className("row")));

        //    We find five elements, since the locator matches a <form> element
        //    plus four <div class="row"> available on the page.
        assertThat(elementList.size()).isEqualTo(5);

        for(WebElement el:elementList){
            System.out.println(el.getTagName());
            System.out.println(el.getAttribute("class"));
        }
    }

}
