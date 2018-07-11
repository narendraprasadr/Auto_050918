package com.macys.mst.macysnet.config;

import com.macys.mst.macysnet.utils.General;

public class Constants {

	public static String filepath = "C:\\artemis_reference\\test-output\\screenshots";
	
	public static String scnshotPassPath = ".\\screenshots\\PASS\\";
	public static String scnshotFailPath = ".\\screenshots\\FAIL\\";
	public static String currentTime = General.getTimeStamp();
	public static String dataFilePath = ".\\data\\Testdata.xlsx";
	public static String serviceURL="http://lp000xstrs0002:8355/api/platform_msp/v1/shipping/postOutboundShipmentRequest";
	public static String expectedCode="400";
	public static String field="ShipmentRequestSubmit";
	public static String values="originID:3,destinationID:6-loadInformation=pieceCount:100,pieceCountInPallets:true,weightLbs:75,cubicFeet:235,pickupDate:07/29/2018,pickupTime:3:30 PM,scac:AAAA,timeZone:ET";

}
