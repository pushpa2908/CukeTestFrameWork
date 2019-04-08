Feature: Login to Website and navigate

@tag1
Scenario Outline: User should be able to login to website
  Given I login to website "https://wordpress.com" with "<username>" and "<password>"
  Then home page "My Site" should open
  
Examples:
	|username|password|
	|pushpa.badhani@infogain.com|Temp@1111|  

@tag2
Scenario: User should be able to view Site Pages content
  Given user should be able to navigate Site Pages
  
@tag2	
Scenario: User should be able to search website
  Given user searches "Google Cloud"
  Then user should get the content "Google Cloud Platform for Systems Operations Professionals (CPO200) Training"
  And user on clicking  webresult should get "This course will introduce you to the implementation"  
  
@tag2
Scenario: User should be able to add site page
  When user clicks to add site page
  Then user should be able to add site content
  
#@tag1
#Scenario: User should be able to send invitation
#  When user enters User details
# |pdbadhani@yahoo.co.in|follower|Welcome message|
#  And sends invitation

@tag1
Scenario: User should be able to send invitation
  When user enters User details
  And sends invitation
  
@tag2
Scenario: User should be able to logout
  When user clicks on logout button he should be able to logout  