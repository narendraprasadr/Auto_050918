AFOJ-34 DC2DC Source Origin address elements from existing DC addresses in FLO for dropdown

Meta:
@issue AFOJ-34

Narrative:

In order to clearly define the Origin address elements for DC to DC shipments
As a MTO colleague managing shipping
I want to source Origin addresses elements from FLO for the dropdown on the new Freight Movement Request screen

Scenario: (15) Validate the DC addresses from FLO listed in alphabetical order and allow the user to select a single address

Meta:
@acceptance
@id AFOJ-34-SC015
@productName Macy'sNET
@moduleName Additional Freight Optimization
@automatedBy BH00446_Sriram
					 
Given Login to MacysNet application as Admin user
When click the link Freight Movement Request
Then take me to the page Freight Movement Request
And select DCTODC radio button
Then Verify All the Origin address are populated
And Verify user to select a single origin address

Examples:
|MacysNet_URL               |Service_URL                                                           |
|http://dev.macysnet.com/AP/|http://lp000xstrs0002:8355/api/platform_msp/v1/shipping/retrieveAllDCs|
