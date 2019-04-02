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

public class AgreeTermsPage extends BasePage {
    @Inject
    CommonUtils commonUtils;

    @Inject
    private AgreeTermsPage(@Named("WebDriver") WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//label[@for='cgv']")
    WebElement agreeTermsCheckbox;

    @FindBy(name = "processCarrier")
    WebElement proceedToCheckout;

    public void clickAgreeTerms() {
        try {
            commonUtils.scrollToViewAndClick(agreeTermsCheckbox, driver);
            logger.debug("Clicked on user agreement terms and conditions checkbox");
            ReportUtil.logInfo(driver, "Clicked on user agreement terms and conditions checkbox", false);
        } catch (Exception e) {
            throw new EtcRuntimeException(e, ExceptionLevel.MINOR, ExceptionCause.APP_RELATED, "Unable to find Agree terms & conditions checkbox");
        }
    }

    public void clickProceedToCheckout() {
        try {
            commonUtils.scrollToViewAndClick(proceedToCheckout, driver);
            logger.debug("Proceeding to checkout");
            ReportUtil.logInfo(driver, "Proceeding to checkout", false);
        } catch (Exception e) {
            throw new EtcRuntimeException(e, ExceptionLevel.MINOR, ExceptionCause.APP_RELATED, "Unable to find Proceed to checkout button");
        }
    }
}
