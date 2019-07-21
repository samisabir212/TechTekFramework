package ParabankPageObjects;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import DriverMaster.MasterWebDriver;

public class ActivityPage extends MasterWebDriver {

	public ActivityPage() throws IOException {
		super();
	}
	
	public static String balanceTxtValue = ".//*[@id='transactionTable']/tbody/tr[1]/td[2]/a";
	public static String firstTransactionLink = ".//*[@id='transactionTable']/tbody/tr[1]/td[2]/a";
	public static String debitAmountTxtValue = ".//*[@id='transactionTable']/tbody/tr[1]/td[3]";

	
	
	public void clickFirstTransactionLink() throws InterruptedException, IOException {
		
		
		//clickOnElementUsingCustomTimeout(firstTransactionLink, driver, 10);
		waitAndClickElementByXpath(firstTransactionLink);
	}
	
	public String getBalanceTxtValue() {
		
		String text = getTextFromElement(balanceTxtValue);
		return text;
		
	}
	
	public void validateWithdrawlDebitAmount(String expectedWithdrawlAmount) throws IOException {
		
		System.out.println("expectedWithdrawlAmount :"+ expectedWithdrawlAmount);
		String actualDebitAmountText = getTextFromElement(debitAmountTxtValue);
		System.out.println("actualDebitAmount : "+ actualDebitAmountText);
		try {
			
			Assert.assertEquals(actualDebitAmountText, expectedWithdrawlAmount);

		}catch(java.lang.AssertionError e) {
			
			COMMENT = e.getMessage();

		}

	}
	

}
