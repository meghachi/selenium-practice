package com.meghabassi.selenium_practice.testng.ch7.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.slf4j.LoggerFactory.getLogger;
import static java.lang.invoke.MethodHandles.lookup;

public class BasePage {

    static final Logger log= (Logger) getLogger(lookup().lookupClass());
    WebDriver driver;
    WebDriverWait wait;
    int timeoutSec=20;

    public BasePage(WebDriver driver){
        this.driver=driver;
        wait=new WebDriverWait(driver, Duration.ofSeconds(timeoutSec));
    }

    public void setTimeoutSec(int timeoutSec){
        this.timeoutSec=timeoutSec;
    }

    public void visit(String url){
        driver.get(url);
    }

    public WebElement find(By element){
        return driver.findElement(element);
    }

    public void click(By element){
        find(element).click();
    }

    public void type(By element,String text){
        find(element).sendKeys(text);
    }

    public boolean isDisplayed(By locator){
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }catch(TimeoutException e){
            log.warn("Timeout of {} wait for {}",timeoutSec,locator);
            return false;
        }
        return true;
    }


}
