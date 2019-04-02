package etc.qa.at.utils;

import com.google.inject.Singleton;
import etc.qa.at.config.ConfigReader;
import etc.qa.at.report.ReportUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

@Singleton
public class CommonUtils {

    static Logger logger = LogManager.getLogger(CommonUtils.class);

    /**
     * This method is to wait for a webelement to be visible and click on it
     *
     * @param driver
     * @param timeout
     * @param element
     */
    public void waitForVisibleAndClick(WebDriver driver, int timeout, WebElement element) {
        waitForVisible(driver, timeout, element);
        element.click();
    }

    /**
     * This method is  to wait for a webelement by locators to be visible and click on it
     *
     * @param driver
     * @param timeout
     * @param xpathOfElement
     */
    public void waitForVisibleAndClick(WebDriver driver, int timeout, By xpathOfElement) {
        waitForVisible(driver, timeout, driver.findElement(xpathOfElement));
        driver.findElement(xpathOfElement).click();
    }

    /**
     * This method is to wait for an element to be clickable and click when enabled
     *
     * @param driver
     * @param timeout
     * @param element
     */
    public void waitForEnableAndClick(WebDriver driver, int timeout, WebElement element) {
        waitForEnable(driver, timeout, element);
        element.click();
    }

    /**
     * This method is to wait for a webelement to be visible on screen
     *
     * @param driver
     * @param timeout
     * @param element
     */
    public void waitForVisible(WebDriver driver, int timeout, WebElement element) {
        logger.info("Waiting maximum of " + timeout + " seconds for visibility of element" + ReportUtil.formatWebelementMessage(element.toString()) + "");
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * This method is  to wait for a webelement to be enabled and clickable on screen
     *
     * @param driver
     * @param timeout
     * @param element
     */
    public void waitForEnable(WebDriver driver, int timeout, WebElement element) {
        logger.info("Waiting maximum of " + timeout + " seconds for element to be enabled" + ReportUtil.formatWebelementMessage(element.toString()) + "");
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * This method is to mouse hover over a webelement
     *
     * @param element
     * @param driver
     */
    public void moveToElement(WebElement element, WebDriver driver) {
        waitForVisible(driver, getTimeoutValue(), element);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    /**
     * This method is to get the timeout value to be waited while finding a webelement
     *
     * @return
     */
    public static int getTimeoutValue() {
        return Integer.parseInt(ConfigReader.getProperty("timeout"));
    }

    /**
     * This method is to scroll to the view of given webelement
     *
     * @param driver
     * @param element
     */
    public void scrollToWebelement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    /**
     * This method is to retrieve the availability of given webelement on screen
     *
     * @param element
     * @param driver
     * @return
     */
    public boolean isElementPresent(By element, WebDriver driver) {
        List<WebElement> elementList = driver.findElements(element);
        return elementList.size() > 0;
    }

    /**
     * This method is to hard wait for a given time value
     *
     * @param timeout
     */
    public static void hardWait(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            logger.error("Exception in thread main during hard wait");
        }
    }

    /**
     * This method is to wait for a webelement to be visible on screen and set a given value to the webelement field
     *
     * @param element
     * @param value
     * @param driver
     */
    public void setValueToWebelement(WebElement element, String value, WebDriver driver) {
        waitForVisible(driver, getTimeoutValue(), element);
        element.sendKeys(value);
        logger.debug("Setting value of " + element.getAttribute("id") + " to : " + value);
        ReportUtil.logInfo(driver, "Setting value of " + element.getAttribute("id") + " to : " + value, false);
    }

    /**
     * This method is to switch the focus of webdriver to a given webelement frame
     *
     * @param frameElement
     * @param driver
     */
    public void switchToFrame(WebElement frameElement, WebDriver driver) {
        driver.switchTo().frame(frameElement);
    }

    /**
     * This method is to scroll to the view of given webelement and click on it
     *
     * @param element
     * @param driver
     */
    public void scrollToViewAndClick(WebElement element, WebDriver driver) {
        waitForVisible(driver, getTimeoutValue(), element);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }
}
