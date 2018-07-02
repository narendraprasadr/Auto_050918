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

    private static final WebDriver gbldriver = LocalDriverManager.getInstance().getDriver();

    public Long TestNGThreadID = Thread.currentThread().getId();

    private Map currentStepRow;

    @BeforeStory
    public void beforeStory() {
        ConcurrentHashMap<String, String> obj = WebDriverListener.EnvMap.get(TestNGThreadID);
        WebDriverListener.EnvMap.put((Thread.currentThread().getId()), obj);
    }

    @Given("user is on DC to DC freight movement page")
    public void userisonpageFreightMovementRequest() throws Exception {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Given("Admin user is on freight movement page")
    public static void Adminuserisonfreightmovementpage() {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @When("Admin user selects DC to DC radio button")
    public static void AdminuserselectsDCtoDCradiobutton() {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("validate the MandatoryFields in DC To DC Pick Up Request page")
    public static void validatetheMandatoryFieldsinDCToDCPickUpRequestpage() {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("validate the OptionalFields in DC To DC Pick Up Request page")
    public static void validatetheOptionalFieldsinDCToDCPickUpRequestpage() {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("validate fields DefaultValue")
    public static void validatefieldsDefaultValue() {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Given("Admin user is on freight movement page and select DC To DC")
    public static void AdminuserisonfreightmovementpageandselectDCToDC() {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }
}
