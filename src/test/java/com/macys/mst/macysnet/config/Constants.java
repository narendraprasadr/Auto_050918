package com.macys.mst.macysnet.config;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriverService;

import com.macys.mst.d2c.automation.drm.kbrobot.KeyboardRobot;
import com.macys.mst.macysnet.utils.General;

public class Constants {

	public static String filepath = "C:\\artemis_reference\\test-output\\screenshots";

	public static String scnshotPassPath = ".\\screenshots\\PASS\\";
	public static String scnshotFailPath = ".\\screenshots\\FAIL\\";
	public static String currentTime = General.getTimeStamp();
	public static String dataFilePath = ".\\data\\Testdata.xlsx";

	public static String loginUrl="http://dev.macysnet.com/AP/";

	public static String colValue =null;
	public static JavascriptExecutor jse= null;

	public static KeyboardRobot kbRobot = null;
	public static ResultSet resultSet = null;
	public static ResultSetMetaData metaData=null;
	public static final String Select = null;
	public static String apptNbr=null;
	



}
