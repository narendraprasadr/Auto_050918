package com.macys.mst.FreightOptimization.stepdefs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.log4j.Logger;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.macys.mst.artemis.db.DBConnections;
import com.macys.mst.artemis.db.DBUtils;
import com.macys.mst.artemis.kdddriver.FetchExampleTable;
import com.macys.mst.artemis.reports.StepDetail;
import com.macys.mst.artemis.selenium.LocalDriverManager;
import com.macys.mst.artemis.selenium.SeUiContextBase;
import com.macys.mst.artemis.selenium.WebDriverListener;
import com.macys.mst.FreightOptimization.config.Constants;
import com.macys.mst.FreightOptimization.db.app.DBMethods;
import com.macys.mst.FreightOptimization.pageobjects.LoginPage;
import com.macys.mst.FreightOptimization.pageobjects.MnetHomePage;
import com.macys.mst.FreightOptimization.pageobjects.RequestfreightMovement;
import com.macys.mst.FreightOptimization.utils.General;
import com.macys.mst.FreightOptimization.utils.RestServicesUtils;
import com.macys.mst.FreightOptimization.sqlconstants.SQLConstants;

public class FreightOptimization {

	private ExamplesTable actionstable;
    private List tablemaprows;
    private Map examplesmap;
    private ConcurrentHashMap obj;
    private WebDriverListener WbDrvrListener;
    private final static WebDriver gbldriver = LocalDriverManager.getInstance().getDriver();
    public Long TestNGThreadID = Thread.currentThread().getId();
    
	
    @BeforeStory
    public void beforeStory() {
        ConcurrentHashMap<String,String> obj = WebDriverListener.EnvMap.get(TestNGThreadID);
        WebDriverListener.EnvMap.put((Thread.currentThread().getId()), obj);
    }

    @Given("MacysNet URL is launched")
    public void MacysNetURLislaunched()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @When("user logged in as Admin user")
    public void userloggedinasAdminuser()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("MacysNet home page should be displayed")
    public void MacysNethomepageshouldbedisplayed()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("a new link called Request Freight Movement should be displayed above Document Library on MacysNet Home Page")
    public void anewlinkcalledRequestFreightMovementshouldbedisplayedaboveDocumentLibraryonMacysNetHomePage()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @When("user logged in as Vendor user")
    public void userloggedinasVendoruser()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("a new link called Request Freight Movement should not be displayed above Document Library on MacysNet Home Page")
    public void anewlinkcalledRequestFreightMovementshouldnotbedisplayedaboveDocumentLibraryonMacysNetHomePage()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

   /* @Given("Login to MacysNet application as Admin user")
    public void LogintoMacysNetapplicationasAdminuser()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }*/

   /* @When("click the link Freight Movement Request")
    public void clickthelinkFreightMovementRequest()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("take me to the page Freight Movement Request")
    public void takemetothepageFreightMovementRequest()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }*/

    @Then("validate that CRC/RTV, DC to DC and Non-Retail radio buttons are displayed")
    public void validatethatCRCRTVDCtoDCandNonRetailradiobuttonsaredisplayed()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }
//34
  /*  @Given("Login to MacysNet application as Admin user")
    public void LogintoMacysNetapplicationasAdminuser()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @When("click the link Freight Movement Request")
    public void clickthelinkFreightMovementRequest()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("take me to the page Freight Movement Request")
    public void takemetothepageFreightMovementRequest()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("select DCTODC radio button")
    public void selectDCTODCradiobutton()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }*/

    @Then("Verify All the Origin address are populated")
    public void VerifyAlltheOriginaddressarepopulated()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("Verify user to select a single origin address")
    public void Verifyusertoselectasingleoriginaddress()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }
