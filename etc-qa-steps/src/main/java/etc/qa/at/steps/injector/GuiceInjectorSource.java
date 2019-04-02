package etc.qa.at.steps.injector;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import cucumber.api.guice.CucumberModules;
import cucumber.runtime.java.guice.InjectorSource;
import etc.qa.at.config.DIModuleUIWeb;
import etc.qa.at.steps.bindings.DIModuleWeb;

public class GuiceInjectorSource implements InjectorSource {
    /**
     * Creates an injector based on runtime targets (DIModuleWeb - for steps , DIUIModuleWeb - for pages
     *
     * @return
     */
    @Override
    public Injector getInjector() {
        return Guice.createInjector(Stage.DEVELOPMENT, CucumberModules.SCENARIO, new DIModuleUIWeb(), new DIModuleWeb());
    }
}
