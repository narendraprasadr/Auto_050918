AFOJ-21 story
Scenario: (5)  Retrieve destination address in the CRC/RTV page

Meta:
@acceptance
@id AFOJ-21-SC005
@productName Macy'sNET
@moduleName Additional Freight Optimization
@automatedBy BH05412_Praveenkumar
					 
Scenario: 
	Given Login to MacysNet application as Admin user
	When click the link Freight Movement Request
	Then take me to the page Freight Movement Request
	When select CRC/RTV radio button
	When select the destination address
	Then retrieve the destination address