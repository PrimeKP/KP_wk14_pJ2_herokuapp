package com.herokuapp.theinternet.utility;

import com.google.common.base.Function;
import com.herokuapp.theinternet.browserfactory.ManageBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class Utility extends ManageBrowser {


    /**
     * this method will click on element
     */

    public void clickOnElement(By by) {
        WebElement loginLink = driver.findElement(by);
        loginLink.click();
    }

    public void sendTextToElement(By by, String text) {
        WebElement emailField = driver.findElement(by);
        emailField.sendKeys(text);
    }

    // this method will be used to get text from element
    public String getTextFromElement(By by) {
        WebElement actualTextMessageElement = driver.findElement(by);

        //will bring text from method xpath string
        return actualTextMessageElement.getText();
    }

    //this method clears content in box
    public void clearContent(By by) {
        driver.findElement(by).clear();
    }

    //==============================Alert methods===================================
    //by creating this method you can call method
    public void switchToAlert() {
        driver.switchTo().alert();
    }

    //this method will accept alert message
    public void acceptAlertMessage() {
        driver.switchTo().alert().accept();
    }

    //this method will reject alert message
    public void dismissAlertMessage() {
        driver.switchTo().alert().dismiss();
    }

    //this method will get text from alert message
    public String getTextFromAlertMessage() {
        return driver.switchTo().alert().getText();
    }

    //this method send text to alert message
    public void sendTextToAlertMessage(String text) {
        driver.switchTo().alert().sendKeys(text);
    }

    //================================select class methods===============================

    //this method will select from dropdown by visible text
    public void selectByVisibleTextFromDropDown(By by, String text) {
        WebElement dropDwon = driver.findElement(by);

        //select class object creation
        Select select = new Select(dropDwon);

        //select by value
        select.selectByVisibleText(text);

    }

    // This method will select the option by value in DOM options

    public void selectByValueFromDropDown(By by, String value) {
        WebElement dropDwon = driver.findElement(by);

        //select class object creation
        Select select = new Select(dropDwon);

        //select by value
        select.selectByValue(value);

    }

    // This method will select the option by index in DOM option
    public void selectByIndexDropDown(By by, int index) {
        WebElement dropDwon = driver.findElement(by);

        //select class object creation
        Select select = new Select(dropDwon);

        //select by index value
        select.selectByIndex(index);

    }

    //above method for finding option by index [second option]
    public void selectByIndexDropDownOpTwo(By by, int index) {

        new Select(driver.findElement(by)).selectByIndex(index);

    }


    // This method will select the option by contains text
    public void selectByContainTextDropDown(By by, String parameter) {
        WebElement dropDown = driver.findElement(by);

        //select class object creation
        Select select = new Select(dropDown);

        //select by contain value
        List<WebElement> list = select.getOptions();

        for (WebElement element : list) {
            System.out.println(element.getText());
            if (element.getText().contains(parameter)) { //here inplace of equals we can use contains
                element.click();
                break;
            }
        }

    }

    //option 2 for above list mehtod [This method will select the option by contains text]
    public void selectByContainTextFromDropDown(By by, String text) {
        List<WebElement> allOptions = new Select(driver.findElement(by)).getOptions();
        for (WebElement options : allOptions) {
            if (options.getText().contains(text)) {
                options.click();
            }
        }
    }
//================================Action Methods [Mouse Methods]===========================================//

    //this method will hover on menu and click on sub menu in dropdown
    public void hoverAndClick(String menu, String subMenu) {
        Actions actions = new Actions(driver);
        WebElement clickMenu = driver.findElement(By.linkText(menu));
        WebElement clickSubMenu = driver.findElement(By.linkText(menu));
        actions.moveToElement(clickMenu).moveToElement(clickSubMenu).click().build().perform();
    }

    //this method hover on elements
    public void mouseHoverOnElement(By by) {
        Actions actions = new Actions(driver);
        WebElement anyElement = driver.findElement(by);
        actions.moveToElement(anyElement).build().perform();
    }

    //this method click on elements
    public void mouseHoverAndClickOnElement(By by) {
        Actions actions = new Actions(driver);
        WebElement anyElement = driver.findElement(by);
        actions.moveToElement(anyElement).click().build().perform();
    }

    //====================================Window Handle Method==================================//

    //this method will close all windows

    public void closeAllWindows(List<String> hList, String parentWindow) {
        for (String str : hList) {
            if (!str.equals(parentWindow)) {
                driver.switchTo().window(str).close();
            }
        }

    }

    //this method will switch to parent window

    public void switchToParentWindow(String parentWindowID) {
        driver.switchTo().window(parentWindowID);
    }

    //this method will find that we switch to right window

    public boolean switchToRightWindow(String windowTitle, List<String> hList) {
        for (String str : hList) {
            String title = driver.switchTo().window(str).getTitle();
            if (title.contains(windowTitle)) {
                System.out.println("Found the right window......");
                return true;
            }

        }
        return false;
    }

    //===================================Wait Methods===========================================//

    //This method will use to wait until  VisibilityOfElementLocated
    public WebElement waitUntilVisibilityOfElementLocated(By by, int time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement waitForElementWithFluentWait(By by, int time, int pollingTime) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(time))
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .ignoring(NoSuchElementException.class);

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(by);
            }
        });
        return element;
    }


    //===================================My Other methods========================================
    //this is Assert method which compares two text
//    public void assertAssert(String message, String expected, String actual) {
//        Assert.assertEquals("", expected, actual);
//   }


}
