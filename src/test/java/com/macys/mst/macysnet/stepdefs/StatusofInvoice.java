package com.macys.mst.macysnet.stepdefs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.macys.mst.artemis.selenium.LocalDriverManager;
import com.macys.mst.artemis.selenium.WebDriverListener;
import com.macys.mst.macysnet.Actionkeywords.MISActionkeywords;
import com.macys.mst.macysnet.MISObjects.Invoice;
import com.macys.mst.macysnet.MISObjects.InvoicesUI;
import com.macys.mst.macysnet.utils.ExistingUIMethods;

public class StatusofInvoice {
	private static Logger logger = Logger.getLogger(StatusofInvoice.class.getName());
	public static ArrayList<InvoicesUI> UIObjList = new ArrayList<>();
	public static ArrayList<Invoice> databaseObjList = new ArrayList<>();
	private ExamplesTable actionstable;

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
	
	@Given ("User is on macysnet page$url")
	public static void login(String url)
	{
		System.out.println(url);
		gbldriver.navigate().to("http://qa.macysnet.com/AP/");
		gbldriver.manage().window().maximize();
		gbldriver.findElement(By.xpath("//input[@name='UserName']")).sendKeys("roint3");
		gbldriver.findElement(By.xpath("//input[@name='Password']")).sendKeys("routing1");
		gbldriver.findElement(By.xpath("//input[@name='submit']")).click();
		
		gbldriver.switchTo().frame("Controls");
		
		
	}
	@When ("select $activity from dropdown")
	public static void selectValuefromDropdown(String activity) throws InterruptedException
	{
		System.out.println(activity);
		WebElement activity1 = gbldriver.findElement(By.xpath("//select[@name='Type']"));
		Select s = new Select(activity1);
		s.selectByVisibleText("Status of Invoice");
		Thread.sleep(3000);
	}
	
	@Then ("Enter account number")
	public static void enterNumber(){
		WebElement division = gbldriver.findElement(By.xpath("//select[@name='Division']"));
		Select s1 = new Select(division);
		s1.selectByVisibleText("Macy's");
		gbldriver.findElement(By.xpath("//input[@name='AccountNumber']")).sendKeys("910927390");
		
		
	}
	@Then ("Enter document number")
	public static void enterDocNumber(){
		gbldriver.findElement(By.xpath("//input[@name='DocNum']")).sendKeys("9027304930");
		gbldriver.findElement(By.xpath("//input[@name='SUBMIT1']")).click();
		
	}	
	@Then ("Store the $Existing results in objects")
	public static void storeInObjects(String existing){
		gbldriver.switchTo().defaultContent();
		gbldriver.switchTo().frame("Results");
		try {
			if(ExistingUIMethods.records_On_Page(gbldriver,"recordsCountExisting")>0)
				{
				logger.info("Inside Action --> Records are displayed in existing screen ");			
		
				ExistingUIMethods.add_Records_On_Page(gbldriver,"existing");
			
			
			}
			else
			{
				/*logger.info("Inside Action --> NO Records are displayed in existing screen  ");
				Assert.assertTrue(false);*/
				System.out.println("Fail"); 
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Then("Get the Invoices information from UI")
	public static void Get_Values_From_UIScreen(WebDriver lclDriver,String locString,String data) throws Exception
	{
		logger.info("Inside Action --> Get_Values_From_UIScreen ");
		
		try {
			Thread.sleep(5000);
			int colIndex=0;
			List<String> tableValuesInList = new ArrayList<String>();
			String[] datacopy = data.split(",");

			
			List<WebElement> rowCount=lclDriver.findElements(By.xpath("//table/tbody/tr"));
			int count=rowCount.size();
			List<WebElement> colHead = lclDriver.findElements(By.xpath("//table/thead/tr/th"));
			int colCount=colHead.size();
			
			for(int i=1; i<=count; i++)
			{
				//System.out.println(colHead.size());
				for (int j = 1; j <=colCount; j++) {
					
					String tabValueTxt = lclDriver.findElement(By.xpath("//table/thead/tr/th[" + j + "]")).getText();
					
					for(String s:datacopy)
					{
					if (tabValueTxt.equalsIgnoreCase(s)) {
						colIndex = j;
						String value=lclDriver.findElement(By.xpath("//table/tbody/tr["+i+"]/td["+colIndex+"]")).getText();
						tableValuesInList.add(value);
						break;
						}
					
					}
					
				}
				UIObjList.add(new InvoicesUI(tableValuesInList));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//System.out.println("list" + tableValuesInList);
		
	}
	/*@Then("Get the Invoices information from Database")
	public static void Get_Values_From_Database() throws Exception
	{
		
	}*/
	@Then("Compare UI values with Database")
	public static void CompareUIWithDatabase()
	{
	}

}
