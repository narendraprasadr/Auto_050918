package com.macys.mst.FreightOptimization.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jbehave.core.model.ExamplesTable;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.macys.mst.FreightOptimization.config.Constants;
import com.macys.mst.FreightOptimization.stepdefs.FreightOptimization;
import com.macys.mst.artemis.customexceptions.SeleniumNonFatalException;
import com.macys.mst.artemis.kdddriver.ObjectRepositoryMap;
import com.macys.mst.artemis.selenium.LocalDriverManager;
import com.macys.mst.artemis.selenium.SeUiContextBase;
import com.macys.mst.artemis.selenium.actions.SeleniumElements;
import com.macys.mst.artemis.selenium.actions.SeleniumWait;




public class General {

	public static WebDriver driver = LocalDriverManager.getInstance().getDriver();
	public static Logger logger = Logger.getLogger(FreightOptimization.class.getName());
	static SeleniumElements objSeleniumElements=new SeleniumElements();
	static SeleniumWait objSeleniumWait=new SeleniumWait();
	public static List<String> multivalues = new ArrayList<String>();
	static SeUiContextBase objSeUiContextBase= new SeUiContextBase();
	public static Map<String, List<String>> UIvalues = new HashMap<String, List<String>>();
	/***********************************************************************************************************************************************************
	 * 'Method name : waitToLoadElement 'Project name :
	 * FreightOptimizationAutomation 'Description : This method is to wait until the element load
	 * 'Developer : Sriram 'Reviewed By : 'Created On : May 2018
	 ************************************************************************************************************************************************************/
	public static void waitToLoadElement(WebElement weleElementName) throws Exception {

		try {
			WebDriverWait webWait = new WebDriverWait(General.driver, 30);
			webWait.withTimeout(10, TimeUnit.SECONDS);
			webWait.pollingEvery(5, TimeUnit.MILLISECONDS);
			webWait.until(ExpectedConditions.visibilityOf(weleElementName));
			Assert.assertTrue(true);
		} catch (Exception e) {

		}
	}

	/***********************************************************************************************************************************************************
	 * 'Method name : selectDropdownValue 'Project name :
	 * FreightOptimizationAutomation 'Description : This method is to perform the
	 * action to select DropDown value 'Developer : Sriram 'Reviewed By : 'Created
	 * On : May 2018
	 ************************************************************************************************************************************************************/
	public static void selectDropdownValue(WebElement weleElementName, String strOption) throws Exception {
		try {
			General.waitToLoadElement(weleElementName);

			weleElementName.click();
			// General.waitforpagetoload();
			Select sel = new Select(weleElementName);
			sel.selectByVisibleText(strOption);
			// General.passScreenShot(option+" selected in dropdown Successfully");
			Assert.assertTrue(true);
		} catch (Exception e) {
			// Log.error("Failed to click on Dropdown "+element);
			// General.failScreenShot(option+" not selected in dropdown");
			throw (e);
		}
	}

	/***********************************************************************************************************************************************************
	 * 'Method name : getTimeStamp 'Project name : FreightOptimizationAutomation
	 * 'Description : This method is to return time 'Developer : Sriram 'Reviewed By
	 * : 'Created On : May 2018
	 ************************************************************************************************************************************************************/
	public static String getTimeStamp() {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("mmddYYYYhhmm");
		String time = dateFormat.format(now);
		return time;
	}

	/***********************************************************************************************************************************************************
	 * 'Method name : userDetails 'Project name : FreightOptimizationAutomation
	 * 'Description : This method is to get uername and password from excel
	 * 'Developer : Sriram 'Reviewed By : 'Created On : May 2018
	 ************************************************************************************************************************************************************/
	public static String[] userDetails(String strUserType) throws Exception {

		try {

			String strUserDetails[] = new String[2];
			File objInputFile = new File(Constants.dataFilePath);
			FileInputStream objFS = new FileInputStream(objInputFile);
			Workbook wb = null;
			wb = new XSSFWorkbook(objFS);
			Sheet sheet = wb.getSheet("Login");
			int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
			for (int i = 0; i < rowCount + 1; i++) {
				Row row = sheet.getRow(i);
				for (int j = 0; j < row.getLastCellNum(); j++) {
					if (row.getCell(j).getStringCellValue().equals(strUserType)) {
						strUserDetails[0] = row.getCell(j + 1).getStringCellValue();
						strUserDetails[1] = row.getCell(j + 2).getStringCellValue();

						return strUserDetails;
					}
				}
			}

		} catch (Exception e) {

			assertFalse("Failed To login", true);
		}
		return null;

	}

