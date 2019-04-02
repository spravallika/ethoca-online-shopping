package etc.qa.glue;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import etc.qa.at.config.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StepHooks {
    Logger logger = LogManager.getLogger(this);

    @Before
    public void setUp(Scenario scenario) {
        logger.debug("====== Initializing test : " + scenario.getName() + " with background ======");
    }

    @After
    public void tearDown(Scenario scenario) {
        DriverFactory.endTest(scenario);
    }
}
