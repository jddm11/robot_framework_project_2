.. default-role:: code

=============================================================
Robot Framework Project by Pablo Sanabria and Juan Diego Diaz
=============================================================

.. contents:: Table of contents:
   :local:
   :depth: 2

Introduction
============

About this project
------------------

VideoClub is a JAVA application built using the MVC architecture.
It consists ......................


Robot Framework overview
------------------------

`Robot Framework`_ is a generic open source test automation framework for
acceptance testing and acceptance test-driven development (ATDD). It has
easy-to-use tabular test data syntax and it utilizes the keyword-driven
testing approach. Its testing capabilities can be extended by test libraries
implemented either with Python or Java, and users can create new higher-level
keywords from existing ones using the same syntax that is used for creating
test cases.

Robot Framework is operating system and application independent. The core
framework is implemented using `Python <http://python.org>`_ and runs also on
`Jython <http://jython.org>`_ (JVM) and `IronPython <http://ironpython.net>`_
(.NET). The framework has a rich ecosystem around it consisting of various
generic test libraries and tools that are developed as separate projects.

For more information about Robot Framework and the ecosystem, see
http://robotframework.org. There you can find plenty more documentation,
demo projects, list of available test libraries and other tools, and so on.

Project application
-------------------

.. code:: robotframework

   *** Settings ***
   Library  bo.edu.ucbcba.videoclub.controller.ClientController
   Library  BuiltIn

   *** Variables ***
   ${USERNAME}             janedoe
   ${PASSWORD}             J4n3D0e
   ${NEW PASSWORD}         e0D3n4J
   ${FIRST_NAME TOO LONG}   First Name is too long, must have less than 25 characters
   ${FIRST_NAME TOO SHORT}  First Name is too short, must have more than 2 characters
   ${FIRST_NAME BLANK}      First Name can't be blank
   ${LAST_NAME TOO LONG}    Last Name is too long, must have less than 25 characters
   ${LAST_NAME TOO SHORT}   Last Name is too short, must have more than 2 characters
   ${LAST_NAME BLANK}       Last Name can't be blank
   ${CI TOO LONG}		CI can't have more than 10 characters
   ${CI TOO SHORT}		CI can't have less than 7 characters
   ${CI BLANK}         CI can't be blank
   ${ALREADY CLIENT}		Already exist a Client with CI:
   ${BLANK}

   *** Test Cases ***
   Creating client with blank first name should fail
       Create client with invalid first name   ${BLANK}    ${FIRST_NAME BLANK}

   Creating client with long first name should fail
       Create client with invalid first name   jhonsnowrickrobotclarkkenthor   ${FIRST_NAME TOO LONG}

   Creating client with short first name should fail
       Create client with invalid first name   k   ${FIRST_NAME TOO SHORT}

   Creating client with blank last name should fail
       Create client with invalid last name    ${BLANK}    ${LAST_NAME BLANK}

   Creating client with long last name should fail
       Create client with invalid last name    hawkingsnowrickrobotclarkkenthor    ${LAST_NAME TOO LONG}

   Creating client with short last name should fail
       Create client with invalid last name    D   ${LAST_NAME TOO SHORT}

   Creating client with blank CI should fail
       Create client with invalid CI  ${BLANK}    ${CI_BLANK}

   Creating client with long CI should fail
       Create client with invalid CI  12929388177    ${CI TOO LONG}

   Creating client with short CI should fail
       Create client with invalid CI  123    ${CI TOO SHORT}

   Creating client with valid information
       ${clients} =    Count clients
       Create client  1299456745  juan_d  perez  nowhere
       ${clients_new} =    Count clients
       ${diff} =   Evaluate    $clients_new-$clients
       Should Be Equal As Integers     ${diff}  1

   Creating client already exists should fail
       Create client duplicated  111111114  ${ALREADY CLIENT}

   Delete non existant user
       ${response} =   deleteClient    123
       Should Be Equal As Integers    ${response}     2

   Delete existant user
       Create client  1299456746  juan_d  perez  nowhere
       ${clients} =    Count clients
       ${response} =   deleteClient    1299456746
       Should Be Equal As Integers    ${response}     1
       ${clients_new} =    Count clients
       ${diff} =   Evaluate    $clients_new-$clients
       Should Be Equal As Integers     ${diff}  -1


   *** Keywords ***
   Create client with invalid first name
       [Arguments]    ${firstname}    ${error}
       ${message} =  Run Keyword And Expect Error	*  create  12345678  ${firstname}  hawking  nowhere
       log  ${message}
       Should Be Equal  ${message}  ValidationException: Validation error: ${error}

   Create client with invalid last name
       [Arguments]    ${lastname}    ${error}
       ${message} =  Run Keyword And Expect Error  *  create  12345678  jhon_doe  ${lastname}  nowhere
       log  ${message}
       Should Be Equal  ${message}  ValidationException: Validation error: ${error}

   Create client with invalid CI
       [Arguments]    ${ci}    ${error}
       ${message} =  Run Keyword And Expect Error	*  create  ${ci}  jhon_doe  hawking  nowhere
       log  ${message}
       Should Be Equal  ${message}  ValidationException: Validation error: ${error}

   Create client
       [Arguments]  ${ci}  ${firstname}  ${lastname}  ${address}
       ${message} =  create  ${ci}  ${firstname}  ${lastname}  ${address}
       log  ${message}
       Should Be Equal  ${message}  ${None}

   Create client duplicated
       [Arguments]  ${ci}  ${error}
       deleteClient    ${ci}
       create  ${ci}  jhon_doe  hawking  nowhere
       ${message} =    Run Keyword And Expect Error  *  create  ${ci}  jhon_doe  hawking  nowhere
       log  ${message}
       Should Be Equal  ${message}  ValidationException: Validation error: ${error} '${ci}'

   Count clients
       ${clients} =    searchClient  ${EMPTY}
       ${size} =   Get Length   ${clients}
       [Return]    ${size}