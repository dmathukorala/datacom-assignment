package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonFunctions;

public class HomePage {
	@SuppressWarnings("unused")
	private WebDriver driver;
	private PayeesPage payeesPage;
	private CommonFunctions functions;
	private Logger log = LogManager.getLogger(this.getClass());

	@FindBy(xpath = "//button[@aria-label='Attention! Main menu with 1 notification']")
	private WebElement menuButton;

	@FindBy(xpath = "//span[contains(text(), 'Payees')]")
	private WebElement payeesButton;

	@FindBy(xpath = "//span[text() = 'Pay or transfer']")
	private WebElement payOrTransferButton;


	public HomePage(WebDriver driver) {
		this.driver = driver;
		functions = new CommonFunctions(driver);
		PageFactory.initElements(driver, this);
	}

	/**
	 * click search button
	 */
	public PayeesPage clickMenuButton() {
		if (functions.waitForElement(menuButton)) {
			log.info("Menu Button Found");
			menuButton.click();
		}
		return new PayeesPage(driver);
	}

	public PayeesPage clickPayeesButton() {
		if (functions.waitForElement(payeesButton)) {
			log.info("Payees Button Found");
			functions.jsClick(payeesButton);
		}
		return new PayeesPage(driver);
	}

	public PaymentsPage clickPayAndTransferButton() {
		if (functions.waitForElement(payOrTransferButton)) {
			log.info("Payments Button Found");
			payOrTransferButton.click();
		}
		return new PaymentsPage(driver);
	}

}
