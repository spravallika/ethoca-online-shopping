package etc.qa.glue;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import etc.qa.at.dto.AccountDetailsDTO;
import etc.qa.at.steps.service.IAccountRegistrationStepsService;

import java.util.List;

@Singleton
public class AccountRegistrationGlue {
    IAccountRegistrationStepsService iAccountRegistrationStepsService;

    @Inject
    AccountRegistrationGlue(IAccountRegistrationStepsService iAccountRegistrationStepsService) {
        this.iAccountRegistrationStepsService = iAccountRegistrationStepsService;
    }

    @Then("Eve is redirected to account registration page upon clicking Proceed to Checkout")
    public void verifyAccountRegistrationPage() {
        iAccountRegistrationStepsService.verifyAccountRegistrationLadingPage();
    }

    @And("Eve enters the following information during registration")
    public void enterDetailsToRegister(List<AccountDetailsDTO> accountDetailsDTO) {
        iAccountRegistrationStepsService.enterDetails(accountDetailsDTO.get(0));
    }

    @And("Eve completes the registration process")
    public void agreeTermsToPaymentTab() {
        iAccountRegistrationStepsService.agreeTermsToPaymentTab();
    }

    @And("Eve sees the (.*) in the last step of registration process on Payment tab")
    public void verifyProductAvailableInPaymentTab(String productName) {
        iAccountRegistrationStepsService.verifyProductAvailableInPaymentTab(productName);
    }
}
