AFOJ-35 DC2DC Destination address elements from existing DC addresses in FLO for dropdown

Meta:
@issue AFOJ-35

Narrative:

In order to clearly define the Destination address elements for DC to DC shipments
As a MTO colleague managing shipping
I want to source Destination addresses elements from FLO for the dropdown on the new Freight Movement Request screen

Scenario: (16) Validate the DC addresses from FLO listed in alphabetical order and allow the user to select a single address

Meta:
@acceptance
@id AFOJ-35-SC016
@productName Macy'sNET
@moduleName Additional Freight Optimization
@automatedBy BH00446_Sriram

Given Login to MacysNet application as Admin user
When click the link Freight Movement Request
Then take me to the page Freight Movement Request
And select DCTODC radio button
Then Verify All the Destination address are populated
And Verify user to select a single Destination address

Examples:
|MacysNet_URL               |Service_URL                                                           |
|http://dev.macysnet.com/AP/|http://lp000xstrs0002:8355/api/platform_msp/v1/shipping/retrieveAllDCs|
