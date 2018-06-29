
package com.macys.mst.FreightOptimization.stepdefs;

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

public class AFOJ45 {

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

    @Then("click on submit button")
    public void clickonsubmitbutton()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

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

    @Then("validate all the fields are cleared on DCTODC page")
    public void validateallthefieldsareclearedonDCTODCpage()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

}
