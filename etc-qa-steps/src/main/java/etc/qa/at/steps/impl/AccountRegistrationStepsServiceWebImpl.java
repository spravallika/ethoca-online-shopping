package etc.qa.at.steps.impl;

import com.google.inject.Inject;
import etc.qa.at.dto.AccountDetailsDTO;
import etc.qa.at.steps.service.IAccountRegistrationStepsService;
import etc.qa.at.ui.pages.*;
import org.junit.Assert;

public class AccountRegistrationStepsServiceWebImpl implements IAccountRegistrationStepsService {
    @Inject
    AuthenticationPage authenticationPage;

    @Inject
    AccountRegistrationPage accountRegistrationPage;

    @Inject
    ShoppingCartSummary shoppingCartSummary;

    @Inject
    AddressesPage addressesPage;

    @Inject
    AgreeTermsPage agreeTermsPage;

    @Inject
    PaymentPage paymentPage;

    @Override
    public void verifyAccountRegistrationLadingPage() {
        shoppingCartSummary.clickProceedToCheckout();
        Assert.assertTrue("**** Account creation landing page is not found after checking out ****", authenticationPage.verifyAccountCreationLandingPage());
    }

    @Override
    public void enterDetails(AccountDetailsDTO accountDetailsDTO) {
        authenticationPage.enterEmailAddress(accountDetailsDTO.getEmailAddress());
        authenticationPage.clickCreateAccount();
        accountRegistrationPage.enterRegistrationDetails(accountDetailsDTO.getFirstName(), accountDetailsDTO.getLastName(), accountDetailsDTO.getPassword(), accountDetailsDTO.getAddressLine(), accountDetailsDTO.getCity(), accountDetailsDTO.getState(), accountDetailsDTO.getPostalCode(), accountDetailsDTO.getMobileNumber());
        accountRegistrationPage.clickSubmitAndRegister();
    }

    @Override
    public void verifyProductAvailableInPaymentTab(String productName) {
        Assert.assertTrue("Select product - " + productName + " is not visible on payment page", paymentPage.verifySelectedProductInPayment());
    }

    @Override
    public void agreeTermsToPaymentTab() {
        addressesPage.clickProceedToCheckout();
        agreeTermsPage.clickAgreeTerms();
        agreeTermsPage.clickProceedToCheckout();
    }
}
