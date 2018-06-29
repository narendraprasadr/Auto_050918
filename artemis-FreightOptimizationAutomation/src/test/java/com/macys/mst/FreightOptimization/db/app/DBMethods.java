package com.macys.mst.FreightOptimization.db.app;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Assert;

import com.macys.mst.FreightOptimization.utils.RestServicesUtils;
import com.macys.mst.artemis.db.DBUtils;

public class DBMethods {

	/** The logger. */
	private static Logger logger = Logger.getLogger(DBMethods.class.getName());

	
	

	

		/*
	 * Get a Single Integer Value
	 */
	public static void verifyColumnsExists (String query) {
	
		logger.info("Query Is : "+query);
		try {
			/*ArrayList<String> strlistValue = new ArrayList<>();
			if (strColumnname.contains(",")) {
				
				for(String subString: strColumnname.split(",")){
					strlistValue.add(subString);
					}
			}
			else {
			
				strlistValue.add(strColumnname);
			} */
			ResultSet result_Set = AppDBMethods.dashBoardResultSet(query);
			if (result_Set.next()) {
				System.out.println("Given columns is exist in data base");
				logger.info("Given columns is exist in data base");
				assertTrue("Given columns is exist in data base", true);
						}
			else {
				logger.info("Given columns is not exist in data base");
				assertFalse("Given columns is not exist in data base", true);
			}
			
			
		} catch (Exception exception) {
			logger.info("the Db values exception is " + exception.getMessage());
		} 
		
	}

	public static Map<String, List<String>> getDBValueInHashMap (Connection con,String query) {
		
		logger.info("Query Is : "+query);
		try {
			
			ResultSet rs = null;
			rs = DBUtils.getresultset(con,query);
			if(rs!=null)
			{
			Map<String, List<String>>resultMap =new HashMap<String, List<String>>();
			
			ResultSetMetaData md = rs.getMetaData();
			int columns = md.getColumnCount();
			while(rs.next())
			{   
				String strTemp=null;
				for (int i = 1; i <= columns; ++i) {
					
					strTemp=columnsNameReplace(md.getColumnName(i));
					
					List<String> cellData =new ArrayList<String>();
					//List<Object> objCellData =new ArrayList<Object>();

					if(resultMap.containsKey(strTemp)) {
						cellData=resultMap.get(strTemp);
					}
					if(rs.getObject(i)!=null)
					{							
						cellData.add(rs.getString(i));
					}
					//System.out.println("Object As values ...+"+strTemp+"--"+cellData.toString());
					resultMap.put(strTemp, cellData);
				}
				
				
			}	
			
			return resultMap;
			}
		} catch (Exception exception) {
			logger.info("the Db values exception is " + exception.getMessage());
		}
		return null; 
		
	}

	
	public static boolean validateServiceResponseWithDBResponse(Map<String, List<String>> serviceResponse, Map<String, List<String>> dbResponse) {
		boolean isMatched = false;
		
		
		for (String key : serviceResponse.keySet()) {
			logger.info("ServiceValue: " + serviceResponse.get(key)
					+ ", DB Value: " + dbResponse.get(key));
			//isMatched = isMatched && serviceResponse.get(key).equals(dbResponse.get(key));
			System.out.println(key+"step3");
			System.out.println(dbResponse.keySet().toString()+"step4");
			////if (isMatched == false) {
				logger.info("Values Mismatch");
			//} else {
				List<Integer> dbResultInt=new ArrayList<Integer>(); 
				List<String> srResult=serviceResponse.get(key);
				List<String> dbResult=dbResponse.get(key);
			
				logger.info("Values Matched");
				System.out.println("BEFORE SORTING::srREsult:.."+srResult.toString());
				System.out.println("BEFORE SORTING::dbREsult:.."+dbResult.toString());

				Collections.sort(srResult);
				System.out.println(key+"step3");
				System.out.println("AFTER SORTING::srREsult:.."+srResult.toString());
				
				
				dbResultInt=RestServicesUtils.sortingNumber(dbResult);
				if(dbResultInt.isEmpty())
				{
				Collections.sort(dbResult);
				System.out.println("AFTER SORTING::dbREsult:.."+dbResult.toString());
				}
				else
				{
					Collections.sort(dbResultInt);
					System.out.println("AFTER SORTING::dbREsult:.."+dbResultInt.toString());
				}
				
				

				//Assert.assertTrue(dbResult.equals(srResult));
				//System.out.println("successfulyy"+dbResult.equals(srResult));
				
				if(dbResult != null && srResult != null && (dbResult.size() == srResult.size())){
					dbResult.removeAll(srResult);
				    if(dbResult.isEmpty()){
				    	isMatched = true;
				    	logger.info("Rest service and data base value are same");
						Assert.assertTrue("Rest service and data base value are same",true);;
		            }else{
		            	//logger.info("Rest service and data base value is not same");
		            }
				}
				else
				{
					logger.info("Rest service and data base value is not same");
				}
				
			/*if (dbResult.equals(srResult)) {
				logger.info("Rest service and data base value are same");
				Assert.assertTrue("Rest service and data base value are same",true);
			} else {
				logger.info("Rest service and data base value is not same");
				//Assert.assertFalse("Rest service and data base value is not same ",true);
			}
			//}*/
		}
		return isMatched;
	}
	

	public static String columnsNameReplace (String strname) {
		//locationID,address1,address2,city,state,country,zipCode
		//loc_nbr, addr_line_1,addr_line_2,city,st_code,ntn_code,zip_code
		
		if (strname.equalsIgnoreCase("loc_nbr")) 
			strname="locationID";
		else if(strname.equalsIgnoreCase("addr_line_1")) 
			strname="address1";
		else if(strname.equalsIgnoreCase("addr_line_2"))
			strname="address2";
		else if(strname.equalsIgnoreCase("city")) 
			strname="city";
		else if(strname.equalsIgnoreCase("st_code")) 
			strname="state";
		else if(strname.equalsIgnoreCase("ntn_code")) 
			strname="country";
		else if(strname.equalsIgnoreCase("zip_code"))
			strname="zipCode";
		else if(strname.equalsIgnoreCase("frgt_typ_nbr"))
			strname="FreightLoadOptions.freightOptions.key";
		else if(strname.equalsIgnoreCase("frgt_typ_desc"))
			strname="FreightLoadOptions.freightOptions.value";
		else if(strname.equalsIgnoreCase("carrier_typ_nbr"))
			strname="FreightLoadOptions.carrierTypes.key";
		else if(strname.equalsIgnoreCase("carr_grp_desc"))
			strname="FreightLoadOptions.carrierTypes.value";
		else if(strname.equalsIgnoreCase("trlr_class_nbr"))
			strname="FreightLoadOptions.trailerClassTypes.key";
		else if(strname.equalsIgnoreCase("trlr_class_name"))
			strname="FreightLoadOptions.trailerClassTypes.value";
			//System.out.println(strname+"---00");
		return strname;
		}
		
	}

	

	

