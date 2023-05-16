package tests;

import config.DriverConfig;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.PayeesPage;
import pages.HomePage;
import pages.PaymentsPage;
import utilities.CommonFunctions;

@Listeners(listeners.TestNGListener.class)
public class DemoBNZTest extends DriverConfig {
	public WebDriver driver;
	private CommonFunctions functions;
	private Logger log = LogManager.getLogger(this.getClass());

	/**
	 * initializes driver
	 *
	 */
	@BeforeTest
	public void setup() {
		driver = initializeDriver();
		functions = new CommonFunctions(driver);
	}

	/**
	 * launches home page URL
	 *
	 */
	@BeforeMethod
	public void launchURL() {
		String url = getUrl("url");
		log.info("Launching URL: " + url);
		driver.get(url);
	}

	/**
	 * actual test, creates objects of pages, runs end-to-end and asserts the result
	 *
	 */
	@Test
	public void tc1_verify_navigate_to_payees_page() {
		HomePage homePage = new HomePage(driver);

		homePage.clickMenuButton();

		PayeesPage payeesPage = homePage.clickPayeesButton();

		Assert.assertEquals(payeesPage.getPageHeading(), "Payees");

		Assert.assertEquals(driver.getCurrentUrl(), "https://demo.bnz.co.nz/client/payees");

	}

	@Test
	public void tc2_verify_add_new_payee() {
		HomePage homePage = new HomePage(driver);

		homePage.clickMenuButton();

		PayeesPage payeesPage = homePage.clickPayeesButton();

		payeesPage.clickAddButton();

		String name = RandomStringUtils.random(10, true, false);

		payeesPage.setInputName(name);

		payeesPage.setAccountNumber("12", "1234", "1234567", "011");

		payeesPage.clickSubmitButton();

		Assert.assertTrue(payeesPage.isSuccessMessageAvailable());

		Assert.assertTrue(payeesPage.searchPayees(name));



	}

	@Test
	public void tc3_verify_payee_name_is_required_field() {
		HomePage homePage = new HomePage(driver);

		homePage.clickMenuButton();

		PayeesPage payeesPage = homePage.clickPayeesButton();

		payeesPage.clickAddButton();

		payeesPage.clickSubmitButton();

		Assert.assertEquals(payeesPage.getErrorMessage(), "A problem was found. Please correct the field highlighted below.");

		Assert.assertEquals(payeesPage.getNameErrorAriaLabel(), "Payee Name is a required field. Please complete to continue.");

		payeesPage.setInputName(RandomStringUtils.random(10, true, false));

		payeesPage.setAccountNumber("12", "1234", "1234567", "011");

		Assert.assertEquals(payeesPage.getNameErrorAriaLabel(), null);

	}

	@Test
	public void tc4_verify_payee_list_is_ordered() {
		HomePage homePage = new HomePage(driver);

		homePage.clickMenuButton();

		PayeesPage payeesPage = homePage.clickPayeesButton();

		payeesPage.clickAddButton();

		String name = RandomStringUtils.random(10, true, false);

		payeesPage.setInputName(name);

		payeesPage.setAccountNumber("12", "1234", "1234567", "011");

		payeesPage.clickSubmitButton();

		Assert.assertTrue(functions.isAscending(payeesPage.getNameElements()));

		payeesPage.clickNameButton();

		Assert.assertTrue(functions.isDescending(payeesPage.getNameElements()));



	}

	@Test
	public void tc5_verify_transfer_funds() {

		HomePage homePage = new HomePage(driver);

		homePage.clickMenuButton();

		PaymentsPage paymentsPage = homePage.clickPayAndTransferButton();

		paymentsPage.clickFromAccountChooser();

		paymentsPage.searchAccount("Everyday");

		Double everyDayBalance = paymentsPage.getAccountBalance();

		paymentsPage.clickAccountCard();

		paymentsPage.clickToAccountChooser();

		paymentsPage.searchAccount("Bills");

		Double billsBalance = paymentsPage.getAccountBalance();

		paymentsPage.clickAccountCard();

		paymentsPage.enterAmount(500.00);

		paymentsPage.clickSubmitButton();

		//verify the transfer

		homePage.clickMenuButton();

		paymentsPage = homePage.clickPayAndTransferButton();

		paymentsPage.clickFromAccountChooser();

		paymentsPage.searchAccount("Everyday");

		Assert.assertEquals(paymentsPage.getAccountBalance(), everyDayBalance - 500.00);

		paymentsPage.clickAccountCard();

		paymentsPage.clickToAccountChooser();

		paymentsPage.searchAccount("Bills");

		Assert.assertEquals(paymentsPage.getAccountBalance(), billsBalance + 500.00);

		paymentsPage.clickAccountCard();

	}


	/**
	 * closes driver
	 */
	@AfterTest
	public void tearDown() {
		if (driver != null) {
			log.debug("End of Test. Closing driver" + System.lineSeparator());
			driver.close();
		}
	}

}
