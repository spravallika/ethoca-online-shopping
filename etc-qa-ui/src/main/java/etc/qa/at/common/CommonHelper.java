package etc.qa.at.common;

import com.google.inject.Singleton;
import etc.qa.at.config.ConfigReader;
import etc.qa.at.report.ReportUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

@Singleton
public class CommonHelper {
    Logger logger = LogManager.getLogger(this);

    /**
     * This method is to launch a given URL on the browser
     *
     * @param driver
     */
    public void launchURL(WebDriver driver) {
        driver.navigate().to(ConfigReader.getProperty("WebsiteUrl"));
        logger.debug("Launched shopping website");
        ReportUtil.logInfo(driver, "Launched shopping website", true);
    }
}
