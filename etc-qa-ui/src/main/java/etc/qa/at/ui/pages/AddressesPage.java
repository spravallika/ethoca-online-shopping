package etc.qa.at.ui.pages;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import etc.qa.at.exceptions.EtcRuntimeException;
import etc.qa.at.exceptions.ExceptionCause;
import etc.qa.at.exceptions.ExceptionLevel;
import etc.qa.at.report.ReportUtil;
import etc.qa.at.ui.pages.base.BasePage;
import etc.qa.at.utils.CommonUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddressesPage extends BasePage {
    @Inject
    CommonUtils commonUtils;

    @Inject
    private AddressesPage(@Named("WebDriver") WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[text()='Addresses']")
    WebElement addressesHeading;

    @FindBy(name = "processAddress")
    WebElement proceedToCheckout;

    public void clickProceedToCheckout() {
        try {
            commonUtils.waitForVisible(driver, CommonUtils.getTimeoutValue(), addressesHeading);
            commonUtils.scrollToViewAndClick(proceedToCheckout, driver);
            logger.debug("Clicked on Proceed to Checkout button");
            ReportUtil.logInfo(driver,"Clicked on Proceed to Checkout button",true);
        } catch (Exception e) {
            throw new EtcRuntimeException(e, ExceptionLevel.MINOR, ExceptionCause.APP_RELATED, "Unable to find Proceed to checkout button");
        }
    }
}
