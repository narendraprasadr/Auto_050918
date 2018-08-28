package com.macys.mst.macysnet.Actionkeywords;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.macys.mst.artemis.config.FileConfig;
import com.macys.mst.artemis.config.GetPasswordCyberArk;
import com.macys.mst.artemis.customexceptions.SeleniumNonFatalException;
import com.macys.mst.artemis.db.DBConnections;
import com.macys.mst.artemis.db.DBUtils;
import com.macys.mst.artemis.reports.StepDetail;
import com.macys.mst.artemis.rest.RestUtilities;
import com.macys.mst.artemis.selenium.SeUiContextBase;
import com.macys.mst.artemis.selenium.actions.SeleniumElements;
import com.macys.mst.artemis.selenium.actions.SeleniumWait;
import com.macys.mst.macysnet.config.Constants;
import com.macys.mst.macysnet.db.app.AppDBMethods;
import com.macys.mst.macysnet.db.app.DBMethods;
import com.macys.mst.macysnet.sqlconstants.SQLConstants;
import com.macys.mst.macysnet.stepdefs.MISRestServices;
import com.macys.mst.macysnet.utils.General;
import com.macys.mst.macysnet.utils.RestServicesUtils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class MISActionkeywords {
	static SeUiContextBase objSeUiContextBase = new SeUiContextBase();
	private static Logger logger = Logger.getLogger(MISActionkeywords.class.getName());
	static int loginCount = 0;
	public static String userType, struserTypes = null;
	public static SeleniumElements objSeleniumElements = new SeleniumElements();
	// static SeleniumWait objSeleniumWait = new SeleniumWait();
	public static RequestSpecification request = RestAssured.given();
	public static JSONObject requestJSON = new JSONObject();
	public static Response response;
	public static String serviceurl = null;
	public static Map<String, List<String>> Restvalues = new HashMap<String, List<String>>();
	public static Map<String, List<String>> DBresultMap = new HashMap<String, List<String>>();
	public static ArrayList<Invoice> serviceObjList = new ArrayList<>();
	public static ArrayList<Invoice> databaseObjList = new ArrayList<>();

	/***********************************************************************************************************************************************************
	 * 'Method name : Macysnet_Login 'Project name : Macys Infrastructure Stability
	 * 'Description : This method is to login Macy's Net Application 'Developer :
	 * Sriram 'Reviewed By : 'Created On : May 2018
	 ************************************************************************************************************************************************************/
	public static void Macysnet_Login(WebDriver lcldriver, String struserType) {

		try {
			General.Close_Window(lcldriver);
			if (verify_LoginUser(struserType)) {

				SeUiContextBase.gotourl(lcldriver, (FileConfig.getInstance().getStringConfigValue("MacysNet.URL")));
				SeUiContextBase.Maximize_Browser_Window(lcldriver);
				struserTypes = struserType;
				StepDetail.addDetail("MacysNET login page is displayed", true);
				assertTrue("MacysNET login page", true);
				logger.info("MacysNET login page is displayed ");

				String passwordobj = null;
				if (struserType.equals("Admin")) {
					userType = FileConfig.getInstance().getStringConfigValue("MacysNet.Admin.userId");
					passwordobj = FileConfig.getInstance().getStringConfigValue("MacysNet.Admin.pwdobjectid");
				} else {
					userType = FileConfig.getInstance().getStringConfigValue("MacysNet.vendor.userId");
					passwordobj = FileConfig.getInstance().getStringConfigValue("MacysNet.vendor.pwdobjectid");
				}

				String cyberarksafe = FileConfig.getInstance().getStringConfigValue("cyberark.safe");
				String cyberarkappid = FileConfig.getInstance().getStringConfigValue("cyberark.appid");

				String password = GetPasswordCyberArk.getpassword(cyberarksafe, cyberarkappid, passwordobj);
				WebElement element = objSeleniumElements.Get_Webelement(lcldriver, "txa_Login_Username");
				// WebElement
				// element=lcldriver.findElement(By.xpath(General.get_Locator("txa_Login_Username")));
				objSeUiContextBase.sendkeys(element, userType);
				assertTrue("MTO Username entered successfully", true);
				element = objSeleniumElements.Get_Webelement(lcldriver, "txa_Login_Password");
				// element=lcldriver.findElement(By.xpath(General.get_Locator("txa_Login_Password")));
				objSeUiContextBase.sendkeys(element, password);
				assertTrue("Password entered successfully", true);
				SeUiContextBase.Capture_Page_Screenshot(lcldriver,
						Constants.scnshotPassPath + "LoginScreen" + General.getTimeStamp() + ".jpg");
				StepDetail.addDetail("username" + ":" + "Password", true);
				element = objSeleniumElements.Get_Webelement(lcldriver, "btn_Login_Login");
				// element=lcldriver.findElement(By.xpath(General.get_Locator("btn_Login_Login")));
				element.click();
				assertTrue("submitted successfully", true);
				logger.info("Login to Macy'sNET application successfully");
			}
			Thread.sleep(2000);
			// objSeleniumWait.Set_Selenium_Timeout(lcldriver, 4);

		} catch (Exception e) {

			logger.error("Failed To login");
			assertFalse("Failed To login", true);
			SeUiContextBase.Capture_Page_Screenshot(General.driver,
					Constants.scnshotFailPath + "LoginScreen" + General.getTimeStamp() + ".jpg");
		}
	}

	/***********************************************************************************************************************************************************
	 * 'Method name : verify_LoginUser 'Project name : Macys Infrastructure
	 * Stability 'Description : This method is to verify login Macy's Net
	 * Application 'Developer : Sriram 'Reviewed By : 'Created On : May 2018
	 ************************************************************************************************************************************************************/
	public static boolean verify_LoginUser(String struserType) {

		if (loginCount == 0) {

			loginCount++;
			return true;
		} else if (loginCount > 0 && struserTypes.equals(struserType)) {
			return false;
		} else {
			return true;
		}
	}

	/***********************************************************************************************************************************************************
	 * 'Method name : switch_Frame 'Project name : Macys Infrastructure Stability
	 * 'Description : This method is to switch frame in UI 'Developer :
	 * Sivanarumugam 'Reviewed By : 'Created On : May 2018
	 ************************************************************************************************************************************************************/
	public static void switch_Frame(WebDriver lcldriver, String locator) {
		logger.info("Inside Action --> switch_Frame ");
		try {
			logger.info(String.format("Selecting frame '%s'.", new Object[] { locator }));

			lcldriver.switchTo().frame(locator);
			logger.info("Action switch_Frame is Successful");
			StepDetail.addDetail(String.format("switch_Frame is successful.", new Object[0]), true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Action Select_Frame failed !");
			StepDetail.addDetail(String.format("Select_Frame is NOT successful.", new Object[0]), false);
			throw new SeleniumNonFatalException(" Action -> Select_Frame : could not be completed!");
		}
	}

	/***********************************************************************************************************************************************************
	 * 'Method name : Switch_Window 'Project name : Macys Infrastructure Stability
	 * 'Description : This method is to switch to window in UI 'Developer : praveen
	 * 'Reviewed By : 'Created On : May 2018
	 ************************************************************************************************************************************************************/
	public static void Switch_Window(WebDriver lcldriver, String strdata) {
		logger.info("Inside Project Action --> Switch_Window ");
		String[] datacopy = null;
		int n = 0;
		try {
			if (strdata.contains(",")) {
				datacopy = strdata.split(",");
				n = Integer.parseInt(datacopy[0]);
			} else {
				n = Integer.parseInt(strdata);
			}
			ArrayList<String> newTab = new ArrayList<String>(lcldriver.getWindowHandles());
			lcldriver.switchTo().window(newTab.get(n));
			logger.info("Inside Project Action --> Successfully Switch To Window ");

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Action Switch_Window failed !");
			throw new SeleniumNonFatalException(" Action -> Switch_Window : could not be completed!");
		}
	}

	/***********************************************************************************************************************************************************
	 * 'Method name : validateServicePostResponseCode 'Project name : Macys
	 * Infrastructure Stability 'Description : This method is to validate post
	 * respone of API Rest 'Developer : Sivanarumugam 'Reviewed By : 'Created On :
	 * May 2018
	 ************************************************************************************************************************************************************/

	public static void Validate_Record_Count() {
		logger.info("Inside Action --> ValidateRecordCount  ");

		try {
			if (General.driver.findElements(By.xpath(General.get_Locator("documentsCount"))).size() > 0) {
				logger.info("Inside Action --> Records are displayed  ");
				Assert.assertTrue(true);
			} else {
				logger.info("Inside Action --> NO Records are displayed  ");
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/***********************************************************************************************************************************************************
	 * 'Method name : Validate_Dropdown_Values 'Project name : Macys Infrastructure
	 * Stability 'Description : This method is to validate 'Dropdown values
	 * 'Developer : Senthilkumar 'Reviewed By : 'Created On : August 2018
	 ************************************************************************************************************************************************************/

	public static void Validate_Dropdown_Values(WebDriver lclDriver, String locString, String data) {

		String[] item = data.split(",");
		logger.info("Inside Action --> Validate the dropdown list  ");
		try {

			List<WebElement> menuCount = lclDriver.findElements(By.xpath(General.get_Locator(locString)));
			if (menuCount.size() == item.length) {
				for (int i = 0; i < menuCount.size(); i++) {

					if (menuCount.get(i).getText().equals(item[i])) {
						logger.info(menuCount.get(i).getText() + " is displayed   ");

					} else {
						logger.info(" NO Values are displayed   ");
						Assert.assertTrue(false);
					}
				}
			} else {
				StepDetail.addDetail(String.format("Dropdown value count is not matched with inputs", new Object[0]),
						false);
				throw new SeleniumNonFatalException(
						String.format("Validate_Dropdown_Values is NOT successful. ", new Object[0]));
			}

			Assert.assertTrue(true);

		} catch (Exception e) {

			e.printStackTrace();
			StepDetail.addDetail(String.format("Dropdown value is not available.", new Object[0]), false);
			throw new SeleniumNonFatalException(
					String.format("Validate_Dropdown_Values is NOT successful. ", new Object[0]));
		}
	}

	public static void Select_Dropdown_Value(WebDriver lclDriver, String locString, String data) {

		logger.info("Inside Action --> Select the value from dropdown list  ");
		try {
			Thread.sleep(5000);
			System.out.println("The input value is : " + data);
			List<WebElement> menuCount = lclDriver.findElements(By.xpath(General.get_Locator(locString)));

			for (int i = 0; i < menuCount.size(); i++) {

				System.out.println(menuCount.get(i).getText());
				// System.out.println(data.trim());
				if (menuCount.get(i).getText().trim().equalsIgnoreCase(data.trim())) {
					menuCount.get(i).click();
					logger.info(menuCount.get(i).getText() + " is selected   ");
					Thread.sleep(3000);

					break;
				} else {
					logger.info("menu value and input was not matched  ");
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
			StepDetail.addDetail(String.format("Dropdown value is not selected.", new Object[0]), false);
			throw new SeleniumNonFatalException(
					String.format("Validate_Dropdown_Values is NOT successful. ", new Object[0]));
		}
	}

	public static void MIS_Click_Element(WebDriver lclDriver, String locString) {

		logger.info("Inside Action --> Click the element ");
		try {
			General.driver.findElement(By.xpath("//form/div[2]/div/div/div")).click();
		} catch (Exception e) {

			e.printStackTrace();
			StepDetail.addDetail(String.format("Element is not clicked.", new Object[0]), false);
			throw new SeleniumNonFatalException(String.format("clickElement is NOT successful. ", new Object[0]));
		}
	}

	public static void MIS_wait_For(WebDriver lclDriver, String data, String seconds) {

		logger.info("Inside Action --> Click the element ");
		try {
			TimeUnit.SECONDS.sleep(Integer.parseInt(seconds));

		} catch (Exception e) {

			e.printStackTrace();
			StepDetail.addDetail(String.format("Wait not successful", new Object[0]), false);
			throw new SeleniumNonFatalException(String.format("Wait NOT successful. ", new Object[0]));
		}
	}

	public static void Validate_Response_Status(String serviceURL, String body) {
		logger.info("Inside Action --> Validate_Response_Status ");
		try {
			serviceURL = serviceURL + body;
			logger.info("request path : " + serviceURL);
			serviceurl = serviceURL;
			Response response = RestAssured.get(serviceURL, new Object[0]);
			if ((response != null) && ((response.getStatusCode() == 200) || (response.getStatusCode() == 201))) {
				logger.info("Status Code for response : " + response.getStatusCode());
				assertTrue("Status Code for response : " + response.getStatusCode(), true);
			} else {
				logger.info("Status Code for response : " + response.getStatusCode());
				assertTrue("Response is null or response code is !=200 ", false);

			}
		} catch (Exception e) {
			e.printStackTrace();
			StepDetail.addDetail(String.format("Validate_Response_Status NOT successful", new Object[0]), false);
			throw new SeleniumNonFatalException(
					String.format("Validate_Response_Status NOT successful. ", new Object[0]));
		}

	}

	/***********************************************************************************************************************************************************
	 * 'Method name : Get_Values_From_Service 'Project name : Macys Infrastructure
	 * Stability 'Description : This method is to Get the values from Service
	 * 'Developer : Sivanarumugam 'Reviewed By : 'Created On : August 2018
	 ************************************************************************************************************************************************************/

	/*public static void Get_Values_From_Service(String getValue) throws Exception {
		logger.info("Inside Action --> Get_Values_From_Service ");
		try {
			logger.info("The Service URL is: " + serviceurl);
			logger.info("The input is: " + getValue);
			Map<String, List<String>> resultMap = new HashMap<String, List<String>>();
			List<String> bodylist = new ArrayList<String>();
			RestAssured.baseURI = serviceurl;
			RequestSpecification request = RestAssured.given();
			Response response = request.get(serviceurl);
			System.out.println(response.asString());

			if (getValue.contains(",")) {

				for (String subString : getValue.split(",")) {

					bodylist = response.jsonPath().getList(subString);
					if (subString.contains("Date")) {
						bodylist.removeAll(Arrays.asList(Integer.valueOf(0)));
						bodylist.removeAll(Arrays.asList(" ", ""));
						bodylist.removeAll(Collections.singleton(null));
						List<String> bodylist2 = new ArrayList<String>();
						for (int i = 0; i < bodylist.size(); i++) {

							String[] arrOfStr = bodylist.get(i).split(" ");
							bodylist2.add(arrOfStr[0].trim());
						}
						resultMap.put(subString.toLowerCase(), bodylist2);
						Restvalues = resultMap;
					} else if (subString.equalsIgnoreCase("checknbr") || subString.equalsIgnoreCase("checkDate")) {
						bodylist.removeAll(Arrays.asList(Integer.valueOf(0)));
						resultMap.put(subString.toLowerCase(), bodylist);
						Restvalues = resultMap;
					} else {

						resultMap.put(subString.toLowerCase(), bodylist);
						Restvalues = resultMap;
					}

				}
			} else if (getValue.equalsIgnoreCase("email")) {
				String responseBody = response.getBody().asString();
				logger.info("Response Body is =>  " + responseBody);

				Pattern pattern = Pattern.compile("[\\w.]+@[\\w.]+");
				Matcher matcher = pattern.matcher(responseBody);

				while (matcher.find()) {
					String email = matcher.group();
					bodylist.add(email);
					System.out.println("Email from service=" + email);
				}
				resultMap.put(getValue, bodylist);
				Restvalues = resultMap;
			} else

			{

				bodylist = response.jsonPath().getList(getValue);
				resultMap.put(getValue, bodylist);
			}

			Restvalues = resultMap;
			logger.info("request value is  : " + resultMap);
			assertTrue("Service values are stored successfully", true);

		} catch (Exception e) {
			e.printStackTrace();
			StepDetail.addDetail(String.format("Service values are not stored successfully", new Object[0]), false);
			throw new SeleniumNonFatalException(
					String.format("Get_Values_From_Service NOT successful. ", new Object[0]));

		}
	}*/
	public static void Get_Values_From_Service(String getValue) throws Exception {
		logger.info("Inside Action --> Get_Values_From_Service ");
		try {
			logger.info("The Service URL is: " + serviceurl);
			logger.info("The input is: " + getValue);
			RestAssured.baseURI = serviceurl;
			RequestSpecification request = RestAssured.given();
			Response response = request.get(serviceurl);
			System.out.println(response.asString());
			String responseBody=response.asString();
			
			String[] datacopy1 = null;
			String[] datacopy2 = null;
			String[] datacopy3 = null;		
				
				datacopy1 = responseBody.split("},");			
			
				for(int i=0;i<datacopy1.length;i++) {			    
					    
					  datacopy2 = datacopy1[i].split(",");
					  
					  String[] myStringArray = new String[datacopy2.length];
					for(int j=0;j<datacopy2.length;j++) {	
				
					datacopy3 = datacopy2[j].split(":");
					
					String s = datacopy3[1].replaceAll("\"", "");	
					
					if(s.contains("-")) {
						
						s = s.substring(0, s.length() - 3);
						
					}else if(s.contains("}") && s.contains("]") ) {
						
						s = s.substring(0, s.length() - 2);
						
					}
					
					myStringArray[j] =s;
			
					}		
						
					serviceObjList=com.macys.mst.macysnet.Actionkeywords.Invoice.addValues(myStringArray[0], myStringArray[1], myStringArray[2], myStringArray[3], myStringArray[4], myStringArray[5], myStringArray[6], myStringArray[7], myStringArray[8], myStringArray[9],myStringArray[10], myStringArray[11], myStringArray[12]);
					}
					
			
			logger.info("request value is  : " + serviceObjList);
			assertTrue("Service values are stored successfully", true);

		} catch (Exception e) {
			 	e.printStackTrace();
			 	StepDetail.addDetail(String.format("Service values are not stored successfully", new Object[0]), false);
			 	throw new SeleniumNonFatalException(String.format("Get_Values_From_Service NOT successful. ", new Object[0]));
			//return null;

		}
	}
	/***********************************************************************************************************************************************************
	 * 'Method name : Get_Values_From_Database 'Project name : Macys Infrastructure
	 * Stability 'Description : This method is to Get the values from Database
	 * 'Developer : Sivanarumugam 'Reviewed By : 'Created On : August 2018
	 ************************************************************************************************************************************************************/
	public static void Get_Values_From_Database(String Connectvitiy, String query) throws Exception {
		logger.info("Inside Action --> Get_Values_From_Database ");
		try {
			Field[] fields = SQLConstants.Select.class.getDeclaredFields();
			String strsql = null;
			Connection connection = null;

			if (Connectvitiy.equalsIgnoreCase("ORACLE")) {

				connection = DBConnections.getinstance("db.Oracle", "LFNDQA").dbConnection();
			}
			if (Connectvitiy.equalsIgnoreCase("SQLServer")) {
				connection = DBConnections.getinstance("db.sqlserver", "MA000XVSQL22").dbConnection();
			}

			strsql = (String) SQLConstants.Select.class.getField(query).get(fields);
			DBresultMap = DBMethods.getDBValueInHashMap(connection, strsql);
			System.out.println("SERVICE =========== START =============  VALUES");
			Invoice.printValuesServiceList(serviceObjList);
			System.out.println("SERVICE =========== END =============  VALUES");
			System.out.println("DATABASE =========== START =============  VALUES");
			Invoice.printValuesServiceList(databaseObjList);
			System.out.println("DATABASE =========== END =============  VALUES");
			/*logger.info(" Restvalues    :" + Restvalues);
			logger.info(" DBresultMap   :" + DBresultMap);*/
			DBConnections.closeDBConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
			StepDetail.addDetail(String.format("Service values are not stored successfully", new Object[0]), false);
			throw new SeleniumNonFatalException(
					String.format("Get_Values_From_Service NOT successful. ", new Object[0]));
		}
	}

	/***********************************************************************************************************************************************************
	 * 'Method name : Compare_Service_And_Database 'Project name : Macys
	 * Infrastructure Stability 'Description : This method is to Compare Service
	 * value with Database 'Developer : Sivanarumugam 'Reviewed By : 'Created On :
	 * August 2018
	 ************************************************************************************************************************************************************/

	public static void Compare_Service_And_Database() {
		logger.info("Inside Action --> Compare_Service_And_Database ");
		try {

			//boolean isMatched = RestServicesUtils.validateServiceResponseWithDBResponse(Restvalues, DBresultMap);
			boolean isMatched = RestServicesUtils.validateServiceResponseWithDBResponse(serviceObjList,databaseObjList);

			if (isMatched) {
				StepDetail.addDetail("Service and Data base value are same", true);
				logger.info(" Service and Data base value are same");
				assertTrue(" Service and Data base value are same", true);
			} else {
				StepDetail.addDetail("Service and Data base value is not same", true);
				logger.info(" Service and Data base value is not same");
				assertFalse(" Service and Data base value is not same", true);
			}

		} catch (Exception e1) {
			logger.info(e1.getMessage());
			StepDetail.addDetail(e1.getMessage(), true);
			assertFalse(e1.getMessage(), true);

		}
	}
}