package com.macys.mst.FreightOptimization.Actionkeywords;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.simple.JSONObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.Date; 
import java.util.HashMap;

import java.util.Map;
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
import com.macys.mst.artemis.rest.RestUtilities;
import com.macys.mst.artemis.selenium.SeUiContextBase;
import com.macys.mst.artemis.selenium.actions.SeleniumElements;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;




public class AFOJActionkeywords {
	static SeUiContextBase objSeUiContextBase= new SeUiContextBase();
	public static Logger logger = Logger.getLogger(FreightOptimization.class.getName());
	static int loginCount=0;
	public static String userType,struserTypes=null;
	static SeleniumElements objSeleniumElements=new SeleniumElements();
	public static RequestSpecification request = RestAssured.given();
	public static JSONObject requestJSON=new JSONObject();
	public static Response response ;
	
    public static void Macysnet_Login(WebDriver lcldriver,String struserType) {

		try {
			if(verify_LoginUser(struserType)) {
			
			SeUiContextBase.gotourl(lcldriver, (FileConfig.getInstance().getStringConfigValue("MacysNet.URL")));
			SeUiContextBase.Maximize_Browser_Window(lcldriver);
			struserTypes=struserType;
			StepDetail.addDetail("MacysNET login page is displayed", true);
			assertTrue("MacysNET login page", true);
			logger.info("MacysNET login page is displayed ");
			
			String passwordobj=null;
			if (struserType.equals("Admin")) {
				userType=FileConfig.getInstance().getStringConfigValue("MacysNet.Admin.userId"); 
			 passwordobj=FileConfig.getInstance().getStringConfigValue("MacysNet.Admin.pwdobjectid");  
			}
			else {
				userType=FileConfig.getInstance().getStringConfigValue("MacysNet.vendor.userId"); 
			 passwordobj=FileConfig.getInstance().getStringConfigValue("MacysNet.vendor.pwdobjectid");
			}
					
			String cyberarksafe=FileConfig.getInstance().getStringConfigValue("cyberark.safe"); 
            String cyberarkappid=FileConfig.getInstance().getStringConfigValue("cyberark.appid");             
                    
            String password=GetPasswordCyberArk.getpassword(cyberarksafe,cyberarkappid, passwordobj);
            
            WebElement element=lcldriver.findElement(By.xpath(General.get_Locator("txa_Login_Username")));
			objSeUiContextBase.sendkeys(element,userType);
			assertTrue("MTO Username entered successfully", true);
			element=lcldriver.findElement(By.xpath(General.get_Locator("txa_Login_Password")));
			objSeUiContextBase.sendkeys(element,password);
			assertTrue("Password entered successfully", true);
			SeUiContextBase.Capture_Page_Screenshot(lcldriver, Constants.scnshotPassPath +"LoginScreen"+General.getTimeStamp()+".jpg");
			StepDetail.addDetail("username" + ":" + "Password", true);
			element=lcldriver.findElement(By.xpath(General.get_Locator("btn_Login_Login")));
			element.click();
			assertTrue("submitted successfully", true);
			logger.info("Login to Macy'sNET application successfully");
			}
			Thread.sleep(4000);

		} catch (Exception e) {

			logger.error("Failed To login");
			assertFalse("Failed To login", true);
			SeUiContextBase.Capture_Page_Screenshot(General.driver, Constants.scnshotFailPath +"LoginScreen"+General.getTimeStamp()+".jpg");
		}
	}
	
