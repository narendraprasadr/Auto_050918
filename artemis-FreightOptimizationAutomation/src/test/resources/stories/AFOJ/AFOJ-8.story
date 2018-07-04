AFOJ-8 CRC Source Origin address elements from existing DC addresses in FLO for dropdown

Meta:
@issue AFOJ-8

Narrative:
In order to clearly define the Origin address elements for CRC outbound shipments
As a MTO colleague managing shipping
I want to source Origin addresses elements from FLO for the dropdown on the new Freight Movement Request screen


Scenario:(1)Validate the DC addresses from FLO listed in alphabetical order and allow the user to select a single address

Meta:
@acceptance
@id AFOJ-8-SC001
@productName Macy'sNET
@moduleName Additional Freight Optimization
@automatedBy BH00446_Sriram
					 
Given Admin user is on freight movement page
When select CRC/RTV radio button
Then Verify All the Origin address are populated
And Verify user to select a single origin address

Examples:
|Service_URL                                                           |
|http://lp000xstrs0002:8355/api/platform_msp/v1/shipping/retrieveAllDCs|