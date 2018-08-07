Meta:
@issue AFOJ-99

Narrative:
In order to execute UAT testing
As a BP UAT tester
I want to restful service to submit a shipment request into the system where on success the shipment ID is returned to the client.


Scenario: REST service call by passing only the mandatory fields required for DC to DC submit request

Meta:
@acceptance
@regression
@automation
@REST
@id AFOJ-99-SC001
@productName MacysNet
@moduleName FreightMovementRequest
@automatedBy BH00446_Sriram

Given <serviceUrl> service is passed with the <field> and <value> parms
When <serviceUrl> service is called with POST method
Then Verify <serviceUrl> return code is <returnCode>
And Validate <ColumnName> is refected in Database FB_NBR column
Examples:
|serviceUrl                                                                          |run|returnCode|field                |value                                                                                                                                                             																																																									                                          																																	   |ColumnName     																 |
|http://lp000xstrs0002:8355/api/platform_msp/v1/shipping/postOutboundShipmentRequest |1  |200       |ShipmentRequestSubmit|userID:BH00446,screenShipmentType:DCTODC,originVendorID:0,originAddrSeqID:0,originLocationID:7,destVendorID:0,destAddrSeqID:0,destLocationID:3-loadInformation=freightOptionID:99,carrierTypeID:1,trailerClassTypeID:90,trailerNumber:,scac:,pieceCount:10,pieceCountInPallets:true,weightLbs:150,cubicFeet:410,pickupDate:currenttime,requestedPickupDate:requestedcurrenttime,driverNotes:I'maratherlongwindeddriversomynotesherearelikelytoexceedthe128charlimit.Anyway...hereismylifestory.Iwasbornin1945 wasanexceptionalchild andgraduatedfirstinmyclass.,dropLive:Drop,ra:RA3|fb/FB_PKUP_TS/pickupDate,fb/CARRIER_TYP_NBR/1,fb/FB_WEIGHT/150,fb/FB_CUBE/410|


Scenario: REST service call by passing only the mandatory fields required for DC to DC submit request validate requested date '%s' cannot be earlier than pickupDate '%s'

Meta:
@acceptance
@regression
@automation
@REST
@id AFOJ-99-SC002
@productName MacysNet
@moduleName FreightMovementRequest
@automatedBy BH00446_Sriram

Given <serviceUrl> service is passed with the <field> and <value> parms
When <serviceUrl> service is called with POST method
Then Verify <ErrorMSG> error message and return code is <returnCode>

Examples:
|serviceUrl                                                                          |run|returnCode|field                |value                                                                                                                                                             																																																									                                       																																		          |ErrorMSG																							  |
|http://lp000xstrs0002:8355/api/platform_msp/v1/shipping/postOutboundShipmentRequest |1  |400       |ShipmentRequestSubmit|userID:BH00446,screenShipmentType:DCTODC,originVendorID:0,originAddrSeqID:0,originLocationID:7,destVendorID:0,destAddrSeqID:0,destLocationID:3-loadInformation=freightOptionID:99,carrierTypeID:1,trailerClassTypeID:90,trailerNumber:,scac:,pieceCount:10,pieceCountInPallets:true,weightLbs:150,cubicFeet:410,pickupDate:08/05/2018 06:30 AM,requestedPickupDate:08/04/2018 04:30 PM,driverNotes:I'maratherlongwindeddriversomynotesherearelikelytoexceedthe128charlimit.Anyway...hereismylifestory.Iwasbornin1945 wasanexceptionalchild andgraduatedfirstinmyclass.,dropLive:Drop,ra:RA3|ERROR  requested date '08/04/2018 04 30 PM' cannot be earlier than pickupDate '08/05/2018 06 30 AM'|

Scenario: REST service call by passing only the mandatory fields required for DC to DC submit request validate requested SCAC='%s' so TrailerNumber cannot be empty

Meta:
@acceptance
@regression
@automation
@REST
@id AFOJ-99-SC003
@productName MacysNet
@moduleName FreightMovementRequest
@automatedBy BH00446_Sriram

Given <serviceUrl> service is passed with the <field> and <value> parms
When <serviceUrl> service is called with POST method
Then Verify <ErrorMSG> error message and return code is <returnCode>

