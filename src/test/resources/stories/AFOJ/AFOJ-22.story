AFOJ-22 Create a restful service to retrieve Freight Type, Carrier Type and Trailer class for CRC & DC2DC

Meta:
@issue AFOJ-22

Narrative:

In order to retrieve Freight Type, Carrier Type and Trailer Class
As a CRC/RTV page
I want to retrieve Freight Type, Carrier Type and Trailer Class

Scenario: (2) Retrieve Freight Type data in the CRC/RTV page

Meta:
@acceptance
@id AFOJ-22-SC002
@productName Macy'sNET
@moduleName FreightMovementRequest
@automatedBy BH00446_SRIRAM
					 
Given REST service return code 200 for the <ServiceURL>
When table exists for storing FreightType
Then get values from rest service for FreightLoadOptions.freightOptions.key,FreightLoadOptions.freightOptions.value
And compare value from FreightType service and database
Examples:
|ServiceURL                                                                   |
|http://lp000xstrs0002:8355/api/platform_msp/v1/shipping/getFreightLoadOptions|

Scenario: (3)  Retrieve Carrier Type data in the DC to DC page

Meta:
@acceptance
@id AFOJ-22-SC003
@productName Macy'sNET
@moduleName FreightMovementRequest
@automatedBy BH00446_SRIRAM

Given REST service return code 200 for the <ServiceURL>
When table exists for storing CarrierType
Then get values from rest service for FreightLoadOptions.carrierTypes.key,FreightLoadOptions.carrierTypes.value
And compare value from CarrierType service and database
Examples:
|ServiceURL                                                                   |
|http://lp000xstrs0002:8355/api/platform_msp/v1/shipping/getFreightLoadOptions|

Scenario: (4)  Retrieve Trailer Class data in the DC to DC page

Meta:
@acceptance
@id AFOJ-22-SC004
@productName Macy'sNET
@moduleName FreightMovementRequest
@automatedBy BH00446_SRIRAM
					 
Given REST service return code 200 for the <ServiceURL>
When table exists for storing TrailerClass
Then get values from rest service for FreightLoadOptions.trailerClassTypes.key,FreightLoadOptions.trailerClassTypes.value
And compare value from TrailerClass service and database
Examples:
|ServiceURL                                                                   |
|http://lp000xstrs0002:8355/api/platform_msp/v1/shipping/getFreightLoadOptions|