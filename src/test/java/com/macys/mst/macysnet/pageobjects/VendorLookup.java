package com.macys.mst.macysnet.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.macys.mst.artemis.selenium.PageObject;
import com.macys.mst.artemis.selenium.SeUiContextBase;
import com.macys.mst.macysnet.utils.General;



	public class VendorLookup extends PageObject {

		public VendorLookup(WebDriver driver) {
			super(driver);
		}
		SeUiContextBase objSeUiContextBase=new SeUiContextBase();

		@FindBy(name = "txtVendorName")
		public WebElement txtVendorName;

		@FindBy(name = "btnSubmit")
		public WebElement btnSubmit;

		@FindBy(xpath = "//tr/td/a")
		public WebElement lnkAccountNumber;

		@FindBy(xpath = "//input[@type='button'][@value='M']")
		public WebElement btnM;

		@FindBy(name = "btnNext")
		public WebElement btnNext;
		@FindBy(name = "grdVPO$ctl02$txtPONum1")
		public WebElement txtPONumber;

		@FindBy(name = "txtTotalCarton")
		public WebElement txtTotalCarton;

		@FindBy(xpath = ".//tbody/tr[2]/td[2]")
		public WebElement tdShipmentNumber;

		@FindBy(name = "txtTotalWeight")
		public WebElement txtTotalWeight;

		@FindBy(name = "txtTotalCube")
		public WebElement txtTotalCube;
		@FindBy(name = "rdSRR")
		public WebElement rdSRR;


		@FindBy(name = "txtBOL")
		public WebElement txtBOL;

		@FindBy(id = "grdAddress_ctl03_btnS")
		public WebElement btnS;

		@FindBy(xpath = "//table/tbody/tr[4]/th")
		public WebElement lblPeakNonPeak;

		@FindBy(xpath = "//table/tbody/tr[5]/th")
		public WebElement lblVIPOverride;

		@FindBy(xpath = "//table/tbody/tr[4]/th[2]/input[@name='chkVip']")
		public WebElement rbPeakNonPeak;

		@FindBy(xpath = "//table/tbody/tr[5]/th[2]/input[@name='chkVipFreeFlow']")
		public WebElement rbVIPOverride;

		@FindBy(name = "txtPriFirstName")
		public WebElement txtPriFirstName;

		@FindBy(name = "txtPriLastName")
		public WebElement txtPriLastName;

		@FindBy(name = "txtPriPhone1")
		public WebElement txtPriPhone1;

		@FindBy(name = "txtPriEmail")
		public WebElement txtPriEmail;

		@FindBy(name = "txtPriEmailConfirm")
		public WebElement txtPriEmailConfirm;

		@FindBy(id = "vndManuCalendar_ListBox1")
		public WebElement ddlShippingCalendar;

		@FindBy(name = "btnUpdate")
		public WebElement btnUpdate;

		@FindBy(name = "btnReturn1")
		public WebElement btnReturnToAddressLookUP;



		public void enterVendorName(String strVendorName) {

			try {
				General.waitToLoadElement(this.txtVendorName);
			} catch (Exception e) {
				e.printStackTrace();
			}

			 objSeUiContextBase.sendkeys(this.txtVendorName, strVendorName);
		}

		public void accountNumber() {

			try {
				General.waitToLoadElement(this.lnkAccountNumber);
			} catch (Exception e) {
				e.printStackTrace();
			}

			 objSeUiContextBase.clickElement(this.lnkAccountNumber);
		}

		public void clickSubmit() {

	try {
		General.waitToLoadElement(this.btnSubmit);
	} catch (Exception e) {
		e.printStackTrace();
	}

	 objSeUiContextBase.clickElement(this.btnSubmit);
}

		public void clickButtonM() {

			try {
				General.waitToLoadElement(this.btnM);
			} catch (Exception e) {
				e.printStackTrace();
			}

			 objSeUiContextBase.clickElement(this.btnM);
		}

public void clickUpdateButton() {

			try {
				General.waitToLoadElement(this.btnUpdate);
			} catch (Exception e) {
				e.printStackTrace();
			}

			 objSeUiContextBase.clickElement(this.btnUpdate);
		}

		public String getLabel(String label) {

			if(label.equals("Peak/Non-Peak")) {

			String strLabel = objSeUiContextBase.storeText(this.lblPeakNonPeak);
			return strLabel;
			}else {
				String strLabel = objSeUiContextBase.storeText(this.lblVIPOverride);
				return strLabel;

			}
		}

public boolean clickRadioButton(String radioButtonLabel) {
	if(radioButtonLabel.equals("Peak/Non-Peak")) {
		try {
				General.waitToLoadElement(this.rbPeakNonPeak);
			} catch (Exception e) {
				e.printStackTrace();
			}

		 objSeUiContextBase.clickElement(this.rbPeakNonPeak);

		 return  this.rbPeakNonPeak.isSelected();


	}else {

			try {
			General.waitToLoadElement(this.rbVIPOverride);
			if(this.rbVIPOverride.isSelected())
			{

			}else
			{
				objSeUiContextBase.clickElement(this.rbVIPOverride);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}


		 return true;
	}

 }

public boolean getRadioButtonStatus(String radioButtonLabel) {



	boolean radioButtonStatus ;
	if(radioButtonLabel.equals("Peak/Non-Peak")) {

		radioButtonStatus = this.rbPeakNonPeak.isSelected();

	return radioButtonStatus;


	}else {
		radioButtonStatus = this.rbVIPOverride.isSelected();
		return radioButtonStatus;

	}
}

public void setShippingDate() {

	try {
		General.waitToLoadElement(this.ddlShippingCalendar);
	} catch (Exception e) {
		e.printStackTrace();
	}

	 objSeUiContextBase.selectValues(this.ddlShippingCalendar,"11:00 AM - 5:00 PM M-F");

}

public void setFirstName() {

	try {
		General.waitToLoadElement(this.txtPriFirstName);
	} catch (Exception e) {
		e.printStackTrace();
	}

	if(this.txtPriFirstName.getText()==null) {
		objSeUiContextBase.sendkeys(this.txtPriFirstName,"aaaa");
	}

}

public void setLastName() {

	try {
		General.waitToLoadElement(this.txtPriLastName);
	} catch (Exception e) {
		e.printStackTrace();
	}

	if(this.txtPriLastName.getText()==null) {
		objSeUiContextBase.sendkeys(this.txtPriLastName,"bbbb");
	}

}

public void setEmailAddress() {

	try {
		General.waitToLoadElement(this.txtPriEmail);
	} catch (Exception e) {
		e.printStackTrace();
	}

	if(this.txtPriEmail.getText()==null) {
		objSeUiContextBase.sendkeys(this.txtPriEmail,"aa@gg.com");
	}

}

public void setConfirmEmailAddress() {

	try {
		General.waitToLoadElement(this.txtPriEmailConfirm);
	} catch (Exception e) {
		e.printStackTrace();
	}

		if(this.txtPriEmailConfirm.getText()==null) {
			objSeUiContextBase.sendkeys(this.txtPriEmailConfirm,"aa@gg.com");
		}

}

public void setTelephoneNo() {

	try {
		General.waitToLoadElement(this.txtPriPhone1);
	} catch (Exception e) {
		e.printStackTrace();
	}

	if(this.txtPriPhone1.getText()==null) {
		objSeUiContextBase.sendkeys(this.txtPriPhone1, "444444444");
	}

}

public void clickReturnButton() {

	try {
		General.waitToLoadElement(this.btnReturnToAddressLookUP);
	} catch (Exception e) {
		e.printStackTrace();
	}

	 objSeUiContextBase.clickElement(this.btnReturnToAddressLookUP);
}


	}