Examples:
|serviceUrl                                                                          |run|returnCode|field                |value                                                                                                                                                             																																																									                                       																																		         	  |ErrorMSG														|
|http://lp000xstrs0002:8355/api/platform_msp/v1/shipping/postOutboundShipmentRequest |1  |400       |ShipmentRequestSubmit|userID:BH00446,screenShipmentType:DCTODC,originVendorID:0,originAddrSeqID:0,originLocationID:7,destVendorID:0,destAddrSeqID:0,destLocationID:3-loadInformation=freightOptionID:99,carrierTypeID:1,trailerClassTypeID:90,trailerNumber:,scac:AACT,pieceCount:10,pieceCountInPallets:true,weightLbs:150,cubicFeet:410,pickupDate:08/05/2018 06:30 AM,requestedPickupDate:08/04/2018 04:30 PM,driverNotes:I'maratherlongwindeddriversomynotesherearelikelytoexceedthe128charlimit.Anyway...hereismylifestory.Iwasbornin1945 wasanexceptionalchild andgraduatedfirstinmyclass.,dropLive:Drop,ra:RA3|ERROR: requested SCAC='AACT' so TrailerNumber cannot be empty|

Scenario: REST service call by passing only the mandatory fields required for DC to DC submit request validate requested TrailerNumber='%s' so SCAC cannot be empty

Meta:
@acceptance
@regression
@automation
@REST
@id AFOJ-99-SC004
@productName MacysNet
@moduleName FreightMovementRequest
@automatedBy BH00446_Sriram

Given <serviceUrl> service is passed with the <field> and <value> parms
When <serviceUrl> service is called with POST method
Then Verify <ErrorMSG> error message and return code is <returnCode>

Examples:
|serviceUrl                                                                          |run|returnCode|field                |value                                                                                                                                                             																																																									                                       																																		         	  |ErrorMSG														|
|http://lp000xstrs0002:8355/api/platform_msp/v1/shipping/postOutboundShipmentRequest |1  |400       |ShipmentRequestSubmit|userID:BH00446,screenShipmentType:DCTODC,originVendorID:0,originAddrSeqID:0,originLocationID:7,destVendorID:0,destAddrSeqID:0,destLocationID:3-loadInformation=freightOptionID:99,carrierTypeID:1,trailerClassTypeID:90,trailerNumber:1234,scac:,pieceCount:10,pieceCountInPallets:true,weightLbs:150,cubicFeet:410,pickupDate:08/05/2018 06:30 AM,requestedPickupDate:08/04/2018 04:30 PM,driverNotes:I'maratherlongwindeddriversomynotesherearelikelytoexceedthe128charlimit.Anyway...hereismylifestory.Iwasbornin1945 wasanexceptionalchild andgraduatedfirstinmyclass.,dropLive:Drop,ra:RA3|ERROR: requested TrailerNumber='1234' so SCAC cannot be empty|

Scenario: REST service call by passing only the mandatory fields required for DC to DC submit request validateInput parameter 'weight, cube, and pieceCount' with value = %d is out of range...must fall between %d and %d inclusive 

Meta:
@acceptance
@regression
@automation
@REST
@id AFOJ-99-SC005
@productName MacysNet
@moduleName FreightMovementRequest
@automatedBy BH00446_Sriram

Given <serviceUrl> service is passed with the <field> and <value> parms
When <serviceUrl> service is called with POST method
Then Verify <ErrorMSG> error message and return code is <returnCode>

Examples:
|serviceUrl                                                                          |run|returnCode|field                |value                                                                                                                                                             																																																									                                       																																		          |ErrorMSG																											  |
|http://lp000xstrs0002:8355/api/platform_msp/v1/shipping/postOutboundShipmentRequest |1  |400       |ShipmentRequestSubmit|userID:BH00446,screenShipmentType:DCTODC,originVendorID:0,originAddrSeqID:0,originLocationID:7,destVendorID:0,destAddrSeqID:0,destLocationID:3-loadInformation=freightOptionID:99,carrierTypeID:1,trailerClassTypeID:90,trailerNumber:,scac:,pieceCount:10,pieceCountInPallets:true,weightLbs:44001,cubicFeet:410,pickupDate:08/05/2018 06:30 AM,requestedPickupDate:08/04/2018 04:30 PM,driverNotes:I'maratherlongwindeddriversomynotesherearelikelytoexceedthe128charlimit.Anyway...hereismylifestory.Iwasbornin1945 wasanexceptionalchild andgraduatedfirstinmyclass.,dropLive:Drop,ra:RA3|ERROR: Input parameter 'weight(Lbs)' with value = 44001 is out of range...must fall between 1 and 44000 inclusive|
|http://lp000xstrs0002:8355/api/platform_msp/v1/shipping/postOutboundShipmentRequest |2  |400       |ShipmentRequestSubmit|userID:BH00446,screenShipmentType:DCTODC,originVendorID:0,originAddrSeqID:0,originLocationID:7,destVendorID:0,destAddrSeqID:0,destLocationID:3-loadInformation=freightOptionID:99,carrierTypeID:1,trailerClassTypeID:90,trailerNumber:,scac:,pieceCount:10,pieceCountInPallets:true,weightLbs:150,cubicFeet:3201,pickupDate:08/05/2018 06:30 AM,requestedPickupDate:08/04/2018 04:30 PM,driverNotes:I'maratherlongwindeddriversomynotesherearelikelytoexceedthe128charlimit.Anyway...hereismylifestory.Iwasbornin1945 wasanexceptionalchild andgraduatedfirstinmyclass.,dropLive:Drop,ra:RA3|ERROR: Input parameter 'Cube(Ft)' with value = 3201 is out of range...must fall between 1 and 3200 inclusive      |
|http://lp000xstrs0002:8355/api/platform_msp/v1/shipping/postOutboundShipmentRequest |3  |400       |ShipmentRequestSubmit|userID:BH00446,screenShipmentType:DCTODC,originVendorID:0,originAddrSeqID:0,originLocationID:7,destVendorID:0,destAddrSeqID:0,destLocationID:3-loadInformation=freightOptionID:99,carrierTypeID:1,trailerClassTypeID:90,trailerNumber:,scac:,pieceCount:1001,pieceCountInPallets:true,weightLbs:150,cubicFeet:410,pickupDate:08/05/2018 06:30 AM,requestedPickupDate:08/04/2018 04:30 PM,driverNotes:I'maratherlongwindeddriversomynotesherearelikelytoexceedthe128charlimit.Anyway...hereismylifestory.Iwasbornin1945 wasanexceptionalchild andgraduatedfirstinmyclass.,dropLive:Drop,ra:RA3|ERROR: Input parameter 'PieceCount' with value = 1001 is out of range...must fall between 1 and 1000 inclusive   |

