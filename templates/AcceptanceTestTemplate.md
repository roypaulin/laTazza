# Acceptance Testing Documentation template

Authors:

Date:

Version:

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

| Scenario ID: SC2 | Corresponds to UC2                                     |
| ---------------- | ------------------------------------------------------ |
| Description      | Colleague uses one capsule of type T, account negative |
| Precondition     | account of C has not enough money to buy capsule T     |
| Postcondition    | account of C updated, count of T updated               |
| Step#            | Step description                                       |
| 1                | Administrator selects capsule type T                   |
| 2                | Administrator selects colleague C                      |
| 3                | Deduce one for quantity of capsule T                   |
| 4                | Deduce price of T from account of C                    |
| 5                | Account of C is negative, issue warning                |

| Scenario ID: SC3 | Corresponds to UC3 |
| ---------------- | ------------------ |
| Description      | ...                |
| Precondition     | ...                |
| Postcondition    | ...                |
| Step#            | ...                |
| 1                | ...                |
|                  |                    |



# Coverage of Scenarios

```
<Report in the following table the coverage of the scenarios listed above. Report at least an API test (defined on the functions of DataImpl only) and a GUI test (created with EyeAutomate) for each of the scenarios. For each scenario, report the functional requirements it covers.
In the API Tests column, report the name of the method of the API Test JUnit class you created. In the GUI Test column, report the name of the .txt file with the test case you created.>
```

### 

| Scenario ID | Functional Requirements covered | API Test(s) | GUI Test(s) |
| ----------- | ------------------------------- | ----------- | ----------- |
| 1           | FR1                             |             |             |
| 2           | FR1                             |             |             |
| ...         |                                 |             |             |
| ...         |                                 |             |             |
| ...         |                                 |             |             |
| ...         |                                 |             |             |



# Coverage of Non Functional Requirements

```
<Report in the following table the coverage of the Non Functional Requirements of the application - only those that can be tested with automated testing frameworks.>
```

### 

| Non Functional Requirement | Test name |
| -------------------------- | --------- |
|                            |           |

