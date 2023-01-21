package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base {

	LoginPage loginPage;
	
	public LoginTest()
	{
		super();
	}
	
	WebDriver driver;
		
	@BeforeMethod
	public void setup() {

		driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		loginPage = homePage.selectLoginOption();
		
		
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	@Test(priority = 1,dataProvider = "validCredentialSupplier")
	public void verifyLoginWithvalidCredentials(String email,String password) {
	
		loginPage.enterEmailAddress(email);
		loginPage.enterPassword(password);
		AccountPage accountPage = loginPage.clickOnLoginButton();
		

		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(),
				"Edit Your Account Information is not displayed");

	}

	@DataProvider(name="validCredentialSupplier")
	public Object [] [] supplyTestData()
	{
		Object[][] data = (Object[][]) Utilities.getTestdataFromExcel("Login");
		return data;
		
	}
	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() {
		loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
        loginPage.enterPassword(dataProp.getProperty("invalidPassword"));		
        loginPage.clickOnLoginButton();
        
        
		String actualwarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningText();
		String expectedwarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");

		Assert.assertTrue(actualwarningMessage.contains(expectedwarningMessage),
				"Expected warning message is not displayed");

	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailAndvalidPassword() {
		

		loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
        loginPage.enterPassword(prop.getProperty("validPassword"));		
        loginPage.clickOnLoginButton();
		
		

        String actualwarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningText();
		String expectedwarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");

		Assert.assertTrue(actualwarningMessage.contains(expectedwarningMessage),
				"Expected warning message is not displayed");

	}

	@Test(priority = 4)
	public void verifyLoginWithvalidEmailAndInvalidPassword() {

		loginPage.enterEmailAddress(prop.getProperty("validEmail"));
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
		

		   String actualwarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningText();
			String expectedwarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");

			Assert.assertTrue(actualwarningMessage.contains(expectedwarningMessage),
					"Expected warning message is not displayed");

	}

	@Test(priority = 5)
	public void verifyLoginWithoutProvidingCredentials() {
	
		loginPage.clickOnLoginButton();

	

		  String actualwarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningText();
			String expectedwarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");

			Assert.assertTrue(actualwarningMessage.contains(expectedwarningMessage),
					"Expected warning message is not displayed");


	}

}