//35
   
  /*  @Given("Login to MacysNet application as Admin user")
    public void LogintoMacysNetapplicationasAdminuser()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @When("click the link Freight Movement Request")
    public void clickthelinkFreightMovementRequest()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("take me to the page Freight Movement Request")
    public void takemetothepageFreightMovementRequest()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("select DCTODC radio button")
    public void selectDCTODCradiobutton()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }
*/
    @Then("Verify All the Destination address are populated")
    public void VerifyAlltheDestinationaddressarepopulated()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("Verify user to select a single Destination address")
    public void VerifyusertoselectasingleDestinationaddress()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }
    //37
   /* @Given("Login to MacysNet application as Admin user")
    public void LogintoMacysNetapplicationasAdminuser()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @When("click the link Freight Movement Request")
    public void clickthelinkFreightMovementRequest()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("take me to the page Freight Movement Request")
    public void takemetothepageFreightMovementRequest()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("select DCTODC radio button")
    public void selectDCTODCradiobutton()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }
*/
    @Then("validate a free form text box called Driver Notes that allows the user to input data")
    public void validateafreeformtextboxcalledDriverNotesthatallowstheusertoinputdata()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("verify the maximum text length of characters accepted is 128.")
    public void verifythemaximumtextlengthofcharactersacceptedis()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }
//45
   /* @Given("Login to MacysNet application as Admin user")
    public void LogintoMacysNetapplicationasAdminuser()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @When("click the link Freight Movement Request")
    public void clickthelinkFreightMovementRequest()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("take me to the page Freight Movement Request")
    public void takemetothepageFreightMovementRequest()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }
*/
    @Then("select DC to DC radio button")
    public void selectDCtoDCradiobutton()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("user user enter all mandatory fields expect Trailer Class")
    public void useruserenterallmandatoryfieldsexpectTrailerClass()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

   /* @Then("click on submit button")
    public void clickonsubmitbutton()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }
*/
    @Then("verify the mandatory fields which are null are highlight with please enter value")
    public void verifythemandatoryfieldswhicharenullarehighlightwithpleaseentervalue()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("select Origin address")
    public void selectOriginaddress()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("select Destination address")
    public void selectDestinationaddress()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("Verify Origin and Destination Should not be same message is display")
    public void VerifyOriginandDestinationShouldnotbesamemessageisdisplay()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("user user enter all mandatory fields")
    public void useruserenterallmandatoryfields()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("refresh Freight Movement Request page")
    public void refreshFreightMovementRequestpage()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    /*@Then("validate all the fields are cleared on DCTODC page")
    public void validateallthefieldsareclearedonDCTODCpage()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }*/
//57
    /*@Given("Login to MacysNet application as Admin user")
    public void LogintoMacysNetapplicationasAdminuser()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @When("click the link Freight Movement Request")
    public void clickthelinkFreightMovementRequest()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("take me to the page Freight Movement Request")
    public void takemetothepageFreightMovementRequest()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("select DCTODC radio button")
    public void selectDCTODCradiobutton()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }
*/
    @Then("user enter all mandatory fields")
    public void userenterallmandatoryfields()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("click on submit button")
    public void clickonsubmitbutton()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("validate all the fields are cleared on DCTODC page")
    public void validateallthefieldsareclearedonDCTODCpage()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("user enter invalid SCAC Code")
    public void userenterinvalidSCACCode()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }
    //59
    @Given("Login to MacysNet application as Admin user")
    public void LogintoMacysNetapplicationasAdminuser()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @When("click the link Freight Movement Request")
    public void clickthelinkFreightMovementRequest()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("take me to the page Freight Movement Request")
    public void takemetothepageFreightMovementRequest()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("select DCTODC radio button")
    public void selectDCTODCradiobutton()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("Verify All the Origin address are populated from Rest services")
    public void VerifyAlltheOriginaddressarepopulatedfromRestservices()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("Verify All the Destination address are populated from Rest services")
    public void VerifyAlltheDestinationaddressarepopulatedfromRestservices()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("Verify All the Freight Type are populated from Rest services")
    public void VerifyAlltheFreightTypearepopulatedfromRestservices()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("Verify All the Carrier Type are populated from Rest services")
    public void VerifyAlltheCarrierTypearepopulatedfromRestservices()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("Verify All the Trailer Class are populated from Rest services")
    public void VerifyAlltheTrailerClassarepopulatedfromRestservices()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }




























}//end
