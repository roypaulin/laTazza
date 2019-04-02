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

#### Supply Company
We assume that the Supply Company offers a limited set of products, the capsules listed in the Informal Description Document, i.e. Coffee, Arabic Cofee, etc. These products can be acquired directly using LaTazza through an API System.

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


rectangle system{
  (LaTazza) as lt
  e--lt 
  lt--v  
  lt--b 
  sc--lt
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
|	FR8		|	Employee buy credit throw money, credit card or retained on the pay slip |

## Non Functional Requirements

\<Describe constraints on functional requirements>

| ID        | Type (efficiency, reliability, ..)           | Description  | Refers to |
| ------------- |:-------------:| :-----:| -----:|
|  NFR1     | Efficiency | F1 less than 1sec  | FR1 |
|  NFR2     | Reiability |  | FR |
|  NFR3     | Usability |  | FR |
|  NFR4     | Maintanability |  | FR |
|  NFR5     | Portability | the code has to be written in java, so it is
indipendent from the machine  | All |
|  NFR6     | Efficiency  | F3 in less than 1sec  | FR3 |
|  NFR7     | Efficiency | F4 in less than 1sec | FR4 |
|  NFR8     | Efficiency | F5 in less than 1sec | FR5 |
|  NFR9     | Efficiency | F7 in less than 1sec  | FR7 |
|  NFR19    | Efficiency | F8 in less than 10sec (time more hight because the interaction with the bank api| FR8 |
|  NFR11    |  |  | FR\<y>|
|  NFR12    |  |  | FR\<y>|
|  NFR13    |  |  | FR\<y>|
|  NFR14    |  |  | FR\<y>|
|  NFR15    |  |  | FR\<y>|
|  NFR16    |  |  | FR\<y>|
|  NFR17    |  |  | FR\<y>|
|  NFR18    |  |  | FR\<y>|
|  NFR19    |  |  | FR\<y>|
|  NFR20    |  |  | FR\<y>|


# Use case diagram and use cases


## Use case diagram
\<define here UML Use case diagram UCD summarizing all use cases, and their relationships>

## Use Cases
\<describe here each use case in the UCD>

### Use case 1, UC1
| Actors Involved        |  |
| ------------- |:-------------:|
|  Precondition     | \<Boolean expression, must evaluate to true before the UC can start> |  
|  Post condition     | \<Boolean expression, must evaluate to true after UC is finished> |
|  Nominal Scenario     | \<Textual description of actions executed by the UC> |
|  Variants     | \<other executions, ex in case of errors> |

### Use case 2, UC2

### Use case \<n>


# Relevant scenarios
State at which UC the scenario refers to
\<a scenario is a sequence of steps that corresponds to a particular execution of one use case>
\<a scenario is more formal description of a story>
\<only relevant scenarios should be described>

## Scenario 1

| Scenario ID: SC1        | Corresponds to UC:  |
| ------------- |:-------------:|
| Step#        | Description  |
|  1     |  |  
|  2     |  |
|  ...     |  |

## Scenario 2

...

# Glossary

\<use UML class diagram to define important concepts in the domain of the system, and their relationships>  <concepts are used consistently all over the document, ex in use cases, requirements etc>

# System Design
\<describe here system design> <must be consistent with Context diagram>
