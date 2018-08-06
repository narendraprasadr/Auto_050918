Meta:
@issue AFOJ-125

Narrative:
In order to retrieve existing shipments
As a MTO colleague managing shipping
I want to Java service created that retrieves existing shipments



Scenario: Rest Service to retrieve shipment details- This will be used in Update Shipment transaction

Meta:
@acceptance
@regression
@automation
@REST
@id AFOJ-125-SC001
@productName MacysNet
@moduleName FreightMovementRequest
@automatedBy BH00446_Sriram

Given <serviceUrl> service is passed with the <field> and <value> parms
When <serviceUrl> service is called with POST method
Then Verify <serviceUrl> return code is <returnCode>
And Validate <ColumnName> is refected in Database FB_NBR column
Then Verify <RetrieveShipmentURL> REST service return code is <returnCode>
Then get <Existingvalues> from rest service and compare given <value>
Examples:
|serviceUrl                                                                          |run|returnCode|field                |value                                                                                                                                                             																																																									                                          																																	   |ColumnName     																 |RetrieveShipmentURL														  |Existingvalues|
|http://lp000xstrs0002:8355/api/platform_msp/v1/shipping/postOutboundShipmentRequest |1  |200       |ShipmentRequestSubmit|userID:BH00446,screenShipmentType:DCTODC,originVendorID:0,originAddrSeqID:0,originLocationID:7,destVendorID:0,destAddrSeqID:0,destLocationID:3-loadInformation=freightOptionID:99,carrierTypeID:1,trailerClassTypeID:90,trailerNumber:,scac:,pieceCount:10,pieceCountInPallets:true,weightLbs:150,cubicFeet:410,pickupDate:currenttime,requestedPickupDate:requestedcurrenttime,driverNotes:I'maratherlongwindeddriversomynotesherearelikelytoexceedthe128charlimit.Anyway...hereismylifestory.Iwasbornin1945 wasanexceptionalchild andgraduatedfirstinmyclass.,dropLive:Drop,ra:RA3|fb/FB_PKUP_TS/pickupDate,fb/CARRIER_TYP_NBR/1,fb/FB_WEIGHT/150,fb/FB_CUBE/410|http://lp000xstrs0002:8355/api/platform_msp/v1/shipping/getExistingShipment/||
