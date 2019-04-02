package etc.qa.at.ui.pages;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import etc.qa.at.common.CommonHelper;
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
public class HomePage extends BasePage {
    @Inject
    CommonUtils commonUtils;

    @Inject
    CommonHelper commonHelper;

    @Inject
    private HomePage(@Named("WebDriver") WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@title='Women']")
    WebElement womenMenuLink;

    @FindBy(xpath = "//a[@title='Summer Dresses']")
    WebElement summerDressesLink;

    public void launchURL() {
        commonHelper.launchURL(driver);
    }

    public void goToWomenMenu() {
        try {
            commonUtils.moveToElement(womenMenuLink, driver);
            logger.debug("Hovered on Women menu link");
            ReportUtil.logInfo(driver, "Hovered on Women menu link", true);
        } catch (Exception e) {
            throw new EtcRuntimeException(e, ExceptionLevel.MINOR, ExceptionCause.APP_RELATED, "Unable to find Women menu link");
        }
    }

    public void selectSummerDresses() {
        try {
            commonUtils.waitForVisibleAndClick(driver, CommonUtils.getTimeoutValue(), summerDressesLink);
            logger.debug("Clicked on Summer Dresses link");
            ReportUtil.logInfo(driver, "Clicked on Summer Dresses link", true);
        } catch (Exception e) {
            throw new EtcRuntimeException(e, ExceptionLevel.MINOR, ExceptionCause.APP_RELATED, "Unable to find Summer dresses link under women's section");
        }
    }


}
