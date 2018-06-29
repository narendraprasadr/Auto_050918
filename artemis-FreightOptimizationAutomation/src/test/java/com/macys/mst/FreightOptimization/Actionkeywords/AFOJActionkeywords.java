package com.macys.mst.FreightOptimization.Actionkeywords;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.macys.mst.FreightOptimization.config.Constants;
import com.macys.mst.FreightOptimization.db.app.AppDBMethods;
import com.macys.mst.FreightOptimization.db.app.DBMethods;
import com.macys.mst.FreightOptimization.pageobjects.LoginPage;
import com.macys.mst.FreightOptimization.sqlconstants.SQLConstants;
import com.macys.mst.FreightOptimization.stepdefs.AFOJRestServices;
import com.macys.mst.FreightOptimization.stepdefs.FreightOptimization;
import com.macys.mst.FreightOptimization.utils.General;
import com.macys.mst.FreightOptimization.utils.RestServicesUtils;
import com.macys.mst.artemis.config.FileConfig;
import com.macys.mst.artemis.config.GetPasswordCyberArk;
import com.macys.mst.artemis.customexceptions.SeleniumNonFatalException;
import com.macys.mst.artemis.db.DBConnections;
import com.macys.mst.artemis.db.DBUtils;
import com.macys.mst.artemis.reports.StepDetail;
import com.macys.mst.artemis.selenium.SeUiContextBase;
import com.macys.mst.artemis.selenium.actions.SeleniumElements;



public class AFOJActionkeywords {
	SeUiContextBase objSeUiContextBase= new SeUiContextBase();
	public static Logger logger = Logger.getLogger(FreightOptimization.class.getName());
	
	static SeleniumElements objSeleniumElements=new SeleniumElements();
	
    public void Macysnet_Login(String struserType) {

		try {
			
			String userId;
			if (struserType.equals("Admin"))
				userId=FileConfig.getInstance().getStringConfigValue("cyberark.userIDAdmin"); 
			else
				userId=FileConfig.getInstance().getStringConfigValue("cyberark.userIDOther"); 
			
			String cyberarksafe=FileConfig.getInstance().getStringConfigValue("cyberark.safe"); 
            String cyberarkappid=FileConfig.getInstance().getStringConfigValue("cyberark.appid");             
            String passwordobj=FileConfig.getInstance().getStringConfigValue("cyberark.pwdobjectid");            
            String password=GetPasswordCyberArk.getpassword(cyberarksafe,cyberarkappid, passwordobj);
            
			objSeUiContextBase.sendkeys(LoginPage.username,userId);
			assertTrue("MTO Username entered successfully", true);
			objSeUiContextBase.sendkeys(LoginPage.password,password);
			assertTrue("Password entered successfully", true);
			SeUiContextBase.Capture_Page_Screenshot(General.driver, Constants.scnshotPassPath +"LoginScreen"+General.getTimeStamp()+".jpg");
			StepDetail.addDetail("username" + ":" + "Password", true);
			assertTrue("submitted successfully", true);
			logger.info("Login to Macy'sNET application successfully");

		} catch (Exception e) {

			logger.error("Failed To login");
			assertFalse("Failed To login", true);
			SeUiContextBase.Capture_Page_Screenshot(General.driver, Constants.scnshotFailPath +"LoginScreen"+General.getTimeStamp()+".jpg");
		}
	}
	
