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
import net.bytebuddy.utility.RandomString;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Singleton
public class AuthenticationPage extends BasePage {
    @Inject
    CommonUtils commonUtils;

    @Inject
    private AuthenticationPage(@Named("WebDriver") WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "email_create")
    WebElement emailAddressInput;

    @FindBy(id = "SubmitCreate")
    WebElement createAccountButton;

    public void enterEmailAddress(String emailAddress) {
        try {
            commonUtils.waitForVisible(driver, CommonUtils.getTimeoutValue(), emailAddressInput);
            commonUtils.scrollToWebelement(driver, emailAddressInput);
            emailAddressInput.sendKeys(emailAddress.split("@")[0] + RandomString.make(4) + "@" + emailAddress.split("@")[1]);
            logger.debug("Entered email address " + emailAddress + " for account registration");
            ReportUtil.logInfo(driver, "Entered email address " + emailAddress + " for account registration", false);
        } catch (Exception e) {
            throw new EtcRuntimeException(e, ExceptionLevel.MINOR, ExceptionCause.APP_RELATED, "Unable to find EmailAddress Field to provide input");
        }
    }

    public void clickCreateAccount() {
        try {
            commonUtils.scrollToViewAndClick(createAccountButton, driver);
            logger.debug("Clicked on Create Account button");
            ReportUtil.logInfo(driver, "Clicked on Create Account button", false);
        } catch (Exception e) {
            throw new EtcRuntimeException(e, ExceptionLevel.MINOR, ExceptionCause.APP_RELATED, "Unable to find Create account button");
        }
    }

    public boolean verifyAccountCreationLandingPage() {
        try {
            commonUtils.waitForVisible(driver, CommonUtils.getTimeoutValue(), createAccountButton);
            return createAccountButton.isDisplayed();
        } catch (Exception e) {
            throw new EtcRuntimeException(e, ExceptionLevel.MINOR, ExceptionCause.APP_RELATED, "Unable to Validate landing page for creating a new user account");
        }
    }
}
