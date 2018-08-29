package com.macys.mst.macysnet.Actionkeywords;

import java.util.ArrayList;

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

	public Invoice(String column1, String column2, String column3, String column4, String column5, String column6,
			String column7, String column8, String column9, String column10, String column11, String column12,
			String column13) {

		this.div = column1;
		this.dunsNbr = column2;
		this.dunsSuf = column3;
		this.invoiceNbr = column4;
		this.transNbr = column5;
		this.transSeq = column6;
		this.invoiceStatus = column7;
		this.tranCode = column8;
		this.desc = column9;
		this.poNbr = column10;
		this.checkNbr = column11;
		this.transDueDate = column12;
		this.transGrossAmt = column13;

	}

	public static ArrayList<Invoice> addValues(String column1, String column2, String column3,
			String column4, String column5, String column6, String column7, String column8, String column9,
			String column10, String column11, String column12, String column13) {

		objList.add(new Invoice(column1, column2, column3, column4, column5, column6, column7, column8, column9,
				column10, column11, column12, column13));
		return objList;

	}
	public static void printValuesServiceList(ArrayList<Invoice> list)
    {
        	 
        for (int i = 0; i < list.size(); i++)
        {      
        	Invoice data = list.get(i);	 
          
        System.out.println(data.div+"      "+data.dunsNbr+"    "+data.dunsSuf+"      "+data.invoiceNbr+" "+data.transNbr+"      "+data.transSeq+"      "+data.invoiceStatus+"    "+data.tranCode+"      "+data.desc+" "+data.poNbr+"      "+data.checkNbr+" "+data.transDueDate+"      "+data.transGrossAmt);
            	            
        }	   
   
    }

}
