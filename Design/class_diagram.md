```plantuml
class DataImpl [[java:it.polito.latazza.data.DataImpl]] {
    +Integer sellCapsules(Integer employeeId, Integer beverageId, Integer numberOfCapsules, Boolean fromAccount)
    +void sellCapsulesToVisitor(Integer beverageId, Integer numberOfCapsules)
    +Integer rechargeAccount(Integer id, Integer amountInCents)
    +void buyBoxes(Integer beverageId, Integer boxQuantity)
    +List<String> getEmployeeReport(Integer employeeId, Date startDate, Date endDate)
    +List<String> getReport(Date startDate, Date endDate)
    +Integer createBeverage(String name, Integer capsulesPerBox, Integer boxPrice)
    +void updateBeverage(Integer id, String name, Integer capsulesPerBox, Integer boxPrice)
    +String getBeverageName(Integer id)
    +Integer getBeverageCapsulesPerBox(Integer id)
    +Integer getBeverageBoxPrice(Integer id)
    +List<Integer> getBeveragesId()
    +Map<Integer,String> getBeverages()
    +Integer getBeverageCapsules(Integer id)
    +Integer createEmployee(String name, String surname)
    +void updateEmployee(Integer id, String name, String surname)
    +String getEmployeeName(Integer id)
    +String getEmployeeSurname(Integer id)
    +Integer getEmployeeBalance(Integer id)
    +List<Integer> getEmployeesId()
    +Map<Integer,String> getEmployees()
    +Integer getBalance()
    +void reset()
}
interface DataInterface [[java:it.polito.latazza.data.DataInterface]] {
}
DataInterface <|.. DataImpl
interface DataInterface [[java:it.polito.latazza.data.DataInterface]] {
    Integer sellCapsules(Integer employeeId, Integer beverageId, Integer numberOfCapsules, Boolean fromAccount)
    void sellCapsulesToVisitor(Integer beverageId, Integer numberOfCapsules)
    Integer rechargeAccount(Integer id, Integer amountInCents)
    void buyBoxes(Integer beverageId, Integer boxQuantity)
    List<String> getEmployeeReport(Integer employeeId, Date startDate, Date endDate)
    List<String> getReport(Date startDate, Date endDate)
    Integer createBeverage(String name, Integer capsulesPerBox, Integer boxPrice)
    void updateBeverage(Integer id, String name, Integer capsulesPerBox, Integer boxPrice)
    String getBeverageName(Integer id)
    Integer getBeverageCapsulesPerBox(Integer id)
    Integer getBeverageBoxPrice(Integer id)
    List<Integer> getBeveragesId()
    Map<Integer,String> getBeverages()
    Integer getBeverageCapsules(Integer id)
    Integer createEmployee(String name, String surname)
    void updateEmployee(Integer id, String name, String surname)
    String getEmployeeName(Integer id)
    String getEmployeeSurname(Integer id)
    Integer getEmployeeBalance(Integer id)
    List<Integer> getEmployeesId()
    Map<Integer,String> getEmployees()
    Integer getBalance()
    void reset()
}
class LaTazza [[java:it.polito.latazza.LaTazza]] {
        - float balance
        + void updateBalance(float amount) throws NotEnoughBalance
    +{static}void main(String[] args)
}
class BeverageException [[java:it.polito.latazza.exceptions.BeverageException]] {
    -{static}long serialVersionUID
}
class Exception [[java:com.sun.tools.jdi.JDWP$Event$Composite$Events$Exception]] {
}
Exception <|-- BeverageException
class DateException [[java:it.polito.latazza.exceptions.DateException]] {
    -{static}long serialVersionUID
}
class Exception [[java:com.sun.tools.jdi.JDWP$Event$Composite$Events$Exception]] {
}
Exception <|-- DateException
class EmployeeException [[java:it.polito.latazza.exceptions.EmployeeException]] {
    -{static}long serialVersionUID
}
class Exception [[java:com.sun.tools.jdi.JDWP$Event$Composite$Events$Exception]] {
}
Exception <|-- EmployeeException
class NotEnoughBalance [[java:it.polito.latazza.exceptions.NotEnoughBalance]] {
    -{static}long serialVersionUID
}
class Exception [[java:com.sun.tools.jdi.JDWP$Event$Composite$Events$Exception]] {
}
Exception <|-- NotEnoughBalance
class NotEnoughCapsules [[java:it.polito.latazza.exceptions.NotEnoughCapsules]] {
    -{static}long serialVersionUID
}
class Exception [[java:com.sun.tools.jdi.JDWP$Event$Composite$Events$Exception]] {
}
Exception <|-- NotEnoughCapsules
class Employee {
   - int id
   - String name
   - String surname
   - float credit
   + float getEmployeecredit()
   + int getEmployeeID()
   + Employee(id,name,surname,credit)
   + void updateCredit(float amount) throw EmployeeException /' the amount can be eighter positive (recharge credit) and negative (buy capsules) '/
}
class Beverage {
   - int id
   - int quantityAvaiable
   - float BoxPrice
   + int getNumberOfBoxes()
   + int getQuantityAvaiable()
   - int capsulePerBox
   - String name
   + Beverage(float price,String name,int capsulePerBox)
   + void setPrice(float price) throws BeverageException
   + void setName(String name) throws BeverageException
   + void setCapsulesPerBox(int caps)
   + String getBeverageName()
   + int getBeverageCapsule()/*what is the meaning??*/
   + float getBevaragePrice();
   + float getBeverageBoxPrice()
   + int getQuantityAvaiable()
   + void updateCapsuleQuantity(int quantity) throws BeverageException /' the number can be eighter positive (buy capsules) and negative (sell capsules) '/
}
class Transaction {
   - Date transationDate
   -char type /*can be P=Purchase C=consumption R=Recharge*/
   -int boxQuantity
   -int beverageId
   -int employeeId
   -float amount
   -boolean fromAccount
}

/'class Purchase {
   -int id
   -int boxQuantity
   +int beverageId
}

class Consumption{
   -int id
   -boolean fromAccount
   -int employeeId
   -int BeverageId
   -fromAccount
}

class Recharge {
   -int id
   -float amount
   -int employeeId
}'/
class Database {
   + List<Integer> getListEmployee()
   + Employee getEmployeeData(int id) throws EmployeeException
   + void updateEmployeeCredit(Int employeeId,float amount) 
   + List<Integer> getListOfBeverage()
   + Beverage getBeverageData(int id) throws BeverageException
   + void updateBeverageQauntity(int id,int quanity) /' save in the db the beverage updated '/
   + float getBalance()
   + void updateBalance(float balance)
   + List<Transaction> getEmployeeReport(int idEmployee,Date startDate,Date endDate)
   + List<Transaction> getReport(Date startDate,Date endDate)
   + void registerTransaction(Transaction transaction)
   + void addBeverage(Beverage bev) throw BeverageException
   + void addEmployee(Beverage bev) throw EmployeeException
   + void truncateTables()
}
LaTazza -- DataImpl
Exception -- DataImpl
DataImpl -- Database
```