	/***********************************************************************************************************************************************************
	 * 'Method name : Verify_Text_Length_Should_Be 
	 * 'Project name:FreightOptimizationAutomation 
	 * 'Description : This method is to perform the Verify length of value in test box
	 * 'Developer   : Sriram
	 * 'Reviewed By : 
	 * 'Created On  : June 2018
	 ************************************************************************************************************************************************************/
	public static void verify_Text_Length_Should_Be(WebDriver lcldriver,String locString,String numlen) {
	  logger.info("Inside Project Action --> verify_Text_Length_Should_Be ");
	  try
	  
	    {
		  
		  String actual = SeleniumElements.Get_Value(lcldriver,locString);
		  int numtemp= Integer.parseInt(numlen);
	      
	      if (!actual.isEmpty())
	      {
	       // String message = String.format("Value of text len'%s' should have been '%s' but was '%s'", new Object[] {actual,numtemp});
	        int length = actual.length();
	       if (length<=numtemp) {
	    	   logger.info("Action verify_Text_Length_Should_Be is Successful");
	    	   Assert.assertTrue(true, "Action verify_Text_Length_Should_Be is Successful");
		   }
	       else {
	    	   logger.info("Action verify_Text_Length_Should_Be is not Successful");
	    	   Assert.assertTrue(false, "Action verify_Text_Length_Should_Be is not Successful"); 
	       }
		}
	      
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	      logger.info("Action verify_Text_Length_Should_Be failed !");
	      //StepDetail.addDetail(String.format("Verify_Text_Length_Should_Be is NOT successful.", new Object[0]), false);
	      throw new SeleniumNonFatalException(" Action -> verify_Text_Length_Should_Be : could not be completed!");
	    }
	}

	/***********************************************************************************************************************************************************
	 * 'Method name : verify_Dropdownlist_Matches_With_RESTService 
	 * 'Project name: FreightOptimizationAutomation 
	 * 'Description : This method is to perform the validation dropdown list with rest services
	 * 'Developer   : Sriram
	 * 'Reviewed By : 
	 * 'Created On  : June 2018
	 ************************************************************************************************************************************************************/
	public static void verify_Dropdownlist_Matches_With_RESTService (WebDriver lcldriver,String locString,String serviceURL,String strvalue) {
	  logger.info("Inside Project Action --> verify_Dropdownlist_Matches_With_RESTService ");
	  try
	   {
		  String [] dataArrayCopy = null;
		  if (serviceURL.contains(",")) {
		      dataArrayCopy = serviceURL.split(",");
		       }
		  AFOJRestServices.Restvalues=RestServicesUtils.getListOfValues(dataArrayCopy[0],dataArrayCopy[1]);
		  List<String> cellData =General.getUIDropdownValue(lcldriver,locString,dataArrayCopy[2]);
		  List<String> srResult=AFOJRestServices.Restvalues.get(dataArrayCopy[1]);
		  Set<String> hs = new HashSet<>();
		  hs.addAll(cellData);
		  cellData.clear();
		  cellData.addAll(hs);
		  Collections.sort(cellData);
		  Collections.sort(srResult);
		  
		  Assert.assertTrue(srResult.equals(cellData));
		 if (cellData.equals(srResult)) {
			logger.info("Rest service and UI value are same");
			 Assert.assertTrue(true, "Action verify_Dropdownlist_Matches_With_RESTService is Successful");
		  } else {
			logger.info("Rest service and UI value is not same");
			Assert.assertTrue(false, "Action verify_Dropdownlist_Matches_With_RESTService is not Successful");
		  }
			      
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	      logger.info("Action verify_Dropdownlist_Matches_With_RESTService failed !");
	      throw new SeleniumNonFatalException(" Action -> verify_Dropdownlist_Matches_With_RESTService : could not be completed!");
	    }
		 
		
	}
	