    public static boolean verify_LoginUser(String struserType) {  
    	
    if(loginCount==0) {
		
		loginCount++;
		return true;
	}else if(loginCount>0 && struserTypes.equals(struserType)) {
		return false;
	}else {
		return true;
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
			   Thread.sleep(1000);
			  WebElement element=  lcldriver.findElement(By.xpath("(.//*[@placeholder='Looking for'])["+runtimeindex+"]"));
			  if(element.isDisplayed())
			  {
			  element.sendKeys(dataArrayCopy[0]);
			  }
				  Thread.sleep(2000);
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
		  String []datacopy=null;
		  int n=0;
		  try
		  {
			  if(strdata.contains(","))
			  {
				  datacopy=strdata.split(",");
				  n=Integer.parseInt(datacopy[0]);  
			  }
			  else
			  {
				  n=Integer.parseInt(strdata);
			  }
			  ArrayList<String> newTab = new ArrayList<String>(lcldriver.getWindowHandles());
			  lcldriver.switchTo().window(newTab.get(n));
			  logger.info("Inside Project Action --> Successfully Switch To Window ");
			 
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
	
	 public static void select_Date(WebDriver lclDriver,String locString,String date) {
			logger.info("Inside Project Action --> select_Date");
			try
			{
				List<WebElement> dateWidget = lclDriver.findElements(By.xpath(General.get_Locator(locString)));

				for (WebElement cell: dateWidget){
					String value=cell.getText();
				   if (value.equals(date)){
					   cell.click();
					   logger.info("Then given '"+date +"' Date is selected successfully");
				      break;
				 }
				}
				
			}catch(Exception e)
			{
				assertFalse(date + " date is not selected", true);
				logger.info("Inside Project Action -->   "+date +" date is not selected");
			}
			
		}
	 public static void validateServicePostResponseCode(String serviceURL,String expectedCode,String field, String value) {
	  	  logger.info("Inside Project Action --> validateServicePostResponseCode, String serviceURL,String field, String value,String expectedCode ");
	  	  try
	  	  
	  	    {
	  	
	  		  
	  		Map<String, Object> requestMap = new HashMap<String, Object>();
	  		Map<String, Object> tempHaspMap = new HashMap<String, Object>();
	  		String field1=null;
	  		
	  		JSONObject tempsubJSON=new JSONObject();
	  		if (value.contains("-")){
	  			String arrayOfKeyValue [] = value.split("-");
	  			JSONObject tempJSON=new JSONObject();
	  			for(String str :arrayOfKeyValue) {
	  				
	  				if (str.contains("=")){
	  					
	  					String arrayOfKeyValue1 [] = str.split("=");
	  					for(String str1 :arrayOfKeyValue1) {
	  						if (str1.contains(",")){
	  							String arrayOfKeyValue2 [] = str1.split(",");
	  							
	  							for(String str2 :arrayOfKeyValue2) {
	  								
	  								String keyValue[] = str2.split(":");
	  								//keyvalue[0] - key, keyvalue[1] - value
	  								tempJSON.put(keyValue[0], keyValue[1]);
	  							}
	  							
	  							
	  						}
	  						else {
	  							field1 = str1;
	  						}
	  						
	  						tempsubJSON.put(field1, tempJSON);	
	  					}
	  				}
	  				else {
	  					
	  					if (str.contains(",")){
	  						
	  						String arrayOfKeyValue3 [] = str.split(",");
	  						
	  						for(String str3 :arrayOfKeyValue3) {
	  							
	  							String keyValue[] = str3.split(":");
	  							//keyvalue[0] - key, keyvalue[1] - value
	  							tempsubJSON.put(keyValue[0], keyValue[1]);
	  						}
	  						
	  						
	  					}
	  					else {
	  						String keyValue[] = str.split(":");
	  						//keyvalue[0] - key, keyvalue[1] - value
	  						tempsubJSON.put(keyValue[0], keyValue[1]);
	  					}
	  				}
	  			}
	  		}
	  		requestJSON.put(field, tempsubJSON);
	  		//AFOJRestServices.request.body(AFOJRestServices.requestJSON.toJSONString());
	  		
	  		request.body(AFOJRestServices.requestJSON.toJSONString());
			response = RestUtilities.postRequestAndReturnResponse(serviceURL, requestJSON.toString());
			
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			int expected=Integer.parseInt(expectedCode);
			Assert.assertEquals(expected,statusCode);
			logger.info("Action validateServicePostResponseCode Pass !");
	  	      //return statusCode;
	  	    }
	  	    catch (Exception e)
	  	    {
	  	      e.printStackTrace();
	  	      logger.info("Action validateServicePostResponseCode failed !");
	  	      //StepDetail.addDetail(String.format("Verify_Text_Length_Should_Be is NOT successful.", new Object[0]), false);
	  	      throw new SeleniumNonFatalException(" Action -> validateServicePostResponseCode : could not be completed!");
	  	    }
	  	}
	 public static void validate_Default_Value_Should_Be(WebDriver lclDriver, String locString,String Values) {
			logger.info("Inside Project Action --> validate_Default_Value_Should_Be ");
			String appValue=null;
			String [] dataArrayCopy = null;
			try
			{
				
				  if (Values.contains(",")) {
				      dataArrayCopy = Values.split(",");
				       }
				  		Thread.sleep(2000);
						
				  			WebElement ele=lclDriver.findElement(By.xpath(General.get_Locator(locString)));
						   	appValue=SeleniumElements.Get_Element_Attribute(ele, dataArrayCopy[1]);
					if(dataArrayCopy[0].contains("/")|| appValue.contains("/"))
					{
						   	Date formatter = new SimpleDateFormat("MM/dd/yyyy").parse(appValue);  
						    formatter.parse(dataArrayCopy[0]);
					  }
					   if(appValue.trim().equalsIgnoreCase(dataArrayCopy[0].trim()))
					   {
						   SeUiContextBase.Capture_Page_Screenshot(lclDriver, Constants.scnshotPassPath +"validate_Default_Value_Should_Be_"+General.getTimeStamp()+".jpg");
						   logger.info("Expected is "+dataArrayCopy[0]+" value and found value is "+appValue);
						   assertTrue("Expected is "+dataArrayCopy[0]+" value and found value is "+appValue, true);
						  
						   
					   }
					   else
					   {
						   SeUiContextBase.Capture_Page_Screenshot(lclDriver, Constants.scnshotFailPath +"validate_Default_Value_Should_Be_Fail_"+General.getTimeStamp()+".jpg");
						   logger.error("Expected is "+dataArrayCopy[0]+" value But found value is "+appValue);
							 assertFalse("Expected is "+dataArrayCopy[0]+" value But found value is "+appValue,true);
					   }
					   
				
			}catch(Exception e)
			{
				SeUiContextBase.Capture_Page_Screenshot(lclDriver, Constants.scnshotFailPath +"validate_Default_Value_Should_Be_Fail_"+General.getTimeStamp()+".jpg");
				 logger.error("Expected is "+dataArrayCopy[0]+" value But found value is "+appValue);
				 assertFalse("Expected is "+dataArrayCopy[0]+" value But found value is "+appValue,true);
			}
		}
	

}