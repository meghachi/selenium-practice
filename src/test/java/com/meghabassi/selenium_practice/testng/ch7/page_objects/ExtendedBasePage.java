package com.meghabassi.selenium_practice.testng.ch7.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;

import java.time.Duration;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class ExtendedBasePage {

    static final Logger log= getLogger(lookup().lookupClass());
    WebDriver driver;
    WebDriverWait wait;

    public void setTimeoutInSec(int timeoutInSec) {
        this.timeoutInSec = timeoutInSec;
    }

    int timeoutInSec=20;
    public ExtendedBasePage(String browser){
        switch(browser){
            case "chrome": driver=new ChromeDriver();
                            break;
            case "edge": driver=new EdgeDriver();
                            break;
            default: driver=new FirefoxDriver();
                            break;
        }

        wait=new WebDriverWait(driver, Duration.ofSeconds(timeoutInSec));

    }

    public void visit(String url){
        driver.get(url);
    }

    public void quit(){
        if(driver !=null)
            driver.quit();
    }

    public WebElement  find(By element){
        return driver.findElement(element);
    }


    public void click(By element){
        find(element).click();
    }

    public void click(WebElement element){
        element.click();
    }

    public void type(WebElement element, String text){
        element.sendKeys(text);
    }
    public void type(By element, String text){
        find(element).sendKeys(text);
    }

    public boolean isDisplayed(ExpectedCondition<?> expectedCondition){
        try{
            wait.until(expectedCondition);
            return true;

        }catch(TimeoutException exception){
            log.warn("Timeout of {} wait for element ", timeoutInSec);
            return false;
        }
    }
    public boolean isDisplayed(By locator){
        return isDisplayed(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public boolean isDisplayed(WebElement element){
        return isDisplayed(ExpectedConditions.visibilityOf(element));
    }
}
