# Acceptance Testing Documentation template

Authors:
- Elia migliore s267552
- Ndjekoua sandjo jean thibaut s256770
- Roy Paulin Justo Nguetsop Kenfack Djouaka s257855

Date: 26/05/2019

Version: final

# Contents

- [Scenarios](#scenarios)

- [Coverage of scenarios](#scenario-coverage)
- [Coverage of non-functional requirements](#nfr-coverage)



# Scenarios

```
<Define here additional scenarios for the application. The two original scenarios in the Requirements Document are reported here.>
```

| Scenario ID: SC1 | Corresponds to UC1                             |
| ---------------- | ---------------------------------------------- |
| Description      | Colleague uses one capsule of type 1           |
| Precondition     | account of C has enough money to buy capsule T |
| Postcondition    | account of C updated, count of T updated       |
| Step#            | Step description                               |
| 1                | Administrator selects capsule type T           |
| 2                | Administrator selects colleague C              |
| 3                | Deduce one for quantity of capsule T           |
| 4                | Deduce price of T from account of C            |

| Scenario ID: SC2 | Corresponds to UC3 |
| ---------------- | ------------------ |
| Description      |  Colleague recharges his personal account                  |
| Precondition     |   Colleague has an account               |
| Postcondition    |  Colleague account is updated               |
| Step#            | Step description              |
| 1                |  Colleague selects the amount to charge               |
|  2                 |    Colleague clicks on Buy credits               |
|  3            |  the Latazza account is updated    |
|  4             |   system adds credits to the Colleague account   |

| Scenario ID: SC3 | Corresponds to UC4 |
| ---------------- | ------------------ |
| Description      |  Visitor uses one capsule of type T                  |
| Precondition     |   there is at least one capsule available               |
| Postcondition    |  count of T updated              |
| Step#            | Step description              |
| 1                |  Administrator selects capsule type T              |
|  2         |     Administrator clicks on Sell      |
|  3                 |    System  deduces one for quantity of capsule T                |

| Scenario ID: SC4 | Corresponds to UC5 |
| ---------------- | ------------------ |
| Description      |  Administrator buy boxes  with success               |
| Precondition     |   LaTazza account has enough money               |
| Postcondition    |  count of capsule type T updated, Latazza account updated             |
| Step#            | Step description              |
| 1                |            Administrator selects number of boxes   |
|  2         |     Administrator selects capsule type      |
|  3                 |    Administrator clicks on sell              |
|  4          |      System updates Latazza account          |
|  5           |      System updates number of  capsule T              |


| Scenario ID: SC5 | Corresponds to UC5 |
| ---------------- | ------------------ |
| Description      |      Administrator wants to see the consumption report          |
| Precondition     |   there has been at least one transaction             |
| Postcondition    |  consumption report is generated             |
| Step#            | Step description              |
| 1                |            Administrator clicks on log   |
|  2         |     Administrator puts dates range     |
|  3                 |    Administrator clicks on generate report             |
|  4          |      System generates consumption report        |

| Scenario ID: SC6 | Corresponds to UC7 |
| ---------------- | ------------------ |
| Description      |      Administrator wants to see the Colleague report          |
| Precondition     |   there is at least one Colleague with an account             |
| Postcondition    |  employee report is generated             |
| Step#            | Step description              |
| 1                |            Administrator clicks on log   |
|  2          |      Administrator selects the Colleague        |
|  3        |     Administrator puts dates range     |
|  4                 |    Administrator clicks on generate report             |
|  5         |      System generates Colleague report        |

| Scenario ID: SC7 | Corresponds to UC8 |
| ---------------- | ------------------ |
| Description      |      insert a new Colleague          |
| Precondition     |   Colleague doesn't have an account yet             |
| Postcondition    | Colleague account is created            |
| Step#            | Step description              |
| 1                |            Administrator clicks on edit and select Employee   |
|  2         |     Administrator puts the employee name     |
|  3                 |    Administrator puts  the initial balance          |
|  4          |      Administrator clicks on Insert       |
|  5        |      the Employee Account is created|

| Scenario ID: SC8 | Corresponds to UC9 |
| ---------------- | ------------------ |
| Description      |      insert a new beverage          |
| Precondition     |  the beverage is not present yet             |
| Postcondition    | a new beverage is created            |
| Step#            | Step description              |
| 1                |            Administrator clicks on edit and select Beverage  |
|  2         |     Administrator puts the beverage data     |
|  4          |      Administrator clicks on Insert       |
|  5        |      the Beverage is created|



# Coverage of Scenarios

```
<Report in the following table the coverage of the scenarios listed above. Report at least an API test (defined on the functions of DataImpl only) and a GUI test (created with EyeAutomate) for each of the scenarios. For each scenario, report the functional requirements it covers.
In the API Tests column, report the name of the method of the API Test JUnit class you created. In the GUI Test column, report the name of the .txt file with the test case you creatAdministrator select number of boxes ed.>
```

###

| Scenario ID | Functional Requirements covered | API Test(s) | GUI Test(s) |
| ----------- | ------------------------------- | ----------- | ----------- |
| 1           | FR1                             |         testSellCapsules    |    test buy capsule employee credit         |
| 2        |    FR3                             |      testRechargeAccount       |   test recharge account          |
| 3           | FR2                             |     testSellCapsulesToVisitor        |  test buy capsule visitor           |
| 4         |   FR4                              |     testBuyBoxesWithSuccess        |  test buy box           |
| 5         |     FR6                            |       testGetReport      |     test get report        |
| 6         |    FR5                             |      testGetReportEmployee       |  test report employee           |
| 7        |        FR7                         |       testCreateEmployee      |   test add employee          |
| 8        |        FR8                         |      testCreateBeverage       |     test add beverage        |




# Coverage of Non Functional Requirements

```
<Report in the following table the coverage of the Non Functional Requirements of the application - only those that can be tested with automated testing frameworks.>
```

###

| Non Functional Requirement | Test name |
| -------------------------- | --------- |
|      Performance                      |  testBuyBoxes         |
|                         |   testSellCapsules        |
|                           |  testSellCapulesToVisitor         |
|                           |   testGetReport        |
|                           |  testGetReportEmployee         |
|                           |    testGetBalance       |
|                           |     testGetEmployees      |
|                           |    testGetEmployeesId       |
|                           |    testGetEmployeeBalance       |
|                           |    testGetEmployeeSurname       |
|                           |     testGetEmployeeName      |
|                           |      testtUpdateEmployee     |
|                           |      testGetBeverageCapsulesSuccess     |
|                           |      testGetBeverages     |
|                           |       testGetBeveragesId    |
|                           |       testGetBeverageBoxPrice    |
|                           |      testGetBeverageCapsulesPerBox     |
|                           |        testGetBeverageName   |
|                           |         testUpdateBeverage  |









