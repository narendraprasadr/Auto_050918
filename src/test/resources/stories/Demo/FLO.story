Story: story1

Scenario: (01) validation of disabled image in FLO

Meta:
@acceptance
@id 09
@ProductName Small Ticket Enhancements(SMT)
@ModuleName FLO
@automated_by BH00148_Krishnakumar Ganesan

Given Launch FLO Application
!-- Then Enter FloApptnbr 30878356 and searchresult
!-- Then select appointment status history
Then Enter shipment 1808930 and search
Then validate shipment details page
Then close Logportal application

