package com.macys.mst.macysnet.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.macys.mst.artemis.selenium.PageObject;

public class ShipmentRequest extends PageObject {
	
	public ShipmentRequest(WebDriver driver) {
		super(driver);
	}

	@FindBy(name = "drpShpToAddr")
	public WebElement ddl_ShipmentRequest_ShipToAddress;

}
