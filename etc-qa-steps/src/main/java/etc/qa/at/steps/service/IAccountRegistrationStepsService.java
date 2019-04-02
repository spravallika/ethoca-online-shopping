package etc.qa.at.steps.service;

import etc.qa.at.dto.AccountDetailsDTO;

import java.util.List;

public interface IAccountRegistrationStepsService {
    void verifyAccountRegistrationLadingPage();

    void enterDetails(AccountDetailsDTO accountDetailsDTO);

    void verifyProductAvailableInPaymentTab(String productName);

    void agreeTermsToPaymentTab();
}
