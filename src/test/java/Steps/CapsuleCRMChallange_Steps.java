package Steps;

import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import CapsuleCRMPageObjects.AccountSettingsPage;
import CapsuleCRMPageObjects.CasesPage;
import CapsuleCRMPageObjects.LandingPage;
import CapsuleCRMPageObjects.LoginPage;
import CapsuleCRMPageObjects.NavBar;
import CapsuleCRMPageObjects.PartyPage;
import CapsuleCRMPageObjects.PeopleAndOrganizationsPage;
import CapsuleCRMPageObjects.SiteAddressGoToPage;
import DriverMaster.MasterWebDriver;
import Lib.Lib;
import Runners.Runner;
import cucumber.api.java.en.Then;

public class CapsuleCRMChallange_Steps extends MasterWebDriver {

	static ExtentTest extentTest;

	public CapsuleCRMChallange_Steps() throws IOException {
		super();
	}


	LandingPage landingPage = new LandingPage();
	LoginPage loginPage = new LoginPage();
	SiteAddressGoToPage siteAddressGoToPage = new SiteAddressGoToPage();
	NavBar navBar = new NavBar();
	PeopleAndOrganizationsPage peopleAndOrgPage = new PeopleAndOrganizationsPage();
	CasesPage casesPage = new CasesPage();
	AccountSettingsPage accountSettingsPage = new AccountSettingsPage();
	
	
	@Then("^I execute \"([^\"]*)\"$")
	public void execute_Naveen_Automationlabs_TestCases(String testname) throws Throwable {


		if (testname.equalsIgnoreCase("Testcase 1")) {
		
			extentTest = Runner.extent.createTest(testname);
			ExtentTest testCase1 = extentTest;

			String url = "https://capsulecrm.com/";
			initializeSelenium();
			loadUrl(url);

//			CustomLogger.log("Navigated to :" + url, testname);
			testCase1.info("navigated to : https://capsulecrm.com/");

			landingPage.clickLogin();
//			CustomLogger.log("Clicked login" + url, testname);

			// login
			siteAddressGoToPage.enterSiteLoginAddress("samisabir212");
			siteAddressGoToPage.clickGoToLogin();
			loginPage.login("samisabir212", "CapsuleCRM212");
//			CustomLogger.log("logged in successfully", testname);

			testCase1.info("Logged in successfully");
			// click on person icon and add a person
			navBar.clickpeopleAndOrganizationsLink();
//			CustomLogger.log("clicked people and org link", testname);

			Thread.sleep(5000);
			peopleAndOrgPage.clickAddPerson();
//			CustomLogger.log("clicked add person link", testname);

			peopleAndOrgPage.addNewPersonWithFullInfo("Mr", "Tony", "Aus", "Tailor", "Saks Fifth Ave", "tailor",
					"954 202 3300", "email212sz@gmail.com", "satashi", "22 south beach blvd", "Miami", "Florida",
					"33101");
//			CustomLogger.log("added person successfully", testname);

			testCase1.info("Added a person Successfully");

			// click on cases icon
			navBar.clickCasesLink();
//			CustomLogger.log("clicked cases link", testname);

			// click on add case button
			casesPage.clickAddCaseLink();
//			CustomLogger.log("clicked add case link", testname);

			// create new case with added person
			String description = "Tony needs a lawyer because the feds are after him for Money Laundering";
			casesPage.enterNewCase("Saks Fifth Ave", "Saks Fifth Ave", "Sami Sabir-Idrissi", description);

			Thread.sleep(3000);

			// verify the correct case got created for the same person
			String actualRelatedNameToCase = casesPage.getNameRelatedToCase();
			String expectedRelatedNameToCase = "Tony Montana";
			if (actualRelatedNameToCase.equalsIgnoreCase(expectedRelatedNameToCase)) {

				System.out.println("Validation requirement : verify the correct case got created for the same person");
				System.out.println("Passed");
				Status = "Passed";
				testCase1.pass("verify the correct case got created for the same person : " + Status);
//				CustomLogger.log("verify the correct case got created for the same person : " + Status, testname);

			} else {
				// increment validation count for failed

				try {
					Assert.assertEquals("expectedRelatedNameToCase", actualRelatedNameToCase);
				} catch (java.lang.AssertionError e) {
					// add message to failedAssertionArrayList
					System.out.println(
							"Validation requirement : verify the correct case got created for the same person");
					System.out.println("Assertion failed :" + e.getMessage());
					testCase1.fail("verify the correct case got created for the same person : " + Status);
//					CustomLogger.log("verify the correct case got created for the same person : " + Status, testname);

				}
			}

			// verify the case status is set to Open
			String actualCaseStatusIcontext = casesPage.getStatusIconText();
			String expectedCaseStatusIconText = "Open";
			if (actualCaseStatusIcontext.equals(expectedCaseStatusIconText)) {

				System.out.println("Validation requirement : verify the case status is set to Open");
				System.out.println("Passed");
				Status = "Passed";
				testCase1.pass("verify the case status is set to Open :" + Status);
//				CustomLogger.log("verify the case status is set to Open :" + Status, testname);

			} else {

				try {
					Assert.assertEquals(expectedCaseStatusIconText, actualCaseStatusIcontext);
				} catch (java.lang.AssertionError e) {
					// add message to failedAssertionArrayList
					System.out.println("Validation requirement : verify the case status is set to Open");
					System.out.println("Assertion failed :" + e.getMessage());
					Status = "Failed";
					testCase1.fail("verify the case status is set to Open : " + Status);
//					CustomLogger.log("verify the case status is set to Open : " + Status, testname);

				}

			}

			// write results to excel with validation count and comment if
			// failure with testng assertion and make sure are logging with
			// extent report

			
			driver.close();
			driver.quit();
			
			
		} else if (testname.equalsIgnoreCase("Testcase 2")) {

			extentTest = Runner.extent.createTest(testname);
			ExtentTest testCase2 = extentTest;
			System.out.println("testname : " + testname);
			String url = "https://capsulecrm.com/";
			loadUrl(url);
			testCase2.info("Navigated to Endpoint URL : " + "https://capsulecrm.com/");
			// 1. Login with correct credentials
//			CustomLogger.log("Clicked login" + url, testname);

			// login
			try {
				
				WebElement siteAddressField = returnWebElementByXpath(siteAddressGoToPage.capsuleSiteAddressField);
				if (siteAddressField.isDisplayed()) {
					siteAddressGoToPage.enterSiteLoginAddress("samisabir212");
					siteAddressGoToPage.clickGoToLogin();
					loginPage.login("samisabir212", "");
					Thread.sleep(6000);
				}
			} catch (NoSuchElementException e) {

			}

			landingPage.clickLogin();

//			CustomLogger.log("logged in successfully", testname);
			testCase2.info("Logged in successfully");

			// 2) click on account name at left top corner
			navBar.clickAccountNameTopRightNavBar();
			System.out.println("clicked On Name at left top corner" + testname);
			Thread.sleep(6000);

//			CustomLogger.log("clicked On Name at left top coner", testname);

			// 3) go to account settings
			navBar.clickAccountSettingsLink();
//			CustomLogger.log("clicked AccountSettings Link", testname);

			// 4) Verify Account Settings page header
			String expectedAccountPageHeaderText = "Account Settings";
			String actualAccountSettingsPageHeaderText = accountSettingsPage.getaccountSettingsPageHeaderText();
//			CustomLogger.log("Validated Account Settings Page header", testname);

			System.out.println("actualAccountSettingsPageHeaderText " + actualAccountSettingsPageHeaderText);

			if (expectedAccountPageHeaderText.equalsIgnoreCase(actualAccountSettingsPageHeaderText)) {
				Status = "Passed";
				testCase2.pass("Verify Account Settings page header :" + Status);
//				CustomLogger.log("Verify Account Settings page header :" + Status, testname);

			} else {
				Status = "Failed";
				testCase2.fail("Verify Account Settings page header :" + Status);
//				CustomLogger.log("Verify Account Settings page header :" + Status, testname);

				try {
					Assert.assertEquals(actualAccountSettingsPageHeaderText, expectedAccountPageHeaderText);
				} catch (java.lang.AssertionError e) {
					System.out.println("Assertion failed : " + e.getMessage());
					testCase2.info(e.getMessage());
//					CustomLogger.log("Verify Account Settings page header :" + "Assertion failed : " + e.getMessage(),
//							testname);

				}
			}
			System.out.println("Verify Account Settings page header :" + Status);

			// 5) Click on each link available at left panel: export,
			// appearance, mail drop box, users, opportunities,
			// tracks, task categories, custom fields, tags, integratio, trash
			// 6). After clicking on each link, verify the page header
			accountSettingsPage.clickAndVerifyEachLinkLeftPanel(testCase2);
//			CustomLogger.log("clicked on each link, verify the page header ", testname);

			// // 7) Click on Appearance and upload a logo image
			try {
				accountSettingsPage.clickAccountSettingsLinkByTextName("Appearance");
//				CustomLogger.log("Clicked on Appearance", testname);

			} catch (StaleElementReferenceException e) {
			}
			System.out.println("Current URL appearence " + driver.getCurrentUrl());
			String filePathToUpload = "/Users/sami/Downloads/P6270082.jpg";
			accountSettingsPage.uploadFiled(filePathToUpload);
//			CustomLogger.log("uploaded a logo image successfully", testname);

			// // 8) Click on Users : Add New User and verify the same user
			Thread.sleep(4000);
			accountSettingsPage.clickAccountSettingsLinkByTextName("Users");
//			CustomLogger.log("Clicked users link", testname);

			accountSettingsPage.clickAddNewUser();
//			CustomLogger.log("Clicked add new user button", testname);

			accountSettingsPage.addNewUserAndVerifyNewUserCreated("Wiz", "Khalifa", "KhalifaKush@gmail.com", "KKcali",
					testCase2);
//			CustomLogger.log("Successfully Added new user and verified that user", testname);

			//
			// // 9) Click on Opportunities : Add new milestone and verify the
			// same
			accountSettingsPage.clickAccountSettingsLinkByTextName("Opportunities");
			accountSettingsPage.clickAddNewMileStoneButton();
			accountSettingsPage.addNewMileStoneAndVerifyMileStoneInfoCreated("MileSton0932", "fancy212", "10", "20",
					testCase2);

			// // 10) Click on Tracks : Add new track and verify the same
			accountSettingsPage.clickAccountSettingsLinkByTextName("Tracks");
			accountSettingsPage.clickTracksLink();
			System.out.println("clicked on tracks");
			accountSettingsPage.addnewOpportunitiesTracksAndVerifyDataCreated("Teach", "BraveHeart", "Success peace",
					"Call", "4", "weeks", "Wiz Khalifa", testCase2);

			// 11) Click on Task Categories : Add new category and verify the
			// same
			accountSettingsPage.clickAccountSettingsLinkByTextName("Task Categories");
			accountSettingsPage.addNewCategoryAndVerifyTheSameCategoryCreated("In-Person", testCase2);

			// 12) Click on Tags : Add new tag and verify the same
			accountSettingsPage.clickAccountSettingsLinkByTextName("Tags");
			accountSettingsPage.addNewTagAndVerifyTheTagCreated("notes", testCase2);
			// 13) Click on Integrations and verify total number of Configure
			// buttons
			accountSettingsPage.clickAccountSettingsLinkByTextName("Integrations");
			int expectedTotalConfigureButtons = 10;
			accountSettingsPage.verifyTotalNumberOfConfigureButtons(expectedTotalConfigureButtons, testCase2);

			
			driver.close();
			driver.quit();
			
		}

	}

}
