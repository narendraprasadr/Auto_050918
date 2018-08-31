package com.macys.mst.macysnet.Actionkeywords;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
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
import org.json.JSONArray;
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
import com.macys.mst.macysnet.MISObjects.Invoice;
import com.macys.mst.macysnet.MISObjects.InvoicesUI;
import com.macys.mst.macysnet.config.Constants;
import com.macys.mst.macysnet.db.app.AppDBMethods;
import com.macys.mst.macysnet.db.app.DBMethods;
import com.macys.mst.macysnet.sqlconstants.SQLConstants;
import com.macys.mst.macysnet.stepdefs.MISRestServices;
import com.macys.mst.macysnet.stepdefs.StatusofInvoice;
import com.macys.mst.macysnet.utils.General;
import com.macys.mst.macysnet.utils.RestServicesUtils;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
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
	public static List<String> UIvalues = new ArrayList<String>();
	public static Map<String, List<String>> UIScreenValues = new HashMap<String, List<String>>();
	public static Map<String, List<String>> Restvalues = new HashMap<String, List<String>>();
	public static Map<String, List<String>> DBresultMap = new HashMap<String, List<String>>();
	public static ArrayList<Invoice> serviceObjList = new ArrayList<>();
	public static ArrayList<InvoicesUI> UIdatabaseObjList = new ArrayList<>();
	public static ArrayList<Invoice> databaseObjList = new ArrayList<>();

	/***********************************************************************************************************************************************************
	 * 'Method name : Macysnet_Login 'Project name :
	 * FreightOptimizationAutomation 'Description : This method is to login
	 * Macy's Net Application 'Developer : Sriram 'Reviewed By : 'Created On :
	 * May 2018
	 ************************************************************************************************************************************************************/
	public static void Macysnet_Login(WebDriver lcldriver, String struserType) {

		try {
			General.Close_Window(lcldriver);
			if (verify_LoginUser(struserType)) {

				SeUiContextBase.gotourl(lcldriver,(FileConfig.getInstance().getStringConfigValue("MacysNet.URL")));
				lcldriver.get("http://dev.macysnet.com/AP/");
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
				if (struserType.equalsIgnoreCase("CKKanuparthi")) {
					userType = "CKKanuparthi";
					password = "macys2018";
				}
				WebElement element = objSeleniumElements.Get_Webelement(lcldriver, "txa_Login_Username");
				// WebElement
				// element=lcldriver.findElement(By.xpath(General.get_Locator("txa_Login_Username")));
				SeUiContextBase.sendkeys(element, userType);
				assertTrue("MTO Username entered successfully", true);
				element = objSeleniumElements.Get_Webelement(lcldriver, "txa_Login_Password");
				// element=lcldriver.findElement(By.xpath(General.get_Locator("txa_Login_Password")));
				SeUiContextBase.sendkeys(element, password);
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
	 * 'Method name : verify_LoginUser 'Project name :
	 * FreightOptimizationAutomation 'Description : This method is to verify
	 * login Macy's Net Application 'Developer : Sriram 'Reviewed By : 'Created
	 * On : May 2018
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
	 * 'Method name : Verify_Text_Length_Should_Be 'Project
	 * name:FreightOptimizationAutomation 'Description : This method is to
	 * perform the Verify length of value in test box 'Developer : Sriram
	 * 'Reviewed By : 'Created On : June 2018
	 ************************************************************************************************************************************************************/
	public static void verify_Text_Length_Should_Be(WebDriver lcldriver, String locString, String numlen) {
		logger.info("Inside Project Action --> verify_Text_Length_Should_Be ");
		try

		{

			String actual = SeleniumElements.Get_Value(lcldriver, locString);
			int numtemp = Integer.parseInt(numlen);

			if (!actual.isEmpty()) {
				// String message = String.format("Value of text len'%s' should
				// have been '%s'
				// but was '%s'", new Object[] {actual,numtemp});
				int length = actual.length();
				if (length <= numtemp) {
					logger.info("Action verify_Text_Length_Should_Be is Successful");
					Assert.assertTrue(true, "Action verify_Text_Length_Should_Be is Successful");
				} else {
					logger.info("Action verify_Text_Length_Should_Be is not Successful");
					Assert.assertTrue(false, "Action verify_Text_Length_Should_Be is not Successful");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Action verify_Text_Length_Should_Be failed !");
			// StepDetail.addDetail(String.format("Verify_Text_Length_Should_Be
			// is NOT
			// successful.", new Object[0]), false);
			throw new SeleniumNonFatalException(" Action -> verify_Text_Length_Should_Be : could not be completed!");
		}
	}

	/***********************************************************************************************************************************************************
	 * 'Method name : verify_Dropdownlist_Matches_With_RESTService 'Project
	 * name: FreightOptimizationAutomation 'Description : This method is to
	 * perform the validation dropdown list with rest services 'Developer :
	 * Sriram 'Reviewed By : 'Created On : June 2018
	 ************************************************************************************************************************************************************/
	/*
	 * public static void verify_Dropdownlist_Matches_With_RESTService(WebDriver
	 * lcldriver, String locString, String serviceURL, String strvalue) {
	 * logger.
	 * info("Inside Project Action --> verify_Dropdownlist_Matches_With_RESTService "
	 * ); try { String[] dataArrayCopy = null; if (serviceURL.contains(",")) {
	 * dataArrayCopy = serviceURL.split(","); } AFOJRestServices.Restvalues =
	 * RestServicesUtils.getListOfValues(dataArrayCopy[0], dataArrayCopy[1]);
	 * List<String> cellData = General.getUIDropdownValue(lcldriver, locString,
	 * dataArrayCopy[2]); List<String> srResult =
	 * AFOJRestServices.Restvalues.get(dataArrayCopy[0]); Set<String> hs = new
	 * HashSet<>(); hs.addAll(cellData); cellData.clear(); cellData.addAll(hs);
	 * Collections.sort(cellData); Collections.sort(srResult);
	 * 
	 * Assert.assertTrue(srResult.equals(cellData)); if
	 * (cellData.equals(srResult)) {
	 * logger.info("Rest service and UI value are same");
	 * Assert.assertTrue(true,
	 * "Action verify_Dropdownlist_Matches_With_RESTService is Successful"); }
	 * else { logger.info("Rest service and UI value is not same");
	 * Assert.assertTrue(false,
	 * "Action verify_Dropdownlist_Matches_With_RESTService is not Successful");
	 * }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); logger.
	 * info("Action verify_Dropdownlist_Matches_With_RESTService failed !");
	 * throw new SeleniumNonFatalException(
	 * " Action -> verify_Dropdownlist_Matches_With_RESTService : could not be completed!"
	 * ); }
	 * 
	 * }
	 */

	public static void verify_Dropdownlist_Matches_With_RESTService(WebDriver lcldriver, String locString,
			String serviceURL, String strvalue) {
		logger.info("Inside Project Action --> verify_Dropdownlist_Matches_With_RESTService ");
		List<String> cellData = new ArrayList<String>();
		List<String> srResult = new ArrayList<String>();
		try {
			String[] dataArrayCopy = null;
			if (serviceURL.contains(",")) {
				dataArrayCopy = serviceURL.split(",");
			}
			// Restvalues=RestServicesUtils.getListOfValues(strURL,strcolName,strvalue);
			System.out.println(dataArrayCopy.length);
			//
			if (dataArrayCopy.length == 3) {
				MISRestServices.Restvalues = RestServicesUtils.getListOfValues(dataArrayCopy[0], dataArrayCopy[1],
						dataArrayCopy[1]);
				srResult = MISRestServices.Restvalues.get(dataArrayCopy[1]);
				// System.out.println(dataArrayCopy[3] + "is Null");
				cellData = General.getUIDropdownValue(lcldriver, locString, dataArrayCopy[2]);
				Set<String> hs = new HashSet<>();
				hs.addAll(cellData);
				cellData.clear();
				cellData.addAll(hs);

			} else

			{
				String serURL = "http://lp000xstrs0002:8355/api/platform_msp/v1/shipping/getVendorsByKeyPhraseAndServiceType";
				String vendorName = "/nr-origin?qv=quark";
				String strValue = "title";
				MISRestServices.Restvalues = RestServicesUtils.getListOfValues(dataArrayCopy[0], dataArrayCopy[1],
						dataArrayCopy[2]);
				System.out.println(dataArrayCopy[4] + " is displayed");
				cellData = General.getUIDropdownValueForSpecificVendor(lcldriver, locString, dataArrayCopy[4]);
				srResult = MISRestServices.Restvalues.get(dataArrayCopy[2]);
			}

			System.out.println("The Service Result is : " + srResult);
			Set<String> hs = new HashSet<>();
			hs.addAll(cellData);
			cellData.clear();
			cellData.addAll(hs);
			System.out.println("The UI Result is : " + cellData);
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

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Action verify_Dropdownlist_Matches_With_RESTService failed !");
			throw new SeleniumNonFatalException(
					" Action -> verify_Dropdownlist_Matches_With_RESTService : could not be completed!");
		}

	}

	/***********************************************************************************************************************************************************
	 * 'Method name : select_MultiDropDown_Value 'Project
	 * name:FreightOptimizationAutomation 'Description : This method is to
	 * perform the validation dropdown list with rest services 'Developer :
	 * Sriram 'Reviewed By : 'Created On : June 2018
	 ************************************************************************************************************************************************************/
	public static void select_MultiDropDown_Value(WebDriver lcldriver, String locString, String strdata) {
		logger.info("Inside Project Action --> select_MultiDropDown_Value ");
		try {

			WebElement element = objSeleniumElements.Get_Webelement(lcldriver, locString);
			element.click();
			String[] dataArrayCopy = null;
			if (strdata.contains(",")) {
				dataArrayCopy = strdata.split(",");
			}

			for (String str : dataArrayCopy) {

				// objSeleniumWait.Set_Selenium_Timeout(lcldriver, 2);
				Thread.sleep(2000);
				element = objSeleniumElements.Get_Webelement(lcldriver, locString);
				element.click();
				// objSeleniumWait.Set_Selenium_Timeout(lcldriver, 2);
				Thread.sleep(2000);
				element = lcldriver.findElement(By.xpath("(.//*[@placeholder='Looking for'])[1]"));
				if (element.isDisplayed()) {
					element.clear();
					element.sendKeys(str);
				} else {
					element = lcldriver.findElement(By.xpath("(.//*[@placeholder='Looking for'])[2]"));
					element.clear();
					element.sendKeys(str);
				}
				Thread.sleep(2000);
				// objSeleniumWait.Set_Selenium_Timeout(lcldriver, 2);
				lcldriver.findElement(By.xpath("//div[@role='option'][1]")).click();
				logger.info(dataArrayCopy[0] + "Drop down selected successful");
				Assert.assertTrue(true, "Drop down selected successful");
				// objSeleniumWait.Set_Selenium_Timeout(lcldriver, 2);
				Thread.sleep(2000);
			}
			logger.info("Action select_MultiDropDown_Value is Successful");
			Assert.assertTrue(true, "Action select_MultiDropDown_Value is Successful");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Action select_MultiDropDown_Value failed !");
			throw new SeleniumNonFatalException(" Action -> select_MultiDropDown_Value : could not be completed!");
		}
	}

	/***********************************************************************************************************************************************************
	 * 'Method name : verify_Dropdownlist_Have_OneValue 'Project
	 * name:FreightOptimizationAutomation 'Description : This method is to
	 * perform the validation dropdown list with rest services 'Developer :
	 * Sriram 'Reviewed By : 'Created On : June 2018
	 ************************************************************************************************************************************************************/
	public static void verify_Dropdownlist_Have_OneValue(WebDriver lcldriver, String locString, String strdata) {
		logger.info("Inside Project Action --> verify_Dropdownlist_Have_OneValue ");
		try {
			String[] dataArrayCopy = null;
			if (strdata.contains(",")) {
				dataArrayCopy = strdata.split(",");
			}

			String strRuntimevalue = objSeleniumElements.Get_Text(lcldriver, locString);

			Assert.assertTrue(strRuntimevalue.equals(dataArrayCopy[dataArrayCopy.length - 1]));
			if (strRuntimevalue.equals(dataArrayCopy[dataArrayCopy.length - 1])) {
				logger.info(strRuntimevalue + "&" + dataArrayCopy[dataArrayCopy.length - 1]
						+ " Drop down list is allow only one value to select");
				Assert.assertTrue(true, "Action verify_Dropdownlist_Have_OneValue is Successful");
			} else {
				logger.info("Drop down list is allow Multi value to select");
				Assert.assertTrue(false, "Action verify_Dropdownlist_Have_OneValue is not Successful");
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Action verify_Dropdownlist_Have_OneValue failed !");
			throw new SeleniumNonFatalException(
					" Action -> verify_Dropdownlist_Have_OneValue : could not be completed!");
		}
	}

	/***********************************************************************************************************************************************************
	 * 'Method name : select_SingleDropDown_Value 'Project
	 * name:FreightOptimizationAutomation 'Description : This method is to
	 * perform the validation dropdown list with rest services 'Developer :
	 * Sriram 'Reviewed By : 'Created On : June 2018
	 ************************************************************************************************************************************************************/
	public static void select_SingleDropDown_Value(WebDriver lcldriver, String locString, String strdata) {
		logger.info("Inside Project Action --> select_SingleDropDown_Value ");
		try {
			// System.out.println(locString);
			String[] dataArrayCopy = null;
			if (strdata.contains(",")) {
				dataArrayCopy = strdata.split(",");
				int runtimeindex = Integer.parseInt(dataArrayCopy[1]);
				Thread.sleep(1000);
				WebElement element = lcldriver
						.findElement(By.xpath("(.//*[@placeholder='Looking for'])[" + runtimeindex + "]"));
				if (element.isDisplayed()) {
					element.sendKeys(dataArrayCopy[0]);
				}
				Thread.sleep(2000);
				List<WebElement> element1 = lcldriver
						.findElements(By.xpath(".//span[@class='jqx-listitem-state-normal jqx-item jqx-rc-all']"));
				logger.info("The element size: " + element1.size());
				for (int j = 0; j < element1.size(); j++) {
					String val = element1.get(j).getText();
					if (val.contains(dataArrayCopy[0])) {
						element1.get(j).click();
						logger.info(dataArrayCopy[0] + " is selected successfully");
					}
				}
			} else {
				WebElement element = objSeleniumElements.Get_Webelement(lcldriver, locString);
				// element1.sendKeys("NIKE");

				// WebElement
				// element=lcldriver.findElement(By.xpath("//*[@id='react-select-2--value']/div[2]/div"));
				boolean isEleDisp = element.isDisplayed();
				System.out.println(element.getText());
				System.out.println(isEleDisp);
				if (isEleDisp == true) {
					// Thread.sleep(2000);
					// element.click();
					element.sendKeys(strdata);
					Thread.sleep(3000);

					element.sendKeys(Keys.TAB);

				}
			}

			Assert.assertTrue(true, "Drop down selected successfully");

			logger.info("Action select_SingleDropDown_Value is Successful");
			Assert.assertTrue(true, "Action select_SingleDropDown_Value is Successful");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Action select_SingleDropDown_Value failed !");
			throw new SeleniumNonFatalException(" Action -> select_SingleDropDown_Value : could not be completed!");
		}
	}

	/***********************************************************************************************************************************************************
	 * 'Method name : validate_Text 'Project name :
	 * FreightOptimizationAutomation 'Description : This method is to verify
	 * given text is present in UI 'Developer : Praveen 'Reviewed By : 'Created
	 * On : May 2018
	 ************************************************************************************************************************************************************/

	public static void validate_Text(WebDriver lcldriver, String locString, String strdata) {
		logger.info("Inside Project Action --> validate_Text ");
		try {
			// objSeleniumWait.Set_Selenium_Timeout(lcldriver, 2);
			Thread.sleep(2000);
			WebElement element = objSeleniumElements.Get_Webelement(lcldriver, locString);
			// lcldriver.findElement(By.xpath(General.get_Locator(locString)));
			SeleniumWait.Fluent_Wait_Until_Element_Visible(lcldriver, element, 30L);

			// SeUiContextBase.Wait_Until_Element_Is_Visible(lcldriver,
			// element);
			String value = element.getText();
			if (value.contains(strdata)) {
				logger.info(value + " message displayed");
				Assert.assertTrue(true, value + " message displayed");
			} else {
				logger.info(value + " message displayed");
				Assert.assertFalse(true, value + " Incorrect message displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Action validate_Text failed !");
			throw new SeleniumNonFatalException(" Action -> validate_Text : could not be completed!");
		}
	}

	/***********************************************************************************************************************************************************
	 * 'Method name : validate_date_Time_values 'Project name :
	 * FreightOptimizationAutomation 'Description : This method is to verify
	 * data and time is present in UI 'Developer : Chenna 'Reviewed By :
	 * 'Created On : May 2018
	 ************************************************************************************************************************************************************/
	public static void validate_date_Time_values(WebDriver lcldriver, String locString, String text) {
		logger.info("Inside AFOJ Project Action --> validate_date_Time_values ");
		try {
			if (text.equalsIgnoreCase("currentdate")) {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("M/d/yyyy");
				LocalDateTime now = LocalDateTime.now();
				text = dtf.format(now);
			}
			WebElement element = objSeleniumElements.Get_Webelement(lcldriver, locString);
			SeleniumWait.Fluent_Wait_Until_Element_Visible(lcldriver, element, 30L);
			String value = element.getAttribute("value");
			if (value.contains(text)) {
				Assert.assertTrue(true,
						String.format("Element %s text matches text %s", new Object[] { element, text }));
				logger.info(String.format("Element %s text matches text %s => OK", new Object[] { element, text }));
				StepDetail.addDetail(String.format("Element %s text matches text %s", new Object[] { element, text }),
						true);
				logger.info("AFOJ Action-> validate_date_Time_values is Successful");
			} else {
				Assert.assertTrue(false, String.format("Element text should match text \'%s\', but its text was %s.",
						new Object[] { text, value }));
				logger.info(String.format("Element text should match text \'%s\', but its text was %s.",
						new Object[] { text, value }));
				StepDetail.addDetail(String.format("Element text should match text \'%s\', but its text was %s.",
						new Object[] { text, value }), false);
				throw new SeleniumNonFatalException(String.format(
						"Element text should match text \'%s\', but its text was %s.", new Object[] { text, value }));
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Action validate_Text failed !");
			throw new SeleniumNonFatalException("AFOJ Action -> validate_date_Time_values : could not be completed!");
		}
	}

	/***********************************************************************************************************************************************************
	 * 'Method name : Switch_Frame 'Project name : FreightOptimizationAutomation
	 * 'Description : This method is to switch frame in UI 'Developer : sriram
	 * 'Reviewed By : 'Created On : May 2018
	 ************************************************************************************************************************************************************/
	public static void Switch_Frame(WebDriver lcldriver, String locator) {
		logger.info("Inside Action --> Switch_Frame ");
		try {
			logger.info(String.format("Selecting frame '%s'.", new Object[] { locator }));

			lcldriver.switchTo().frame(locator);
			logger.info("Action Switch_Frame is Successful");
			StepDetail.addDetail(String.format("switch_Frame is successful.", new Object[0]), true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Action Select_Frame failed !");
			StepDetail.addDetail(String.format("Select_Frame is NOT successful.", new Object[0]), false);
			throw new SeleniumNonFatalException(" Action -> Select_Frame : could not be completed!");
		}
	}

	/***********************************************************************************************************************************************************
	 * 'Method name : Switch_Window 'Project name :
	 * FreightOptimizationAutomation 'Description : This method is to switch to
	 * window in UI 'Developer : praveen 'Reviewed By : 'Created On : May 2018
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
	 * 'Method name : Verify_Value_inDatabase 'Project name :
	 * FreightOptimizationAutomation 'Description : This method is to vaildate
	 * value in UI and database 'Developer : sriram 'Reviewed By : 'Created On :
	 * May 2018
	 ************************************************************************************************************************************************************/

	public static void Verify_Value_inDatabase(String colValue, String strcode) throws Exception {

		try {
			if (!strcode.isEmpty()) {
				String strname = null;
				String strcomper = null;
				AppDBMethods.connection = DBConnections.getinstance("db.Oracle", "LFCBIZ01DB").dbConnection();
				if (colValue.contains(",")) {

					for (String subString : colValue.split(",")) {
						String temparr[] = subString.split("/");

						if (colValue.equalsIgnoreCase("SCACCode")) {
							strname = SQLConstants.Select.SCACCode;
						} else {
							strname = SQLConstants.Select.query.replace("#fieldName", temparr[1])
									.replace("#tableName", temparr[0]).replace("#conditionFieldName", strcode)
									.replace("#value", MISRestServices.shipmentNum);
							strcomper = temparr[2];
							System.out.println(strcomper);
						}

						/*
						 * String strname = null; if
						 * (colValue.equalsIgnoreCase("SCACCode")) { strname =
						 * SQLConstants.Select.SCACCode; } else {
						 * strname=SQLConstants.Select.query.replace(
						 * "#fieldName", fieldName).replace("#tableName",
						 * tableName).replace("#conditionFieldName",
						 * conditonFieldName).replace("#value",Constants.
						 * colValue); }
						 */

						// String strsql = strname.replace("#strcode", strcode);
						/// System.out.println(strsql);

						String dbvalues = DBUtils.getDBValueInString(AppDBMethods.connection, strname);
						// System.out.println(dbvalues+"--"+strsql);

						if (dbvalues.equalsIgnoreCase(strcomper)) {
							logger.info(dbvalues + " message displayed");
							Assert.assertTrue(true, dbvalues + " message displayed");

						} else {
							logger.info(strcode + " message displayed");
							Assert.assertFalse(true, strcode + " Incorrect message displayed");

						}
					}
					DBConnections.closeDBConnection(AppDBMethods.connection);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/***********************************************************************************************************************************************************
	 * 'Method name : select_Date 'Project name : FreightOptimizationAutomation
	 * 'Description : This method is to select date in UI and database
	 * 'Developer : praveen 'Reviewed By : 'Created On : May 2018
	 ************************************************************************************************************************************************************/

	public static void select_Date(WebDriver lclDriver, String locString, String date) {
		logger.info("Inside Project Action --> select_Date");
		try {

			List<WebElement> dateWidget = objSeleniumElements.Get_Webelements(lclDriver, locString);
			// List<WebElement> dateWidget =
			// lclDriver.findElements(By.xpath(General.get_Locator(locString)));

			for (WebElement cell : dateWidget) {
				String value = cell.getText();
				if (value.equals(date)) {
					cell.click();
					logger.info("Then given '" + date + "' Date is selected successfully");
					break;
				}
			}

		} catch (Exception e) {
			assertFalse(date + " date is not selected", true);
			logger.info("Inside Project Action -->   " + date + " date is not selected");
		}

	}

	/***********************************************************************************************************************************************************
	 * 'Method name : validateServicePostResponseCode 'Project name :
	 * FreightOptimizationAutomation 'Description : This method is to validate
	 * post respone of API Rest 'Developer : sriram 'Reviewed By : 'Created On :
	 * May 2018
	 ************************************************************************************************************************************************************/

	public static void validateServicePostResponseCode(String serviceURL, String expectedCode, String field,
			String value) {
		logger.info(
				"Inside Project Action --> validateServicePostResponseCode, String serviceURL,String field, String value,String expectedCode ");
		try

		{

			Map<String, Object> requestMap = new HashMap<String, Object>();
			Map<String, Object> tempHaspMap = new HashMap<String, Object>();
			String field1 = null;

			JSONObject tempsubJSON = new JSONObject();
			if (value.contains("-")) {
				String arrayOfKeyValue[] = value.split("-");
				JSONObject tempJSON = new JSONObject();
				for (String str : arrayOfKeyValue) {

					if (str.contains("=")) {

						String arrayOfKeyValue1[] = str.split("=");
						for (String str1 : arrayOfKeyValue1) {
							if (str1.contains(",")) {
								String arrayOfKeyValue2[] = str1.split(",");

								for (String str2 : arrayOfKeyValue2) {

									String keyValue[] = str2.split(":");
									// keyvalue[0] - key, keyvalue[1] - value
									tempJSON.put(keyValue[0], keyValue[1]);
								}

							} else {
								field1 = str1;
							}

							tempsubJSON.put(field1, tempJSON);
						}
					} else {

						if (str.contains(",")) {

							String arrayOfKeyValue3[] = str.split(",");

							for (String str3 : arrayOfKeyValue3) {

								String keyValue[] = str3.split(":");
								// keyvalue[0] - key, keyvalue[1] - value
								tempsubJSON.put(keyValue[0], keyValue[1]);
							}

						} else {
							String keyValue[] = str.split(":");
							// keyvalue[0] - key, keyvalue[1] - value
							tempsubJSON.put(keyValue[0], keyValue[1]);
						}
					}
				}
			}
			requestJSON.put(field, tempsubJSON);
			// AFOJRestServices.request.body(AFOJRestServices.requestJSON.toJSONString());

			request.body(MISRestServices.requestJSON.toJSONString());
			response = RestUtilities.postRequestAndReturnResponse(serviceURL, requestJSON.toString());

			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			int expected = Integer.parseInt(expectedCode);
			Assert.assertEquals(expected, statusCode);
			logger.info("Action validateServicePostResponseCode Pass !");
			// return statusCode;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Action validateServicePostResponseCode failed !");
			// StepDetail.addDetail(String.format("Verify_Text_Length_Should_Be
			// is NOT
			// successful.", new Object[0]), false);
			throw new SeleniumNonFatalException(" Action -> validateServicePostResponseCode : could not be completed!");
		}
	}

	/***********************************************************************************************************************************************************
	 * 'Method name : validate_Default_Value_Should_Be 'Project name :
	 * FreightOptimizationAutomation 'Description : This method is to validate
	 * 'Developer : Praveen 'Reviewed By : 'Created On : May 2018
	 ************************************************************************************************************************************************************/

	public static void validate_Default_Value_Should_Be(WebDriver lclDriver, String locString, String Values) {
		logger.info("Inside Project Action --> validate_Default_Value_Should_Be ");
		String appValue = null;
		String[] dataArrayCopy = null;
		try {

			if (Values.contains(",")) {
				dataArrayCopy = Values.split(",");
			}
			// objSeleniumWait.Set_Selenium_Timeout(lclDriver, 2);
			Thread.sleep(2000);
			WebElement ele = objSeleniumElements.Get_Webelement(lclDriver, locString);
			appValue = SeleniumElements.Get_Element_Attribute(ele, dataArrayCopy[1]);
			if (dataArrayCopy[0].contains("/") || appValue.contains("/")) {
				Date formatter = new SimpleDateFormat("MM/dd/yyyy").parse(appValue);
				Date.parse(dataArrayCopy[0]);
			}
			if (appValue.trim().equalsIgnoreCase(dataArrayCopy[0].trim())) {
				SeUiContextBase.Capture_Page_Screenshot(lclDriver, Constants.scnshotPassPath
						+ "validate_Default_Value_Should_Be_" + General.getTimeStamp() + ".jpg");
				logger.info("Expected is " + dataArrayCopy[0] + " value and found value is " + appValue);
				assertTrue("Expected is " + dataArrayCopy[0] + " value and found value is " + appValue, true);

			} else {
				SeUiContextBase.Capture_Page_Screenshot(lclDriver, Constants.scnshotFailPath
						+ "validate_Default_Value_Should_Be_Fail_" + General.getTimeStamp() + ".jpg");
				logger.error("Expected is " + dataArrayCopy[0] + " value But found value is " + appValue);
				assertFalse("Expected is " + dataArrayCopy[0] + " value But found value is " + appValue, true);
			}

		} catch (Exception e) {
			SeUiContextBase.Capture_Page_Screenshot(lclDriver, Constants.scnshotFailPath
					+ "validate_Default_Value_Should_Be_Fail_" + General.getTimeStamp() + ".jpg");
			logger.error("Expected is " + dataArrayCopy[0] + " value But found value is " + appValue);
			assertFalse("Expected is " + dataArrayCopy[0] + " value But found value is " + appValue, true);
		}
	}

	/***********************************************************************************************************************************************************
	 * 'Method name : validate_Default_Value_Should_Be 'Project name :
	 * FreightOptimizationAutomation 'Description : This method is to validate
	 * 'Developer : Praveen 'Reviewed By : 'Created On : May 2018
	 ************************************************************************************************************************************************************/

	public static void AFOJ_InputData(WebDriver lclDriver, String strOrigin) {
		try {
			WebElement element;
			if (!strOrigin.isEmpty()) {
				element = objSeleniumElements.Get_Webelement(lclDriver, "txt_CRCRTV_OriginName");
				SeUiContextBase.sendkeys(element, strOrigin);
			}
		} catch (Exception e) {
		}
	}

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
	 * 'Method name : Validate_Dropdown_Values 'Project name : Macys
	 * Infrastructure Stability 'Description : This method is to validate
	 * 'Dropdown values 'Developer : Senthilkumar 'Reviewed By : 'Created On :
	 * August 2018
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
			Thread.sleep(2000);
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
			// StepDetail.addDetail(String.format("Element is not clicked.", new
			// Object[0]), false);
			// throw new SeleniumNonFatalException(String.format("clickElement
			// is NOT successful. ", new Object[0]));
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

	public static void Get_Values_From_Service(String getValue) throws Exception {
		logger.info("Inside Action --> Get_Values_From_Service ");
		try {
			Get_Values_From_ServiceInObject(getValue);
			/*
			 * logger.info("The Service URL is: " + serviceurl);
			 * logger.info("The input is: " + getValue); Map<String,
			 * List<String>> resultMap = new HashMap<String, List<String>>();
			 * List<String> bodylist = new ArrayList<String>();
			 * RestAssured.baseURI = serviceurl; RequestSpecification request =
			 * RestAssured.given(); Response response = request.get(serviceurl);
			 * //System.out.println(response.asString()); String
			 * responseBody=response.asString();
			 * 
			 * 
			 * if (getValue.contains(",")) {
			 * 
			 * for (String subString : getValue.split(",")) {
			 * 
			 * bodylist = response.jsonPath().getList(subString); if
			 * (subString.contains("Date")) {
			 * bodylist.removeAll(Arrays.asList(Integer.valueOf(0)));
			 * bodylist.removeAll(Arrays.asList(" ", ""));
			 * bodylist.removeAll(Collections.singleton(null)); List<String>
			 * bodylist2 = new ArrayList<String>(); for (int i = 0; i <
			 * bodylist.size(); i++) {
			 * 
			 * String[] arrOfStr = bodylist.get(i).split(" ");
			 * bodylist2.add(arrOfStr[0].trim()); }
			 * resultMap.put(subString.toLowerCase(), bodylist2); Restvalues =
			 * resultMap; } else if (subString.equalsIgnoreCase("checknbr") ||
			 * subString.equalsIgnoreCase("checkDate")) {
			 * bodylist.removeAll(Arrays.asList(Integer.valueOf(0)));
			 * resultMap.put(subString.toLowerCase(), bodylist); Restvalues =
			 * resultMap; } else {
			 * 
			 * resultMap.put(subString.toLowerCase(), bodylist); Restvalues =
			 * resultMap; }
			 * 
			 * } } else if(getValue.equalsIgnoreCase("email")){ responseBody =
			 * response.getBody().asString();
			 * logger.info("Response Body is =>  " + responseBody);
			 * 
			 * Pattern pattern = Pattern.compile("[\\w.]+@[\\w.]+"); Matcher
			 * matcher = pattern.matcher(responseBody);
			 * 
			 * while(matcher.find()){ String email = matcher.group();
			 * bodylist.add(email);
			 * System.out.println("Email from service="+email); }
			 * resultMap.put(getValue, bodylist); Restvalues = resultMap; } else
			 * 
			 * {
			 * 
			 * bodylist = response.jsonPath().getList(getValue);
			 * resultMap.put(getValue, bodylist); }
			 * 
			 * Restvalues = resultMap; logger.info("request value is  : " +
			 * resultMap); assertTrue("Service values are stored successfully",
			 * true);
			 */

		} catch (Exception e) {
			e.printStackTrace();
			StepDetail.addDetail(String.format("Service values are not stored successfully", new Object[0]), false);
			throw new SeleniumNonFatalException(
					String.format("Get_Values_From_Service NOT successful. ", new Object[0]));

		}
	}

	public static void Get_Values_From_ServiceInObject(String getValue) throws Exception {
		logger.info("Inside Action --> Get_Values_From_ServiceInObject ");
		try {
			logger.info("The Service URL is: " + serviceurl);
			logger.info("The input is: " + getValue);
			RestAssured.baseURI = serviceurl;
			RequestSpecification request = RestAssured.given();
			Response response = request.get(serviceurl);
			System.out.println(response.asString());
			String responseBody = response.asString();

			JSONArray jsonArr = new JSONArray(responseBody);

			for (int i = 0; i < jsonArr.length(); i++) {
				List<String> bodylist = new ArrayList<String>();
				org.json.JSONObject jsonobject = jsonArr.getJSONObject(i);
				// System.out.println(jsonobject);
				if (getValue.contains(",")) {

					for (String subString : getValue.split(",")) {
						// System.out.println(jsonobject.get(subString));

						if (subString.contains("Date")) {
							String value = String.valueOf(jsonobject.get(subString));

							bodylist.add(value.substring(0, 11));
						} else {
							String value = String.valueOf(jsonobject.get(subString));

							bodylist.add(value);
						}
					}

					serviceObjList.add(new Invoice(bodylist));

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			StepDetail.addDetail(String.format("Service values are not stored successfully", new Object[0]), false);
			throw new SeleniumNonFatalException(
					String.format("Get_Values_From_ServiceInObject NOT successful. ", new Object[0]));

		}
	}

	public static void Get_Values_From_UIScreen(String getValue) throws Exception {
		logger.info("Inside Action --> Get_Values_From_UIScreen ");
		try {
			logger.info("The input is: " + getValue);
			Map<String, List<String>> resultMap = new HashMap<String, List<String>>();
			List<String> bodylist = new ArrayList<String>();

			if (getValue.contains(",")) {

				String s[] = getValue.split(",");

				for (String s1 : s) {
					resultMap.put(s1, bodylist);
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
			// return resultMap;

		} catch (Exception e) {
			e.printStackTrace();
			StepDetail.addDetail(String.format("Service values are not stored successfully", new Object[0]), false);
			throw new SeleniumNonFatalException(
					String.format("Get_Values_From_Service NOT successful. ", new Object[0]));
			// return null;

		}
	}

	public static void Get_Values_From_DatabaseInObject(String Connectvitiy, String query) throws Exception {
		logger.info("Inside Action --> Get_Values_From_DatabaseInObject ");
		try {
			Field[] fields = SQLConstants.Select.class.getDeclaredFields();
			String strsql = null;
			Connection connection = null;

			if (Connectvitiy.equalsIgnoreCase("ORACLE")) {

				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				connection = DriverManager.getConnection("jdbc:oracle:thin:@//LFNDDB.federated.fds:1511/LFND", "mnetqa",
						"mnetqa1");
			}
			if (Connectvitiy.equalsIgnoreCase("SQLServer")) {

				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
				connection = DBConnections.getinstance("db.sqlserver", "MT000XBSQL105").dbConnection();
			}
			strsql = (String) SQLConstants.Select.class.getField(query).get(fields);
			logger.info("Query Is : " + strsql);
			List<String> dbList = new ArrayList<String>();
			ResultSet rs = null;
			Statement stmt = null;
			stmt = connection.createStatement();

			rs = stmt.executeQuery(strsql);
			if (rs != null) {

				ResultSetMetaData md = rs.getMetaData();
				int columns = md.getColumnCount();
				while (rs.next()) {

					for (int i = 1; i <= columns; ++i) {

						if (rs.getObject(i) != null) {
							dbList.add(rs.getString(i).trim());

						}

					}
					MISActionkeywords.UIdatabaseObjList.add(new InvoicesUI(dbList));

				}

			}
			DBConnections.closeDBConnection(connection);
		}

		catch (Exception e) {
			e.printStackTrace();
			StepDetail.addDetail(String.format("DB values are not stored successfully", new Object[0]), false);
			throw new SeleniumNonFatalException(
					String.format("Get_Values_From_DatabaseInObject NOT successful. ", new Object[0]));
		}
	}

	public static void Get_Values_From_Database(String Connectvitiy, String query) throws Exception {
		logger.info("Inside Action --> Get_Values_From_Database ");
		try {
			Field[] fields = SQLConstants.Select.class.getDeclaredFields();
			String strsql = null;
			Connection connection = null;

			if (Connectvitiy.equalsIgnoreCase("ORACLE")) {

				String documentNBr[] = null;
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				connection = DriverManager.getConnection("jdbc:oracle:thin:@//LFNDDB.federated.fds:1511/LFND", "mnetqa",
						"mnetqa1");
			}
			if (Connectvitiy.equalsIgnoreCase("SQLServer")) {

				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
				/*
				 * SQLServerDataSource ds = new SQLServerDataSource();
				 * ds.setUser("fdsnetwebuser"); ds.setPassword("m96xrt");
				 * ds.setServerName("MT000XBSQL105"); ds.setPortNumber(1433);
				 * ds.setDatabaseName("FDSNet"); connection =
				 * ds.getConnection();
				 */
				// connection = DriverManager.getConnection(Constants.uri,
				// Constants.username,Constants.password);
				connection = DBConnections.getinstance("db.sqlserver", "MT000XBSQL105").dbConnection();
			}

			// connection = DBConnections.getinstance("db.Oracle",
			// "LFNDQA").dbConnection();
			/*
			 * if(strvalue.contains("/")) { documentNBr=strvalue.split("/"); }
			 * if(poValue.equalsIgnoreCase("PO")) { strsql =
			 * SQLConstants.Select.INVOICE_BY_PO.replace("#POs",
			 * documentNBr[3]); } if(poValue.equalsIgnoreCase("Invoice")) {
			 * strsql = SQLConstants.Select.INVOICE.replace("#documentnbr",
			 * documentNBr[3]); }
			 */
			strsql = (String) SQLConstants.Select.class.getField(query).get(fields);
			DBresultMap = DBMethods.getDBValueInHashMap(connection, strsql);
			/*
			 * logger.info(" Restvalues    :"+Restvalues);
			 * logger.info(" DBresultMap   :"+DBresultMap);
			 */
			System.out.println("SERVICE =========== START =============  VALUES");
			Invoice.printValuesServiceList(serviceObjList);
			System.out.println("SERVICE =========== END =============  VALUES");
			System.out.println("DATABASE =========== START =============  VALUES");
			// Invoice.printValuesServiceList(databaseObjList);
			System.out.println("DATABASE =========== END =============  VALUES");

			/*
			 * logger.info(" Restvalues    :"+serviceObjList);
			 * logger.info(" DBresultMap   :"+databaseObjList);
			 */
			DBConnections.closeDBConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
			StepDetail.addDetail(String.format("Service values are not stored successfully", new Object[0]), false);
			throw new SeleniumNonFatalException(
					String.format("Get_Values_From_Service NOT successful. ", new Object[0]));
		}
	}

	public static void Compare_Service_And_Database() {

		try {

			// RestUtilities.validateServiceResponseWithDBResponse(Restvalues,DBresultMap);
			// boolean isMatched =
			// RestServicesUtils.validateServiceResponseWithDBResponse(Restvalues,DBresultMap);
			// boolean isMatched =
			// RestServicesUtils.validateServiceResponseWithDBResponse(serviceObjList,databaseObjList);

			/*
			 * if (isMatched) {
			 * StepDetail.addDetail("Service and Data base value are same",
			 * true); logger.info(" Service and Data base value are same");
			 * assertTrue(" Service and Data base value are same",true); } else
			 * { StepDetail.addDetail("Service and Data base value is not same",
			 * true); logger.info(" Service and Data base value is not same");
			 * assertFalse(" Service and Data base value is not same",true); }
			 */

		} catch (Exception e1) {
			logger.info(e1.getMessage());
			StepDetail.addDetail(e1.getMessage(), true);
			assertFalse(e1.getMessage(), true);

		}
	}

	public static void Get_Dropdown_Values_In_List(WebDriver lclDriver, String locPath) {
		logger.info("Inside Action --> Get_Dropdown_Values_In_List ");
		int ddvalueCount = 0;
		String data = "accountnbr,accountname";

		try {
			lclDriver.findElement(By.xpath("//div[3]/div/div/div/div[@role='button']")).click();
			ddvalueCount = lclDriver.findElements(By.xpath(General.get_Locator(locPath))).size();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			logger.info(" No value in dropdown ");
		}

		for (int i = 1; i <= ddvalueCount; i++) {

			try {
				String value = null;
				value = lclDriver.findElement(By.xpath("//ul[@role='listbox']/li[" + i + "]")).getText();
				if (value.contains("-")) {
					String s3[] = value.split("-");

					for (String s4 : s3) {

					}

				}
				UIvalues.add(value);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		logger.info(" Dropdown values are : " + UIvalues);

		for (int i = 0; i < UIvalues.size(); i++) {
			if (UIvalues.get(i).contains("-")) {
				String s[] = UIvalues.get(i).split("-");
				String d1[] = data.split(",");

				for (String s2 : d1) {

				}

			}
		}

	}

	public static void Page_Should_Not_Contain_Element(WebDriver lcldriver, String locString) {
		logger.info("Inside Action from local --> Page_Should_Not_Contain_Element ");
		try {
			// SeleniumElements.Page_Should_Not_Contain_Element(lcldriver,
			// General.get_Locator(locString));
			if (SeleniumElements.isElementPresent(lcldriver, General.get_Locator(locString))) {
				String message = String.format("Page should not have contained %s ", new Object[] { locString });
				Assert.assertTrue(false,
						String.format("Page should not have contained %s ", new Object[] { locString }));
				logger.info(message);
				StepDetail.addDetail(String.format("Page should not have contained %s ", new Object[] { locString }),
						false);
				throw new SeleniumNonFatalException(message);
			}
			Assert.assertTrue(true, String.format("Current page does not contain %s.", new Object[] { locString }));
			logger.info(String.format("Current page does not contain %s.", new Object[] { locString }));
			StepDetail.addDetail(String.format("Current page does not contain %s.", new Object[] { locString }), true);
			logger.info("Action Page_Should_Not_Contain_Element is Successful");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Action Page_Should_Not_Contain_Element failed !");
			StepDetail.addDetail(String.format("Page_Should_Not_Contain_Element is NOT successful.", new Object[0]),
					false);
			throw new SeleniumNonFatalException(" Action -> Page_Should_Not_Contain_Element : could not be completed!");
		}
	}
	
	public static void Compare_UISCREEN_values_With_Database() {
		logger.info("Inside Action from local --> CompareUIvalueswithDatabase ");
		try {
		//System.out.println(StatusofInvoice.UIObjList.size());
		//System.out.println(MISActionkeywords.UIdatabaseObjList.size());
		if (StatusofInvoice.UIObjList.size() == MISActionkeywords.UIdatabaseObjList.size()) {

			for (int i = 0; i < StatusofInvoice.UIObjList.size(); i++) {

				InvoicesUI service = StatusofInvoice.UIObjList.get(i);
				InvoicesUI database = MISActionkeywords.UIdatabaseObjList.get(i);

				if (service.documentNo.trim().equals(database.documentNo.trim())
						&& service.status.trim().equals(database.status.trim())
						&& service.desc.trim().equals(database.desc.trim())
						&& service.dueDate.trim().equals(database.dueDate.trim())
						&& service.amount.trim().equals(database.amount.trim())) {
					logger.info(" UI SCREEN and Data base records are equal");

				} else {

					/*
					 * logger.info(serviceList.get(i));
					 * logger.info(dbList.get(i));
					 */
					logger.info(" UI SCREEN and Data base records are not equal");
					break;

				}

			}

		} else {

			logger.info(" UI SCREEN and Data base records are not equal");
			assertFalse("  UI SCREEN and Data base records are not equal", true);

		}

	}
catch (Exception e) {
	e.printStackTrace();
	logger.info("Action CompareUIvalueswithDatabase failed !");
	StepDetail.addDetail(String.format("CompareUIvalueswithDatabase is NOT successful.", new Object[0]),
			false);
	throw new SeleniumNonFatalException(" Action -> CompareUIvalueswithDatabase : could not be completed!");
}
	}

	
	public static void Report_validation(WebDriver lclDriver,String inputs) throws Exception {

		
		String[] input = inputs.split("/");
		
		String activity = input[0];
		String division = input[1];
		String accountNumber = input[2];
		
		
		logger.info("Inside Action --> "+input[0]+" Report_validation  ");
		
		boolean ismatched = false;
		
		if(activity.equals("Trial Balance"))
		{
		try {
			Thread.sleep(70000);
			
		} catch (InterruptedException e1) {
		
			e1.printStackTrace();
		}
		}
		String reportName = lclDriver.findElement(By.xpath("//h3")).getText();
		String reportDetails = lclDriver.findElement(By.xpath(General.get_Locator("statusDetails"))).getText();
		
		System.out.println(reportDetails);
		
		String[] splitReportDetails = reportDetails.split("\\r?\\n");	
		
		String[] div = splitReportDetails[2].split(":");
		System.out.println(div[1]);
		
		String[] accNo = splitReportDetails[3].split(":");
		System.out.println(accNo[1]);
		
		
			if(General.records_On_Page(lclDriver,"documentsCount")>0)
				{
				logger.info("Inside Action --> Records are displayed  ");		
			
				}else {
					
					logger.info("Inside Action --> No Record are displayed  ");	
					Assert.assertTrue(false);				
				}		
			
			if(reportName.equals(activity) && div[1].trim().equals(division) && accNo[1].trim().equals(accountNumber)) {
				logger.info("Inside Action --> Report name, Division, Account number are matched  ");	
				Assert.assertTrue(true);
			}else {
				ismatched = false;
				Assert.assertTrue(false);
			}

	}
}