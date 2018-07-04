
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
import org.jbehave.core.model.ExamplesTable;
import org.openqa.selenium.WebDriver;

public class AFOJ15 {

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

    @Given("user logged in as Admin user")
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

    @Given("user logged in as Vendor user")
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

}
