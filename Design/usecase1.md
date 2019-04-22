@startuml
skinparam sequenceArrowThickness 2
skinparam roundcorner 20
skinparam maxmessagesize 60
skinparam sequenceParticipant underline
actor Employee as em
actor Administrator as ad
participant "LaTazza" as tazza
participant "DataImpl" as di
participant "Database" as db
participant "Employee" as emp
participant "Beverage" as bev
em -> ad : request buy capsule
ad -> tazza : sellCapsule(id,beverageID,numberofcapsule,contant)
tazza -> di : sellCapsule(id,beverageID,numberofcapsule,contant)
di -> db : getEmployee(id)
db -> di : Employee
di -> db : getBeverage(id)
db -> di : Beverage
di -> emp : updateBalance(-cost)
di -> bev : updateCapsule(-buyed)
di -> db : updateEmployee(employee)
di -> db : updateBeverage(beverage)
di -> db : updateBalance(updatedBalance)
di -> tazza : creditUpdated
tazza -> ad : creditUpdated
ad -> em : creditUpdated
@enduml
