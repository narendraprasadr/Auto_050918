package com.macys.mst.macysnet.stepdefs;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.macys.mst.artemis.kdddriver.FetchExampleTable;
import com.macys.mst.artemis.selenium.LocalDriverManager;
import com.macys.mst.artemis.selenium.WebDriverListener;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.openqa.selenium.WebDriver;

public class MIS {

	private ExamplesTable actionstable;

	private List tablemaprows;

	private Map examplesmap;

	private ConcurrentHashMap obj;

	private WebDriverListener WbDrvrListener;

	private static final WebDriver gbldriver = LocalDriverManager.getInstance().getDriver();

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

	@When("user enter the Account no <accountNo>, check no <checkNo> and click on submit button")
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

	@When("user enter the Account no <accountNo>, check no <checkNo> and click on submit button")
	public static void userentertheAccountnoaccountNochecknocheckNoandclickonsubmitbutton() {
		FetchExampleTable.sendInputActions(gbldriver, null);
	}
}
