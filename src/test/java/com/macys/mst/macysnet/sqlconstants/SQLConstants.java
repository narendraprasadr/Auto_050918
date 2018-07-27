package com.macys.mst.macysnet.sqlconstants;



public interface SQLConstants {

	/**
	 * All Queries used in this project as constants
	 *
	 */

	interface Select {

		public static final String shp_ship_to  = "Select value from dbo.shp_ship_to";
		public static final String LOC_ADDR  = "Select  addr_line_1,addr_line_2,city,st_code,ntn_code,zip_code from LOC_ADDR where loc_nbr in (#ship_ship_to) ORDER BY loc_nbr";

		public static final String FreightType ="select min(frgt_typ_nbr)as frgt_typ_nbr,frgt_typ_desc from frgt_typ group by frgt_typ_desc";
		public static final String CarrierType ="select min(CARRIER_TYP_NBR) as CARRIER_TYP_NBR, carr_grp_desc from carr_grp_typ group by CARR_GRP_DESC";
		public static final String TrailerClass ="select trlr_class_nbr,trlr_class_name from trlr_class";

		public static final String SCACCode  = "Select VND_CARR_SCAC from vendor where VND_CARR_SCAC ='#strcode'";

		/*public static final String Vendor  ="SELECT DISTINCT addr_line_1,vnd_nbr, addr_line_2,city,state as st_code,ntn_code,zip_code,loc_nbr \n" +
				"FROM VND_VND_ROLE_ADDR\n" +
				"WHERE vnd_nbr IN (SELECT v.vnd_nbr FROM Vendor v WHERE v.vnd_name like #VendorName) AND LTRIM(RTRIM(addr_line_1)) IS NOT NULL\n" +
				"ORDER BY vnd_nbr ASC";*/
		public static final String Vendor  ="SELECT DISTINCT a.vnd_nbr,a.vnd_name,b.addr_line_1,b.addr_line_2,b.city,b.state as \"st_code\", b.zip_code,b.ntn_code,b.loc_nbr,B.ADDR_SEQ_NBR\n" +
				"FROM vendor a JOIN VND_VND_ROLE_ADDR b\n" +
				"\n" +
				"ON a.vnd_nbr = b.VND_NBR\n" +
				"\n" +
				"WHERE A.VND_NBR IN (\n" +
				"\n" +
				"select vnd_nbr\n" +
				"\n" +
				"from vendor\n" +
				"\n" +
				"where vnd_name like 'QUAR%'\n" +
				"\n" +
				"and LTRIM(RTRIM(b.addr_line_1)) IS NOT NULL)\n" +
				"\n" +
				"ORDER BY vnd_name ASC";
		public static final String store="Select a.loc_nbr,b.addr_line_1,b.addr_line_2,b.city,b.st_code,b.zip_code,b.ntn_code  from\n" +
				"locn a JOIN loc_addr b\n" +
				"ON a.loc_nbr = b.loc_nbr\n" +
				"AND a.LOCN_STAT_CODE = 'A'\n" +
				"WHERE LOCN_TYP_CODE IN ('STR','DC') and LOCN_Name like #store";

		public static final String extr_ts  = "select extr_ts from intgrtn_Ctl where extr_key ='#extr_key'";

		public static final String appt_nbr = "select appt_nbr from fb where fb_nbr='#appt_nbr'";

		public static final String query = "select #fieldName from #tableName where #conditionFieldName ='#value'";





	}

	;

  }
