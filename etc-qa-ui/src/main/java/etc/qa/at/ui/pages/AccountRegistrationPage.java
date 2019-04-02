package etc.qa.at.ui.pages;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import etc.qa.at.exceptions.EtcRuntimeException;
import etc.qa.at.exceptions.ExceptionCause;
import etc.qa.at.exceptions.ExceptionLevel;
import etc.qa.at.report.ReportUtil;
import etc.qa.at.ui.pages.base.BasePage;
import etc.qa.at.utils.CommonUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class AccountRegistrationPage extends BasePage {
    @Inject
    CommonUtils commonUtils;

    @Inject
    private AccountRegistrationPage(@Named("WebDriver") WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[text()='Create an account]")
    WebElement createAccountHeading;

    @FindBy(id = "customer_firstname")
    WebElement firstName;

    @FindBy(id = "customer_lastname")
    WebElement lastName;

    @FindBy(id = "passwd")
    WebElement password;

    @FindBy(id = "address1")
    WebElement addressInput;

    @FindBy(id = "city")
    WebElement cityInput;

    @FindBy(id = "id_state")
    WebElement stateDropdown;

    @FindBy(id = "postcode")
    WebElement postalCode;

    @FindBy(id = "phone_mobile")
    WebElement mobileNumber;

    @FindBy(id = "submitAccount")
    WebElement registerButton;

    public void setFirstName(String firstNameValue) {
        try {
            commonUtils.setValueToWebelement(firstName, firstNameValue, driver);
        } catch (Exception e) {
            throw new EtcRuntimeException(e, ExceptionLevel.MINOR, ExceptionCause.APP_RELATED, "Unable to find FirstName Field to provide input");
        }
    }

    public void setLastName(String lastNameValue) {
        try {
            commonUtils.setValueToWebelement(lastName, lastNameValue, driver);
        } catch (Exception e) {
            throw new EtcRuntimeException(e, ExceptionLevel.MINOR, ExceptionCause.APP_RELATED, "Unable to find LastName Field to provide input");
        }
    }

    public void setPassword(String passwordValue) {
        try {
            commonUtils.setValueToWebelement(password, passwordValue, driver);
        } catch (Exception e) {
            throw new EtcRuntimeException(e, ExceptionLevel.MINOR, ExceptionCause.APP_RELATED, "Unable to find Password Field to provide input");
        }
    }

    public void setAddressInput(String addressValue) {
        try {
            commonUtils.setValueToWebelement(addressInput, addressValue, driver);
        } catch (Exception e) {
            throw new EtcRuntimeException(e, ExceptionLevel.MINOR, ExceptionCause.APP_RELATED, "Unable to find AddressLine Field to provide input");
        }
    }

    public void setCityInput(String cityValue) {
        try {
            commonUtils.setValueToWebelement(cityInput, cityValue, driver);
        } catch (Exception e) {
            throw new EtcRuntimeException(e, ExceptionLevel.MINOR, ExceptionCause.APP_RELATED, "Unable to find City Field to provide input");
        }
    }

    public void setStateDropdown(String stateValue) {
        try {
            commonUtils.scrollToWebelement(driver, stateDropdown);
            Select stateDrpdwn = new Select(stateDropdown);
            stateDrpdwn.selectByVisibleText(stateValue);
        } catch (Exception e) {
            throw new EtcRuntimeException(e, ExceptionLevel.MINOR, ExceptionCause.APP_RELATED, "Unable to find State dropdown Field to provide input");
        }
    }

    public void setPostalCode(String postalCodeValue) {
        try {
            commonUtils.setValueToWebelement(postalCode, postalCodeValue, driver);
        } catch (Exception e) {
            throw new EtcRuntimeException(e, ExceptionLevel.MINOR, ExceptionCause.APP_RELATED, "Unable to find PostcalCode Field to provide input");
        }
    }

    public void setMobileNumber(String mobileNumberValue) {
        try {
            commonUtils.setValueToWebelement(mobileNumber, mobileNumberValue, driver);
        } catch (Exception e) {
            throw new EtcRuntimeException(e, ExceptionLevel.MINOR, ExceptionCause.APP_RELATED, "Unable to find MobileNumber Field to provide input");
        }
    }

    public void clickSubmitAndRegister() {
        try {
            commonUtils.scrollToViewAndClick(registerButton, driver);
            ReportUtil.logInfo(driver,"Clicked on Register button",true);
        } catch (Exception e) {
            throw new EtcRuntimeException(e, ExceptionLevel.MINOR, ExceptionCause.APP_RELATED, "Unable to find Register button to complete registration process");
        }
    }

    public void enterRegistrationDetails(String fistName, String lastName, String pwd, String address, String city, String state, String pincode, String mobNum) {
        setFirstName(fistName);
        setLastName(lastName);
        setPassword(pwd);
        setAddressInput(address);
        setCityInput(city);
        setStateDropdown(state);
        setPostalCode(pincode);
        setMobileNumber(mobNum);
    }
}
