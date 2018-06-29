
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

public class AFOJ32 {

    private ExamplesTable actionstable;
    private List tablemaprows;
    private Map examplesmap;
    private ConcurrentHashMap obj;
    private WebDriverListener WbDrvrListener;
    private final static WebDriver gbldriver = LocalDriverManager.getInstance().getDriver();
    public Long TestNGThreadID = Thread.currentThread().getId();
    private Map currentStepRow;

    @BeforeStory
    public void beforeStory() {
        ConcurrentHashMap<String,String> obj = WebDriverListener.EnvMap.get(TestNGThreadID);
        WebDriverListener.EnvMap.put((Thread.currentThread().getId()), obj);
    }

    @Given("user is on page Freight Movement Request")
    public void userisonpageFreightMovementRequest()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @When("select DCTODC radio button")
    public void selectDCTODCradiobutton()
        throws Exception
    {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("validate the below fields are mandatory in DC To DC Pick Up Request page: $actionstable")
    public void validatethebelowfieldsaremandatoryinDCToDCPickUpRequestpage(ExamplesTable actionstable)
        throws Exception
    {
        List<Map<String, String>> tablemaprows = actionstable.getRows();
        for (Map<String, String> currentStepRow: tablemaprows) {
            FetchExampleTable.sendInputActions(gbldriver, currentStepRow);
        }
    }

    public void verifytheOptionalfieldsinDCToDCPickUpRequestpage(ExamplesTable actionstable)
        throws Exception
    {
        List<Map<String, String>> tablemaprows = actionstable.getRows();
        for (Map<String, String> currentStepRow: tablemaprows) {
        }
    }

}
