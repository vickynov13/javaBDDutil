package org.bdd.tools.helper;

import org.bdd.tools.frameworkConfig.Base;
import org.bdd.tools.frameworkConfig.TestContext;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebHelper extends Base {
    public WebHelper(TestContext context) {
        super(context);
    }

    public void waitForLoadState() {
        wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState"));
    }
    public boolean isElementClickable(WebElement element) {
        try {
            scrollToView(element);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    public void scrollToView(WebElement element) {
            if(!isElementVisible(element)){
                JavascriptExecutor je = (JavascriptExecutor) driver;
                je.executeScript("arguments[0].scrollIntoView(); ", element);
                je.executeScript("window.scrollBy(0, -100)");
            }
    }
    public void scrollToView(By element) {
            if(!isElementVisible(driver.findElement(element))){
                JavascriptExecutor je = (JavascriptExecutor) driver;
                je.executeScript("arguments[0].scrollIntoView(); ", driver.findElement(element));
                je.executeScript("window.scrollBy(0, -100)");
            }
    }

    public boolean isElementVisible(WebElement element) {
        try {
            waitForLoadState();
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(element));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean isElementVisibleBy(By element) {
        try {
            scrollToView(element);
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public void fillText(WebElement element, String elementValue) {
            scrollToView(element);
            element.clear();
            element.sendKeys(elementValue);
    }
    public void clickBy(By element, String elementDesc) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element)).click();
    }
}


