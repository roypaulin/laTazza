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
|    Employee               | they are the employees of the company and they can use the application LaTazza to buy capsules in other to make coffee           |
| Visitor | they may be either clients of the company or friends or family of any employee which can buy a capsule to make coffee|
|Manager | He is also an employee of the company but have been designated by the colleagues to manage the sale and supply of capsules  |
|Bank | in case the customer buy by credit card, the bank should take the operation in charge|
|supply company | need an access to the app in order to monitor capsules supply activities to  the manager's company|

# Context Diagram and interfaces

## Context Diagram
\<Define here Context diagram using UML use case diagram>
```plantuml
actor employee as e
actor supply_company as sc
actor visitor as v
actor bank as b


rectangle system{
  (LaTazza) as lt
  e-lt :"use"
  lt-v
  lt-b :"take in charge payment"
  sc-lt:"supply"
}
```
\<actors are a subset of stakeholders>

## Interfaces
| Actor | Logical Interface | Physical Interface  |
| ------------- |:-------------:| -----:|
| Employee      | GUI to give the possibility to the customer to check availabily of the capsules and to manage his account | touch screen |
| Vistor |GUI wich may be used to check the availabily of cpsules before buying |no need because will be served by the manager|
| Bank|API to interact with the bank in order to perform the debit in case the customer buy by credit card;Date should be sent using a defined format(either json or xml) |
| Supply company | GUI in order to check the supply requests,archive the accomplishment of a supply and access the list of previous supply in a certains time window |touch sreen to manage the supply requests and access spply history

# Stories and personas
\<A Persona is a realistic impersonation of an actor. Define here a few personas and describe in plain text how a persona interacts with the system>

\<Persona is-an-instance-of actor>  \<stories will be formalized later as use cases>


# Functional and non functional requirements

## Functional Requirements

\<In the form DO SOMETHING, or VERB NOUN, describe high level capabilities of the system> <will match to high level use cases>

| ID        | Description  |
| ------------- |:-------------:|
|  FR1     |  |  
|  FR2     |  |
|  ...     |  |

## Non Functional Requirements

\<Describe constraints on functional requirements>

| ID        | Type (efficiency, reliability, ..)           | Description  | Refers to |
| ------------- |:-------------:| :-----:| -----:|
|  NFR1     |  |  | FR\<x>|
|  NFR2     |  |  | FR\<y>|
|  ...     |  |  | FR\<x>|


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
