MIS-35 Security Service - Limit Account # selection based on login 

Meta:
@issue MIS-35

Narrative:
In order to maintain proper security within Payable when selecting an Account # 

As a user

I want to see only the Account #s that are associated with my user login in the Account # dropdown

Scenario: Security Service Limit Account # selection based on login
Meta:
@id MIS-35-SC01
@acceptance
@automation
@rest
@productName MacysNet
@moduleName AP
@project MIS
@automatedBy BH07405_Senthilkumar K

Given REST service return code 200  					
Then Get the values from Service and Database			
And Compare the Service values with Database

Examples:
|serviceUrl                                                        |returnCode|inputs          |values                |
|http://lp000xstrs0002:8360/api/macysnet/v1/vendorAP/AcctListByUser|200       |/?userId=B051UXG|accountNbr,accountName|
