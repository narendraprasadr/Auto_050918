package com.macys.mst.macysnet.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.junit.Assert;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import com.macys.mst.artemis.db.DBConnections;
import com.macys.mst.artemis.reports.StepDetail;
import com.macys.mst.artemis.selenium.LocalDriverManager;
import com.macys.mst.artemis.selenium.SeUiContextBase;
import com.macys.mst.macysnet.db.app.DBMethods;
import com.macys.mst.macysnet.sqlconstants.SQLConstants;
import com.macys.mst.macysnet.stepdefs.MISRestServices;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestServicesUtils {
	public static WebDriver driver = LocalDriverManager.getInstance().getDriver();
	public static Logger logger = Logger.getLogger(RestServicesUtils.class.getName());
	static SeUiContextBase objSeUiContextBase = new SeUiContextBase();
	public static RequestSpecification request = RestAssured.given();
	public static JSONObject requestJSON = new JSONObject();
	public static Response response;
	public static String serviceURL = null;
	public static Map<String, List<String>> DBresultMap = new HashMap<String, List<String>>();
	public static Map<String, List<String>> Restvalues = new HashMap<String, List<String>>();

	/***********************************************************************************************************************************************************
	 * 'Method name : putStatusCode 'Project name :
	 * MIS Automation 'Description : This method is to verify status code is 200 or !200
	 * 'Developer : Sriram 'Reviewed By : 'Created On : June 2018
	 ************************************************************************************************************************************************************/
	public static void putStatusCode(String strpath) throws Exception {

		try {
			logger.info("request path : " + strpath);
			Response response = RestAssured.get(strpath);

			if (response == null || response.getStatusCode() != 200) {

				logger.info("Response is null or response code is !=200 ");

			} else {

				logger.info("Successfully Response and  response code is =200 ");
			}


		} catch (Exception e) {

			Assert.assertFalse("Response is null or response code is !=200 ",true);
		}
	}

	public static List<Integer> sortingNumber(List<String> input)


	{
		List<Integer> numbers = new ArrayList<Integer>();
		List<String> strings = new ArrayList<String>();

		for (String s : input) {
            if (s.matches("-?\\d+")) {
                numbers.add(Integer.parseInt(s));
            } else {
                strings.add(s);
            }
        }
		return numbers;

	}
	
	public static void verfiyRequestResponse(String path,String body)
	  {
		path=path+body;
		logger.info("request path : " + path);
	    Response response = RestAssured.get(path, new Object[0]);
	    if ((response != null) && ((response.getStatusCode() == 200) || (response.getStatusCode() == 201))) {
	    	logger.info("Status Code for response : " + response.getStatusCode());
	    	Assert.assertTrue("Status Code for response : " + response.getStatusCode(),true);
	    }
	    else
	    {
	    	logger.info("Status Code for response : " + response.getStatusCode());
		    Assert.assertTrue("Response is null or response code is !=200 ", false);
	    	
	    }

	  }
	
	public static void verifyResponseStatus(int returnCode, String RestURL, String inputParameters) throws Exception {
		serviceURL = RestURL + inputParameters;
		logger.info("request path : " + serviceURL);
		try {
			Response response = RestAssured.get(serviceURL);
			if (response == null || response.getStatusCode() != 200) {
				StepDetail.addDetail("Response is null or response code is !=200", true);
				logger.info("Response is null or response code is !=200 ");
				assertFalse("Response is null or response code is !=200 ",true);
			} else {
				StepDetail.addDetail("Successfully Response and  response code is =200 ", true);
				logger.info("Successfully Response and  response code is =200 ");
				assertTrue("Successfully Response and  response code is =200 ",true);
			}
			response = RestAssured.get(serviceURL);
			Assert.assertEquals(returnCode, response.getStatusCode());
		} catch (Exception e) {
			StepDetail.addDetail("Response is null or response code is !=200", true);
			assertFalse("Response is null or response code is !=200 ",true);
		}
	}

	public static Map<String, List<String>> getRestValues(String strURL,String strvalue) throws Exception {
		try {
			Map<String, List<String>> resultMap = new HashMap<String, List<String>>();
			List<String> bodylist = new ArrayList<String>();
			RestAssured.baseURI = strURL;
			RequestSpecification request = RestAssured.given();
			Response response = request.get();

			if (strvalue.contains(",")) {

				for (String subString : strvalue.split(",")) {

					bodylist = response.jsonPath().getList(subString);

					if (subString.contains("Date")) {
						List<String> bodylist2 = new ArrayList<String>();
						for (int i = 0; i < bodylist.size(); i++) {

							String[] arrOfStr = bodylist.get(i).split(" ");
							bodylist2.add(arrOfStr[0].trim());
						}
						resultMap.put(subString.toLowerCase(), bodylist2);
						Restvalues = resultMap;
					} else {

						resultMap.put(subString.toLowerCase(), bodylist);
						Restvalues = resultMap;
					}
				}
			} else {

				bodylist = response.jsonPath().getList(strvalue);
				resultMap.put(strvalue, bodylist);
				Restvalues = resultMap;
			}
			logger.info("request value is  : " + resultMap);
			StepDetail.addDetail("Request value displayed successfully ", true);
			

		} catch (Exception e) {
			StepDetail.addDetail("Response is null or response code is !=200", true);
			assertFalse("Response is null or response code is !=200 ",true);
		}
		return Restvalues;
		

	}

	public static void CompareDBwithServiceData(String inputParameters) {
			Connection connection=null;
			String documentNBr[]=null;
			String strsql=null;
		try {
			connection =  DBConnections.getinstance("db.Oracle", "LFNDQA").dbConnection();
			if(inputParameters.contains("/"))
			{
				documentNBr=inputParameters.split("/");
			}
			strsql = SQLConstants.Select.INVOICE.replace("#documentnbr", documentNBr[3]);
			DBresultMap = DBMethods.getDBValueInHashMap(connection, strsql);
			logger.info(" Restvalues    :"+Restvalues);
			logger.info(" DBresultMap   :"+DBresultMap);
			DBConnections.closeDBConnection(connection);

			boolean isMatched = validateServiceResponseWithDBResponse(Restvalues, DBresultMap);

			if (isMatched) {
				StepDetail.addDetail("Service and Data base value are same", true);
				logger.info(" Service and Data base value are same");
				Assert.assertTrue(" Service and Data base value are same",true);
			} else {
				StepDetail.addDetail("Service and Data base value is not same", true);
				logger.info(" Service and Data base value is not same");
				Assert.assertFalse(" Service and Data base value is not same",true);
			}

		} catch (Exception e1) {
			logger.info(e1.getMessage());
			StepDetail.addDetail(e1.getMessage(), true);
			assertFalse(e1.getMessage(), true);
			

		}
	}

	public static void CompareServiceDataWithDatabase() {
		
	try {
		
		boolean isMatched = validateServiceResponseWithDBResponse(Restvalues, MISRestServices.DBresultMap);

		if (isMatched) {
			StepDetail.addDetail("Service and Data base value are same", true);
			logger.info(" Service and Data base value are same");
			Assert.assertTrue(" Service and Data base value are same",true);
		} else {
			StepDetail.addDetail("Service and Data base value is not same", true);
			logger.info(" Service and Data base value is not same");
			Assert.assertFalse(" Service and Data base value is not same",true);
		}

	} catch (Exception e1) {
		logger.info(e1.getMessage());
		StepDetail.addDetail(e1.getMessage(), true);
		assertFalse(e1.getMessage(), true);
		

	}
}
	public static boolean validateServiceResponseWithDBResponse(Map<String, List<String>> serviceResponse,
			Map<String, List<String>> dbResponse) {
			boolean isMatched = false;
		try {
			for (String key : serviceResponse.keySet()) {
				//System.out.println(serviceResponse.get(key));
				//System.out.println(dbResponse.get(key));
				List<Integer> dbResultInt = new ArrayList<Integer>();
				List<String> srResult = serviceResponse.get(key);
				List<String> dbResult = dbResponse.get(key);
				Set<String> hs = new HashSet<>();
				//dbResult.removeAll(Arrays.asList("  ", ""));
				//dbResult.removeAll(Arrays.asList(" ", ""));
				//srResult.removeAll(Arrays.asList(" ", ""));

				logger.info("srREsult:" + key + ".." + srResult.toString().trim());
				logger.info("dbREsult:" + key + ".." + dbResult.toString().trim());

				if (dbResult != null && srResult != null && (dbResult.size() == srResult.size())) {

					for (int i = 0; i < dbResult.size(); i++) {

						boolean statuse = isInteger(dbResult.get(i));

						if (statuse == true) {
							if (srResult.toString().trim().equals(dbResult.toString().trim())) {
								isMatched = true;
								StepDetail.addDetail("Service and Data base value are same", true);
								logger.info("Rest service "+"'"+key + "' value Matches with Data base "+key+" value are same");
								assertTrue("Service and Data base value are same", true);
							} 
							else {
								isMatched = false;
								StepDetail.addDetail("Service and Data base value are not same", true);
								//logger.info("'"+key + "' Rest service and Data base value are not same");
								assertFalse("Service and data base value are not same", true);
								break;
							}

						} else {

							if (dbResult.get(i).equals(srResult.get(i))) {
								isMatched = true;
								StepDetail.addDetail("Service and Data base value are same", true);
								logger.info("Rest service "+"'"+key + "' value Matches with Data base "+key+" value are same");
								//logger.info(key + " Rest service and Data base value are same");
								assertTrue("Service and Data base value are same", true);
							} else {
								isMatched = false;
								StepDetail.addDetail("Service and Data base value are not same", true);
								//logger.info(key + " Rest service and Data base value are not same");
								assertFalse("Service and data base value are not same", true);
								break;
							}

						}
					}
				} else {
					isMatched = false;
					StepDetail.addDetail("Service and Data base value are not same", true);
					logger.info(key + " Rest service and Data base value are not same");
					assertFalse("Service and data base value are not same", true);
				}
			}
			
		} catch (Exception e) {
			logger.info(e.getMessage());
			assertFalse("Service and data base value are not same", true);
		}
		return isMatched;
	}

	public static boolean isInteger(String s) {
		return isInteger(s, 10);
	}

	public static boolean isInteger(String s, int radix) {
		if (s.isEmpty())
			return false;
		for (int i = 0; i < s.length(); i++) {
			if (i == 0 && s.charAt(i) == '-') {
				if (s.length() == 1)
					return false;
				else
					continue;
			}
			if (Character.digit(s.charAt(i), radix) < 0)
				return false;
		}
		return true;
	}
	/***********************************************************************************************************************************************************
	 * 'Method name : putStatusCode 'Project name :
	 * MIS Automation 'Description : This method is to get value
	 * 'Developer : Sriram 'Reviewed By : 'Created On : June 2018
	 ************************************************************************************************************************************************************/
	public static List<String> getListOfValues1(String strpath, String val) throws Exception {

		try {
			List<String> bodylist = new ArrayList<String>();
			RestAssured.baseURI = strpath;
			RequestSpecification request = RestAssured.given();
			//Response response = request.get(strName);

			Response response = request.request(Method.GET,val);
			 String responseBody = response.getBody().asString();
			System.out.println("Response Body is =>  " + responseBody);
			
			Pattern pattern = Pattern.compile("[\\w.]+@[\\w.]+");
	        Matcher matcher = pattern.matcher(responseBody);
	        
	        while(matcher.find()){
	            String email = matcher.group();
	            bodylist.add(email);
	            System.out.println("group="+email);
	        }
			
			if(responseBody.contains(","))
			{
				
			}else
			{
				//bodylist.add();
			}
			
			
		/*	if (getValue.contains(",")) {


				for(String subString: getValue.split(",")){

					bodylist = response.jsonPath().getList(subString);
					resultMap.put(subString, bodylist);

					}
			}
			else {

				bodylist = response.jsonPath().getList(getValue);
				resultMap.put(getValue, bodylist);
			}
*/

			logger.info("request value is  : " + bodylist);
			return  bodylist;

		}
	        catch (Exception e) {

			Assert.assertFalse("Response is null or response code is !=200 ",true);
			return  null;


		}
	}
	/***********************************************************************************************************************************************************
	 * 'Method name : putStatusCode 'Project name :
	 * MIS Automation 'Description : This method is to get value
	 * 'Developer : Sriram 'Reviewed By : 'Created On : June 2018
	 ************************************************************************************************************************************************************/
	public static Map<String, List<String>> getListOfValues(String strpath,String strName,String getValue) throws Exception {

		try {
			Map<String, List<String>> resultMap =new HashMap<String, List<String>>();
			List<String> bodylist = new ArrayList<String>();
			RestAssured.baseURI = strpath;
			RequestSpecification request = RestAssured.given();
			//Response response = request.get(strName);

			Response response = request.get();
			if (getValue.contains(",")) {


				for(String subString: getValue.split(",")){

					bodylist = response.jsonPath().getList(subString);
					resultMap.put(subString, bodylist);

					}
			}
			else {

				bodylist = response.jsonPath().getList(getValue);
				resultMap.put(getValue, bodylist);
			}


			logger.info("request value is  : " + resultMap);
			return  resultMap;

		} catch (Exception e) {

			Assert.assertFalse("Response is null or response code is !=200 ",true);
			return  null;


		}
	}

	/***********************************************************************************************************************************************************
	 * 'Method name : putStatusCode 'Project name :
	 * MIS Automation 'Description : This method is to get value
	 * 'Developer : Praveen 'Reviewed By : 'Created On : June 2018
	 ************************************************************************************************************************************************************/
	public static Map<String, List<String>> getListOfValues(String strpath,String getValue) throws Exception {
		logger.info("The path is: " + strpath);
		logger.info("The input is: " + getValue);
		try {
			Map<String, List<String>> resultMap = new HashMap<String, List<String>>();
			List<String> bodylist = new ArrayList<String>();
			RestAssured.baseURI = strpath;
			RequestSpecification request = RestAssured.given();
			Response response = request.get();

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
			} else {

				bodylist = response.jsonPath().getList(getValue);
				resultMap.put(getValue, bodylist);
			}

			logger.info("request value is  : " + resultMap);
			Assert.assertTrue("Service values are stored successfully", true);
			return resultMap;

		} catch (Exception e) {
			Assert.assertFalse("Service values are not stored successfully", true);
			return null;

		}
	}
	//test

	public static Map<String, List<Object>> test(String strpath,String strvalue) throws Exception {

		try {
			Map<String, List<Object>> resultMap =new HashMap<String, List<Object>>();
			List<Object> bodylist = new ArrayList<Object>();
			RestAssured.baseURI = strpath;
			RequestSpecification request = RestAssured.given();
			Response response = request.get("http://lp000xstrs0002:8360/api/macysnet/v1/vendorAP/VendorInvoices/71/910927390/9009701504");
			System.out.println("pass");
			if (strvalue.contains(",")) {


				for(String subString: strvalue.split(",")){

					bodylist = response.jsonPath().getList(subString);
					resultMap.put(subString.toLowerCase(), bodylist);

					}
			}
			else {

				bodylist = response.jsonPath().getList(strvalue);
				resultMap.put(strvalue.toLowerCase(), bodylist);
			}

				System.out.println();
			logger.info("request value is  : " + resultMap);
			return  resultMap;

		} catch (Exception e) {
			System.out.println("pass"+e);
			Assert.assertFalse("Response is null or response code is !=200 ",true);
			return  null;


		}
	}

	public static void postRestServiceCallwithRequestParms(String serviceURL,String field, String value){


	Map<String, Object> requestMap = new HashMap<String, Object>();
	Map<String, Object> tempHaspMap = new HashMap<String, Object>();
	String field1=null;
	//String keyValue[] = str2.split(":");  String keyValue[] = str1.split(":",2);
	//String keyValue[] = str3.split(":"); 
	//String keyValue[] = str.split(":");  


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
							//String keyValue[] = str1.split(":",2);
							String keyValue[] = str2.split(":");

							if(keyValue[0].equalsIgnoreCase("pickuptime")) {
								keyValue[1]=keyValue[1]+":"+keyValue[2];
							}
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
						//String keyValue[] = str3.split(":",2);
						//keyvalue[0] - key, keyvalue[1] - value
						tempsubJSON.put(keyValue[0], keyValue[1]);
					}


				}
				else {
					//String keyValue[] = str.split(":",2);
					String keyValue[] = str.split(":");
					//keyvalue[0] - key, keyvalue[1] - value
					tempsubJSON.put(keyValue[0], keyValue[1]);
				}
			}
		}
	}
	System.out.println("");

	MISRestServices.requestJSON.put(field, tempsubJSON);
	//AFOJRestServices.request.body(AFOJRestServices.requestJSON.toJSONString());


	}

}
