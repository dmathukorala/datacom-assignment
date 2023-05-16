package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonFunctions;

import java.util.ArrayList;
import java.util.List;

public class PayeesPage {
	@SuppressWarnings("unused")
	private WebDriver driver;
	private KompanyPage kompanyPage;
	CommonFunctions functions;
	private Logger log = LogManager.getLogger(this.getClass());

	@FindBy(xpath = "//span[contains(text(), 'Payees')]")
	private WebElement pageHeading;

	@FindBy(xpath = "//span[contains(text(), 'Add')]")
	private WebElement addButton;

	@FindBy(xpath = "//*[@id='ComboboxInput-apm-name']")
	private WebElement inputName;

	@FindBy(xpath = "//*[@id='apm-bank']")
	private WebElement inputBank;

	@FindBy(xpath = "//*[@id='apm-branch']")
	private WebElement inputBranch;

	@FindBy(xpath = "//*[@id='apm-account']")
	private WebElement inputAccount;

	@FindBy(xpath = "//*[@id='apm-suffix']")
	private WebElement inputSuffix;

	@FindBy(xpath = "//button[@class='js-submit Button Button--primary']")
	private WebElement submitButton;

	@FindBy(xpath = "//span[contains(text(), 'Payee added')]")
	private WebElement successMessage;

	@FindBy(xpath = "//input[@placeholder='Search payees']")
	private WebElement searchPayees;

	@FindBy(xpath = "//div[@class='error-header']")
	private WebElement errorMessage;

	@FindBy(xpath = "//span[@class='js-payee-name']")
	private List<WebElement> nameElementList;

	@FindBy(xpath = "//span[text()= 'Name']")
	private WebElement nameButton;

	public PayeesPage(WebDriver driver) {
		this.driver = driver;
		functions = new CommonFunctions(driver);
		PageFactory.initElements(driver, this);
	}


	public String getPageHeading(){
		String heading = null;
		if(functions.waitForElement(pageHeading))
			heading = pageHeading.getText();
		return heading;
	}

	public void clickAddButton(){
		if(functions.waitForElement(addButton))
			addButton.click();
	}

	public void setInputName(String name){
		if(functions.waitForElement(addButton)) {
			inputName.sendKeys(name);
			inputName.sendKeys(Keys.ENTER);
		}

	}

	public void setAccountNumber(String bank, String branch, String account, String suffix){
		if(functions.waitForElement(inputBank) && functions.waitForElement(inputBranch) && functions.waitForElement(inputAccount) && functions.waitForElement(inputSuffix)) {
			inputBank.sendKeys(bank);
			inputBranch.sendKeys(branch);
			inputAccount.sendKeys(account);
			inputSuffix.sendKeys(suffix);
			inputSuffix.sendKeys(Keys.ENTER);
		}

	}

	public void clickSubmitButton(){
		if(functions.waitForElement(submitButton))
			submitButton.click();
	}

	public boolean isSuccessMessageAvailable(){
		if(functions.waitForElement(successMessage))
			return true;
		else
			return false;
	}

	public boolean searchPayees(String payee){

		if(functions.waitForElement(searchPayees)) {
			searchPayees.sendKeys(payee);
		}
		if(functions.waitForElement(driver.findElement(By.xpath("//span[contains(text(), "+payee+")]")))){
			return true;
		}else{
			return false;
		}
	}

	public String getErrorMessage(){
		if(functions.waitForElement(errorMessage))
			return errorMessage.getText();
		else
			return null;
	}

	public String getNameErrorAriaLabel(){
		if(functions.waitForElement(inputName))
			return inputName.getAttribute("aria-label");
		else
			return null;
	}

	public void clickNameButton(){
		if(functions.waitForElement(nameButton))
			nameButton.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

	}

	public List<String> getNameElements(){
		List<String> nameList = new ArrayList<>();
		for(WebElement nameElement : nameElementList){
			nameList.add(nameElement.getText());
		}
		return nameList;
	}

	public KompanyPage clickAndNavigateToKompany(String kompany) {
		WebElement result360kompanyElement = driver.findElement(By.xpath("//div[@title='"+kompany+"']"));
		if (functions.waitForElement(result360kompanyElement)) {
			result360kompanyElement.click();
		}

		return new KompanyPage(driver);
	}

}
