package com.macys.mst.macysnet.stepdefs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.macys.mst.artemis.kdddriver.FetchExampleTable;
import com.macys.mst.artemis.reports.StepDetail;
import com.macys.mst.artemis.selenium.LocalDriverManager;
import com.macys.mst.artemis.selenium.WebDriverListener;
import com.macys.mst.artemis.selenium.actions.SeleniumElements;
import com.macys.mst.macysnet.Actionkeywords.MISActionkeywords;
import com.macys.mst.macysnet.MISObjects.Invoice;
import com.macys.mst.macysnet.MISObjects.InvoicesUI;
import com.macys.mst.macysnet.utils.General;
import com.macys.mst.macysnet.utils.RestServicesUtils;

import org.apache.log4j.Logger;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MIS {

	private ExamplesTable actionstable;

	private static Logger logger = Logger.getLogger(MIS.class.getName());
	private List tablemaprows;

	private Map examplesmap;

	private ConcurrentHashMap obj;

	private WebDriverListener WbDrvrListener;

	static final WebDriver gbldriver = LocalDriverManager.getInstance().getDriver();

	public Long TestNGThreadID = Thread.currentThread().getId();

	@BeforeStory
	public void beforeStory() {
		ConcurrentHashMap<String, String> obj = WebDriverListener.EnvMap.get(TestNGThreadID);
		WebDriverListener.EnvMap.put((Thread.currentThread().getId()), obj);
	}

	@Given("vendor user is on <URL> AccountsPayable screen")
	public void vendoruserisonURLAccountsPayablescreen() throws Exception {
		FetchExampleTable.sendInputActions(gbldriver, null);
	}

	@Then("AP Query option is available for the <user>")
	public void APQueryoptionisavailablefortheuser() throws Exception {
		FetchExampleTable.sendInputActions(gbldriver, null);
	}

	@Then("user clicks on AP Query option")
	public void userclicksonAPQueryoption() throws Exception {
		FetchExampleTable.sendInputActions(gbldriver, null);
	}

	@Then("AP Query Screen has displayed")
	public void APQueryScreenhasdisplayed() throws Exception {
		FetchExampleTable.sendInputActions(gbldriver, null);
	}

	@Then("Activity widget <Activities> has displayed on AP Query Screen")
	public void ActivitywidgetActivitieshasdisplayedonAPQueryScreen() throws Exception {
		FetchExampleTable.sendInputActions(gbldriver, null);
	}

	@When("user selects Status of Invoice option from Activity")
	public void userselectsStatusofInvoiceoptionfromActivity() throws Exception {
		FetchExampleTable.sendInputActions(gbldriver, null);
	}

	@Then("validate Division, Account Number, Invoice Number widgets are displayed")
	public void validateDivisionAccountNumberInvoiceNumberwidgetsaredisplayed() throws Exception {
		FetchExampleTable.sendInputActions(gbldriver, null);
	}

	@Given("user on AP query screen to validate the <activity> for <division> division")
	public void useronAPqueryscreentovalidatetheactivityfordivisiondivision() throws Exception {
		FetchExampleTable.sendInputActions(gbldriver, null);
	}

	@When("user enter the Account no Invoice no and click on submit button")
	public void userentertheAccountnoaccountNoInvoicenoinvoiceNoandclickonsubmitbutton() throws Exception {
		// Thread.sleep(5000);
		FetchExampleTable.sendInputActions(gbldriver, null);
	}

	@Then("validate the results of Invoice Status")
	public void validatetheresultsofInvoiceStatus() throws Exception {
		FetchExampleTable.sendInputActions(gbldriver, null);
	}

	@Given("Activity widget has displayed on AP Query Screen")
	public static void ActivitywidgethasdisplayedonAPQueryScreen() {
		FetchExampleTable.sendInputActions(gbldriver, null);
	}

	@When("user selects Check Detail option from Activity")
	public static void userselectsCheckDetailoptionfromActivity() {
		FetchExampleTable.sendInputActions(gbldriver, null);
	}

	@Then("validate Division, Account Number, Check Number widgets are displayed")
	public static void validateDivisionAccountNumberCheckNumberwidgetsaredisplayed() {
		FetchExampleTable.sendInputActions(gbldriver, null);
	}

	@When("user enter the Account no <accountNo>, Invoice no <invoiceNo> and click on submit button")
	public static void userentertheAccountnoaccountNochecknoinvoiceNoandclickonsubmitbutton() {
		FetchExampleTable.sendInputActions(gbldriver, null);
	}

	@Then("validate the results of Check Detail screen")
	public static void validatetheresultsofCheckDetailscreen() {
		FetchExampleTable.sendInputActions(gbldriver, null);
	}

	@Given("Activity <Activities> has displayed on AP Query Screen")
	public static void ActivityActivitieshasdisplayedonAPQueryScreen() {
		FetchExampleTable.sendInputActions(gbldriver, null);
	}

	@When("user selects <activity> from Activity")
	public static void userselectsactivityfromActivity() {
		FetchExampleTable.sendInputActions(gbldriver, null);
	}

	@Then("Division widget <Division> has displayed on AP Query Screen")
	public static void DivisionwidgetDivisionhasdisplayedonAPQueryScreen() {
		FetchExampleTable.sendInputActions(gbldriver, null);
	}

	@Then("validate Account Number, Check Number widgets are displayed")
	public static void validateAccountNumberCheckNumberwidgetsaredisplayed() {
		FetchExampleTable.sendInputActions(gbldriver, null);
	}

	@When("user enter the Account no <accountNo> and click on submit button")
	public static void userentertheAccountnoaccountNoandclickonsubmitbutton() {
		FetchExampleTable.sendInputActions(gbldriver, null);
	}

	@Then("validate the results of <activity>")
	public static void validatetheresultsofactivity() {
		FetchExampleTable.sendInputActions(gbldriver, null);
	}

	@Given("REST service return code 200")
	public static void RESTservicereturncode() {
		FetchExampleTable.sendInputActions(gbldriver, null);
	}

	@Then("Get the values from Service and Database")
	public static void GetthevaluesfromServiceandDatabase() {
		FetchExampleTable.sendInputActions(gbldriver, null);
	}

	@Then("Compare the Service values with Database")
	public static void ComparetheServicevalueswithDatabase() {
		FetchExampleTable.sendInputActions(gbldriver, null);
	}

	/*
	 * @When("user enter the Account no <accountNo>, check no <checkNo> and click on submit button"
	 * ) public static void
	 * userentertheAccountnoaccountNochecknocheckNoandclickonsubmitbutton() {
	 * FetchExampleTable.sendInputActions(gbldriver, null); }
	 */
	// MIS-38
	@Given("user on AP query screen$URL")
	public void APQueryScreen(String url) throws Exception {
		SeleniumElements objSeleniumElements = new SeleniumElements();
		gbldriver.get(url);
		WebElement element = objSeleniumElements.Get_Webelement(gbldriver, "lblAPQuery");
		element.click();
		// gbldriver.findElement(General.get_Locator("lblAPQuery")).click();
		// MISRestServices.test();
	}

	@When("When Select activity in AP Query screen")
	public void selectActivitiy() {
		MISActionkeywords.Select_Dropdown_Value(gbldriver, "ddlActivityOptions", "Trial Balance");
	}

	@When("When Select division in AP Query screen")
	public void selectDivision() {
	}

	@When("When Select accountnumber in AP Query screen")
	public void selectaccountnumber() {
	}

	@Given("user on AP query page")
	public static void useronAPquerypage() {
		FetchExampleTable.sendInputActions(gbldriver, null);
	}

	@When("Select activity in AP Query screen")
	public static void SelectactivityinAPQueryscreen() {
		FetchExampleTable.sendInputActions(gbldriver, null);
	}

	@When("Select division in AP Query screen")
	public static void SelectdivisioninAPQueryscreen() {
		FetchExampleTable.sendInputActions(gbldriver, null);
	}

	@Then("Get the accountnumber from AccountNumber dropdown")
	public static void GettheaccountnumberfromAccountNumberdropdown() {
		FetchExampleTable.sendInputActions(gbldriver, null);
	}

	@Then("Compare the UI Accountnbr with Database")
	public static void ComparetheUIAccountnbrwithDatabase() {
		FetchExampleTable.sendInputActions(gbldriver, null);
	}

	@Then("AP Query screen is available in a new tab")
	public static void APQueryscreenisavailableinanewtab() {
		FetchExampleTable.sendInputActions(gbldriver, null);
	}

	@Given("Vendor user is on Macysnet application")
	public static void VendoruserisonMacysnetapplication() {
		FetchExampleTable.sendInputActions(gbldriver, null);
	}

	@Then("AP Query screen is NOT available for the vendor who doesnot have access")
	public static void APQueryscreenisNOTavailableforthevendorwhodoesnothaveaccess() {
		FetchExampleTable.sendInputActions(gbldriver, null);
	}

	@Then("Get the Invoices information from UI")
	public static void GettheInvoicesinformationfromUI() {
		FetchExampleTable.sendInputActions(gbldriver, null);
	}

	@Then("Get the Invoices information from Database")
	public static void GettheInvoicesinformationfromDatabase() throws Exception {
		//String query = "INVOICEUI";
		//MISActionkeywords.Get_Values_From_Database("ORACLE", query);
		//MISActionkeywords.Get_Values_From_DatabaseInObject("ORACLE", query);
		 FetchExampleTable.sendInputActions(gbldriver, null);
	}

	@Then("Compare UI values with Database")
	public static void CompareUIvalueswithDatabase() {
		FetchExampleTable.sendInputActions(gbldriver, null);
		//MISActionkeywords.Compare_UISCREEN_values_With_Database();
		
	}
	
	@Given("user <user> on AP query screen to validate the divison with activity <activity>")
	public void useronAPqueryscreentovalidatetheactivity() throws Exception {
		FetchExampleTable.sendInputActions(gbldriver, null);
	}
	
	@Then("Get the values from Services and Compare the Service values with Database")
	public static void GetthevaluesfromServiceandComparetheservicevalueswithDatabase() {
		FetchExampleTable.sendInputActions(gbldriver, null);
	}
	
}
