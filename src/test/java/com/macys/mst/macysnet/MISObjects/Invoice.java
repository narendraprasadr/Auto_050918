package com.macys.mst.macysnet.MISObjects;

import java.util.ArrayList;
import java.util.List;

public class Invoice {

	public static ArrayList<Invoice> objList = new ArrayList<Invoice>();
	public String div;
	public String dunsNbr;
	public String dunsSuf;
	public String invoiceNbr;
	public String transNbr;
	public String transSeq;
	public String invoiceStatus;
	public String tranCode;
	public String desc;
	public String poNbr;
	public String checkNbr;
	public String transDueDate;
	public String transGrossAmt;

	public Invoice(List<String> bodylist) {

		if (bodylist.size() == 5) {
			this.div = bodylist.get(0).toString();
			this.dunsNbr = bodylist.get(1).toString();
			this.dunsSuf = bodylist.get(2).toString();
			this.invoiceNbr = bodylist.get(3).toString();
			this.transGrossAmt = bodylist.get(4).toString();

		} else {

			this.div = bodylist.get(0).toString();
			this.dunsNbr = bodylist.get(1).toString();
			this.dunsSuf = bodylist.get(2).toString();
			this.invoiceNbr = bodylist.get(3).toString();
			this.transNbr = bodylist.get(4).toString();
			this.transSeq = bodylist.get(5).toString();
			this.invoiceStatus = bodylist.get(6).toString();
			this.tranCode = bodylist.get(7).toString();
			this.desc = bodylist.get(8).toString();
			this.poNbr = bodylist.get(9).toString();
			this.checkNbr = bodylist.get(10).toString();
			this.transDueDate = bodylist.get(11).toString();
			this.transGrossAmt = bodylist.get(12).toString();
		}

	}

	public static ArrayList<Invoice> getObjList() {
		return objList;
	}

	public static void setObjList(ArrayList<Invoice> objList) {
		Invoice.objList = objList;
	}

	public String getDiv() {
		return div;
	}

	public void setDiv(String div) {
		this.div = div;
	}

	public String getDunsNbr() {
		return dunsNbr;
	}

	public void setDunsNbr(String dunsNbr) {
		this.dunsNbr = dunsNbr;
	}

	public String getDunsSuf() {
		return dunsSuf;
	}

	public void setDunsSuf(String dunsSuf) {
		this.dunsSuf = dunsSuf;
	}

	public String getInvoiceNbr() {
		return invoiceNbr;
	}

	public void setInvoiceNbr(String invoiceNbr) {
		this.invoiceNbr = invoiceNbr;
	}

	public String getTransNbr() {
		return transNbr;
	}

	public void setTransNbr(String transNbr) {
		this.transNbr = transNbr;
	}

	public String getTransSeq() {
		return transSeq;
	}

	public void setTransSeq(String transSeq) {
		this.transSeq = transSeq;
	}

	public String getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public String getTranCode() {
		return tranCode;
	}

	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getPoNbr() {
		return poNbr;
	}

	public void setPoNbr(String poNbr) {
		this.poNbr = poNbr;
	}

	public String getCheckNbr() {
		return checkNbr;
	}

	public void setCheckNbr(String checkNbr) {
		this.checkNbr = checkNbr;
	}

	public String getTransDueDate() {
		return transDueDate;
	}

	public void setTransDueDate(String transDueDate) {
		this.transDueDate = transDueDate;
	}

	public String getTransGrossAmt() {
		return transGrossAmt;
	}

	public void setTransGrossAmt(String transGrossAmt) {
		this.transGrossAmt = transGrossAmt;
	}

	public Invoice() {
		super();
	}

	
	public static void printValuesServiceList(ArrayList<Invoice> list) {

		for (int i = 0; i < list.size(); i++) {
			Invoice data = list.get(i);

			System.out.println(data.div + "      " + data.dunsNbr + "    " + data.dunsSuf + "      " + data.invoiceNbr
					+ " " + data.transNbr + "      " + data.transSeq + "      " + data.invoiceStatus + "    "
					+ data.tranCode + "      " + data.desc + " " + data.poNbr + "      " + data.checkNbr + " "
					+ data.transDueDate + "      " + data.transGrossAmt);

		}

	}

}
