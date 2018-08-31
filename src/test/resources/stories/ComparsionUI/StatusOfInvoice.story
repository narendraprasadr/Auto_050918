Status of Invoice comparsion

Narrative:
In order to communicate effectively to the business some functionality
As a development team
I want to use Behaviour-Driven Development
					 
Scenario:  Comparing the existing UI Status of Inovice report with New UI screen in AP Query Screen

Given User is on macysnet page<URL>
When select activity from dropdown
!-- Then select division from dropdown
Then Enter account number
Then Enter document number
Then Store the <Existing> results in objects
!-- Given user on AP query page
!-- When Select activity in AP Query screen
!-- And Select division in AP Query screen
!-- Then Enter account number
!-- Then Enter document number
!-- And Store the <New> results in objects
!-- And Compare the ExistingUI Screen with NEW UI UIScreen

Examples:
|URL|Activity|Division|AccNumber|DocNumber|
|qa.macynet.com|Status of Invoice|Macy's|910927390|9027304930|
