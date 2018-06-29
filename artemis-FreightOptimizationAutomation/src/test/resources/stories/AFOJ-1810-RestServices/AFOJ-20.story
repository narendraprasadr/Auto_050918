AFOJ-20 story


Meta:
@acceptance
@rest
@id AFOJ-20-SC001
@productName MacysNET
@moduleName FreightMovementRequest
@automatedBy 

Scenario: (001) REST Service to retrieve origin address
					 
Given LOCN_NBR and DC Name data exists in SHIP_SHIP_TO SQL SERVER table
Then Atleast one matching row for addr_line_1 from SHIP_SHIP_TO exists in LOC_ADDR oracle table
Then Verify REST service return code is 200 for http://lp000xstrs0002:8355/api/platform_msp/v1/shipping/retrieveAllDCs
Then get values from rest service for address1,address2,city,state,country,zipCode
Then compare value from AllDCs service and database