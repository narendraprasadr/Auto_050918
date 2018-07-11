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
		
		
		
		
		
		
		
		
		
		
	}

	;

  }
