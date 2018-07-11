Meta:
@issue AFOJ-71
!-- AFOJ-71: ALL SCREENS Create a mock restful service to produce a canned response for submit
Narrative:
In order to execute UAT testing
As a BP UAT tester
I want to mock restful service to produce a canned response for submit


Scenario: REST service call by passing only the mandatory fields required for DC to DC submit request

Meta:
@acceptance
@regression
@automation
@REST
@id AFOJ-71-SC001
@productName MacysNet
@moduleName FreightMovementRequest
@automatedBy B005725_Chenna Kanuparthi

Given <serviceUrl> service is passed with the <field> and <value> parms
When <serviceUrl> service is called with POST method
Then Verify <serviceUrl> return code is <returnCode>
And Validate <SCACCode> is refected in Database SCACCode column
Examples:
|serviceUrl                                                                         |run|returnCode|field                |value                                                                                                                                                                       |SCACCode|
|http://lp000xstrs0002:8355/api/platform_msp/v1/shipping/postOutboundShipmentRequest|1  |200       |ShipmentRequestSubmit|originID:3,destinationID:7-loadInformation=pieceCount:100,pieceCountInPallets:true,weightLbs:75,cubicFeet:235,pickupDate:07/23/2018,pickupTime:3:30 PM,scac:AACT,timeZone:ET|AACT|
|http://lp000xstrs0002:8355/api/platform_msp/v1/shipping/postOutboundShipmentRequest|2  |200       |ShipmentRequestSubmit|originID:3,destinationID:7-loadInformation=pieceCount:100,pieceCountInPallets:true,weightLbs:75,cubicFeet:235,pickupDate:07/23/2018,pickupTime:3:30 PM,timeZone:ET          ||
|http://lp000xstrs0002:8355/api/platform_msp/v1/shipping/postOutboundShipmentRequest|3  |400       |ShipmentRequestSubmit|originID:3,destinationID:7-loadInformation=pieceCount:100,pieceCountInPallets:true,weightLbs:75,cubicFeet:235,pickupDate:07/23/2018,pickupTime:3:30 PM,scac:bbbb,timeZone:ET||
