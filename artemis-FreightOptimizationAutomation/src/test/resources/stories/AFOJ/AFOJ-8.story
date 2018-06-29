AFOJ-8 story
Scenario: (11) Validate the DC addresses from FLO listed in alphabetical order and allow the user to select a single address

Meta:
@acceptance
@id AFOJ-8-SC011
@productName Macy'sNET
@moduleName Additional Freight Optimization
@automatedBy BH05412_Praveenkumar
					 
Scenario: 
Given Login to MacysNet application as Admin user
When click the link Freight Movement Request
Then take me to the page Freight Movement Request
When select CRC/RTV radio button
When select the origin address
Then provide DC addresses in alphabetical order
Then allow the user to select a single address