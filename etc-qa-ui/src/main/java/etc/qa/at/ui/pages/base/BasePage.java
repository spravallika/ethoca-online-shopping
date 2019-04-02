package etc.qa.at.ui.pages.base;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import etc.qa.at.utils.CommonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    protected WebDriver driver;
    protected Logger logger = LogManager.getLogger(this);

    @Inject
    CommonUtils commonUtils;

    @Inject
    protected BasePage(@Named("WebDriver") WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
