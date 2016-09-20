.. default-role:: code

=============================================================
Robot Framework Project by Pablo Sanabria and Juan Diego Diaz
=============================================================

.. contents:: Table of contents:
   :ClientController:
   :depth: 2

Introduction
============

About this project
------------------

.. code:: robotframework

	*** Settings ***
	
	Library  bo.edu.ucbcba.videoclub.controller.ClientController
	Library  BuiltIn
	
	*** Variables ***
	${USERNAME}             janedoe
	${PASSWORD}             J4n3D0e
	${NEW PASSWORD}         e0D3n4J
	${FIRSTNAME TOO LONG}   First Name is too long, must have less than 25 characters
	${FIRSTNAME TOO SHORT}  First Name is too short, must have more than 2 characters
	${LASTNAME TOO LONG}    Last Name is too long, must have less than 25 characters
	${LASTNAME TOO SHORT}   Last Name is too short, must have more than 2 characters
	${CI TOO LONG}			CI can't have more than 10 characters
	${CI TOO SHORT}			CI can't have less than 7 characters
	${ALREADY USER}			Already exist a Client with CI:
	
	*** Test Cases ***
	Creating user with invalid firstname should fail
	    [Template]					   Invalid firstname
	    jhonsnowrickrobotclarkkenthor  ${FIRSTNAME TOO LONG}
	    k            				   ${FIRSTNAME TOO SHORT}  
	
	Creating user with invalid lastname should fail
	    [Template]    					  Invalid lastname
	    hawkingsnowrickrobotclarkkenthor  ${LASTNAME TOO LONG}
	    D  								  ${LASTNAME TOO SHORT}
	
	Creating user with invalid identification should fail
	    [Template]		Invalid identification
	    12345678910     ${CI TOO LONG}
	    12345  			${CI TOO SHORT}
	    
	Creating user with valid information
		Create user  1299456745  juan_d  perez  nowhere
		
	Creating user already exists should fail
		Create user duplicated  111111114  ${ALREADY USER}	    
		
	*** Keywords ***
	Invalid firstname
	    [Arguments]    ${firstname}    ${error}
	    ${message} =  Run Keyword And Expect Error	*	create  12345678	${firstname}  hawking  nowhere
	    log  ${message}
	    Should Be Equal  ${message}  ValidationException: Validation error: ${error}
	    
	Invalid lastname
	    [Arguments]    ${lastname}    ${error}
	    ${message} =  Run Keyword And Expect Error	*	create  12345678	jhon_doe  ${lastname}  nowhere
	    log  ${message}
	    Should Be Equal  ${message}  ValidationException: Validation error: ${error}
	    
	Invalid identification
	    [Arguments]    ${ci}    ${error}
	    ${message} =  Run Keyword And Expect Error	*	create  ${ci}	jhon_doe  hawking  nowhere
	    log  ${message}
	    Should Be Equal  ${message}  ValidationException: Validation error: ${error}  
	          
	Create user
	    [Arguments]  ${ci}  ${firstname}  ${lastname}  ${address}
	    ${message} =    create    ${ci}	${firstname}  ${lastname}  ${address}
	    log  ${message}
	    Should Be Equal    ${message}  ${None}
	    
	Create user duplicated
	    [Arguments]  ${ci}  ${error}
	    				create    ${ci}	jhon_doe  hawking  nowhere
	    ${message} =    Run Keyword And Expect Error	*  create    ${ci}  jhon_doe  hawking  nowhere
	    log  ${message}
	    Should Be Equal    ${message}  ValidationException: Validation error: ${error} '${ci}'
	   
