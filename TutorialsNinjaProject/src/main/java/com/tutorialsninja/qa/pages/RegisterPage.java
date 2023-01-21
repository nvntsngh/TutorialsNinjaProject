package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;

	@FindBy(id = "input-firstname")
	private WebElement firstNameField;

	@FindBy(id = "input-lastname")
	private WebElement lastNameField;

	@FindBy(id = "input-email")
	private WebElement emailAddressField;

	@FindBy(id = "input-telephone")
	private WebElement telephoneField;

	@FindBy(id = "input-password")
	private WebElement passwordField;

	@FindBy(id = "input-confirm")
	private WebElement passwordConfirmField;

	@FindBy(name = "agree")
	private WebElement privacyPolicyField;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButton;

	@FindBy(xpath = "//input[@name='newsletter'][@value='1']")
	private WebElement yesNewsletterOption;

	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailAddressWarning;

	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarning;

	@FindBy(xpath = "//div[contains(text(),'First Name must be between 1 and 32 characters!')]")
	private WebElement firstNamewarning;

	@FindBy(xpath = "//div[contains(text(),'Last Name must be between 1 and 32 characters!')]")
	private WebElement lastNameWarning;

	@FindBy(xpath = "//div[contains(text(),'E-Mail Address does not appear to be valid!')]")
	private WebElement emailWarning;

	@FindBy(xpath = "//div[contains(text(),'Telephone must be between 3 and 32 characters!')]")
	private WebElement telephoneWarning;

	@FindBy(xpath = "//div[contains(text(),'Password must be between 4 and 20 characters!')]")
	private WebElement passwordWarning;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String retrivepasswordWarning() {
		String passwordWaningText = passwordWarning.getText();
		return passwordWaningText;
	}

	public String retriveTelephoneWarning() {
		String telephoneWaningText = telephoneWarning.getText();
		return telephoneWaningText;
	}

	public String retriveEmailWarning() {
		String emailWaningText = emailWarning.getText();
		return emailWaningText;
	}

	public String retriveLastNameWarning() {
		String lastNameWarningText = lastNameWarning.getText();
		return lastNameWarningText;
	}

	public String retriveFirstNameWarning() {
		String firstNameWarningText = firstNamewarning.getText();
		return firstNameWarningText;
	}

	public String retriveprivacyPolicyWarning() {
		String privacyPolicyWarningText = privacyPolicyWarning.getText();
		return privacyPolicyWarningText;
	}

	public void enterFirstName(String firstNameText) {
		firstNameField.sendKeys(firstNameText);
	}

	public void enterLastName(String lastNameText) {
		lastNameField.sendKeys(lastNameText);
	}

	public void enterEmailAddress(String emailText) {
		emailAddressField.sendKeys(emailText);
	}

	public void enterTelephoneNumber(String telephoneText) {
		telephoneField.sendKeys(telephoneText);
	}

	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);
	}

	public void enterConfirmPassword(String passwordConfirmText) {
		passwordConfirmField.sendKeys(passwordConfirmText);
	}

	public void selectPrivacyPolicy() {
		privacyPolicyField.click();
	}

	public AccountSuccessPage clickOnContinueButton() {
		continueButton.click();
		return new AccountSuccessPage(driver);
	}

	public void selectYesNewsletterOption() {
		yesNewsletterOption.click();
	}

	public String retrieveDuplicateEmailAddressWarning() {
		String duplicateEmailAddressWarnintText = duplicateEmailAddressWarning.getText();
		return duplicateEmailAddressWarnintText;

	}
}
