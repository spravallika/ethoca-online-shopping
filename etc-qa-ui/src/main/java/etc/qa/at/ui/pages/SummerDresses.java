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

@Singleton
public class SummerDresses extends BasePage {
    @Inject
    CommonUtils commonUtils;

    @Inject
    ScopedData scopedData;

    @Inject
    private SummerDresses(@Named("WebDriver") WebDriver driver) {
        super(driver);
    }

    public void clickQuickViewDressType(String dressType) {
        try {
            By dressTypeXpath = By.xpath(String.format("//a[@title='%s']", dressType));
            commonUtils.waitForVisible(driver, CommonUtils.getTimeoutValue(), driver.findElement(dressTypeXpath));
            commonUtils.moveToElement(driver.findElement(By.xpath(String.format("//a[@title='%s']/img", dressType))), driver);
            By quickViewDressType = By.xpath(String.format("//a[@title='%s']/following-sibling::a", dressType));
            commonUtils.waitForVisibleAndClick(driver, CommonUtils.getTimeoutValue(), quickViewDressType);
            logger.debug("Clicked on Quick View option for printed chiffon dress");
            ReportUtil.logInfo(driver,"Clicked on Quick View option for printed chiffon dress",true);
            scopedData.putAttributeValue("DressType", String.class, dressType);
        } catch (Exception e) {
            throw new EtcRuntimeException(e, ExceptionLevel.MINOR, ExceptionCause.APP_RELATED, "Unable to quick view the selected product to view product details");
        }
    }
}
