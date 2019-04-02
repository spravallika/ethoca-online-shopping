package etc.qa.glue;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import etc.qa.at.steps.service.ISearchAndOrderStepsService;

@Singleton
public class SelectProductGlue {
    ISearchAndOrderStepsService iSearchAndOrderStepsService;

    @Inject
    SelectProductGlue(ISearchAndOrderStepsService iSearchAndOrderStepsService){
        this.iSearchAndOrderStepsService = iSearchAndOrderStepsService;
    }

    @Given("Eve launches the online shopping website")
    public void launchSite(){
        iSearchAndOrderStepsService.launchSite();
    }

    @And("Eve selects Summer dresses from Women's section")
    public void selectSummerDresses(){
        iSearchAndOrderStepsService.selectSummerDressesFromWomen();
    }
}
