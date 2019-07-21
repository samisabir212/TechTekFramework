package CapsuleCRMPageObjects;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import DriverMaster.MasterWebDriver;

public class CasesPage extends MasterWebDriver {

	public CasesPage() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static String addCaseLink = ".//a[contains(text(), 'Add Case')]";
	public static String casesRelatesToTxtField = "//input[@id='partySearch']";
	public static String nameTxtField = "//input[@id='caseNameDecorate:name']";
	public static String descriptionTxtField = "caseDescriptionDecorate:description";
	public static String saveBtn = "save";
	public static String leftPanelForName = "//section[@class='col col-border-right']//div[@class='panel']/div[2]/div/div/span/a";

	public static String caseStatusIcon = "//span[@class='kase-summary-status-open']";

	public String getNameRelatedToCase() {
		
		String text = getTextFromElement(leftPanelForName);
		return text;
	}

	public String getStatusIconText() {
		
		String text = getTextFromElement(caseStatusIcon);
		return text;
	}

	public void clickAddCaseLink() throws InterruptedException, IOException {
		waitAndClickElementByXpath(addCaseLink);
	}

	public void enterNewCase(String personOrOrganization, String OptionWithText, String name, String description)
			throws Exception {

		// get character of the string and for each character thread.sleep
		char[] chars = personOrOrganization.toCharArray();

		String[] s = new String[chars.length];
		for (int i = 0; i < chars.length; i++) {
			s[i] = String.valueOf(chars[i]);
		}

		for (String letter : s) {
			Thread.sleep(400);
			sendKeysToWebElementByXpath(casesRelatesToTxtField, letter);
		}

		Thread.sleep(3000);
		sendKeys(casesRelatesToTxtField, Keys.DOWN);
		sendKeys(casesRelatesToTxtField, Keys.ENTER);

		// selectOptionWithText(OptionWithText);
		Thread.sleep(2000);
		
		
		System.out.println(isElementDislplayed(nameTxtField));
		sendKeysToWebElementByXpath(nameTxtField, name);
		sendKeysToWebElementByXpath(descriptionTxtField, description);

		waitAndClickElementByXpath(saveBtn);

	}
}
