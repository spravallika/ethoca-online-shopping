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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Singleton
public class ShoppingCartSummary extends BasePage {
    @Inject
    CommonUtils commonUtils;

    @Inject
    private ShoppingCartSummary(@Named("WebDriver") WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[text()='Proceed to checkout']")
    WebElement proceedToCheckout;

    public void clickProceedToCheckout() {
        try {
            commonUtils.scrollToViewAndClick(proceedToCheckout,driver);
            logger.debug("Clicked on Proceed to Checkout to continue with the order");
            ReportUtil.logInfo(driver,"Clicked on Proceed to Checkout to continue with the order",true);
        } catch (Exception e) {
            throw new EtcRuntimeException(e, ExceptionLevel.MINOR, ExceptionCause.APP_RELATED, "Unable to Proceed to checkout from shopping cart summary");
        }
    }
}
