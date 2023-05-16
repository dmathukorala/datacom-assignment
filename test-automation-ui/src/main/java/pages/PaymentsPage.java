package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonFunctions;

public class PaymentsPage {
	@SuppressWarnings("unused")
	private WebDriver driver;
	CommonFunctions functions;
	private Logger log = LogManager.getLogger(this.getClass());

	@FindBy(xpath = "//span[contains(text(), 'Payees')]")
	private WebElement pageHeading;

	@FindBy(xpath = "//button[@data-testid = 'from-account-chooser']")
	private WebElement fromAccountChooser;

	@FindBy(xpath = "//button[@data-testid = 'to-account-chooser']")
	private WebElement toAccountChooser;

	@FindBy(xpath = "//input[@placeholder = 'Search']")
	private WebElement inputFromSearch;

	@FindBy(xpath = "//button[@data-monitoring-label= 'Transfer Form Account Card']")
	private WebElement accountCardButton;

	@FindBy(xpath = "//input[@name = 'amount']")
	private WebElement amountElement;

	@FindBy(xpath = "//button[@data-monitoring-label= 'Transfer Form Submit']")
	private WebElement submitButton;

	@FindBy(xpath = "//button[@data-monitoring-label= 'Transfer Form Account Card']/div/div/div[2]/p[2]")
	private WebElement accountBalance;

	public PaymentsPage(WebDriver driver) {
		this.driver = driver;
		functions = new CommonFunctions(driver);
		PageFactory.initElements(driver, this);
	}


	public void clickFromAccountChooser(){
		if(functions.waitForElement(fromAccountChooser))
			fromAccountChooser.click();

	}

	public void clickToAccountChooser(){
		if(functions.waitForElement(toAccountChooser))
			toAccountChooser.click();

	}

	public void searchAccount(String fromAccount){
		if(functions.waitForElement(inputFromSearch))
			inputFromSearch.sendKeys(fromAccount);
	}

	public void clickAccountCard(){
		if(functions.waitForElement(accountCardButton))
			accountCardButton.click();
	}

	public void enterAmount(Double amountValue){
		if(functions.waitForElement(amountElement))
			amountElement.sendKeys(amountValue.toString());
	}

	public void clickSubmitButton(){
		if(functions.waitForElement(submitButton))
			submitButton.click();
	}

	public Double getAccountBalance(){
		Double balance = null;
		if(functions.waitForElement(accountBalance)) {
			balance = Double.valueOf(accountBalance.getText().substring(0, accountBalance.getText().indexOf(".")).replaceAll("[^\\d.]", ""));
		}
		return balance;
	}

}
