MIS-22 Upgrade UI for Check Detail screen in MNET AP

MIS-22 Check Detail search

Meta:
@issue MIS-22

Narrative:
In order to view an updated Check Detail screen

As a user

I want to the technology behind the UI upgraded Search requirements to retrieve the data in AP shall be Account # and Division Division selection available shall be Macy's, Bloomingdale's, Bloomingdale's Outlet Store and All Division Summary User shall have the option to select a specific division to view within the screen with toggle capability between divisions When the user selects All Division Summary, the summary by each division shall be displayed with a total at the bottom

Scenario: (1) Validate all the available widgets in division of Check detail screen

Meta:
@id MIS-22-SC001
@automated_by BH07405_Senthilkumar K
@regression
@ProductName Macy'sNet
@ModuleName AP forms

Given vendor user is on <URL> AccountsPayable screen
Then AP Query option is available for the <user>
Then user clicks on AP Query option
Then AP Query Screen has displayed
When user selects <activity> from Activity
Then Division widget <Division> has displayed on AP Query Screen
Then validate Account Number, Check Number widgets are displayed

Examples:
|URL                                |activity    |Division                                                        |user  |
|http://ma001xsweb95/accountspayable|Check Detail|All,Macy's,Macy's Backstage,Bloomingdale's,Bloomingdale's Outlet|vendor|

Scenario: (2) Check Detail search
Meta:
@id MIS-22-SC002
@acceptance
@automatedBy BH07405_Senthilkumar K
@productName MacysNet
@moduleName AP forms

Given user on AP query screen to validate the <activity> for <division> division
When user enter the Account no <accountNo> and click on submit button
Then validate the results of <activity>

Examples:
|URL                                |activity    |division|accountNo|
|http://ma001xsweb95/accountspayable|Check Detail|Macy's  |910927390|
|http://ma001xsweb95/accountspayable|Check Detail|All     |910927390|