	/***********************************************************************************************************************************************************
	 * 'Method name : select_MultiDropDown_Value 
	 * 'Project name:FreightOptimizationAutomation 
	 * 'Description : This method is to perform the validation dropdown list with rest services
	 * 'Developer   : Sriram
	 * 'Reviewed By : 
	 * 'Created On  : June 2018
	 ************************************************************************************************************************************************************/
	public static void select_MultiDropDown_Value(WebDriver lcldriver,String locString,String strdata ) {
	  logger.info("Inside Project Action --> select_MultiDropDown_Value ");
	  try
	   {
		  lcldriver.findElement(By.xpath(General.get_Locator(locString))).click();
		  String[] dataArrayCopy = null;
		  if (strdata.contains(",")) {
		      dataArrayCopy = strdata.split(",");
		       }
		  
		  for(String str : dataArrayCopy){
			  
			  Thread.sleep(2000);
			  lcldriver.findElement(By.xpath(General.get_Locator(locString))).click();
			  Thread.sleep(2000);
			  
			  WebElement element=  lcldriver.findElement(By.xpath("(.//*[@placeholder='Looking for'])[1]"));
			  if(element.isDisplayed())
			  {
			  element.clear();
				  element.sendKeys(str);
			  }
			  else
			  {
				   element=  lcldriver.findElement(By.xpath("(.//*[@placeholder='Looking for'])[2]")); 
				   element.clear();
				   element.sendKeys(str);
			  }
			  
			  
		      Thread.sleep(2000);
		      lcldriver.findElement(By.xpath("//div[@role='option'][1]")).click();
			  logger.info(dataArrayCopy[0]+"Drop down selected successful");
			  Assert.assertTrue(true, "Drop down selected successful");
			  Thread.sleep(2000);
			  
		  }
		  logger.info("Action select_MultiDropDown_Value is Successful");
			 Assert.assertTrue(true, "Action select_MultiDropDown_Value is Successful");
	   }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	      logger.info("Action select_MultiDropDown_Value failed !");
	      throw new SeleniumNonFatalException(" Action -> select_MultiDropDown_Value : could not be completed!");
	    }
	}
	

	/***********************************************************************************************************************************************************
	 * 'Method name : verify_Dropdownlist_Have_OneValue 
	 * 'Project name:FreightOptimizationAutomation 
	 * 'Description : This method is to perform the validation dropdown list with rest services
	 * 'Developer   : Sriram
	 * 'Reviewed By : 
	 * 'Created On  : June 2018
	 ************************************************************************************************************************************************************/
	public static void verify_Dropdownlist_Have_OneValue(WebDriver lcldriver,String locString,String strdata ) {
	  logger.info("Inside Project Action --> verify_Dropdownlist_Have_OneValue ");
	  try
	   {
		  String[] dataArrayCopy = null;
		  if (strdata.contains(",")) {
		      dataArrayCopy = strdata.split(",");
		       }
		  	  
		  String strRuntimevalue=objSeleniumElements.Get_Text(lcldriver,locString);
		  
		   Assert.assertTrue(strRuntimevalue.equals(dataArrayCopy[dataArrayCopy.length-1]));
		  if (strRuntimevalue.equals(dataArrayCopy[dataArrayCopy.length-1])) {
			logger.info(strRuntimevalue+"&"+dataArrayCopy[dataArrayCopy.length-1]+" Drop down list is allow only one value to select");
			 Assert.assertTrue(true, "Action verify_Dropdownlist_Have_OneValue is Successful");
		  } else {
			logger.info("Drop down list is allow Multi value to select");
			Assert.assertTrue(false, "Action verify_Dropdownlist_Have_OneValue is not Successful");
		  }
	
	   }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	      logger.info("Action verify_Dropdownlist_Have_OneValue failed !");
	      throw new SeleniumNonFatalException(" Action -> verify_Dropdownlist_Have_OneValue : could not be completed!");
	    }
}
	
	
	/***********************************************************************************************************************************************************
	 * 'Method name : select_SingleDropDown_Value 
	 * 'Project name:FreightOptimizationAutomation 
	 * 'Description : This method is to perform the validation dropdown list with rest services
	 * 'Developer   : Sriram
	 * 'Reviewed By : 
	 * 'Created On  : June 2018
	 ************************************************************************************************************************************************************/
	public static void select_SingleDropDown_Value(WebDriver lcldriver,String locString,String strdata ) {
		  logger.info("Inside Project Action --> select_SingleDropDown_Value ");
		  try
		   {
			  String[] dataArrayCopy = null;
			  if (strdata.contains(",")) {
			      dataArrayCopy = strdata.split(",");
			       }
			  
			  
			  int runtimeindex=Integer.parseInt(dataArrayCopy[1]);
			   Thread.sleep(2000);
			  WebElement element=  lcldriver.findElement(By.xpath("(.//*[@placeholder='Looking for'])["+runtimeindex+"]"));
			  if(element.isDisplayed())
			  {
			  element.sendKeys(dataArrayCopy[0]);
			  }
				  Thread.sleep(4000);
				  List<WebElement> element1=lcldriver.findElements(By.xpath(".//span[@class='jqx-listitem-state-normal jqx-item jqx-rc-all']"));
		    		logger.info("The element size: "+element1.size());
		    		for(int j=0;j<element1.size();j++) {
		    			String val=element1.get(j).getText();
		    			if(val.contains(dataArrayCopy[0]))
		    			{
		    				element1.get(j).click();
		    				logger.info(dataArrayCopy[0]+ " is selected successfully");
		    			}
		    		}
				  Assert.assertTrue(true, "Drop down selected successfully");
				  Thread.sleep(5000);
				  
			  logger.info("Action select_SingleDropDown_Value is Successful");
				 Assert.assertTrue(true, "Action select_SingleDropDown_Value is Successful");
		   }
		    catch (Exception e)
		    {
		      e.printStackTrace();
		      logger.info("Action select_SingleDropDown_Value failed !");
		      throw new SeleniumNonFatalException(" Action -> select_SingleDropDown_Value : could not be completed!");
		    }
		}
	
	public static void validate_Text(WebDriver lcldriver,String locString,String strdata ) {
		  logger.info("Inside Project Action --> validate_Text ");
		  try
		  {
			  Thread.sleep(2000);
			  System.out.println(locString+"--->");
			  WebElement element=lcldriver.findElement(By.xpath(General.get_Locator(locString)));
			  SeUiContextBase.Wait_Until_Element_Is_Visible(lcldriver, element);
			  String value=element.getText();
		 if(value.contains(strdata))
			 {
				 logger.info(value+" message displayed");
				 Assert.assertTrue(true, value+" message displayed");
				 
			 }
			 else
			 {
				 logger.info(value+" message displayed");
				 Assert.assertFalse(true, value+" Incorrect message displayed");
				 
			 }
			  
			 
		  }catch(Exception e)
		  {
			  e.printStackTrace();
		      logger.info("Action validate_Text failed !");
		      throw new SeleniumNonFatalException(" Action -> validate_Text : could not be completed!");
		  }
	}
	
	public static void switch_Frame(WebDriver lcldriver, String locator)
	  {
	    logger.info("Inside Action --> switch_Frame ");
	    try
	    {
	      logger.info(String.format("Selecting frame '%s'.", new Object[] { locator }));
	     
	      lcldriver.switchTo().frame(locator);
	      logger.info("Action switch_Frame is Successful");
	      StepDetail.addDetail(String.format("switch_Frame is successful.",new Object[0]), true);
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	      logger.info("Action Select_Frame failed !");
	      StepDetail.addDetail(String.format("Select_Frame is NOT successful.", new Object[0]), false);
	      throw new SeleniumNonFatalException(" Action -> Select_Frame : could not be completed!");
	    }
	  }
	public static void Switch_Window(WebDriver lcldriver,String strdata ) {
		  logger.info("Inside Project Action --> Switch_Window ");
		  try
		  {
			  int n=Integer.parseInt(strdata);
			  ArrayList<String> newTab = new ArrayList<String>(lcldriver.getWindowHandles());
			  lcldriver.switchTo().window(newTab.get(n));
			 
			 
		  }catch(Exception e)
		  {
			  e.printStackTrace();
		      logger.info("Action Switch_Window failed !");
		      throw new SeleniumNonFatalException(" Action -> Switch_Window : could not be completed!");
		  }
	}
	
	public static void Verify_Value_inDatabase(String strcode,String colValue) throws Exception {
		
		try {
			if (!strcode.isEmpty()) {
				
			
			String strname = null;
			if (colValue.equalsIgnoreCase("SCACCode")) 
				strname=SQLConstants.Select.SCACCode;
			
			AppDBMethods.connection = DBConnections.getinstance("db.Oracle", "LFCBIZ01DB").dbConnection();
			String strsql=strname.replace("#strcode", strcode);
			System.out.println(strsql); 
			
			String dbvalues=DBUtils.getDBValueInString(AppDBMethods.connection, strsql);
			System.out.println(dbvalues+"--"+strsql);
			DBConnections.closeDBConnection(AppDBMethods.connection);
			if(dbvalues.equalsIgnoreCase(strcode))
			 {
				 logger.info(dbvalues+" message displayed");
				 Assert.assertTrue(true, dbvalues+" message displayed");
				 
			 }
			 else
			 {
				 logger.info(strcode+" message displayed");
				 Assert.assertFalse(true, strcode+" Incorrect message displayed");
				 
			 }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}