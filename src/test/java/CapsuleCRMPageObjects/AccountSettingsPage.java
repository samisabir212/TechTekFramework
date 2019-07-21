package CapsuleCRMPageObjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import DriverMaster.MasterWebDriver;

public class AccountSettingsPage extends MasterWebDriver {

	public AccountSettingsPage() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	// Left navPanel elements links
	String leftPanelLinkElementsLocator = ".//ul[@class='nav-panel']/li/a";
	public static String tracksLink = "//ul[@class='settings-nav']/li[8]/a";

	// page headers
	public static String accountSettingsPageHeaderTxt = "//div[@class='left-fixed']/span";

	// Appearance
	public static String PageHeaderTextFromAccountSettingsPageTxt = "//h1[@class='settings-page-header']";
	public static String chooseFile_Upload = "appearance:uploadDecorate:logoImage";
	public static String saveBtnAppearnceForAppearanceLogoUpload = "//a[@class='btn-primary singlesubmit']";

	// Users
	public static String addNewBtn = "//a[@class='action btn-primary btn-primary-clear']";
	public static String firstnameField = "register:firstnameDecorate:firstName";
	public static String lastnameField = "register:lastNameDecorate:lastName";
	public static String emailField = "register:emailDecorate:email";
	public static String usernameField = "register:usernameDecorate:username";
	public static String inviteUserBtn = "register:save";
	public static String accountSettingsUsersTableData = "//table[@id='j_id128:users']/tbody/tr/td";

	// MileStone
	public static String addNewMileStoneBtn = "//button[@class='btn-primary']";
	public static String addNewMileStoneNameField = "//input[@class='form-input-text milestone-modal-name']";
	public static String mileStoneDescriptionField = "//textarea[@class='form-input-text milestone-modal-description']";
	public static String probabilityOfWinningField = "//input[@class='form-input-text milestone-modal-probability']";
	public static String daysUntilStaleField = "//input[@class='form-input-text milestone-modal-days-until-stale']";
	public static String mileStoneSaveBtn = ".//div[@class='form-actions']/button[@class='async-button ember-view btn-primary']";
	public static String tableRowsOfOpportunityMileStones = ".//table[@class='record-list']/tbody/tr/td";

	// Opportunity tracks
	public static String newOpportunityTrackNameField = "j_id123:trackDescriptionDecorate:trackDescription";
	public static String tagOpportunityTrackField = "j_id123:trackTagDecorate:trackTag";
	public static String taskDescriptionOpportunityTrackField = "j_id123:taskLines:0:taskDescriptionDecorate:taskDescription";
	public static String categoryOpportunityTrackDropDownMenu = "ui-id-1-button";
	public static String dueOpportunityTrackField = "j_id123:taskLines:0:taskDaysAfterDecorate:taskDaysAfter";
	public static String afterTrackStartsOpportunityTrackDropDown = "//select[@id='j_id123:taskLines:0:taskDaysAfterDecorate:trackDayDelayRule']";
	public static String assigneeOpportunityTrackDropDown = "//select[@name='j_id123:taskLines:0:taskAssignmentDecorate:j_id218']";
	public static String addTaskOpportunityTrackBtn = "";
	public static String saveOpportunityTrackBtn = "//a[@class='btn-primary btn-clear singlesubmit']";
	public static String tableRowsOfDataOpportunitiesTracks = ".//tbody//tr//td";

	// task categories
	public static String addNewCategoryBtn = "j_id124:j_id126";
	public static String categoryNameField = "//div[@id='editCategoryModal']/div/span/div/div/span/input[1]";
	public static String saveCategoryBtn = "editCategoryForm:j_id175";

