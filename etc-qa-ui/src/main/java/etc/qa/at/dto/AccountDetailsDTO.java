package etc.qa.at.dto;

import cucumber.deps.com.thoughtworks.xstream.annotations.XStreamAlias;

public class AccountDetailsDTO {
    @XStreamAlias("EmailAddress")
    String emailAddress;

    @XStreamAlias("FirstName")
    String firstName;

    @XStreamAlias("Password")
    String password;

    @XStreamAlias("PostcalCode")
    String postalCode;

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XStreamAlias("LastName")
    String lastName;

    @XStreamAlias("AddressLine")
    String addressLine;

    @XStreamAlias("City")
    String city;

    @XStreamAlias("State")
    String state;

    @XStreamAlias("MobileNumber")
    String mobileNumber;
}
