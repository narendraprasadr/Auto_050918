package com.macys.mst.macysnet.KBDDDriver;

import java.util.HashMap;
import java.util.Map;

import org.jbehave.core.model.ExamplesTable;
import org.openqa.selenium.WebDriver;

import com.macys.mst.artemis.kdddriver.ExecuteAction;
import com.macys.mst.artemis.kdddriver.KDDdriverMap;
import com.macys.mst.artemis.selenium.actions.SeleniumElements;
import com.macys.mst.macysnet.Actionkeywords.MISActionkeywords;
import com.macys.mst.macysnet.config.Constants;
import com.macys.mst.macysnet.stepdefs.StatusofInvoice;
import com.macys.mst.macysnet.utils.General;

public class MacysNetKBDDriver extends KDDdriverMap{
	

	  private static Map<String, ExecuteAction> actionsKeywords = null;
	  
	  public static void inputActionExecutor(WebDriver LclDriver, String inputaction, String object, String data)
	    throws Exception
	  {
	    Map<String, ExecuteAction> localActions = populateKeywords(LclDriver, object, data);
	    setActions(localActions);
	    if (getActions().get(inputaction) != null)
	    {
	      System.out.println("Inside Generic Driver");
	      ((ExecuteAction)getActions().get(inputaction)).executeAction();
	      System.out.println("Executed Action FROM GENERIC DRIVER:::::::::::::::::::::::" + inputaction);
	    }
	    else
	    {
	      System.out.println("Inside KDD Driver");
	      KDDdriverMap.inputActionExecutor(LclDriver, inputaction, object, data);
	    }
	  }
	  
	  public static void performaAction(WebDriver lclDriver, ExamplesTable table)
	    throws Exception
	  {
	    for (Map<String, String> row : table.getRows())
	    {
	      String lclaction = ((String)row.get("Action")).toString().trim();
	      String lclobject = ((String)row.get("Object_logical_Name")).toString().trim();
	      String lcldata = ((String)row.get("Data")).toString().trim();
	      
	      inputActionExecutor(lclDriver, lclaction, lclobject, lcldata);
	    }
	  }
	  
	  public static Map<String, ExecuteAction> populateKeywords(final WebDriver LclDriver, final String object, final String data)
	    throws Exception
	  {
	    String[] dataArrayCopy = { data };
	    if (data.contains(",")) {
	      dataArrayCopy = data.split(",");
	    }
	   final  String[] dataArray = dataArrayCopy;
	    
	    final SeleniumElements Selelements = new SeleniumElements();
	    
	    actionsKeywords = new HashMap<String, ExecuteAction>(){
	      private static final long serialVersionUID = 1L;{
	         put("Switch_Frame" , new ExecuteAction(){public void executeAction(){MISActionkeywords.Switch_Frame(LclDriver, data);}});
	         put("Switch_Window" , new ExecuteAction(){public void executeAction(){MISActionkeywords.Switch_Window(LclDriver, data);}});
	         put("select_Date" , new ExecuteAction(){public void executeAction(){MISActionkeywords.select_Date(LclDriver, object, data);}});
	         put("Login_Application" , new ExecuteAction(){public void executeAction(){MISActionkeywords.Macysnet_Login(LclDriver,data);}});
	         put("validate_Default_Value_Should_Be" , new ExecuteAction(){public void executeAction(){MISActionkeywords.validate_Default_Value_Should_Be(LclDriver,object, data);}});
	         put("Validate_Dropdown_Values" , new ExecuteAction(){public void executeAction(){MISActionkeywords.Validate_Dropdown_Values(LclDriver,object, data);}});
	         put("Validate_Record_Count" , new ExecuteAction(){public void executeAction(){MISActionkeywords.Validate_Record_Count();}});
	         put("Select_Dropdown_Value" , new ExecuteAction(){public void executeAction(){MISActionkeywords.Select_Dropdown_Value(LclDriver,object,data);}});
	         put("MIS_wait_For" , new ExecuteAction(){public void executeAction(){MISActionkeywords.MIS_wait_For(LclDriver,object,data);}});
	         put("Validate_Response_Status" , new ExecuteAction(){public void executeAction(){MISActionkeywords.Validate_Response_Status(dataArray[0],dataArray[1]);}});
	         put("Get_Values_From_Service" , new ExecuteAction(){public void executeAction() throws Exception{MISActionkeywords.Get_Values_From_Service(data);}});
	         put("Get_Values_From_Database" , new ExecuteAction(){public void executeAction() throws Exception{MISActionkeywords.Get_Values_From_Database(dataArray[0],dataArray[1]);}});
	         put("Get_Dropdown_Values_In_List" , new ExecuteAction(){public void executeAction() throws Exception{MISActionkeywords.Get_Dropdown_Values_In_List(LclDriver, object);}});
	         put("Compare_Service_And_Database" , new ExecuteAction(){public void executeAction(){MISActionkeywords.Compare_Service_And_Database();}});
	         put("Verify_Page_Should_Not_Contain_Element" , new ExecuteAction(){public void executeAction(){MISActionkeywords.Page_Should_Not_Contain_Element(LclDriver,object);}});
	         put("Get_Values_From_UIScreen" , new ExecuteAction(){public void executeAction() throws Exception{MISActionkeywords.Get_Values_From_UIScreen(LclDriver, object, data);}});
	         put("Get_Values_From_DatabaseInObject" , new ExecuteAction(){public void executeAction() throws Exception{MISActionkeywords.Get_Values_From_DatabaseInObject(dataArray[0],dataArray[1]);}});
	         put("Compare_UISCREEN_values_With_Database" , new ExecuteAction(){public void executeAction() throws Exception{MISActionkeywords.Compare_UISCREEN_values_With_Database();}});
	         put("Validate_Report" , new ExecuteAction(){public void executeAction() throws Exception{MISActionkeywords.Report_validation(LclDriver, data);}});

	      }};													
	       return actionsKeywords;
	    
	  }


}
