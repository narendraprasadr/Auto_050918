package com.macys.mst.macysnet.MISObjects;

import java.util.List;

public class InvoicesUI {
	public  String documentNo;
	public  String status;
	public  String desc;
	public  String dueDate;
	public  String amount;
	/**
	 * @param dueDate
	 * @param documentNo
	 * @param checkDate
	 * @param amount
	 */
	public InvoicesUI(List<String> bodylist) {
		super();
		this.documentNo = bodylist.get(0).toString();;
		this.status = bodylist.get(1).toString();;
		this.desc = bodylist.get(2).toString();;
		this.dueDate = bodylist.get(3).toString();;
		this.amount = bodylist.get(4).toString();;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getDocumentNo() {
		return documentNo;
	}
	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	

}
