package com.macys.mst.macysnet.stepdefs;

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
import org.junit.Assert;

import com.macys.mst.artemis.db.DBConnections;
import com.macys.mst.artemis.db.DBUtils;
import com.macys.mst.artemis.rest.RestUtilities;
import com.macys.mst.artemis.selenium.WebDriverListener;
import com.macys.mst.macysnet.Actionkeywords.AFOJActionkeywords;
import com.macys.mst.macysnet.db.app.AppDBMethods;
import com.macys.mst.macysnet.db.app.DBMethods;
import com.macys.mst.macysnet.sqlconstants.SQLConstants;
import com.macys.mst.macysnet.utils.RestServicesUtils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class AFOJRestServices {


	public static List<String> dbvalues = new ArrayList<String>();
	public static Map<String, List<String>> DBresultMap =new HashMap<String, List<String>>();
	public static Map<String, List<String>> Restvalues = new HashMap<String, List<String>>();
	public static String DBvaildation=null;
	public static String serviceURL=null;
	public static Map<String, String> mapShp_Ship_to = null;
	public static String columnInValuesStr = null;

	public static RequestSpecification request = RestAssured.given();
	public static JSONObject requestJSON=new JSONObject();
	public static Response response ;
	public static String shipmentNum ;

	 public Long TestNGThreadID = Thread.currentThread().getId();
	 public static Logger logger = Logger.getLogger(FreightOptimization.class.getName());
	    @BeforeStory
	    public void beforeStory() {
	        ConcurrentHashMap<String,String> obj = WebDriverListener.EnvMap.get(TestNGThreadID);
	        WebDriverListener.EnvMap.put((Thread.currentThread().getId()), obj);
	    }


	@Given("LOCN_NBR and DC Name data exists in SHIP_SHIP_TO SQL SERVER table")
	public static void VerifyLocationURL() throws Exception {

		try {
			DBMethods.connect_DataBase("db.sqlserver", "MA000XVSQL22");
			AppDBMethods.connection = DBConnections.getinstance("db.sqlserver", "MA000XVSQL22").dbConnection();
			DBMethods.verifyColumnsExists(SQLConstants.Select.shp_ship_to);
			dbvalues=DBUtils.getDBValueInList(AppDBMethods.connection, SQLConstants.Select.shp_ship_to);
			DBvaildation=DBUtils.getValuesForSQl(dbvalues);
			DBConnections.closeDBConnection(AppDBMethods.connection);
			System.out.println(DBConnections.isDBConnectionOpen(AppDBMethods.connection)+"after");
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	@Given("$ColName column exists for $VendorName in $Vendor table in $oracle database")
	public static void VerifyLocationURLExists(String strColName,String strTableName,String strVendorName,String strDBType) throws Exception {

		try {
			String strDBName=null,strDBUserName=null,strsql=null;
			if (strDBType.equalsIgnoreCase("SQL")) {
				strDBName="db.sqlserver";
				strDBUserName="MA000XVSQL22";

			}
			else if (strDBType.equalsIgnoreCase("oracle")) {
				strDBName="db.Oracle";
				strDBUserName="LFCBIZ01DB";

			}
			DBMethods.connect_DataBase(strDBName,strDBUserName);
			AppDBMethods.connection = DBConnections.getinstance(strDBName,strDBUserName).dbConnection();

			if (strVendorName.equalsIgnoreCase("Vendor")) {
				strsql=SQLConstants.Select.Vendor.replace("#VendorName",strTableName);

			}
			System.out.println(strTableName+strsql);
			DBMethods.verifyColumnsExists(strsql);
			DBresultMap=DBMethods.getDBValueInHashMap(AppDBMethods.connection, strsql);
			DBConnections.closeDBConnection(AppDBMethods.connection);
			System.out.println(DBConnections.isDBConnectionOpen(AppDBMethods.connection)+"after");
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
	@When("table exists for storing $strName")
	public static void VerifygivenDatabase(String strName) throws Exception {

		try {
			DBresultMap.clear();
			Restvalues.clear();
			AppDBMethods.connection = DBConnections.getinstance("db.Oracle", "LFCBIZ01DB").dbConnection();

			if (strName.equalsIgnoreCase("FreightType")) {
				strName=SQLConstants.Select.FreightType;
			} else if(strName.equalsIgnoreCase("CarrierType")) {
				strName=SQLConstants.Select.CarrierType;
			} else if(strName.equalsIgnoreCase("TrailerClass")) {
				strName=SQLConstants.Select.TrailerClass;
			}



			DBMethods.verifyColumnsExists(strName);
			//dbvalues=DBUtils.getDBValueInList(AppDBMethods.connection, strTemp);
			DBresultMap=DBMethods.getDBValueInHashMap(AppDBMethods.connection, strName);
			//DBvaildation=DBUtils.getValuesForSQl(dbvalues);
			DBConnections.closeDBConnection(AppDBMethods.connection);
			//System.out.println(DBConnections.isDBConnectionOpen(AppDBMethods.connection)+"after");
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	@Given("REST service return code 200 for the $ServiceURL")
	public static void VerifyResponsesCodesIs200(@Named("ServicesURL")String ServiceURL) throws Exception {
		serviceURL=ServiceURL;
	RestServicesUtils.putStatusCode(serviceURL);
	}


	@Then("Atleast one matching row for $strsqlcol from $SHIP_SHIP_TO exists in LOC_ADDR oracle table")
	public static void Verifydatabasecolumn(String strsqlcol,String strcolName) throws Exception {
		AppDBMethods.connection = DBConnections.getinstance("db.Oracle", "LFCBIZ01DB").dbConnection();
		String strsql=null;
		if (strsqlcol.equalsIgnoreCase("addr_line_1")) {
		strsql=SQLConstants.Select.LOC_ADDR.replace("#ship_ship_to", DBvaildation);
		}
		else if(strsqlcol.equalsIgnoreCase("ADDR_NBR_XREF")) {
		strsql=SQLConstants.Select.Vendor.replace("#'VendorName'",strcolName);
		}
		//String strsql=SQLConstants.Select.LOC_ADDR.replace("#strsqlvalue", strsqlcol);
		//strsql=strsql.replace("#ship_ship_to", DBvaildation);
		DBMethods.verifyColumnsExists(strsql);
		//ResultSet result_Set = getresultset(con,query);
		//dbvalues=DBUtils.getDBValueInList(AppDBMethods.connection, strsql);
		//dbmapvalues=DBUtils.getDBValuesInMap(AppDBMethods.connection, strsql);

		DBresultMap=DBMethods.getDBValueInHashMap(AppDBMethods.connection, strsql);

		//dbmapvalues=DBUtils.resultSetToList(AppDBMethods.connection, strsql);
		DBConnections.closeDBConnection(AppDBMethods.connection);

	}

	@Then("get values from rest service for $strvalue")
	public void postbodyvalue(String strvalue) throws Exception {
		Restvalues=RestServicesUtils.getListOfValues(serviceURL,strvalue);
	}
	@Then("get values from $serviceUrl rest service $VendorName for $strvalue")
	public void postbodyvalue(String strURL,String strcolName,String strvalue) throws Exception {

	serviceURL="http://lp000xstrs0002:8355/api/platform_msp/v1/shipping/getVendorsByKeyPhraseAndServiceType";

		//serviceURL="http://lp000xstrs0002:8355/api/platform_msp/v1/shipping/retrieveAllDCs";
		Restvalues=RestServicesUtils.getListOfValues(strURL,strcolName,strvalue);
	}

	@Then("compare value from $strName service and database")
	public void compareDBandRestService(String strName) throws Exception {

		//Collections.sort(dbvalues);
		//Collections.sort(Restvalues);
		System.out.println(DBresultMap+"step2");
		boolean isMatched=DBMethods.validateServiceResponseWithDBResponse(Restvalues, DBresultMap);

		//Assert.assertTrue(dbvalues.equals(Restvalues));
		//System.out.println("successfulyy"+dbvalues.equals(Restvalues));

	if (isMatched) {
		logger.info(strName+" service and data base value are same");
		Assert.assertTrue(strName+" service and data base value are same",true);
	} else {
		logger.info(strName+" service and data base value is not same");
		Assert.assertFalse(strName+" service and data base value is not same ",true);
	}
	}

	@Given("$RESTURL service is passed with the $field and $value parms")
	public static void requestParms(@Named("RESTURL")String serviceURL,@Named("field")String field,@Named("value")String value ) throws Exception {

		try {

			RestServicesUtils.postRestServiceCallwithRequestParms(serviceURL,field, value);

		} catch (Exception e) {
			e.printStackTrace();
		}


	}
	@When("$RESTURL service is called with POST method")
	public static void sendPost(@Named("RESTURL")String serviceURL) throws Exception {

		try {

			AFOJRestServices.request.body(AFOJRestServices.requestJSON.toJSONString());
			response = RestUtilities.postRequestAndReturnResponse(serviceURL, requestJSON.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
	@Then("Verify $serviceURL return code is $numStatuscode")
	public static void verifyResponseStatus(@Named("RESTURL")String serviceURL,@Named("numStatuscode")int num) throws Exception {

		try {


			int statusCode = response.getStatusCode();
			String successCode = response.getBody().asString();
			if(successCode.contains(",")) {
				String temparr[]=successCode.split(",");
				String strtemp[]= temparr[1].split(":");
				shipmentNum=strtemp[1].replace("\"", " ");
				shipmentNum=shipmentNum.trim();


			}
			System.out.println(statusCode+shipmentNum);
			Assert.assertEquals( num,statusCode);



		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	@Then("Validate $SCACCode is refected in Database $SCACCode column")
public static void verifyDatabaseStatus(@Named("SCACCode")String strcode,String strcloumn) throws Exception {

		try {

			System.out.println("eneter");
			AFOJActionkeywords.Verify_Value_inDatabase(strcode,strcloumn);



		} catch (Exception e) {
			e.printStackTrace();
		}


	}


	@Then("Verify REST service return code is $returnCode for $serviceUrl and for $VendorName")
	public static void verifyResponseStatus(@Named("returnCode")int num,String RestURL,String VendorName) throws Exception {

		try {

			serviceURL=RestURL+VendorName;
			RestServicesUtils.putStatusCode(serviceURL);
			response=RestAssured.get(serviceURL);
			System.out.println("response         "+response);
			//int statusCode = response.getStatusCode();
			//System.out.println(statusCode);
			Assert.assertEquals(200,response.getStatusCode());
			} catch (Exception e) {
			e.printStackTrace();
		}


	}
@When("Vnd_name column exists get data from ALL DC")
	public static void verifyResponseStatus() throws Exception {

		try {
			VerifyLocationURL();
			AppDBMethods.connection = DBConnections.getinstance("db.Oracle", "LFCBIZ01DB").dbConnection();
			String strsql=SQLConstants.Select.LOC_ADDR.replace("#ship_ship_to", DBvaildation);
			Map<String, List<String>> tempDBresultMap =new HashMap<String, List<String>>();
			tempDBresultMap=DBMethods.getDBValueInHashMap(AppDBMethods.connection, strsql);
			DBresultMap.putAll(tempDBresultMap);
			DBConnections.closeDBConnection(AppDBMethods.connection);
			System.out.println(DBresultMap.toString()+"Scintel");
			}
		catch (Exception e) {
			e.printStackTrace();
		}


	}

}

