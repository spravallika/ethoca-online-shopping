package etc.qa.at.config;

import cucumber.api.Scenario;
import etc.qa.at.enums.DriverType;
import etc.qa.at.enums.OSType;
import etc.qa.at.exceptions.EtcRuntimeException;
import etc.qa.at.exceptions.ExceptionCause;
import etc.qa.at.exceptions.ExceptionLevel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {
    static Logger logger = LogManager.getLogger(DriverFactory.class);
    static WebDriver webDriver;

    public static WebDriver getDesktopDriver() {
        try {
            String browserType = ConfigReader.getProperty("browser");
            String osType = ConfigReader.getProperty("os");
            try {
                switch (OSType.valueOf(osType.toUpperCase())) {
                    case MAC:
                        System.setProperty("webdriver.chrome.driver", "../drivers/chromedriver");
                        System.setProperty("webdriver.ie.driver", "../drivers/IEDriverServer");
                        System.setProperty("webdriver.gecko.driver", "../drivers/geckodriver");
                        break;
                    case WINDOWS:
                        System.setProperty("webdriver.chrome.driver", "../drivers/chromedriver.exe");
                        System.setProperty("webdriver.ie.driver", "../drivers/IEDriverServer.exe");
                        System.setProperty("webdriver.gecko.driver", "../drivers/geckodriver.exe");
                        break;
                }
            } catch (Exception e) {
                throw new EtcRuntimeException(e, ExceptionLevel.MAJOR, ExceptionCause.CONFIG_PROPERTIES, "Invalid os type provided - Please choose either Windows/Mac");
            }
            logger.debug("BrowserType at DriverFactory is set to : " + browserType);
            switch (DriverType.valueOf(browserType.toUpperCase())) {
                case CHROME:
                    webDriver = new ChromeDriver(new ChromeOptions().addArguments("--start-maximized"));
                    logger.debug("Launching webdriver on browser : " + browserType + " on OS : " + osType);
                    webDriver.manage().window().maximize();
                    return webDriver;
                case IE:
                    webDriver = new InternetExplorerDriver();
                    logger.debug("Launching webdriver on browser : " + browserType + " on OS : " + osType);
                    webDriver.manage().window().maximize();
                    return webDriver;
                case FIREFOX:
                    webDriver = new FirefoxDriver(new FirefoxOptions().addArguments("--start-maximized"));
                    logger.debug("Launching webdriver on browser : " + browserType + " on OS : " + osType);
                    webDriver.manage().window().maximize();
                    return webDriver;
                case SAFARI:
                    webDriver = new SafariDriver();
                    logger.debug("Launching webdriver on browser : " + browserType + " on OS : " + osType);
                    webDriver.manage().window().maximize();
                    return webDriver;
                default:
                    break;
            }
        } catch (Exception e) {
            throw new EtcRuntimeException(e, ExceptionLevel.MAJOR, ExceptionCause.CONFIG_PROPERTIES, "Invalid os type provided - Please choose either Windows/Mac");
        }
        return webDriver;
    }

    public static void endTest(Scenario scenario) {
        if (webDriver != null) {
            logger.debug("**** Ending test execution **** " + scenario.getName());
            logger.debug("*** Closing all the active browser sessions ***");
            webDriver.quit();
        }
    }
}