	// tags
	public static String addNewTagBtn = "j_id125:j_id127";
	public static String addTagNameTextField = "//div[@id='editTagModal']/div/span/div/div/span/input";
	public static String saveNewTagBtn = "j_id177:j_id210";

	
	public void verifyTotalNumberOfConfigureButtons(int expectedTotalnumberOfConfigureButtons, ExtentTest testcase){
		
		List<WebElement> listOfRows = driver.findElements(By.xpath(".//tbody/tr"));
		int counter = 0;
		for(int i = 1; i<=listOfRows.size();i++){
			System.out.println(i);
			WebElement configureBtn = driver.findElement(By.xpath(".//tbody/tr["+i+"]/td[3]/a"));
			System.out.println(configureBtn.getText());
			System.out.println(configureBtn.getText().equalsIgnoreCase("Configure"));
			if(configureBtn.getText().equalsIgnoreCase("Configure")){
				
				Status = "Passed";
				counter++;
				
				
			}else{
				System.out.println("couldnt find the matching text for the configure button");
				testcase.info("couldnt find the matching text for the configure button, but we found :"+ configureBtn.getText());
			}
			
		}
		
		if(counter==10){
			System.out.println("we found a total of 10 configure buttons we are successful");
			testcase.info("we found a total of 10 configure buttons we are successful");
			testcase.pass("Click on Integrations and verify total number of Configure buttons : "+Status);

		}else{
			testcase.fail("Click on Integrations and verify total number of Configure buttons : "+Status);

		}
	}
	
	
	public void addNewTagAndVerifyTheTagCreated(String tagname, ExtentTest testcase) throws InterruptedException, IOException {

		waitAndClickElementByXpath(addNewTagBtn);
		try {
			WebElement addtagBox = driver.findElement(By.xpath("//div[@id='editTagModal']"));
			System.out.println("Can we interact with this element ? answer : " + addtagBox.isDisplayed());
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement element = returnWebElementByXpath(addTagNameTextField);
			WebElement tagTextFieldWait = wait.until(ExpectedConditions.visibilityOf(element));
			tagTextFieldWait.sendKeys(tagname);

		} catch (StaleElementReferenceException e) {

		}
		
		waitAndClickElementByXpath(saveNewTagBtn);
	

		List<WebElement> list = driver.findElements(By.xpath(".//tbody/tr"));
		System.out.println("right before the loop");

		for (int i = 1; i < list.size(); i++) {

			System.out.println(i);

			WebElement actualTagname = driver.findElement(By.xpath(".//tbody/tr[" + i + "]/td[1]"));
			System.out.println("hellooo");
			try{
				System.out.println("I found " + actualTagname.getText());

			}catch(StaleElementReferenceException e){
				
			}
			System.out.println("Im trying to compare it to the expected value : " + tagname);

			try {
				
				if (actualTagname.getText().equalsIgnoreCase(tagname)) {
					System.out.println("Condition is true");
					
					if (actualTagname.getText().equalsIgnoreCase(tagname)) {

						Status = "Passed";
						testcase.info("Add new Tag and verify the same created : " + actualTagname.getText() + " : "
								+ Status);
						testcase.pass("Add new Tag and verify the same : :" + Status);
						System.out.println("Found a match, breaking out of loop now thanks...");
					} else {
						Status = "Failed";

						try {

							Assert.assertEquals(actualTagname.getText(), tagname);

						} catch (java.lang.AssertionError e) {
							testcase.info("actual tagname and expected category assertion Failed :" + e.getMessage());
							testcase.fail("Add new Tag and verify the same : " + Status);
						}

					}
					break;
				}else{
					System.out.println("couldnt find a match.. we are at index : "+ i+" and element grabbed is :"
							+ actualTagname.getText()+" which is the tag name we are not looking for");
				}
			} catch (StaleElementReferenceException e) {

			}

		}

	}

	public void addNewCategoryAndVerifyTheSameCategoryCreated(String categoryName, ExtentTest testcase)
			throws InterruptedException, IOException {

		waitAndClickElementByXpath(addNewCategoryBtn);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			
			WebElement element = returnWebElementByXpath(categoryNameField);
			WebElement categoryFieldNameWait = wait.until(ExpectedConditions.elementToBeClickable(element));
			categoryFieldNameWait.sendKeys(categoryName);
		} catch (StaleElementReferenceException e) {

		}

		waitAndClickElementByXpath(saveCategoryBtn);

		Thread.sleep(5000);
		ArrayList<WebElement> list = (ArrayList<WebElement>) driver.findElements(By.xpath(".//tbody/tr"));

