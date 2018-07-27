AFOJ-48 - NonRet (origin) and CRC (destination) Create a restful service to retrieve Origin Address elements

Meta:
@issue AFOJ-48
 
Narrative:

In order to retrieve Origin Address elements
As a Non-Retail origin and CRC destination page
I want to retrieve Origin Address elements via a restful end point

Scenario:REST Service to retrieve origin address

Meta:
@acceptance
@rest
@automation
@id AFOJ-48-SC001
@productName MacysNET
@moduleName Non-Retail Request
@automatedBy BH00446_Sriram
					 
				 
Given vnd_name column exists for 'QUARK%' in Vendor table in oracle database
Then Verify REST service return code is <returnCode> for <serviceUrl> and for <VendorName>
Then get values from <serviceUrl> rest service <VendorName> for address1,address2,city,state,country,zipCode
Then compare value from NonRetail service and database

Examples:
|serviceUrl                                                                                                   |VendorName              |returnCode|
|http://lp000xstrs0002:8355/api/platform_msp/v1/shipping/getVendorsByKeyPhraseAndServiceType                  |/nr-origin?qv=QUARK     |200       |

Scenario:REST Service to retrieve destination address
Meta:
@acceptance
@rest
@automation
@id AFOJ-48-SC002
@productName MacysNET
@moduleName Non-Retail Request
@automatedBy BH00446_Sriram
					 
					 
Given vnd_name column exists for 'STONE%' in LOCN table in oracle database
Then Verify REST service return code is <returnCode> for <serviceUrl> and for <VendorName>
Then get values from <serviceUrl> rest service <VendorName> for address1,address2,city,state,country,zipCode
Then compare value from NonRetail service and database

Examples:
|serviceUrl                                                                                                   |VendorName              |returnCode|
|http://lp000xstrs0002:8355/api/platform_msp/v1/shipping/getVendorsByKeyPhraseAndServiceType                  |/nr-dest?qv=STONE       |200       |