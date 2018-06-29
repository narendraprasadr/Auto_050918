AFOJ-59 DC2DC Execute an initial load of data for the DC to DC page

Meta:
@issue AFOJ-59

Narrative:

In order to have all of the data needed to create a DC to DC movement
As a MTO colleague managing shipping
I want to initial data load executed when I open the DC to DC page where backend services are invoked to produce the data to be loaded

Scenario: DC2DC Execute an initial load of data for the DC to DC page

Meta:
@acceptance
@id AFOJ-59-SC019
@productName Macy'sNET
@moduleName Additional Freight Optimization
@automatedBy BH00446_Sriram
					 
Given Login to MacysNet application as Admin user
When click the link Freight Movement Request
Then take me to the page Freight Movement Request
And select DCTODC radio button
Then Verify All the Origin address are populated from Rest services

Examples:
|MacysNet_URL               |Service_URL                                                           |
|http://dev.macysnet.com/AP/|http://lp000xstrs0002:8355/api/platform_msp/v1/shipping/retrieveAllDCs|


Scenario: DC2DC Execute an initial load of data for the DC to DC page

Meta:
@acceptance
@id AFOJ-59-SC023
@productName Macy'sNET
@moduleName Additional Freight Optimization
@automatedBy BH00446_Sriram
					 
Given Login to MacysNet application as Admin user
When click the link Freight Movement Request
Then take me to the page Freight Movement Request
And select DCTODC radio button
Then Verify All the Destination address are populated from Rest services

Examples:
|MacysNet_URL               |Service_URL                                                           |
|http://dev.macysnet.com/AP/|http://lp000xstrs0002:8355/api/platform_msp/v1/shipping/retrieveAllDCs|

Scenario: DC2DC Execute an initial load of data for the DC to DC page

Meta:
@acceptance
@id AFOJ-59-SC024
@productName Macy'sNET
@moduleName Additional Freight Optimization
@automatedBy BH00446_Sriram
					 
Given Login to MacysNet application as Admin user
When click the link Freight Movement Request
Then take me to the page Freight Movement Request
And select DCTODC radio button
Then Verify All the Freight Type are populated from Rest services

Examples:
|MacysNet_URL               |Service_URL                                                                  |
|http://dev.macysnet.com/AP/|http://lp000xstrs0002:8355/api/platform_msp/v1/shipping/getFreightLoadOptions|

Scenario: DC2DC Execute an initial load of data for the DC to DC page

Meta:
@acceptance
@id AFOJ-59-SC025
@productName Macy'sNET
@moduleName Additional Freight Optimization
@automatedBy BH00446_Sriram
					 
Given Login to MacysNet application as Admin user
When click the link Freight Movement Request
Then take me to the page Freight Movement Request
And select DCTODC radio button
Then Verify All the Carrier Type are populated from Rest services

Examples:
|MacysNet_URL               |Service_URL                                                                  |
|http://dev.macysnet.com/AP/|http://lp000xstrs0002:8355/api/platform_msp/v1/shipping/getFreightLoadOptions|

Scenario: DC2DC Execute an initial load of data for the DC to DC page

Meta:
@acceptance
@id AFOJ-59-SC026
@productName Macy'sNET
@moduleName Additional Freight Optimization
@automatedBy BH00446_Sriram
					 
Given Login to MacysNet application as Admin user
When click the link Freight Movement Request
Then take me to the page Freight Movement Request
And select DCTODC radio button
Then Verify All the Trailer Class are populated from Rest services

Examples:
|MacysNet_URL               |Service_URL                                                                  |
|http://dev.macysnet.com/AP/|http://lp000xstrs0002:8355/api/platform_msp/v1/shipping/getFreightLoadOptions|