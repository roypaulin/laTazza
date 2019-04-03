# Requirements Document Template

**Authors:**
Elia Migliore @s228279,  
Jean Thibaut Ndjekoua Sandjo @s256770,  
Riccardo Mereu @s265599,  
Roy Paulin Justo Nguetsop Kenfack Djouaka @s257855

**Date:**

**Version:**

# Contents

- [Stakeholders](#stakeholders)
- [Context Diagram and interfaces](#context-diagram-and-interfaces)
	+ [Context Diagram](#context-diagram)
	+ [Interfaces](#interfaces)

- [Stories and personas](#stories-and-personas)

- [Functional and non functional requirements](#functional-and-non-functional-requirements)
	+ [Functional Requirements](#functional-requirements)
	+ [Non functional requirements](#non-functional-requirements)
- [Use case diagram and use cases](#use-case-diagram-and-use-cases)
	+ [Use case diagram](#use-case-diagram)
	+ [Use cases](#use-cases)
	+ [Relevant scenarios](#relevant-scenarios)
- [Glossary](#glossary)
- [System design](#system-design)

# Stakeholders
| Stakeholder name  | Description |
| ----------------- |:-----------:|
|	Employee	| They are the employees of the company and they can use the application LaTazza to buy capsules in order to make coffee|
|	Visitor	    | They may be either clients of the company or friends or family of any employee which can buy a capsule to make coffee|
|	Manager	    | He is also an employee of the company but have been designated by the colleagues to manage the sale and supply of capsules  |
|	Supply Company	| Need an access to the app in order to monitor capsules supply activities to the Manager's company |

**nb:** We assume that the Supply Company offers a limited set of products, the capsules listed in the Informal Description Document, i.e. Coffee, Arabic Cofee, etc. These products can be acquired directly using LaTazza through an API System.

# Context Digram and interfaces

## Context Diagram
\<Define here Context diagram using UML use case diagram>
\<actors are a subset of stakeholders>

```plantuml
left to right direction
skinparam packageStyle rectangle

actor Employee as e
actor "Supply Company" as sc
actor Visitor as v
actor Bank as b
actor Manager as m

rectangle system{
  (LaTazza) as lt
  e--lt 
  lt--v   
  lt--b 
  sc--lt
  m--lt
}
```

## Interfaces
| Actor | Logical Interface | Physical Interface  |
| ------------- |:-------------:| -----:|
| Employee      | GUI to give the possibility to the customer to check availabily of the capsules, view the prices and to manage his account (view remaining credit and charge it, view list of expenses) | Touch screen |
| Vistor |GUI wich may be used to check the availabily of capsules before buying and the price of the products |no need because will be served by the manager|
| Bank|API to interact with the bank in order to perform the debit in case the customer buy by credit card;Date should be sent using a defined format(either json or xml are good choices but xml is more easy to understand and reduces the errors due to the xmlsd that validates the data before the processing) |not needed
| Supply company | Assuming the supply company has it own system to handle supplies,we should provide some API to interact with that system in order to facilitate supply activities. | not needed

# Stories and personas
\<A Persona is a realistic impersonation of an actor. Define here a few personas and describe in plain text how a persona interacts with the system>

\<Persona is-an-instance-of actor>  \<stories will be formalized later as use cases>
### Persona 1  
**Paolo Capaldo**  
53, Manager and CEO of Riesbinaria, father of six children

As a Software Architect, Paolo has the duty of grant coffee to his employees and has to monitor and manage the finance related to the coffee maker.  
Each employee has a different taste on coffee, in Italy coffee is very important and a developer without coffee cannot produce valid and correct code.  
Each year some money related to the capsules go lost and Paolo must pay from his own pocket. This situation is really frustrating, so he wants to find a software that helps him to manage the coffee extending also the use of that machine to the occasional visitor of the agency.




# Functional and non functional requirements

## Functional Requirements
| 	ID		|	Description	|
| ------------- |:-------------|
|	FR1		|	Agent Manager sell capsules to agent employee or to agent visitor |  
|	FR2		|	Agent Manager buy boxes of capsule from supply company |
|	FR3		|	Agent Manager increment or decrement credit of an Employee |
|	FR4		| 	Agent Manager increment or decrement the debt of an Employee |
|	FR5		|	Agent Manager check the inventory (product avaiability and product price) |
|	FR6		| 	Agent Employee or Agent Visitor buy Capsule or Boxes  |
|	FR7		|	Agent Employee get his/her balance |
|	FR8		|	Employee buy credit by cash, credit card or retained on the pay slip |

## Non Functional Requirements


| ID        | Type (efficiency, reliability, ..)           | Description  | Refers to |
| ------------- |:-------------:| :-----:| :-----:|
|  NFR1     | Efficiency | F1 less than 1sec  | FR1 |
|  NFR2     | Reiability | the number of capsule must never be less thant 0 | FR1 |
|  NFR3     | Usability | the action to be done must require less than 3 screen changes  | All |
|  NFR4     | Maintanability | the interaction with the bank for the credit card must me done with REST paradigma  | FR8 |
|  NFR5     | Portability | the code has to be written in java, so it is indipendent from the machine  | All |
|  NFR6     | Efficiency  | F3 in less than 0.5sec  | FR3 |
|  NFR7     | Efficiency | F4 in less than 0.5sec | FR4 |
|  NFR8     | Efficiency | F5 in less than 0.5sec | FR5 |
|  NFR9     | Efficiency | F7 in less than 0.5sec  | FR7 |
|  NFR19    | Efficiency | F8 in less than 10sec (time more hight because the interaction with the bank api| FR8 |
|  NFR11    | Domain | the Currency is Euro € | All |
|  NFR12    | Domain | the credit must be always >= -10€  | All |
|  NFR13    | Reiability | the software must check if at the end of buy the credit is >=-10€ | FR1 |
|  NFR14    | Domain | at the end of month if the credit of a Employee is <0 the money to get a positive credit are taken automatically from the salaty | FR1 |
|  NFR15    | Reiability | if the Employee buy credit throw credit card the system must check that the card is valid  | FR8 |
|  NFR16    | Reiability  | the agent Employee and the agent Manager can't be the same phisical person | FR1 |


# Use case diagram and use cases


## Use case diagram
```plantuml
actor Manager as m
actor Employee as e
actor "supply company" as s
actor visitor as v
actor bank as b


(Sell capsule) as sc
(manage credit and debt) as mcd
(check cash account) as cca
(buy boxes of capsule) as bbc
(check inventory) as ci
(select employee) as se
(choose beverage type) as cbt
(select number of capsule) as snc
(check order) as co
(check balance) as cb
(supply capsules ) as sca
(send deliverer) as sd
(check local account) as cla

m -- sc
m -- bbc
m -- mcd

e <|-- m
sc .> se : include
sc .> cbt : include
sc .> snc : include
bbc .> cca : include
bbc .> ci : include
sc .> ci : include
sc .> cb : include
sca .> co : include
sca .> sd : include


sc -- e
bbc -- s
sc -- v
b -- bbc
b -- mcd
s -- sca
sca -- b
e -- cla
```
## Use Cases
\<describe here each use case in the UCD>

### Use case 1, Sell capsule
| Actors Involved        | manager(employee), employee, visitor |
| ------------- |:-------------:| 
|  Precondition     | at least one capsule is available for the requested type |  
|  Post condition     | the number of capsules for the selected type is updated |
|  Nominal Scenario     | the system shows details about the sell, the manager fill the empty fields with the information given by the customer, he/she clicks Sell button and the system automatically updates all data  |
|  Variants     | an employee type client initially want to pay through the account but after change idea / a visitor want to buy some capsules but notice after he/she does not have cash so the manager must cancel the operation/ the debt threshold is reached so the operation is stopped |

### Use case 2, Buy boxes of capsules
| Actors Involved        | manager(employee), supply company |
| ------------- |:-------------:| 
|  Precondition     | at least one beverage type with less than one remaining box (less than 50 capsules) |  
|  Post condition     | the order is sent to the supplier |
|  Nominal Scenario     | the system shows a summary about the inventory, the manager fills a small form with details about the quantity and the type of beverage to buy and bank data about the cash account , and then clicks on buy to send the order to the supplier database and the bank handles the money transaction |
|  Variants     | there is not enough money to buy so the system cancel the order and the manager has first to go put some money in the bank account   |


### Use case 3, Manage credit and debt
| Actors Involved        | manager, employee,bank |
| ------------- |:-------------:| 
|  Precondition     | employee has an account |  
|  Post condition     | the employee's balance is updated |
|  Nominal Scenario     | the employee wants to buy credits  , the manager takes the cash or the payment is handled by the employee's bank, fills a form with the employee's data, put the amount to add to the balance and when he has finished the system updates the employee's data   |
|  Variants     | the manager chose the wrong employee or the employee does not have enough money  |

### Use case 4, Supply capsules
| Actors Involved        | supply company, bank |
| ------------- |:-------------:| 
|  Precondition     |the company has an account |  
|  Post condition     | the order is done |
|  Nominal Scenario     |the company  has its own LaTazza interface, logs in, checks if capsule boxes have been ordered, send a deliverer to the clients accordingly after receiving the payment notification from the bank   |
|  Variants     |  the supply company sends the wrong order, so it has to cancel the previous delivery, and  rechecks the order |

### Use case 5, Check local account
| Actors Involved        | employee |
| ------------- |:-------------:| 
|  Precondition     |the employee has a local account |  
|  Post condition     | the last login  date is updated |
|  Nominal Scenario     |the employee can have a personal account on LaTazza and can access through an interface, watch the updated details about his credits, his payments history, and the last log in  date   |
|  Variants     |  the supply company sends the wrong order, so it has to cancel the previous delivery, and  rechecks the order |



# Relevant scenarios
## Scenario 1

| Scenario ID: SC1        | Corresponds to UC: Sell capsule |
| ------------- |:-------------:| 
| Step#        | Description  |
|  1     | the customer orders some capsules to the manager |  
|  2     | the manager clicks on "start a sell operation"  |
|  3     | the manager chooses the customer's type |
|  4     | the system shows the inventory, the local  account data(if employee) and a form to fill whose content depends on the customer's type |
|  5     | the manager checks the inventory |
|  6     | the manager selects the employee(if customer==employee) |
|  7     | the manager selects number of capsules|
|  8     | the manager selects beverage type|
|  9     | the manager selects the payment method (only for the employee) |
|  10     | the manager clicks on "sell" |
|  11     | system considers the capsules as sold and updates the remaining number |




## Scenario 2

| Scenario ID: SC2        | Corresponds to UC: Buy boxes of capsules |
| ------------- |:-------------:| 
| Step#        | Description  |
|  1     | the manager clicks on "buy capsules"  |
|  2     | the manager checks the inventory |
|  3     | the system shows the inventory and the cash account |
|  4     | the manager checks the cash account |
|  5     | the manager checks the inventory |
|  6     | the manager puts the number of boxes he wants to buy and the type of beverage |
|  7     | the manager clicks on "buy"|
|  8     | the bank handles the payment to the supply company|
|  9     |the system shows the order's summary |
|  10     | the  order is sent to the supply company |


## Scenario 3

| Scenario ID: SC3        | Corresponds to UC: Supply capsules |
| ------------- |:-------------:| 
| Step#        | Description  |
|  1     | the supply company manager clicks on "show orders"  |
|  2     | the system displays the orders |
|  3     | the manager chooses one |
|  4     | the system shows details about the chosen order |
|  5     | the bank handles the payment |
|  6     | the order  is set as "ongoing" |
|  7     | a deliverer is sent|
|  8    | the order is set as  done|

## Scenario 3

| Scenario ID: SC4        | Corresponds to UC: Manage credit and debt|
| ------------- |:-------------:| 
| Step#        | Description  |
|  1     | the bank handles the payment |
|  2     | the manager clicks on "charge credits" |
|  3     | the manager selects the client |
|  4     | the manager puts the amount |
|  5     | the manager clicks on "charge" |
|  6     | the system shows the charging operation details  |
|  7     | the system updates the employee's balance|



# Glossary

\<use UML class diagram to define important concepts in the domain of the system, and their relationships>  <concepts are used consistently all over the document, ex in use cases, requirements etc>

```plantuml
class Visitor{
}

class Employee{
ID
name
surname
balance
}

class Client{
}


class Manager{
ID
name
surname
}


class Sale{
date
}

class Purchase{
date
}

class Capsule{
price
type
}


class Box {
price
}


class Inventory{
}


Box o-- “50” Capsule
Inventory o-- Capsule

Client <|-- Employee
Client <|-- Visitor

Sale -- Client: buys
Sale -- Capsule
Sale -- Manager: sells

Purchase -- Box
Purchase “*” --  Manager
```

# System Design
\<describe here system design> <must be consistent with Context diagram>
