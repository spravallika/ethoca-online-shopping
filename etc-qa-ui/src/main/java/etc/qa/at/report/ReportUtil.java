package etc.qa.at.report;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import etc.qa.at.config.ConfigReader;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ReportUtil {
    public static void logInfo(WebDriver driver, String message, boolean takeScreenshot) {
        logInfo(message);
        if (takeScreenshot)
            takeScreenshot(driver);
    }

    public static void logError(WebDriver driver, String message, boolean takeScreenshot) {
        logError(message);
        if (takeScreenshot)
            takeScreenshot(driver);
    }

    public static void logWarn(WebDriver driver, String message, boolean takeScreenshot) {
        logWarn(message);
        if (takeScreenshot)
            takeScreenshot(driver);
    }

    public static void logInfo(String message) {
        CustomCucumberFormatter.stepTestThreadLocal.get().info(message);
    }

    public static void logError(String message) {
        CustomCucumberFormatter.stepTestThreadLocal.get().error(message);
    }

    public static void logWarn(String message) {
        CustomCucumberFormatter.stepTestThreadLocal.get().warning(message);
    }

    public static void takeScreenshot(WebDriver driver) {
        try {
            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotLocation = ConfigReader.getModuleName() + File.separator + sourceFile.getName();
            FileUtils.copyFile(sourceFile, new File(screenshotLocation));
            MediaEntityModelProvider mediaEntityModelProvider = MediaEntityBuilder.createScreenCaptureFromPath(new File(screenshotLocation).getPath()).build();
            CustomCucumberFormatter.stepTestThreadLocal.get().info("", mediaEntityModelProvider);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String formatWebelementMessage(String message) {
        if (message == null || message.length() < 1 || !message.contains(" -> "))
            return message;
        return message.substring(message.indexOf(" -> ") + 4, message.length() - 1);
    }
}
