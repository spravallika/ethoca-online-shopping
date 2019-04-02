package etc.qa.at.ui.pages;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import etc.qa.at.exceptions.EtcRuntimeException;
import etc.qa.at.exceptions.ExceptionCause;
import etc.qa.at.exceptions.ExceptionLevel;
import etc.qa.at.report.ReportUtil;
import etc.qa.at.ui.pages.base.BasePage;
import etc.qa.at.utils.CommonUtils;
import etc.qa.at.utils.ScopedData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

@Singleton
public class QuickViewModal extends BasePage {
    @Inject
    CommonUtils commonUtils;

    @Inject
    ScopedData scopedData;

    @FindBy(name = "group_1")
    WebElement sizeOptions;

    @FindBy(id = "add_to_cart")
    WebElement addToCartButton;

    @FindBy(xpath = "//*[@title='Continue shopping']")
    WebElement continueShoppingButton;

    @FindBy(xpath = "//a[@title='View my shopping cart']")
    WebElement cartButton;

    @FindBy(xpath = "//a[@title='Check out']")
    WebElement checkoutOption;

    @FindBy(className = "fancybox-iframe")
    WebElement quickViewFrame;

    @Inject
    private QuickViewModal(@Named("WebDriver") WebDriver driver) {
        super(driver);
    }

    public void selectSizeForProduct(String size) {
        try {
            commonUtils.waitForVisible(driver, CommonUtils.getTimeoutValue(), quickViewFrame);
            commonUtils.switchToFrame(quickViewFrame, driver);
            commonUtils.waitForVisible(driver, CommonUtils.getTimeoutValue(), driver.findElement(By.xpath("//*[text()='" + scopedData.getAttributeValue("DressType") + "']")));
            Select sizeDropdown = new Select(sizeOptions);
            sizeDropdown.selectByVisibleText(size);
            logger.debug("Selected size : " + size + " for the product");
            ReportUtil.logInfo(driver, "Selected size : " + size + " for the product", false);
        } catch (Exception e) {
            throw new EtcRuntimeException(e, ExceptionLevel.MINOR, ExceptionCause.APP_RELATED, "Unable to find select Size for the product");
        }
    }

    public void addProductToCart() {
        try {
            commonUtils.waitForVisibleAndClick(driver, CommonUtils.getTimeoutValue(), addToCartButton);
            logger.debug("Added the product to cart");
            ReportUtil.logInfo(driver, "Added the product to cart", false);
        } catch (Exception e) {
            throw new EtcRuntimeException(e, ExceptionLevel.MINOR, ExceptionCause.APP_RELATED, "Unable to add product to cart");
        }
    }

    public void continueShopping() {
        try {
            commonUtils.scrollToViewAndClick(continueShoppingButton, driver);
            logger.debug("Clicked on Continue Shopping Button");
            ReportUtil.logInfo(driver, "Clicked on Continue Shopping Button", false);
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            throw new EtcRuntimeException(e, ExceptionLevel.MINOR, ExceptionCause.APP_RELATED, "Unable to find Continue shopping button");
        }
    }

    public void viewCartAndCheckout() {
        try {
            commonUtils.moveToElement(cartButton, driver);
            logger.debug("Clicked on cart to view items");
            ReportUtil.logInfo(driver, "Clicked on cart to view items", false);
            commonUtils.scrollToViewAndClick(checkoutOption, driver);
            logger.debug("Clicked on checkout option to proceed");
            ReportUtil.logInfo(driver, "Clicked on checkout option to proceed", false);
        } catch (Exception e) {
            throw new EtcRuntimeException(e, ExceptionLevel.MINOR, ExceptionCause.APP_RELATED, "Unable to view the cart to proceed to checkout");
        }
    }
}
