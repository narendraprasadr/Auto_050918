package com.macys.mst.macysnet.utils;

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

import com.macys.mst.artemis.customexceptions.SeleniumNonFatalException;
import com.macys.mst.artemis.kdddriver.ObjectRepositoryMap;
import com.macys.mst.artemis.selenium.LocalDriverManager;
import com.macys.mst.artemis.selenium.SeUiContextBase;
import com.macys.mst.artemis.selenium.actions.SeleniumElements;
import com.macys.mst.artemis.selenium.actions.SeleniumWait;
import com.macys.mst.macysnet.Actionkeywords.MISActionkeywords;
import com.macys.mst.macysnet.config.Constants;




public class General {

	public static WebDriver driver = LocalDriverManager.getInstance().getDriver();
	public static Logger logger = Logger.getLogger(General.class.getName());
	static SeleniumElements objSeleniumElements=new SeleniumElements();
	static SeleniumWait objSeleniumWait=new SeleniumWait();
	public static List<String> multivalues = new ArrayList<String>();
	static SeUiContextBase objSeUiContextBase= new SeUiContextBase();
	public static Map<String, List<String>> UIvalues = new HashMap<String, List<String>>();
	/***********************************************************************************************************************************************************
	 * 'Method name : waitToLoadElement 'Project name :
	 * Macys Infrastructure Stability 'Description : This method is to wait until the element load
	 * 'Developer : Sivanarumugam 'Reviewed By : 'Created On : May 2018
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
	/***********************************************************************************************************************************************************
	 * 'Method name : getTimeStamp 'Project name : Macys Infrastructure Stability
	 * 'Description : This method is to return time 'Developer : Sivanarumugam 'Reviewed By
	 * : 'Created On : May 2018
	 ************************************************************************************************************************************************************/
	public static String getTimeStamp() {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("mmddYYYYhhmm");
		String time = dateFormat.format(now);
		return time;
	}

	/***********************************************************************************************************************************************************
	 * 'Method name : isHyperLink 'Project name : Macys Infrastructure Stability
	 * 'Description : This method is to vaildate given element is link are not
	 * 'Developer : Sivanarumugam 'Reviewed By : 'Created On : May 2018
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
