package com.macys.mst.macysnet.pageobjects;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.macys.mst.artemis.selenium.PageObject;
import com.macys.mst.artemis.selenium.SeUiContextBase;
import com.macys.mst.macysnet.utils.General;


public class HomePage extends PageObject {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public static Logger logger = Logger.getLogger(HomePage.class.getName());
	SeUiContextBase objSeUiContextBase=new SeUiContextBase();

	@FindBy(xpath = "//span[@class='menuheading'][3]/following-sibling::a[1][contains(text(),'Peak Week Schedule')]")
	public WebElement lnkPeakWeekSchedule;

	@FindBy(xpath = "//span[contains(text(),'Shipping - Administration')][@class='menuheading']")
	public WebElement lblShippingAdministration;

	@FindBy(xpath = "//span[@class='menuheading'][3]/following-sibling::a[contains(text(),'Peak Days by 3 Digit Zip')]")
	public WebElement lnkPeakDaysbyZIP;

	@FindBy(xpath = "//span[@class='menuheading']/following-sibling::a[2][contains(text(),'Pick Up Days by ZIP')]")
	public WebElement lnkPickUpDaysByZIP;

	@FindBy(xpath = "//span[@class='menuheading']/following-sibling::a[1][contains(text(),'Vendor Lookup')]")
	public WebElement lnkVendorLookup;


		public boolean checklinkAvailabilty(WebDriver driver,String strLink) throws InterruptedException {

			Thread.sleep(3000);

		driver.switchTo().frame("Controls");

		Boolean blnElementAvailability=false;

try {
		if(strLink.equals("Peak Week Schedule")) {
			General.waitToLoadElement(this.lnkPeakWeekSchedule);
			blnElementAvailability =  SeUiContextBase.isElementDisplayed(lnkPeakWeekSchedule);
		}else if (strLink.equals("Peak Days by 3 Digit Zip")) {
			General.waitToLoadElement(this.lnkPeakDaysbyZIP);
			blnElementAvailability =  SeUiContextBase.isElementDisplayed(lnkPeakDaysbyZIP);

		}else if(strLink.equals("Pick Up Days by ZIP")) {
			General.waitToLoadElement(this.lnkPickUpDaysByZIP);
			blnElementAvailability =  SeUiContextBase.isElementDisplayed(lnkPickUpDaysByZIP);
		}

}catch(Exception e) {
	logger.info(strLink+" is not available");
}
		driver.switchTo().defaultContent();
		return blnElementAvailability;
	}

	public String checkLinkPosition(WebDriver driver,String strLink) {

		String strElementName = null;
		driver.switchTo().frame("Controls");

		if(strLink.equals("Peak Week Schedule")) {
			strElementName =	objSeUiContextBase.storeText(lnkPeakWeekSchedule);

		}else if (strLink.equals("Peak Days by 3 Digit Zip")) {
			strElementName =	objSeUiContextBase.storeText(lnkPeakDaysbyZIP);

		}else if(strLink.equals("Pick Up Days by ZIP")) {
			strElementName =	objSeUiContextBase.storeText(lnkPickUpDaysByZIP);

		}
		driver.switchTo().defaultContent();
	    return strElementName;
	}

	public boolean shippingAdminLabelAvailabilty(WebDriver driver) {

		boolean blnElementAvailability;

	try {
		driver.switchTo().frame("Controls");
		General.waitToLoadElement(this.lblShippingAdministration);
		blnElementAvailability =  SeUiContextBase.isElementDisplayed(lblShippingAdministration);
			return blnElementAvailability;
	 }catch(Exception e) {
		 return false;
	 }
	}


	public void clickLink(WebDriver driver,String strGivenLink) throws InterruptedException {
		Thread.sleep(1000);

		driver.switchTo().frame("Controls");
		if(strGivenLink.equals("Peak Week Schedule")) {

			try {
				objSeUiContextBase.clickElement(lnkPeakWeekSchedule);
			}catch(Exception e){
				logger.info(strGivenLink+" is not available");
			}
		}else if(strGivenLink.equals("Vendor Lookup")) {

			try {
			objSeUiContextBase.clickElement(lnkVendorLookup);
			}catch(Exception e){
				logger.info(strGivenLink+" is not available");
			}
		}else if(strGivenLink.equals("Pick Up Days by ZIP")) {
			objSeUiContextBase.clickElement(lnkPickUpDaysByZIP);

		}

	}
}
