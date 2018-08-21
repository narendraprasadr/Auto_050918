package com.macys.mst.macysnet.stepdefs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.json.simple.JSONObject;

import com.macys.mst.artemis.db.DBConnections;
import com.macys.mst.artemis.db.DBUtils;
import com.macys.mst.artemis.rest.RestUtilities;
import com.macys.mst.artemis.selenium.WebDriverListener;
import com.macys.mst.macysnet.config.Constants;
import com.macys.mst.macysnet.db.app.DBMethods;
import com.macys.mst.macysnet.sqlconstants.SQLConstants;
import com.macys.mst.macysnet.utils.RestServicesUtils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class MISRestServices implements SQLConstants {

	public static List<String> dbvalues = new ArrayList<String>();
	private static Logger logger = Logger.getLogger(MISRestServices.class.getName());
	public static Map<String, List<String>> DBresultMap = new HashMap<String, List<String>>();

	public static Map<String, List<String>> Restvalues = new HashMap<String, List<String>>();
	public static List<String> RestListvalues = new ArrayList<String>();
	public static String DBvaildation = null;
	public static String serviceURL = null;
	public static String serviceInput = null;
	public static Map<String, String> mapShp_Ship_to = null;
	public static String columnInValuesStr = null;
	public static RequestSpecification request = RestAssured.given();
	public static JSONObject requestJSON = new JSONObject();
	public static Response response;
	public static String shipmentNum;
	public static Map<Object, List<String>> testMap = new HashMap<Object, List<String>>();
	public static Map<String, List<Object>> testMAp1 = new HashMap<String, List<Object>>();
	public static Map<String, List<Object>> TestDBresultMap = new HashMap<String, List<Object>>();
	public static Map<String, List<Object>> Restvalues1 = new HashMap<String, List<Object>>();

	public Long TestNGThreadID = Thread.currentThread().getId();

	@BeforeStory
	public void beforeStory() {
		ConcurrentHashMap<String, String> obj = WebDriverListener.EnvMap.get(TestNGThreadID);
		WebDriverListener.EnvMap.put((Thread.currentThread().getId()), obj);
	}

	@Given("REST service return code 200 for the $ServiceURL $inputs")
	public static void VerifyResponsesCodesIs200(@Named("serviceUrl") String ServiceURL, @Named("inputs") String input)
			throws Exception {

		String valu = null;
		// valu=RestUtilities.postRequestResponse(ServiceURL, input);
		RestServicesUtils.verfiyRequestResponse(ServiceURL, input);
		System.out.println(valu);
		serviceURL = ServiceURL;
		RestServicesUtils.verifyResponseStatus(200, serviceURL, input);
		serviceInput = input;
		// RestListvalues = RestServicesUtils.getListOfValues1(serviceURL,input);
		// serviceURL = serviceURL + input;
	}

	@When("Pass the input $values for the service and retrieve")
	public void postbodyvalue(String strvalue) throws Exception {
		// Restvalues = RestServicesUtils.getListOfValues(serviceURL, strvalue);
	}

	@When("Get the $values from Service for POs")
	public void getValueFromService(String strValue) throws Exception {
		serviceURL = serviceURL + serviceInput;

		Restvalues = RestServicesUtils.getListOfValues(serviceURL, strValue);
	}

	@When("Get email Address from Service")
	public void getEmailFromService() throws Exception {

		try {
			RestListvalues = RestServicesUtils.getListOfValues1(serviceURL, serviceInput);
			logger.info(RestListvalues + "' Service values retrieved succcessfully");
			assertTrue(RestListvalues + "' Service values retrieved succcessfully", true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(RestListvalues + "' Service values NOT retrieved succcessfully");
			assertFalse(RestListvalues + "' Service values NOT retrieved succcessfully", true);
		}
	}

	@Then("Get email Address from Database")
	public void getEmailFromDatabase() throws Exception {
		// Thread.sleep(5000);
		try {

			Connection connection = null;
			String strsql = null;
			connection = DBConnections.getinstance("db.sqlserver", "MA000XVSQL22").dbConnection();
			strsql = SQLConstants.Select.PossibleUserEmail;
			dbvalues = DBUtils.getDBValueInList(connection, strsql);
			logger.info(dbvalues + "' database values retrieved succcessfully");
			assertTrue(dbvalues + "' database values retrieved succcessfully", true);
		} catch (Exception e) {
			logger.info(e.getMessage());
			assertFalse("SQL Error", true);
		}

	}

	@Then("Compare the Service values with Database")
	public void compareDBwithService() throws Exception {

		// System.out.println(dbvalues);

		try {
			if (RestListvalues.equals(dbvalues)) {
				logger.info("Service value '" + RestListvalues + "' is Matched with '" + dbvalues + "' databas values");
				assertTrue("Service value '" + RestListvalues + "' is Matched with '" + dbvalues + "' databas values",
						true);
				// System.out.println("PASSPASSPASSPASSPASSPASS");
			} else {
				logger.info(
						"Service value '" + RestListvalues + "' is NOT Matched with '" + dbvalues + "' databas values");
				assertFalse(
						"Service value '" + RestListvalues + "' is NOT Matched with '" + dbvalues + "' databas values",
						true);
				// System.out.println("FAIL");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("Service value '" + RestListvalues + "' is NOT Matched with '" + dbvalues + "' databas values");
			assertFalse("Service value '" + RestListvalues + "' is NOT Matched with '" + dbvalues + "' databas values",
					true);
		}
	}

	@Then("Get the $values from Database for $POs")
	public void getValueFromDatabase(String strvalue, String poValue) throws Exception {
		try {
			Field[] fields = SQLConstants.Select.class.getDeclaredFields();

			Connection connection = null;
			String documentNBr[] = null;
			String strsql = null;
			connection = DBConnections.getinstance("db.Oracle", "LFNDQA").dbConnection();
			if (strvalue.contains("/")) {
				documentNBr = strvalue.split("/");
			}
			if (poValue.equalsIgnoreCase("PO")) {
				strsql = SQLConstants.Select.INVOICE_BY_PO.replace("2914988", documentNBr[3]);
			}
			if (poValue.equalsIgnoreCase("Invoice")) {
				strsql = SQLConstants.Select.INVOICE.replace("#documentnbr", documentNBr[3]);
			}
			// strsql=(String) SQLConstants.Select.class.getField("INVOICE").get(fields);
			DBresultMap = DBMethods.getDBValueInHashMap(connection, strsql);
			logger.info(" Restvalues    :" + Restvalues);
			logger.info(" DBresultMap   :" + DBresultMap);
			DBConnections.closeDBConnection(connection);
		} catch (Exception e) {

		}
	}

	@Then("Compare the Service values with Database $values")
	public void CompareServiceAndDatabaseValues(String strvalue) throws Exception {
		try {
			RestServicesUtils.CompareServiceDataWithDatabase();
			logger.info("DB Records are matched with REST Service Records");

		} catch (Exception e1) {
			logger.info("DB Records are NOT matched with REST Service Records");
			assertFalse(e1.getMessage(), true);

		}
	}

	@Then("Verify the service returns the last 50 Invoices $inputs")
	public void ValidateInvoices(String inputParameters) {

		try {
			RestServicesUtils.CompareDBwithServiceData(inputParameters);
			logger.info("DB Records are matched with REST Service Records");

		} catch (Exception e1) {
			logger.info("DB Records are NOT matched with REST Service Records");
			assertFalse(e1.getMessage(), true);

		}
	}

	@Then("Verify the service returns the last 50 $POs $inputs")
	public void ValidatePO(String inputParameters, String POs) {

		try {
			RestServicesUtils.CompareDBwithServiceData(inputParameters);
			logger.info("DB Records are matched with REST Service Records");

		} catch (Exception e1) {
			logger.info("DB Records are NOT matched with REST Service Records");
			assertFalse(e1.getMessage(), true);

		}
	}
}
