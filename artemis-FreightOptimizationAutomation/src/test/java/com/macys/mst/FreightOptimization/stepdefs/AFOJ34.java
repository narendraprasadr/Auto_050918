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

public class AFOJ34 {

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

    @Given("Admin user is on freight movement page")
    public void Adminuserisonfreightmovementpage() throws Exception {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @When("select DCTODC radio button")
    public void selectDCTODCradiobutton() throws Exception {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("Verify All the Origin address are populated")
    public void VerifyAlltheOriginaddressarepopulated() throws Exception {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }

    @Then("Verify user to select a single origin address")
    public void Verifyusertoselectasingleoriginaddress() throws Exception {
        FetchExampleTable.sendInputActions(gbldriver, null);
    }
}
