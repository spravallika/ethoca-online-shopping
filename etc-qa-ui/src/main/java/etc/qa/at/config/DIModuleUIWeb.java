package etc.qa.at.config;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import cucumber.runtime.java.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;

public class DIModuleUIWeb extends AbstractModule {
    @Override
    protected void configure() {

    }

    @Provides
    @ScenarioScoped
    @Named("WebDriver")
    public WebDriver getDriver() {
        return DriverFactory.getDesktopDriver();
    }
}
