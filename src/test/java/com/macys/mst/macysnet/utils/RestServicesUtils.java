package com.macys.mst.macysnet.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.macys.mst.artemis.selenium.LocalDriverManager;
import com.macys.mst.macysnet.stepdefs.AFOJRestServices;
import com.macys.mst.macysnet.stepdefs.FreightOptimization;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestServicesUtils {
	public static WebDriver driver = LocalDriverManager.getInstance().getDriver();
	public static Logger logger = Logger.getLogger(FreightOptimization.class.getName());

	/***********************************************************************************************************************************************************
	 * 'Method name : putStatusCode 'Project name :
	 * FreightOptimizationAutomation 'Description : This method is to verify status code is 200 or !200
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
	/***********************************************************************************************************************************************************
	 * 'Method name : putStatusCode 'Project name :
	 * FreightOptimizationAutomation 'Description : This method is to get value
	 * 'Developer : Sriram 'Reviewed By : 'Created On : June 2018
	 ************************************************************************************************************************************************************/
	public static Map<String, List<String>> getListOfValues(String strpath,String strName,String getValue) throws Exception {

		try {
			Map<String, List<String>> resultMap =new HashMap<String, List<String>>();
			List<String> bodylist = new ArrayList<String>();
			RestAssured.baseURI = strpath;
			RequestSpecification request = RestAssured.given();
			Response response = request.get(strName);
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
	 * FreightOptimizationAutomation 'Description : This method is to get value
	 * 'Developer : Sriram 'Reviewed By : 'Created On : June 2018
	 ************************************************************************************************************************************************************/
	public static Map<String, List<String>> getListOfValues(String strpath,String getValue) throws Exception {

		try {
			Map<String, List<String>> resultMap =new HashMap<String, List<String>>();
			List<String> bodylist = new ArrayList<String>();
			RestAssured.baseURI = strpath;
			RequestSpecification request = RestAssured.given();
			Response response = request.get();
			System.out.println("pass");

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
	AFOJRestServices.requestJSON.put(field, tempsubJSON);
	//AFOJRestServices.request.body(AFOJRestServices.requestJSON.toJSONString());


	}

}
