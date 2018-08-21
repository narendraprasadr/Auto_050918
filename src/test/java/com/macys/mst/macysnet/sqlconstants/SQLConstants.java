package com.macys.mst.macysnet.sqlconstants;

public interface SQLConstants {

	/**
	 * All Queries used in this project as constants
	 *
	 */

	interface Select {

		public static final String query = "select #fieldName from #tableName where #conditionFieldName ='#value'";

		public static final String SPCL_RTE_RQST = "select SPCL_DESC,SPCL_RTE_NBR from SPCL_RTE_RQST where SPCL_RTE_NBR=200";
		public static final String SPCL_RTE_RQST_LBL = "select max(lbl_nbr) from SPCL_RTE_RQST_LBL  where SPCL_RTE_NBR=200";
		public static final String USER_TEXT = "select USER_TEXT from SHPMT_SPCL_RTE_RQST  where fb_nbr = #FBNumber";

		public static final String INVOICE = " Select\n" + "            A.DIV as div,\n"
				+ "            A.DUNS_NBR as dunsNbr,\n" + "            A.DUNS_SUF as dunsSuf,\n"
				+ "            A.TRANS_NUM as transNbr,\n" + "            ATA.TRANS_SEQ as transSeq,\n" +

				"            ATA.TRAN_CODE as trancode,\n" + "            ATT.LONG_DESC,\n"
				+ "            ATA.DOCUMENT_NBR as invoicenbr,\n" + "            ATA.MATCH_PO_NUMBER as poNbr,\n"
				+ "            CH.CHECK_NBR as checkNbr,\n" +

				"            A.TRANS_DUE_DATE as transDueDate,\n" + "            ATA.TRANS_GROSS_AMT as transGrossAmt\n"
				+ "            from AP_TRANS A,\n"
				+ "                 (select div, duns_nbr, duns_suf, document_nbr, trans_num,\n"
				+ "                         trans_seq, document_status, tran_code,\n"
				+ "                         match_po_number,check_nbr, trans_gross_amt\n"
				+ "                 from ap_trans_amt\n" + "                 where \n"
				+ "                 document_nbr between 9027304930  and (9027304930  + 200)\n"
				+ "                 and \n" + "                 div = 71\n"
				+ "                 and duns_nbr = 9109273\n" + "                 and duns_suf = 90\n"
				+ "                 and tran_code in (21,23,25,27,229,511,513,514,814,914)\n"
				+ "                 order by document_nbr) ATA,\n" + "                 CHECK_HDR CH,\n"
				+ "                 AP_TRANS_TYPE ATT\n" + "            where A.DUNS_NBR = ATA.DUNS_NBR\n"
				+ "              AND A.DUNS_SUF = ATA.DUNS_SUF\n" + "              AND A.TRANS_NUM = ATA.TRANS_NUM\n"
				+ "              AND A.DIV = ATA.DIV\n" + "              AND CH.DIV (+)= ATA.DIV\n"
				+ "              AND CH.DUNS_NBR (+)= ATA.DUNS_NBR\n"
				+ "              AND CH.DUNS_SUF (+)= ATA.DUNS_SUF\n"
				+ "              AND CH.CHECK_NBR (+)= ATA.CHECK_NBR\n"
				+ "              AND ATT.TRAN_CODE = ATA.TRAN_CODE\n" + "             and rownum <= 50";

		public static final String INVOICE_BY_PO_old = "SELECT ATA.DOCUMENT_NBR as invoicenbr, ATT.LONG_DESC,  \n"
				+ "        ATA.DOCUMENT_STATUS as invoicestatus, CH.CHECK_NBR as checkNbr, CH.CHECK_DATE as checkDate, AT.TRANS_DUE_DATE as transDueDate, ATA.TRANS_GROSS_AMT  as transGrossAmt\n"
				+ "        FROM AP_TRANS AT, AP_TRANS_AMT ATA, CHECK_HDR CH, AP_TRANS_TYPE ATT  \n"
				+ "        Where AT.DUNS_NBR =  9109273\n" + "         And AT.DUNS_SUF =  90\n"
				+ "         And AT.DIV =  71\n" + "         AND AT.ENTRY_PO_NUMBER = #POs\n"
				+ "         AND AT.DUNS_NBR = ATA.DUNS_NBR AND AT.DUNS_SUF = ATA.DUNS_SUF\n"
				+ "         AND AT.TRANS_NUM = ATA.TRANS_NUM AND AT.DIV = ATA.DIV\n"
				+ "         AND ATA.TRAN_CODE IN (21, 23, 25, 27, 511, 513, 514, 814, 914)\n"
				+ "         AND CH.DUNS_NBR (+)= ATA.DUNS_NBR AND CH.DUNS_SUF (+)= ATA.DUNS_SUF\n"
				+ "         AND CH.DIV (+) = ATA.DIV AND CH.CHECK_NBR (+)= ATA.CHECK_NBR\n"
				+ "         AND ATA.TRAN_CODE = ATT.TRAN_CODE";

