package com.macys.mst.FreightOptimization.KBDDDriver;

import java.util.HashMap;
import java.util.Map;

import org.jbehave.core.model.ExamplesTable;
import org.openqa.selenium.WebDriver;
import com.macys.mst.FreightOptimization.Actionkeywords.AFOJActionkeywords;
import com.macys.mst.FreightOptimization.utils.General;
import com.macys.mst.artemis.kdddriver.ExecuteAction;
import com.macys.mst.artemis.kdddriver.KDDdriverMap;
import com.macys.mst.artemis.selenium.actions.SeleniumElements;

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
	         put("Verify_Text_Length_Should_Be" , new ExecuteAction(){public void executeAction(){AFOJActionkeywords.verify_Text_Length_Should_Be(LclDriver,object,data);}});
	         put("AFOJ_verify_Dropdownlist_Matches_With_RESTService" , new ExecuteAction(){public void executeAction(){AFOJActionkeywords.verify_Dropdownlist_Matches_With_RESTService(LclDriver, object, data, data);}});
	         put("AFOJ_select_MultiDropDown_Value" , new ExecuteAction(){public void executeAction(){AFOJActionkeywords.select_MultiDropDown_Value(LclDriver, object, data);}});
	         put("AFOJ_verify_Dropdownlist_Have_OneValue" , new ExecuteAction(){public void executeAction(){AFOJActionkeywords.verify_Dropdownlist_Have_OneValue(LclDriver, object, data);}});
	         put("AFOJ_select_SingleDropDown_Value" , new ExecuteAction(){public void executeAction(){AFOJActionkeywords.select_SingleDropDown_Value(LclDriver, object, data);}});
	         put("AFOJ_validate_Text" , new ExecuteAction(){public void executeAction(){AFOJActionkeywords.validate_Text(LclDriver, object, data);}});
	         put("switch_Frame" , new ExecuteAction(){public void executeAction(){AFOJActionkeywords.switch_Frame(LclDriver, data);}});
	         put("Switch_Window" , new ExecuteAction(){public void executeAction(){AFOJActionkeywords.Switch_Window(LclDriver, data);}});
	         put("select_Date" , new ExecuteAction(){public void executeAction(){AFOJActionkeywords.select_Date(LclDriver, object, data);}});
	         put("Login_Application" , new ExecuteAction(){public void executeAction(){AFOJActionkeywords.Macysnet_Login(LclDriver,data);}});
	       //  put("waitToLoadElement" , new ExecuteAction(){public void executeAction(){General.waitToLoadElement(LclDriver,object);}});
	         

	      }};													
	       return actionsKeywords;
	    
	  }


}
