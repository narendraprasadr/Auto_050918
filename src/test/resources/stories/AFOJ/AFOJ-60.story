AFOJ-60 NonRet -  Execute an initial load of data for the Non-Retail page

AFOJ-60 story

Meta:
@issue AFOJ-60

Narrative:

In order to have all of the data needed to create a Non-Retail movement
As a MTO colleague managing shipping
I want to initial data load executed when I open the Non-Retail page where backend services are invoked to produce the data to be loaded

Scenario: Non-Retail Execute an initial load of data for the Non-Retail page

Meta:
@acceptance
@id AFOJ-60-SC001
@productName Macy'sNET
@moduleName Additional Freight Optimization
@automatedBy BH00446_Sriram
					 
Given Admin user is on freight movement page
When select Non-Retail radio button
Then Verify All the Freight Type are populated from Rest services

Examples:
|Service_URL                                                                  |
|http://lp000xstrs0002:8355/api/platform_msp/v1/shipping/getFreightLoadOptions|

Scenario: Non-Retail Execute an initial load of data for the Non-Retail page

Meta:
@acceptance
@id AFOJ-60-SC002
@productName Macy'sNET
@moduleName Additional Freight Optimization
@automatedBy BH00446_Sriram
					 
Given Admin user is on freight movement page
When select Non-Retail radio button
Then Verify All the Carrier Type are populated from Rest services

Examples:
|Service_URL                                                                  |
|http://lp000xstrs0002:8355/api/platform_msp/v1/shipping/getFreightLoadOptions|

Scenario: Non-Retail Execute an initial load of data for the Non-Retail page

Meta:
@acceptance
@id AFOJ-60-SC003
@productName Macy'sNET
@moduleName Additional Freight Optimization
@automatedBy BH00446_Sriram
					 
Given Admin user is on freight movement page
When select Non-Retail radio button
Then Verify All the Trailer Class are populated from Rest services

Examples:
|Service_URL                                                                  |
|http://lp000xstrs0002:8355/api/platform_msp/v1/shipping/getFreightLoadOptions|