package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base {

	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	public RegisterTest() {
		super();
	}

	WebDriver driver;

	@BeforeMethod
	public void setup() {

		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		 registerPage = homePage.selectRegisterOption();
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyRegisterAnaccountWithMandatoryFields() {

		registerPage.enterFirstName(dataProp.getProperty("firstname"));
		registerPage.enterLastName(dataProp.getProperty("lastname"));
		registerPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectPrivacyPolicy();
		 accountSuccessPage = registerPage.clickOnContinueButton();

		
		String actualSuucessHeading = accountSuccessPage.retrieveAccountSuccesspageHeading();
		Assert.assertEquals(actualSuucessHeading, dataProp.getProperty("accountSuccessfullyCreatedheading"),
				"Account Success Page is not Displayed");

	}

	@Test(priority = 2)

	public void verifyRegisteringAnaccountByProvidingAllFields() {

		registerPage.enterFirstName(dataProp.getProperty("firstname"));
		registerPage.enterLastName(dataProp.getProperty("lastname"));
		registerPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();

		String actualSuucessHeading = accountSuccessPage.retrieveAccountSuccesspageHeading();
		Assert.assertEquals(actualSuucessHeading, dataProp.getProperty("accountSuccessfullyCreatedheading"),
				"Account Success Page is not Displayed");

	}

	@Test(priority = 3)

	public void verifyRegisteringAnaccountWithExistingEmailAddress() {

		registerPage.enterFirstName(dataProp.getProperty("firstname"));
		registerPage.enterLastName(dataProp.getProperty("lastname"));
		registerPage.enterEmailAddress(prop.getProperty("validEmail"));
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();

		String actualwarning = registerPage.retrieveDuplicateEmailAddressWarning();
		Assert.assertTrue(actualwarning.contains(dataProp.getProperty("duplicateEmailWarning")),
				"Warning message regarding duplicate email address is not displayed");

	}

	@Test(priority = 4)
	public void verifyRegisteringAccountWithoutFillingAnyDetails() {
		registerPage.clickOnContinueButton();

		String ActualPrivacypolicywarning = registerPage.retriveprivacyPolicyWarning();
		Assert.assertTrue(ActualPrivacypolicywarning.contains(dataProp.getProperty("privacyPolicyWarning")),
				"Privacy Policy Warning message is not displayed");

		String ActualfirstNameWarning = registerPage.retriveFirstNameWarning();
		Assert.assertTrue(ActualfirstNameWarning.contains(dataProp.getProperty("firstnameWarning")),
				"First name warning message is not Displayed");

		String ActualLastNameWarning = registerPage.retriveLastNameWarning();
		Assert.assertTrue(ActualLastNameWarning.contains(dataProp.getProperty("lastnamewarning")),
				"Last name warning message is not Displayed");

		String ActualEmailWarning = registerPage.retriveEmailWarning();
		Assert.assertTrue(ActualEmailWarning.contains(dataProp.getProperty("emailWarning")),
				"Email warning message is not Displayed");

		String ActualTelephoneWarning = registerPage.retriveTelephoneWarning();
		Assert.assertTrue(ActualTelephoneWarning.contains(dataProp.getProperty("telephonewarning")),
				"Telephone warning message is not Displayed");

		String ActualPasswordWarning = registerPage.retrivepasswordWarning();
		Assert.assertTrue(ActualPasswordWarning.contains(dataProp.getProperty("passwordWarning")),
				"Password warning message is not Displayed");

	}

}