	/***********************************************************************************************************************************************************
	 * 'Method name : isHyperLink 'Project name : FreightOptimizationAutomation
	 * 'Description : This method is to vaildate given element is link are not
	 * 'Developer : Sriram 'Reviewed By : 'Created On : May 2018
	 ************************************************************************************************************************************************************/
	public static boolean isHyperLink(WebElement weleElementName, String strlinkname) throws Exception {

		try {

			General.waitToLoadElement(weleElementName);
			String tagName = weleElementName.getTagName();
			if (tagName.contains("a")) {
				logger.info(strlinkname + " link is displayed");
				Assert.assertTrue(true);
				return true;
			} else {
				logger.info(strlinkname + " link is not displayed");
				Assert.assertTrue(false);
				return false;
			}

		} catch (Exception e) {
			throw (e);
		}
	}

	public static void multipleValues(ExamplesTable fieldValues) {
		try {
			multivalues.clear();
			for (Map<String, String> row : fieldValues.getRows()) {
				String values = row.get("MandatoryFields");
				multivalues.add(values);
			}
			assertTrue("Multiple values are added", true);
		} catch (Exception e) {
			assertFalse("Multiple values are not added", true);
			e.printStackTrace();

		}
	}

	/***********************************************************************************************************************************************************
	 * 'Method name  : getUIDropdownValue
	 * 'Project name : FreightOptimizationAutomation
	 * 'Description  : This method is to get DropDown value from UI in List
	 * 'Developer    : Sriram
	 * 'Reviewed By  :
	 * 'Created On   : June 2018
	 ************************************************************************************************************************************************************/
	public static List<String> getUIDropdownValue(WebDriver lcldriver, String locString, String locIndex) throws Exception {
		try {


			  JavascriptExecutor js=(JavascriptExecutor)lcldriver;
			  js.executeScript("window.scrollBy(0,100)");
			  Thread.sleep(2000);
			List<String> cellData =new ArrayList<String>();
			int runloop=1;
			for (; runloop <=2 ; runloop++)
		      {
			for (int j = 1; j <=7; j++)
		      {

				if (lcldriver.findElements(By.xpath(get_Locator(locString)+"["+j+"]")).size()!= 0) {
				//if (objSeleniumElements.Get_Webelements(lcldriver, locString+"["+j+"]").size()!= 0) {
				String str1 =  lcldriver.findElement(By.xpath(get_Locator(locString)+"["+j+"]")).getText();
		        cellData.add(str1);
				}
		       }

			if (lcldriver.findElement(By.xpath("(//*[contains(@id,'jqxScrollAreaDownverticalScrollBarinnerListBoxjqxDropDownList')])["+locIndex+"]")).getSize().getHeight()!= 0) {
				lcldriver.findElement(By.xpath("(//*[contains(@id,'jqxScrollAreaDownverticalScrollBarinnerListBoxjqxDropDownList')])["+locIndex+"]")).click();
				runloop=1;
			}
			objSeleniumWait.Set_Selenium_Timeout(lcldriver, 2);
		 }
			logger.info("Action getUIDropdownValue is Successful");
			//System.out.println(cellData.toString());
			return cellData;
		} catch (Exception e) {
			//Log.error("Failed to click on Dropdown "+element);
			logger.info("Action getUIDropdownValue failed !");
			throw (e);
		}
	}


	/***********************************************************************************************************************************************************
	 * 'Method name  : getUIDropdownValue
	 * 'Project name : FreightOptimizationAutomation
	 * 'Description  : This method is to get DropDown value from UI in List
	 * 'Developer    : Sriram
	 * 'Reviewed By  :
	 * 'Created On   : June 2018
	 * @return
	 ************************************************************************************************************************************************************/
	public static String  get_Locator( String locString) throws Exception {
		try {
	ObjectRepositoryMap orm = new ObjectRepositoryMap("ObjectRepositoryMap.properties");
    String locator = orm.properties.getProperty(locString);
    String locatorType = locator.split(":", 2)[0].trim();
    //logger.info(locatorType);
    String locatorValue = locator.split(":", 2)[1].trim();
    //logger.info(locatorValue);

    return locatorValue;
		}
		catch (Exception e) {
			//Log.error("Failed to click on Dropdown "+element);
			logger.info("Action get_Locator failed !");
			throw (e);
		}
	}
	public static void Close_Window(WebDriver lcldriver) {
		  logger.info("Inside Project Action --> Close Window ");
		  String []datacopy=null;
		  int n=0;
		  try
		  {
			    ArrayList<String> newTab = new ArrayList<String>(lcldriver.getWindowHandles());

			   if (newTab.size() >1)
			   {
				   System.out.println(newTab.size()+"pass");
			  lcldriver.switchTo().window(newTab.get(1));
			  lcldriver.close();
			  lcldriver.switchTo().window(newTab.get(0));
			  logger.info("Inside Project Action --> Close Window ");
				  // }
			   }

		  }catch(Exception e)
		  {
			  e.printStackTrace();
		      logger.info("Action Close Window");
		      throw new SeleniumNonFatalException(" Action ->Close Window");
		  }
	}


}
