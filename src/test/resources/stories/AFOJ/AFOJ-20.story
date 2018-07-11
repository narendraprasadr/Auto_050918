AFOJ-20 story
Scenario: (9)  Retrieve origin address in the CRC/RTV page

Meta:
@acceptance
@id AFOJ-20-SC009
@productName Macy'sNET
@moduleName Additional Freight Optimization
@automatedBy BH05412_Praveenkumar
					 
Scenario: 
Given Login to MacysNet application as Admin user
When click the link Freight Movement Request
Then take me to the page Freight Movement Request
When select CRC/RTV radio button
When select the origin address
Then retrieve the origin address