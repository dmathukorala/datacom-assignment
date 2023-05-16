package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonFunctions;

public class KompanyPage {
	@SuppressWarnings("unused")
	private WebDriver driver;
	CommonFunctions functions;
	private Logger log = LogManager.getLogger(this.getClass());

	@FindBy(xpath = "//dd[@class=' text-capitalize  '][contains(text(), 'ATU')]")
	private WebElement registrationNumber;

	public KompanyPage(WebDriver driver) {
		this.driver = driver;
		functions = new CommonFunctions(driver);
		PageFactory.initElements(driver, this);
	}

	/**
	 * returns first search result
	 *
	 * @return - String
	 */

	public String getRegistrationNumber() {
		String registrationNumber = null;
		if (functions.waitForElement(this.registrationNumber)) {
			registrationNumber = this.registrationNumber.getText().toString();
		}
		return registrationNumber;
	}

}
