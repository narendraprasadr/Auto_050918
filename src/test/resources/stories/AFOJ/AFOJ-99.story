AFOJ-99 Create a restful service to produce a "Freight Bill" for "submit"

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
|serviceUrl                                                                      		       |run|returnCode|field                |value                                                                                                                                                             																																																									                                                                                                                                                                                                                  |ColumnName     																						                                                                        |
|http://lp000xstrs0002:8355/api/platform_msp/v1/shipping/postOutboundShipmentRequest_INDev|1  |200       |ShipmentRequestSubmit|userID:BH00446,screenShipmentType:DCTODC,originVendorID:0,originAddrSeqID:0,originLocationID:3,destVendorID:0,destAddrSeqID:0,destLocationID:7-loadInformation=freightptionID:99,carrierTypeID:1,trailerClassTypeID:90,scac:AACT,pieceCount:100,pieceCountInPallets:true,weightLbs:75,cubicFeet:235,pickupDate:07/25/2018,pickupTime:6:30 AM,timeZone:ET,driverNotes:HeyDave letsstopatthecoffeeshopat6thandWaverlybeforepickinguptheshipment|fb/FB_PKUP_TS/2018-07-25 06:30:00,fb/CARRIER_TYP_NBR/1,fb/VND_NBR_CARRIER/2001,fb/FB_WEIGHT/75,fb/FB_CUBE/235|
