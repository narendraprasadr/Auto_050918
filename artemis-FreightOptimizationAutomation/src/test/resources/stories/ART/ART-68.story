ART-68 ART-68

Meta:
@issue ART-68

Narrative:
In order to Identify missing aborted waves on the batch status and failing to act accordingly
As a System 
I want to Display a warning screen on Work Plan Console if there are aborted waves


Scenario: Verify that "Aborted Waves" hyperlink is added on work plan console automation screen. 

Meta:
@acceptance
@id ART-68-SC001
@ProductName Proteus
@ModuleName Work_plan_console
@automatedBy RACF_Prasad Banala



Given User login to Proteus UI
When User Navigates to Work plan console automation screen
Then Verify that Aborted Waves link is getting displayed

Examples:
|ProteusURL                              |
|http://vcia4188:9080/MSTWMWeb/login/auth|         



Scenario: Verify that "Aborted Waves" hyperlink is blue when there are no aborted waves .

Meta:
@acceptance
@id ART-68-SC002
@ProductName Proteus
@ModuleName Work_plan_console
@automatedBy RACF_Prasad Banala

Given there are no aborted waves 
And User login to Proteus UI
When User Navigates to Work plan console automation screen 
Then the Aborted Waves text should be in blue

Examples:
|ProteusURL                              |
|http://vcia4188:9080/MSTWMWeb/login/auth|

Scenario: Verify that "Aborted Waves" hyperlink is REd when there are aborted waves.

Meta:
@acceptance
@id ART-68-SC003
@ProductName Proteus
@ModuleName Work_plan_console

Given there are aborted waves
And User login to Proteus UI
When User Navigates to Work plan console automation screen 
Then the Aborted Waves text should be in red
And Onclick of the hyperlink displays the list of aborted waves


Examples:
|ProteusURL                              |
|http://vcia4188:9080/MSTWMWeb/login/auth|
