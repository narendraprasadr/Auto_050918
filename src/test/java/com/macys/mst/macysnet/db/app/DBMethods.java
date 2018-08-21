package com.macys.mst.macysnet.db.app;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.Assert;

import com.macys.mst.artemis.db.DBConnections;
import com.macys.mst.artemis.db.DBUtils;

public class DBMethods {

	/** The logger. */
	private static Logger logger = Logger.getLogger(DBMethods.class.getName());
	public static Connection oracleConnection = null;


	public static void connect_DataBase(String dbtype, String dbSchema) {

		logger.info("Inside Database Action----->connect_DataBase");
		try {
			AppDBMethods.connection = DBConnections.getinstance(dbtype,dbSchema).dbConnection();
			logger.info("Db connect Successful");
		}

		catch (Exception exception) {
			logger.info("Db is not connect Successful");
		}

	}


		/*
	 * Get a Single Integer Value
	 */
	public static void verifyColumnsExists (String query) {

		logger.info("Query Is : "+query);
		try {
			
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
		Map<String, List<String>>resultMap =new HashMap<String, List<String>>();
		ResultSet rs = null;
		Statement stmt = null;
		try {

			
			//rs = DBUtils.getresultset(con,query);
			stmt = con.createStatement();
		      
		     rs= stmt.executeQuery(query);
			//System.out.println(rs);
			String strTemp=null;
			if(rs!=null)
			{
		

			ResultSetMetaData md = rs.getMetaData();
			int columns = md.getColumnCount();
			while(rs.next())
			{
			
				for (int i = 1; i <= columns; ++i) {
					//System.out.println(md.getColumnName(i));

					strTemp=columnsNameReplace(md.getColumnName(i));
					strTemp = strTemp.toLowerCase();
					List<String> cellData =new ArrayList<String>();
					//List<Object> objCellData =new ArrayList<Object>();

					if(resultMap.containsKey(strTemp)) {
						cellData=resultMap.get(strTemp);
					}
					if(rs.getObject(i)!=null)
					{
						cellData.add(rs.getString(i).trim());
					}/*else {
						cellData.add("0");
					}*/
					//System.out.println("Object As values ...+"+strTemp+"--"+cellData.toString());
					resultMap.put(strTemp, cellData);
				}


			}
			getDate(resultMap,"transduedate");
			getDate(resultMap,"checkdate");
			
			/*if(resultMap.containsKey("transduedate")) {
				List<String> cellData2 =new ArrayList<String>();
				List<String> cellData3 =new ArrayList<String>();
				 cellData2=resultMap.get("transduedate");
				 for(int i=0;i<cellData2.size();i++) {
				      
					 String [] arrOfStr = cellData2.get(i).split(" ");
					 cellData3.add(arrOfStr[0].trim());
				      
				      }
				 resultMap.put("transduedate", cellData3);
				
			}else if(resultMap.containsKey("checkdate")) {
				List<String> cellData2 =new ArrayList<String>();
				List<String> cellData3 =new ArrayList<String>();
				 cellData2=resultMap.get("checkdate");
				 for(int i=0;i<cellData2.size();i++) {
				      
					 String [] arrOfStr = cellData2.get(i).split(" ");
					 cellData3.add(arrOfStr[0].trim());
				      
				      }
				 resultMap.put("checkdate", cellData3);
				
			}
			else {
				
			}*/

			return resultMap;
			}
		} catch (Exception exception) {
			logger.info("the Db values exception is " + exception.getMessage());
		}
		return resultMap;

	}
	public static Map<String, List<String>> getDate(Map<String, List<String>>resultDate,String date)
			{
		Map<String, List<String>>resultMap =new HashMap<String, List<String>>();
		try {
			
				
			if(resultDate.containsKey(date)) {
				List<String> cellData2 =new ArrayList<String>();
				List<String> cellData3 =new ArrayList<String>();
				 cellData2=resultDate.get(date);
				 for(int i=0;i<cellData2.size();i++) {
				      
					 String [] arrOfStr = cellData2.get(i).split(" ");
					 cellData3.add(arrOfStr[0].trim());
				      
				      }
				 resultDate.put(date, cellData3);
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultDate;
		
	}
//test
	public static Map<String, List<Object>> testGetDBValueInHashMap (Connection con,String query) {

		logger.info("Query Is : "+query);
		try {

			ResultSet rs = null;
			rs = DBUtils.getresultset(con,query);
			if(rs!=null)
			{
			Map<String, List<Object>>resultMap =new HashMap<String, List<Object>>();

			ResultSetMetaData md = rs.getMetaData();
			int columns = md.getColumnCount();
			while(rs.next())
			{
				String strTemp=null;
				
				for (int i = 1; i <= columns; ++i) {

					strTemp=columnsNameReplace(md.getColumnName(i)).toLowerCase();
					System.out.println("DB value is:"+strTemp);
					List<Object> cellData =new ArrayList<Object>();
					
					//List<Object> objCellData =new ArrayList<Object>();
					if(strTemp.equalsIgnoreCase("LONG_DESC"))
					{
					strTemp="desc";	
					}

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

	public static boolean validateServiceResponseWithDBResponse(Map<String, List<Object>> serviceResponse, Map<String, List<Object>> dbResponse) {
		boolean isMatched = false;



		for (String key : serviceResponse.keySet()) {
						List<Object> dbResultInt=new ArrayList<Object>();
				List<Object> srResult=serviceResponse.get(key);
				List<Object> dbResult=dbResponse.get(key);
				Set<Object> hs = new HashSet<>();
				dbResult.removeAll(Arrays.asList("  ",""));
				dbResult.removeAll(Arrays.asList(" ",""));
				srResult.removeAll(Arrays.asList(" ",""));
						//logger.info("Values Matched");
				System.out.println("srREsult:"+key+".."+srResult.toString());
				System.out.println("dbREsult:"+key+".."+dbResult.toString());

				
				int dbRes=dbResult.size();
				int reResult=srResult.size();
				

			if (dbRes==reResult) {
				for(int i=0;i<dbRes;i++)
				{
					Object dataBaseRes=dbResult.get(i);
					Object restsreviceRes=srResult.get(i);
					if(String.valueOf(dataBaseRes).equals(String.valueOf(restsreviceRes)))
					{
						System.out.println(String.valueOf(dataBaseRes)+" DB element and Rest element is matched "+String.valueOf(restsreviceRes));
						logger.info(String.valueOf(dataBaseRes)+" DB element and Rest element is matched "+String.valueOf(restsreviceRes));
						Assert.assertTrue("Rest service and data base value are same",true);
					}
					else
					{
						System.out.println(String.valueOf(dataBaseRes)+" DB element and Rest element is not matched "+String.valueOf(restsreviceRes));
					logger.info("Rest service and data base value is not same");
						
					}}}
					
					else
					{
						System.out.println("Size is not matched");
						logger.info("Size is not same");
					}
			
		}

		return isMatched;
	}
	public static boolean testValidateServiceResponseWithDBResponse(Map<String, List<Object>> restvalues, Map<String, List<Object>> dBresultMap) {
		boolean isMatched = false;



		for (String key : restvalues.keySet()) {
			

				List<Integer> dbResultInt=new ArrayList<Integer>();
				List<Object> srResult=restvalues.get(key);
				List<Object> dbResult=dBresultMap.get(key);
				Set<String> hs = new HashSet<>();
				dbResult.removeAll(Arrays.asList("  ",""));
				dbResult.removeAll(Arrays.asList(" ",""));
				srResult.removeAll(Arrays.asList(" ",""));
				//hs.addAll(srResult);
				//srResult.clear();
				//srResult.addAll(hs);

				/*Set<String> hss = new HashSet<>();
				hss.addAll(dbResult);
				dbResult.clear();
				dbResult.addAll(hss);*/

				//logger.info("Values Matched");
				System.out.println("srREsult:"+key+".."+srResult.toString());
				System.out.println("dbREsult:"+key+".."+dbResult.toString());

				//Collections.sort(srResult);
				///System.out.println(key+"step3");
				//System.out.println("AFTER SORTING::srREsult:.."+srResult.toString());


				/*dbResultInt=RestServicesUtils.sortingNumber(dbResult);
				if(dbResultInt.isEmpty())
				{
				Collections.sort(dbResult);
				System.out.println("AFTER SORTING::dbREsult:.."+dbResult.toString());
				}
				else
				{
					Collections.sort(dbResultInt);
					System.out.println("AFTER SORTING::dbREsult:.."+dbResultInt.toString());
				}*/



				//Assert.assertTrue(dbResult.equals(srResult));
				//System.out.println("successfulyy"+dbResult.equals(srResult));

				/*if(dbResult != null && srResult != null && (dbResult.size() == srResult.size())){
					dbResult.removeAll(srResult);
				    if(dbResult.isEmpty()){
				    	isMatched = true;
				    	logger.info(key+" Rest service and data base value are same");
						Assert.assertTrue("Rest service and data base value are same",true);;
		            }else{
		            	isMatched = false;
		            	logger.info(key+" Rest service and data base value is not same and value is "+dbResult.toString());
		            }
				}
				else
				{
					isMatched = false;
					logger.info(key+"Rest service and data base value is not same");
				}*/

			if (dbResult.equals(srResult)) {
				logger.info("Rest service and data base value are same");
				Assert.assertTrue("Rest service and data base value are same",true);
			} else {
				logger.info("Rest service and data base value is not same");
				assertFalse("Rest service and data base value is not same ",true);
			}
			//}
		}

		return isMatched;
	}


	public static String columnsNameReplace (String strname) {
		//locationID,address1,address2,city,state,country,zipCode
		//loc_nbr, addr_line_1,addr_line_2,city,st_code,ntn_code,zip_code

		if (strname.equalsIgnoreCase("loc_nbr")) {
			strname="locationID";
		} else if(strname.equalsIgnoreCase("addr_line_1")) {
			strname="address1";
		} else if(strname.equalsIgnoreCase("addr_line_2")) {
			strname="address2";
		} else if(strname.equalsIgnoreCase("city")) {
			strname="city";
		} else if(strname.equalsIgnoreCase("st_code")) {
			strname="state";
		} else if(strname.equalsIgnoreCase("ntn_code")) {
			strname="country";
		} else if(strname.equalsIgnoreCase("zip_code")) {
			strname="zipCode";
		} else if(strname.equalsIgnoreCase("frgt_typ_nbr")) {
			strname="FreightLoadOptions.freightOptions.key";
		} else if(strname.equalsIgnoreCase("frgt_typ_desc")) {
			strname="FreightLoadOptions.freightOptions.value";
		} else if(strname.equalsIgnoreCase("carrier_typ_nbr")) {
			strname="FreightLoadOptions.carrierTypes.key";
		} else if(strname.equalsIgnoreCase("carr_grp_desc")) {
			strname="FreightLoadOptions.carrierTypes.value";
		} else if(strname.equalsIgnoreCase("trlr_class_nbr")) {
			strname="FreightLoadOptions.trailerClassTypes.key";
		} else if(strname.equalsIgnoreCase("trlr_class_name")) {
			strname="FreightLoadOptions.trailerClassTypes.value";
		}else if(strname.equalsIgnoreCase("long_desc")) {
			strname="desc";
		}else if(strname.equalsIgnoreCase("invoiceStatus")) {
			strname="invoiceStatus";
		}else if(strname.equalsIgnoreCase("transgrossamt")) {
			strname="transGrossAmt";
		}
		else if(strname.equalsIgnoreCase("invoicenbr")) {
			strname="invoicenbr";
		}
		
			//System.out.println(strname+"---00");
		return strname;
		}



	public static void verifyColumns(String query) {

		logger.info("Query Is : "+query);
		ResultSet resultSet = null;

		try {

			for(int i=0; i<180;i=i+10)
			{
				resultSet = AppDBMethods.dashBoardResultSet(query);
				System.out.println(resultSet);

				if(resultSet==null)
				{
					Thread.sleep(i);
					System.out.println("Sleep executed"+i);
					TimeUnit.SECONDS.sleep(i);

				}
				else
				{
					System.out.println("exited from ...."+i);
					break;
				}
			}
			if (resultSet.next()) {
				for(int j=0;j<180;j=j+10)
				{
				if(resultSet.getString(1).equals(null))
				{
					System.out.println("It is null"+j);
					Thread.sleep(j);
					TimeUnit.SECONDS.sleep(j);

				}
				else
				{
					System.out.println("No Null");
					System.out.println(resultSet.getString(1));
					break;

				}
				}
				logger.info("Given '"+resultSet.getString(1)+"' is exist in data base");
				assertTrue("Given columns is exist in data base", true);
			}
			else {
				logger.info("Given Value is not exist in data base");
				assertFalse("Given Value is not exist in data base", true);
			}


		} catch (Exception exception) {
			logger.info("the Db values exception is " + exception.getMessage());
		}

	}
	/*public static Map<String, List<String>> getDBValueInHashMap (Connection con,String query) {

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

	}*/

	public static String getTestResultSet(String strDBType,String strquery,String strColumnName) throws Exception {
		String resultSet = null;

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
				ResultSet rs = AppDBMethods.dashBoardResultSet(strquery);
				ResultSetMetaData md = rs.getMetaData();
				int columns = md.getColumnCount();
				if (rs.next()) {
					String strTemp=null;
					for (int i = 1; i <= columns; ++i) {
					strTemp=columnsNameReplace(md.getColumnName(i));


					if(strColumnName.equalsIgnoreCase(strTemp)) {
						resultSet=rs.getString(i);
					}



				}


			}
				//DBConnections.closeDBConnection(AppDBMethods.connection);
			}catch (Exception e1) {
				//StepDetail.addDetail("Oracle DB Connection NOT Successfull", false);
				assertFalse("Oracle DB Connection  NOT successful",true);

			}
			return resultSet;




	}


	public static void verifyColumnsExistsInDataBase(String strDBType,String strquery) throws Exception {

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

			DBMethods.verifyColumnsExists(strquery);
			DBConnections.closeDBConnection(AppDBMethods.connection);
			System.out.println(DBConnections.isDBConnectionOpen(AppDBMethods.connection)+"after");
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}





