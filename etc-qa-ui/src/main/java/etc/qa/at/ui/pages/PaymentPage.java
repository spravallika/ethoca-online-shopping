package etc.qa.at.ui.pages;

import com.google.inject.Inject;
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

public class PaymentPage extends BasePage {
    @Inject
    CommonUtils commonUtils;

    @Inject
    ScopedData scopedData;

    @Inject
    private PaymentPage(@Named("WebDriver") WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "step_end")
    WebElement paymentTabLastStep;

    public boolean verifySelectedProductInPayment() {
        try {
            commonUtils.waitForVisible(driver, CommonUtils.getTimeoutValue(), paymentTabLastStep);
            By productPath = By.xpath("//*[contains(text(),'" + scopedData.getAttributeValue("DressType").toString() + "')]");
            if (commonUtils.isElementPresent(productPath, driver)) {
                logger.debug("Selected product : " + scopedData.getAttributeValue("DressType").toString() + " : is available on Payment tab");
                ReportUtil.logInfo(driver, "Selected product : " + scopedData.getAttributeValue("DressType").toString() + " : is available on Payment tab", false);
                return true;
            } else {
                logger.debug("Selected product : " + scopedData.getAttributeValue("DressType").toString().toUpperCase() + " : is not available on Payment tab");
                ReportUtil.logInfo(driver, "Selected product : " + scopedData.getAttributeValue("DressType").toString() + " : is available on Payment tab", false);
                return false;
            }
        } catch (Exception e) {
            throw new EtcRuntimeException(e, ExceptionLevel.MINOR, ExceptionCause.APP_RELATED, "Unable to find the selected product in payment tab");
        }
    }
}
