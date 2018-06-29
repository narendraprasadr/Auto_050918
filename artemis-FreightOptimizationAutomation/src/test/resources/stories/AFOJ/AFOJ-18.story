AFOJ-18 story
Scenario: (13) Allow user to select single destination address

Meta:
@acceptance
@id AFOJ-18-SC013
@productName Macy'sNET
@moduleName Additional Freight Optimization
@automatedBy BH05412_Praveenkumar
					 
Scenario: 
Given Login to MacysNet application as Admin user
When click the link Freight Movement Request
Then take me to the page Freight Movement Request
When select CRC/RTV radio button
When select the destination address
Then provide DC addresses in alphabetical order
Then allow the user to select a single address