		public static final String INVOICE_BY_PO = "SELECT  \n" + "        ATA.DOCUMENT_NBR as invoicenbr,\n"
				+ "        ATA.DOCUMENT_STATUS as invoiceStatus,\n" + "				ATT.LONG_DESC,\n"
				+ "				CH.CHECK_NBR as checkNbr,\n" + "        CH.CHECK_DATE as checkDate,\n"
				+ "			  AT.TRANS_DUE_DATE as transDueDate,\n"
				+ "				ATA.TRANS_GROSS_AMT as transGrossAmt\n"
				+ "        FROM AP_TRANS AT, AP_TRANS_AMT ATA, CHECK_HDR CH, AP_TRANS_TYPE ATT  \n"
				+ "        Where AT.DUNS_NBR =  9109273\n" + "         And AT.DUNS_SUF =  90\n"
				+ "         And AT.DIV =  71\n" + "         AND AT.ENTRY_PO_NUMBER = 2914988\n"
				+ "         AND AT.DUNS_NBR = ATA.DUNS_NBR AND AT.DUNS_SUF = ATA.DUNS_SUF\n"
				+ "         AND AT.TRANS_NUM = ATA.TRANS_NUM AND AT.DIV = ATA.DIV\n"
				+ "         AND ATA.TRAN_CODE IN (21,23,25,814)\n"
				+ "         AND CH.DUNS_NBR (+)= ATA.DUNS_NBR AND CH.DUNS_SUF (+)= ATA.DUNS_SUF\n"
				+ "         AND CH.DIV (+) = ATA.DIV AND CH.CHECK_NBR (+)= ATA.CHECK_NBR\n"
				+ "         AND ATA.TRAN_CODE = ATT.TRAN_CODE";

		public static final String PossibleUserEmail = "SELECT DISTINCT TOP 100 PERCENT cm.MEDIUM as email\n"
				+ "FROM         dbo.CONTACT_MEDIUM AS cm INNER JOIN\n"
				+ "                      dbo.PERSON AS p ON p.PERSON_NBR = cm.PERSON_NBR INNER JOIN\n"
				+ "                      dbo.PERSON_TO_CONTACT AS ptc ON ptc.PERSON_NBR = p.PERSON_NBR INNER JOIN\n"
				+ "                      dbo.PERSON_TO_ACCOUNT AS PA ON p.PERSON_NBR = PA.PERSON_NBR INNER JOIN\n"
				+ "                      dbo.VENDOR_ACCOUNT AS VA ON VA.VND_ACCT_ID = PA.VND_ACCT_ID\n"
				+ "WHERE     (p.ENABLED_FLAG = 1) AND (p.SEND_NO_EMAIL_FLAG = 0) AND (cm.MEDIUM_TYP_NBR = 4) AND (cm.MEDIUM IS NOT NULL)\n"
				+ "and ((VA.ACCT_NBR    like'%00001351090%')) and P.USER_NAME='B051UXG'";

		public static final String AcctListByUser = "select va.acct_nbr as accountnbr, va.account_name as accountname\n"
				+ "from vendor_account va\n" + "inner join person_to_account pta on va.VND_ACCT_ID = pta.VND_ACCT_ID\n"
				+ "inner join person p on pta.person_nbr=p.PERSON_NBR\n" + "where p.user_name= 'b051uxg'\n"
				+ "and ACCT_TYPE_CODE='DUNS'";

	}

	;

}
