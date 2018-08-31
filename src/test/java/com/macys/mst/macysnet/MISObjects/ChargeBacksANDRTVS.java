package com.macys.mst.macysnet.MISObjects;

import java.util.ArrayList;
import java.util.List;

public class ChargeBacksANDRTVS {
	public static ArrayList<ChargeBacksANDRTVS> newScreendatasList=new ArrayList<>();
	public static ArrayList<ChargeBacksANDRTVS> existingScreendatasList=new ArrayList<>();
	


	public  String documentNo;
	public  String deptVendor;
	public  String dueDate;
	public  String purchaseOrderNo;
	public  String transType;
	public  String totalCost;
	public  String receiptNo;
	public  String carrier;
	public  String freightBill;
	public  String billofLading;
	public  String styleTotalCost;
	public  String cartons;
	public  String weight;
	  
	  
	    
	   public ChargeBacksANDRTVS(List<String> bodylist) {

				if (bodylist.size() == 13) {
		     
	 
	         this.documentNo = bodylist.get(0).toString();
	         this.deptVendor = bodylist.get(1).toString();
	         this.dueDate = bodylist.get(2).toString();
	         this.purchaseOrderNo = bodylist.get(3).toString();
	         this.transType = bodylist.get(4).toString();
	         this.totalCost = bodylist.get(5).toString();
	         this.receiptNo = bodylist.get(6).toString();
	         this.carrier = bodylist.get(7).toString();
	         this.cartons = bodylist.get(8).toString();
	         this.weight = bodylist.get(9).toString();
	         this.freightBill = bodylist.get(10).toString();
	         this.billofLading = bodylist.get(11).toString();
	         this.styleTotalCost = bodylist.get(12).toString();    
	         
	    }
	   
	   }



	public String getDocumentNo() {
		return documentNo;
	}



	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}



	public String getDeptVendor() {
		return deptVendor;
	}



	public void setDeptVendor(String deptVendor) {
		this.deptVendor = deptVendor;
	}



	public String getDueDate() {
		return dueDate;
	}



	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}



	public String getPurchaseOrderNo() {
		return purchaseOrderNo;
	}



	public void setPurchaseOrderNo(String purchaseOrderNo) {
		this.purchaseOrderNo = purchaseOrderNo;
	}



	public String getTransType() {
		return transType;
	}



	public void setTransType(String transType) {
		this.transType = transType;
	}



	public String getTotalCost() {
		return totalCost;
	}



	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}



	public String getReceiptNo() {
		return receiptNo;
	}



	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}



	public String getCarrier() {
		return carrier;
	}



	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}



	public String getFreightBill() {
		return freightBill;
	}



	public void setFreightBill(String freightBill) {
		this.freightBill = freightBill;
	}



	public String getBillofLading() {
		return billofLading;
	}



	public void setBillofLading(String billofLading) {
		this.billofLading = billofLading;
	}



	public String getStyleTotalCost() {
		return styleTotalCost;
	}



	public void setStyleTotalCost(String styleTotalCost) {
		this.styleTotalCost = styleTotalCost;
	}



	public String getCartons() {
		return cartons;
	}



	public void setCartons(String cartons) {
		this.cartons = cartons;
	}



	public String getWeight() {
		return weight;
	}



	public void setWeight(String weight) {
		this.weight = weight;
	}
	    
	   
	 


}