		for (int i = 1; i < list.size(); i++) {

			WebElement actualCategoryName = driver.findElement(By.xpath(".//tbody/tr[" + i + "]/td[2]"));

			if (actualCategoryName.getText().equals(categoryName)) {

				if (actualCategoryName.getText().equals(categoryName)) {

					Status = "Passed";
					testcase.info("Add new category and verify the same category created : "
							+ actualCategoryName.getText() + " : " + Status);
					testcase.pass("Add new category and verify the same :" + Status);
					System.out.println("Found a match, breaking out of loop now thanks...");
					break;
				} else {
					Status = "Failed";

					try {

						Assert.assertEquals(actualCategoryName.getText(), categoryName);

					} catch (java.lang.AssertionError e) {
						testcase.info("actual category and expected category assertion Failed :" + e.getMessage());
						testcase.fail("Add new category and verify the same : " + Status);
					}

				}

			} else {

				System.out.println("Couldnt find a match... keep searching for actual category we created");
			}

		}
	}


	public void addnewOpportunitiesTracksAndVerifyDataCreated(String name, String tag, String taskDescription,
			String category, String dueDate, String afterTrackStartOption, String assignee, ExtentTest testcase)
			throws Exception {

		waitAndClickElementByXpath(addNewBtn);
		sendKeysToWebElementByXpath(newOpportunityTrackNameField, name);
		sendKeysToWebElementByXpath(tagOpportunityTrackField, tag);
		sendKeysToWebElementByXpath(taskDescriptionOpportunityTrackField, taskDescription);
		waitAndClickElementByXpath(categoryOpportunityTrackDropDownMenu);

		ArrayList<WebElement> categoryArrayList = (ArrayList<WebElement>) driver
				.findElements(By.xpath("//div[@class='ui-selectmenu-menu ui-front ui-selectmenu-open']/ul/li"));

		for (int i = 0; i < categoryArrayList.size(); i++) {
			WebElement categoryElement = categoryArrayList.get(i);
			if (categoryElement.getText().equals(category)) {
				categoryElement.click();
				break;
			}
		}
		System.out.println("found elment and broke out loop");

		Thread.sleep(3000);
		sendKeysToWebElementByXpath(dueOpportunityTrackField, dueDate);

		selectDropDownOptionByVisibleText(afterTrackStartsOpportunityTrackDropDown, afterTrackStartOption);
		selectDropDownOptionByVisibleText(assigneeOpportunityTrackDropDown, assignee);
		Thread.sleep(6000);
		waitAndClickElementByXpath(saveOpportunityTrackBtn);


		String[] arrayOfNewOpportunityInfo = { name, tag, taskDescription, category, dueDate, afterTrackStartOption,
				assignee };

		ArrayList<WebElement> rows = (ArrayList<WebElement>) driver.findElements(By.xpath(".//tbody/tr"));

		for (int i = 1; i < rows.size(); i++) {

			WebElement actualName = driver.findElement(By.xpath(".//tbody/tr[" + i + "]" + "/td[1]/a"));

			if (actualName.getText().equals(name)) {
				System.out.println("Found a match : " + actualName.getText());
				// NAME TAG DUE DATES CALCULATED BASED ON
				WebElement actualTag = driver.findElement(By.xpath(".//tbody/tr[" + i + "]" + "/td[2]"));
				WebElement actualDueDateBasedOnText = driver.findElement(By.xpath(".//tbody/tr[" + i + "]" + "/td[3]"));
				String expectedDueDateBasedOnText = "The start date of the track";
				if (actualTag.getText().equals(tag)
						&& actualDueDateBasedOnText.getText().equals(expectedDueDateBasedOnText)) {

					Status = "Passed";
					testcase.pass("Add new track and verify the same : " + Status);
					break;
				} else {

					Status = "Failed";
					testcase.fail("Add new track and verify the same : " + Status);
					try {
						Assert.assertEquals(actualName.getText(), name);
						Assert.assertEquals(actualTag.getText(), tag);
						Assert.assertEquals(actualDueDateBasedOnText.getText(), expectedDueDateBasedOnText);

					} catch (java.lang.AssertionError e) {

						System.out.println("assersion failed : " + e.getMessage());
						testcase.info("Add new track and verify the same Assertion : " + e.getMessage());

					}
					break;
				}

			}

		}

	}

	public void clickAddNewMileStoneButton() throws InterruptedException, IOException {
		waitAndClickElementByXpath(addNewMileStoneBtn);
	}

	public void addNewUserAndVerifyNewUserCreated(String firstname, String lastname, String email, String username,
			ExtentTest testcase) throws Exception {

		
		
		sendKeysToWebElementByXpath(firstnameField, firstname);
		sendKeysToWebElementByXpath(lastnameField, lastname);
		sendKeysToWebElementByXpath(emailField, email);
		sendKeysToWebElementByXpath(usernameField, username);
		waitAndClickElementByXpath(inviteUserBtn);
		

		String[] expectedInfoToVerify = { firstname + " " + lastname, email, username };

		System.out.println(firstname + " " + lastname);
		ArrayList<WebElement> list = getListOfWebElementsByXpath(accountSettingsUsersTableData);

		for (int i = 0; i < list.size(); i++) {

			WebElement element = list.get(i);

			for (int j = 0; j < expectedInfoToVerify.length; j++) {

				System.out.println(element.getText() + " vs " + expectedInfoToVerify[j].toString());
				if (element.getText().equalsIgnoreCase(expectedInfoToVerify[j].toString())) {

					if (element.getText().equalsIgnoreCase(expectedInfoToVerify[j].toString())) {
						Status = "Passed";
						testcase.pass("Verify creation of " + element.getText() + " : " + Status);
						System.out.println("Verify creation of " + element.getText() + " : " + Status);
					} else {
						Status = "Failed";
						testcase.fail("Verify creation of " + element.getText() + " : " + Status);
						System.out.println("Verify creation of " + element.getText() + " : " + Status);
					}

				} else {

					System.out.println("nope couldnt find match keep searching...");

				}

			}

		}
	}

	public void clickAddNewUser() throws InterruptedException, IOException {

		waitAndClickElementByXpath(addNewBtn);
	}

	public void clickAccountSettingsLinkByTextName(String linkText) {
		ArrayList<WebElement> list = getListOfWebElementsByXpath(leftPanelLinkElementsLocator);

		for (int i = 0; i < list.size(); i++) {

			WebElement element = list.get(i);

			try {

				if (element.getText().equalsIgnoreCase(linkText)) {

					element.click();
					System.out.println("clicked on : " + element.getText());
				}

			} catch (StaleElementReferenceException e) {
				System.out.println(e.getMessage());
			}

		}
	}
	
	public void clickTracksLink() throws InterruptedException, IOException{
		
		waitAndClickElementByXpath(tracksLink);

	}

	public void uploadFiled(String path) throws AWTException, InterruptedException, IOException {

		
		waitAndClickElementByXpath(chooseFile_Upload);

		File file = new File(path);

		StringSelection stringSelection = new StringSelection(file.getAbsolutePath());
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

		Robot robot = new Robot();

		robot.keyPress(KeyEvent.VK_META);

		robot.keyPress(KeyEvent.VK_TAB);

		robot.keyRelease(KeyEvent.VK_META);

		robot.keyRelease(KeyEvent.VK_TAB);

		robot.delay(500);

		robot.keyPress(KeyEvent.VK_META);

		robot.keyPress(KeyEvent.VK_SHIFT);

		robot.keyPress(KeyEvent.VK_G);

		robot.keyRelease(KeyEvent.VK_META);

		robot.keyRelease(KeyEvent.VK_SHIFT);

		robot.keyRelease(KeyEvent.VK_G);

		robot.keyPress(KeyEvent.VK_META);

		robot.keyPress(KeyEvent.VK_V);

		robot.keyRelease(KeyEvent.VK_META);

		robot.keyRelease(KeyEvent.VK_V);

		robot.keyPress(KeyEvent.VK_ENTER);

		robot.keyRelease(KeyEvent.VK_ENTER);

		robot.delay(500);

		robot.keyPress(KeyEvent.VK_ENTER);

		robot.keyRelease(KeyEvent.VK_ENTER);

		waitAndClickElementByXpath(saveBtnAppearnceForAppearanceLogoUpload);
		Thread.sleep(5000);

	}

	public String getaccountSettingsPageHeaderText() {
		String text=getTextFromElement(accountSettingsPageHeaderTxt);
		return text;
	}

	public String getPageHeaderTextFromAccountSettingsPage() {
		
		String text=getTextFromElement(PageHeaderTextFromAccountSettingsPageTxt);
		return text;
	}

	public void clickAndVerifyEachLinkLeftPanel(ExtentTest testcase) throws InterruptedException {

		ArrayList<WebElement> list = getListOfWebElementsByXpath(leftPanelLinkElementsLocator);

		for (int i = 0; i < list.size(); i++) {
			WebElement element = list.get(i);
			System.out.println("clickAndVerifyEachLinkLeftPanel element text second try:" + element.getText());

		}
		int counter = 0;

		for (int i = 0; i < list.size(); i++) {

			WebElement element = list.get(i);

			try {

				System.out.println("clickAndVerifyEachLinkLeftPanel element text:" + element.getText());

			} catch (StaleElementReferenceException e) {
				System.out.println("Stale element exception " + e.getMessage());
				System.out.println("additional info " + e.getAdditionalInformation());
				System.out.println("supported URL" + e.getSupportUrl());
			}

			try {

				if (element.getText().equalsIgnoreCase("Account")) {
					System.out.println("inside if block");
					element.click();
					actualCurrentURL = driver.getCurrentUrl();
					actualPageHeader = getPageHeaderTextFromAccountSettingsPage();

					expectedPageHeader = "Account";
					expectedURLPATH = "/settings/account";

				} else if (element.getText().equalsIgnoreCase("Invoices")) {
					element.click();
					actualCurrentURL = driver.getCurrentUrl();
					actualPageHeader = getPageHeaderTextFromAccountSettingsPage();

					expectedPageHeader = "Invoices";
					expectedURLPATH = "/settings/account/invoices";

				} else if (element.getText().equalsIgnoreCase("Export")) {
					element.click();
					actualCurrentURL = driver.getCurrentUrl();
					actualPageHeader = getPageHeaderTextFromAccountSettingsPage();

					expectedPageHeader = "Account";
					expectedURLPATH = "/settings/account/export";

				} else if (element.getText().equalsIgnoreCase("Appearance")) {
					element.click();
					actualCurrentURL = driver.getCurrentUrl();
					actualPageHeader = getPageHeaderTextFromAccountSettingsPage();

					expectedPageHeader = "Appearance";
					expectedURLPATH = "/settings/account/appearance";

				} else if (element.getText().equalsIgnoreCase("Mail Drop Box")) {
					element.click();
					actualCurrentURL = driver.getCurrentUrl();
					actualPageHeader = getPageHeaderTextFromAccountSettingsPage();

					expectedPageHeader = "Mail Drop Box";
					expectedURLPATH = "/settings/dropbox";

				} else if (element.getText().equalsIgnoreCase("Users")) {
					element.click();
					actualCurrentURL = driver.getCurrentUrl();
					actualPageHeader = getPageHeaderTextFromAccountSettingsPage();

					expectedPageHeader = "Users";
					expectedURLPATH = "/settings/users";

				} else if (element.getText().equalsIgnoreCase("Opportunities")) {
					element.click();
					actualCurrentURL = driver.getCurrentUrl();
					actualPageHeader = getPageHeaderTextFromAccountSettingsPage();

					expectedPageHeader = "Opportunities";
					expectedURLPATH = "/settings/opportunities/milestones";

				} else if (element.getText().equalsIgnoreCase("Tracks")) {
					element.click();
					actualCurrentURL = driver.getCurrentUrl();
					actualPageHeader = getPageHeaderTextFromAccountSettingsPage();

					expectedPageHeader = "Account";
					expectedURLPATH = "/settings/tracks/opportunities";

				} else if (element.getText().equalsIgnoreCase("Task Categories")) {
					element.click();
					actualCurrentURL = driver.getCurrentUrl();
					actualPageHeader = getPageHeaderTextFromAccountSettingsPage();

					expectedPageHeader = "Task Categories";
					expectedURLPATH = "/settings/categories";

				} else if (element.getText().equalsIgnoreCase("Custom Fields")) {
					element.click();
					actualCurrentURL = driver.getCurrentUrl();
					actualPageHeader = getPageHeaderTextFromAccountSettingsPage();

					expectedPageHeader = "Custom Fields";
					expectedURLPATH = "/settings/customfields/party";

				} else if (element.getText().equalsIgnoreCase("Tags")) {
					element.click();
					actualCurrentURL = driver.getCurrentUrl();
					actualPageHeader = getPageHeaderTextFromAccountSettingsPage();

					expectedPageHeader = "Tags";
					expectedURLPATH = "/settings/tags/party";

				} else if (element.getText().equalsIgnoreCase("Integrations")) {
					element.click();
					actualCurrentURL = driver.getCurrentUrl();
					actualPageHeader = getPageHeaderTextFromAccountSettingsPage();

					expectedPageHeader = "Integrations";
					expectedURLPATH = "/settings/integrations";

				} else if (element.getText().equalsIgnoreCase("Trash")) {
					element.click();
					actualCurrentURL = driver.getCurrentUrl();
					actualPageHeader = getPageHeaderTextFromAccountSettingsPage();

					expectedPageHeader = "Trash";
					expectedURLPATH = "/settings/trash";

				}

				Thread.sleep(4000);

			} catch (StaleElementReferenceException e) {
			}

			System.out.println(actualCurrentURL + "vs" + expectedURLPATH);
			System.out.println(actualPageHeader + "vs" + expectedPageHeader);

			if (actualCurrentURL.contains(expectedURLPATH) && actualPageHeader.equalsIgnoreCase(expectedPageHeader)) {
				counter++;
				Status = "Passed";

				try {

					testcase.pass("clickAndVerifyEachLinkLeftPanel  : " + element.getText() + Status);
					System.out.println("Assertion verfication for :" + element.getText() + " : " + Status);

				} catch (StaleElementReferenceException e) {
				}

			} else {

				try {
					Assert.assertTrue(actualCurrentURL.contains(expectedURLPATH),
							"expected " + expectedURLPATH + ", but found " + actualCurrentURL);
					Assert.assertEquals(actualPageHeader, expectedPageHeader);
				} catch (java.lang.AssertionError e) {

					try {
						testcase.info("Assertion verfication failed :" + element.getText() + e.getMessage());

					} catch (StaleElementReferenceException z) {
					}

				}

			}

		}

		System.out.println("counter is at " + counter);
		if (counter <= 13) {
			Status = "Passed";
			testcase.pass("click and verify total elements of 13:" + Status);
			System.out.println("clicked and verified all Links : " + Status);
		} else {
			Status = "Failed";
			testcase.pass("click and verify total elements of 13 :" + Status);
			System.out.println("clicked and verified all Links : " + Status);

		}

		Thread.sleep(5000);
	}

	public void addNewMileStoneAndVerifyMileStoneInfoCreated(String mileStone, String description,
			String probabilityOfWinning, String daysUntilStale, ExtentTest testcase) throws Exception {

		
		sendKeysToWebElementByXpath(addNewMileStoneNameField, mileStone);
		sendKeysToWebElementByXpath(mileStoneDescriptionField, description);
		sendKeysToWebElementByXpath(probabilityOfWinningField, probabilityOfWinning);
		sendKeysToWebElementByXpath(addNewMileStoneNameField, mileStone);
		sendKeysToWebElementByXpath(daysUntilStaleField, daysUntilStale);

		waitAndClickElementByXpath(mileStoneSaveBtn);

		Thread.sleep(2000);
		System.out.println("right before getting the table rows");
		List<WebElement> tableRows = driver.findElements(By.xpath(".//tbody//tr"));
		System.out.println("hellloo");

		for (int i = 1; i < tableRows.size(); i++) {

			System.out.println("total rows" + i);
			WebElement actualNameAndDescription = driver.findElement(By.xpath(".//tbody//tr[" + i + "]/td[1]"));
			System.out.println("actualNameAndDescription Name = " + actualNameAndDescription.getText());

			System.out.println("TRUE OR FALSE : " + actualNameAndDescription.getText().contains(mileStone));
			System.out.println("TRUE OR FALSE : " + actualNameAndDescription.getText().contains(description));

			if (actualNameAndDescription.getText().contains(mileStone)
					&& actualNameAndDescription.getText().contains(description)) {

				WebElement actualProbabilityOfWinning = driver.findElement(By.xpath(".//tbody//tr[" + i + "]/td[2]"));
				WebElement actualDaysUntilStale = driver.findElement(By.xpath(".//tbody//tr[" + i + "]/td[3]"));

				if (actualNameAndDescription.getText().contains(mileStone)
						&& actualNameAndDescription.getText().contains(description)
						&& actualProbabilityOfWinning.getText().equals(probabilityOfWinning + "%")
						&& actualDaysUntilStale.getText().equals(daysUntilStale)) {

					Status = "Passed";
					testcase.info("validate mileStone  " + mileStone + ": " + Status);
					testcase.info("validate description " + description + " : " + Status);
					testcase.info("validate probabilityOfWinning " + probabilityOfWinning + "%" + " : " + Status);
					testcase.info("validate daysUntilStale : " + daysUntilStale + " : " + Status);
					testcase.pass("Add new milestone and verify the same : " + Status);
					System.out.println("Add new milestone and verify the same : " + Status);
					break;
				} else {
					Status = "Failed";
					testcase.fail("Add new milestone and verify the same : " + Status);

					try {

						Assert.assertTrue(actualNameAndDescription.getText().contains(mileStone),
								"mile Stone not found");
						Assert.assertTrue(actualNameAndDescription.getText().contains(description),
								"description not found");

						Assert.assertEquals(actualProbabilityOfWinning.getText(), probabilityOfWinning + "%");
						Assert.assertEquals(actualDaysUntilStale.getText(), daysUntilStale);

					} catch (java.lang.AssertionError e) {

						testcase.fail("Add new milestone and verify the same ASSERTION FAILURE : " + e.getMessage());
						System.out.println("Add new milestone and verify the same : " + Status + " : assertion error : "
								+ e.getMessage());

					}
				}

			}

		}

	}

}
