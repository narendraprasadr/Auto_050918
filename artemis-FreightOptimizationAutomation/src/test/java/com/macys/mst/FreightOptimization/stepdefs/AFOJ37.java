
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

public class AFOJ37 {

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

    @Then("select DCTODC radio button")
    public void selectDCTODCradiobutton()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

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

}