Scenario: REST service call by passing only the mandatory fields required for DC to DC submit request validate Invalid SCAC Code(=%s) 

Meta:
@acceptance
@regression
@automation
@REST
@id AFOJ-99-SC006
@productName MacysNet
@moduleName FreightMovementRequest
@automatedBy BH00446_Sriram

Given <serviceUrl> service is passed with the <field> and <value> parms
When <serviceUrl> service is called with POST method
Then Verify <ErrorMSG> error message and return code is <returnCode>

Examples:
|serviceUrl                                                                          |run|returnCode|field                |value                                                                                                                                                             																																																									                                       																																		         	|ErrorMSG				 |
|http://lp000xstrs0002:8355/api/platform_msp/v1/shipping/postOutboundShipmentRequest |1  |400       |ShipmentRequestSubmit|userID:BH00446,screenShipmentType:DCTODC,originVendorID:0,originAddrSeqID:0,originLocationID:7,destVendorID:0,destAddrSeqID:0,destLocationID:3-loadInformation=freightOptionID:99,carrierTypeID:1,trailerClassTypeID:90,trailerNumber:123,scac:1234,pieceCount:10,pieceCountInPallets:true,weightLbs:441,cubicFeet:410,pickupDate:08/05/2018 06:30 AM,requestedPickupDate:08/04/2018 04:30 PM,driverNotes:I'maratherlongwindeddriversomynotesherearelikelytoexceedthe128charlimit.Anyway...hereismylifestory.Iwasbornin1945 wasanexceptionalchild andgraduatedfirstinmyclass.,dropLive:Drop,ra:RA3|Invalid SCAC Code(=1234)|

Scenario: REST service call by passing only the mandatory fields required for DC to DC submit request validate Invalid SCAC Code(=%s) 

Meta:
@acceptance
@regression
@automation
@REST
@id AFOJ-99-SC007
@productName MacysNet
@moduleName FreightMovementRequest
@automatedBy BH00446_Sriram

Given <serviceUrl> service is passed with the <field> and <value> parms
When <serviceUrl> service is called with POST method
Then Verify <ErrorMSG> error message and return code is <returnCode>

Examples:
|serviceUrl                                                                    		       |run|returnCode|field                |value                                                                                                                                                             																																																									                                       																															      |ErrorMSG						       |
|http://lp000xstrs0002:8355/api/platform_msp/v1/shipping/postOutboundShipmentRequest_INDev |1  |404       |ShipmentRequestSubmit|userID:BH00446,screenShipmentType:DCTODC,originVendorID:0,originAddrSeqID:0,originLocationID:7,destVendorID:0,destAddrSeqID:0,destLocationID:3-loadInformation=freightOptionID:99,carrierTypeID:1,trailerClassTypeID:90,trailerNumber:,scac:,pieceCount:10,pieceCountInPallets:true,weightLbs:441,cubicFeet:410,pickupDate:currenttime,requestedPickupDate:currenttime,driverNotes:I'maratherlongwindeddriversomynotesherearelikelytoexceedthe128charlimit.Anyway...hereismylifestory.Iwasbornin1945 wasanexceptionalchild andgraduatedfirstinmyclass.,dropLive:Drop,ra:RA3|Resource not found. Please check URL|
