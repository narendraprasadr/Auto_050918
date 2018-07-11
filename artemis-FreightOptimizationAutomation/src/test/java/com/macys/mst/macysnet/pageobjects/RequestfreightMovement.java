package com.macys.mst.macysnet.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.apache.log4j.Logger;

import com.macys.mst.artemis.selenium.PageObject;
import com.macys.mst.artemis.selenium.SeUiContextBase;
import com.macys.mst.macysnet.config.Constants;
import com.macys.mst.macysnet.stepdefs.FreightOptimization;
import com.macys.mst.macysnet.utils.General;

public class RequestfreightMovement extends PageObject {

	public static Logger logger = Logger.getLogger(FreightOptimization.class.getName());
	public RequestfreightMovement(WebDriver driver) {
		super(driver);
	}
	
	
	@FindBy(xpath = "//span[@class='menuheading'][3]/following-sibling::a[2][contains(text(),'Pick Up Days by ZIP')]")
	public static WebElement freightMovementRequestHeader;
	
	@FindBy(xpath = "")
	public static WebElement crc_rtvlabel;
	
	@FindBy(xpath = "")
	public static WebElement dctodclabel;
	
	@FindBy(xpath = "")
	public static WebElement nonretaillabel;
	
	@FindBy(xpath = "")
	public static WebElement crc_rtvradiobutton;
	
	@FindBy(xpath = "")
	public static WebElement dctodcradiobutton;
	
	@FindBy(xpath = "")
	public static WebElement nonretailradiobutton;
	
	
	
	
	
	
public static void radiobuttonvalidationReqFreight(String strpagename) throws Exception {		

	//driver.switchTo().defaultContent();	
	//driver.switchTo().frame("Controls");
	//Thread.sleep(2000);
	 boolean crc_rtvlabelAvailability = SeUiContextBase.isElementDisplayed(RequestfreightMovement.crc_rtvlabel);
	 boolean dctodclabelAvailability = SeUiContextBase.isElementDisplayed(RequestfreightMovement.dctodclabel);
	 boolean nonretaillabelAvailability = SeUiContextBase.isElementDisplayed(RequestfreightMovement.nonretaillabel);
	 boolean crc_rtvradiobuttonAvailability =SeUiContextBase.isElementDisplayed(RequestfreightMovement.crc_rtvradiobutton);
	 boolean dctodcradiobuttonAvailability = SeUiContextBase.isElementDisplayed(RequestfreightMovement.dctodcradiobutton);
	 boolean nonretailradiobuttonAvailability = SeUiContextBase.isElementDisplayed(RequestfreightMovement.nonretailradiobutton);
	if(crc_rtvlabelAvailability==true && dctodclabelAvailability==true && nonretaillabelAvailability==true && crc_rtvradiobuttonAvailability==true && dctodcradiobuttonAvailability==true && nonretailradiobuttonAvailability==true)
	{
		Assert.assertTrue(true);
	 	logger.info(strpagename+" is displayed");
	 	SeUiContextBase.Capture_Page_Screenshot(General.driver, Constants.scnshotPassPath +"RadioButton"+General.getTimeStamp()+".jpg");
	}
	else
	{
		Assert.assertTrue(false);
		logger.info(strpagename+" is  not displayed");
		SeUiContextBase.Capture_Page_Screenshot(General.driver, Constants.scnshotFailPath +"RadioButton"+General.getTimeStamp()+".jpg");
	}
			
	}

	

}
