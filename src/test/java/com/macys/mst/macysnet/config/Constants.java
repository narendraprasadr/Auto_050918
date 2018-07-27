package com.macys.mst.macysnet.config;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriverService;

import com.macys.mst.d2c.automation.drm.kbrobot.KeyboardRobot;
import com.macys.mst.macysnet.pageobjects.LoginPage;
import com.macys.mst.macysnet.utils.General;

public class Constants {

	public static String filepath = "C:\\artemis_reference\\test-output\\screenshots";

	public static String scnshotPassPath = ".\\screenshots\\PASS\\";
	public static String scnshotFailPath = ".\\screenshots\\FAIL\\";
	public static String currentTime = General.getTimeStamp();
	public static String dataFilePath = ".\\data\\Testdata.xlsx";
	public static String serviceURL="http://lp000xstrs0002:8355/api/platform_msp/v1/shipping/postOutboundShipmentRequest";
	public static String expectedCode="400";
	public static String field="ShipmentRequestSubmit";
	public static String values="originID:3,destinationID:6-loadInformation=pieceCount:100,pieceCountInPallets:true,weightLbs:75,cubicFeet:235,pickupDate:07/29/2018,pickupTime:3:30 PM,scac:AAAA,timeZone:ET";

	public static String loginUrl="http://dev.macysnet.com/AP/";

	public static String driver = "oracle.jdbc.driver.OracleDriver";
	public static String uri = null;
	public static String username = "";
	public static String password ="";
	public static String colValue =null;
	public static String JDAUrl = "http://ma001xstms91:7001/jda/shell/";
	public static String userName = "BH00337";
	public static String passWord = "may2018";
	public String searchJDA = "transportation smartbench";
	public static JavascriptExecutor jse= null;

	public static WiniumDriverService service = null;
	public static DesktopOptions option = null;





	//public static WiniumDriver Winiumdriver = null;
	public static Logger logger= Logger.getLogger(LoginPage.class.getName());
	public static KeyboardRobot kbRobot = null;
	public static ResultSet resultSet = null;
	public static ResultSetMetaData metaData=null;
	//public static LoginPage screenShot = new LoginPage();
	//public static InputData ipData= null;
	public static final String Select = null;
	//public static Screen s =new Screen();
	public static String apptNbr=null;
	//public static String winiumDriverPath=System.getProperty("basedir")+"\\drivers\\Winium.Desktop.Driver.exe";
	public static String logportalPath="C:\\LogPortal\\BIZ\\LP_Launcher.exe";
	public static String SikuliPath="C:\\artemis-FreightOptimizationAutomation\\sikuli_images\\";





}
